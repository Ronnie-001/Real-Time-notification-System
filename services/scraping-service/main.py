from fastapi import FastAPI

from app.routes.scrapingRouter import routerScrape

app = FastAPI()

# include the different routers.
app.include_router(routerScrape)

# test endpoint
@app.get("/scraping-service/v1/test")
async def root():
    return {"test": "Working!"}
