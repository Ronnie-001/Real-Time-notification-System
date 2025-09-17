from fastapi import APIRouter, Depends
from fastapi.exceptions import HTTPException
from fastapi.security import OAuth2PasswordBearer

from sqlalchemy.ext.asyncio import AsyncSession

from app.models import data
from app.services.scrapingService import encryptPassword
from app.services.userDetailsSchema import LoginDetailsModel
from app.dependencies import getDb

import jwt
import os 
from dotenv import load_dotenv
from pathlib import Path

scrapingRouter = APIRouter()

# Check authorisation headers for the bearer tokens
oauth2_scheme = OAuth2PasswordBearer(tokenUrl="token")

# Load the secret key from the .env file
env_path = Path(__file__).parent / '.env'
load_dotenv(dotenv_path=env_path)

SECRET_KEY = os.getenv("SECRET_KEY")
ALGORITHM = "HS512" 

"""
Send a POST request to grab the users login, so that their specific 
timetable can be webscraped from KentVision.
"""
@scrapingRouter.get("scraping-service/v1/get-login-details")
async def grabUserLoginDetails(details: LoginDetailsModel, 
                               token: str = Depends(oauth2_scheme), 
                               db: AsyncSession = Depends(getDb)) -> str:

    jwt_exception = HTTPException(
        status_code=401,
        detail="Could not extract the JWT token from authorisation headers",
        headers={"WWW-Authenticate" : "Bearer"},
    )
    
    # decode the JWT token and retrieve the user's ID.
    try:
        payload = jwt.decode(token, SECRET_KEY, ALGORITHM) 
        users_id = payload.get("ID")
        if users_id is None:
            raise jwt_exception
    except jwt.InvalidTokenError:
        raise jwt_exception
    
    # add a new user into the database
    user_details = data.Data (
        user_id = users_id,
        email = details.email,
        password = await encryptPassword(details.password),
    )
    
    # TODO: Add the user into the database

    return "Login details added to the database!" 
