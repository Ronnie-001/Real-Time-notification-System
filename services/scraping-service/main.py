from fastapi import FastAPI
from app.routes.scrapingRouter import scrapingRouter
from app.database.dbconn import DATABASE_URL

app = FastAPI()

# test endpoint
@app.get("/scraping-service/v1/test")
async def root() -> str:
    return "Working!"

print(DATABASE_URL)

# include the different routers.
app.include_router(scrapingRouter)
