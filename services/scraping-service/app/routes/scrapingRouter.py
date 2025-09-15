from fastapi import APIRouter

scrapingRouter = APIRouter()

"""
Send a POST request to grab the users login, so that their specific 
timetable can be webscraped from KentVision.
"""

@scrapingRouter.get("scraping-service/v1/get-login-details")
async def grabUserLoginDetails() -> str:
    return "Working!"
    
