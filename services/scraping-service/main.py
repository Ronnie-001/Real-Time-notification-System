from fastapi import FastAPI

from app.routes.scrapingRouter import scrapingRouter
from app.utils import middlewareRouter

app = FastAPI()

# test endpoint
@app.get("/scraping-service/v1/test")
async def root() -> str:
    return "Working!"


# include the different routers.
app.include_router(scrapingRouter)
app.include_router(middlewareRouter)
