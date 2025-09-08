from fastapi import FastAPI
from app.routes.scrapingRouter import scrapingRouter

service = FastAPI()

# include the different routers.
service.include_router(scrapingRouter)
