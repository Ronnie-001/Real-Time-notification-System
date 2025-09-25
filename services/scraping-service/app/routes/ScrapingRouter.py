from typing import Annotated
from fastapi import APIRouter, HTTPException, Header

from selenium import webdriver
from selenium.webdriver.common.by import By
from sqlalchemy import select

from app.services.userDetailsService import getIdFromJwt
from app.models.table.data import Data

scrapingRouter = APIRouter()

# Navigate to the timeable where the user's timetable is.
@scrapingRouter.post("/scraping-service/v1/webscrape-timetable")
def navigateToTimetable(Authorization: Annotated[str | None, Header()] = None):

    # throw an exception if no authorisation headers are found
    jwt_exception = HTTPException(
        status_code=401,
        detail="Could not extract the JWT token from 'Authorization' header.",
        headers={"WWW-Authenticate" : "Bearer"},
    )
    
    if Authorization is None:
        raise jwt_exception

    kentVisionWebsite = "https://evision.kent.ac.uk/urd/sits.urd/run/siw_lgn" 

    # Initalise the webdriver for Chrome.
    driver = webdriver.Chrome()

    # Navigate to KentVision's Application Portal.
    driver.get(kentVisionWebsite) 
    studentApplicationPortalButton = driver.find_element(By.ID, "kent-student-login-button")
    studentApplicationPortalButton.click()
    
    # Grab the students email from the database, to login to KentVision.
    users_id = getIdFromJwt(Authorization.split(" "))
    stmt = select(Data).where(Data.user_id == users_id)

