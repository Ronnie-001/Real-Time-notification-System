from fastapi import FastAPI
from app.routes.scrapingRouter import scrapingRouter

app = FastAPI()

# include the different routers.
app.include_router(scrapingRouter)
