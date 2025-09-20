from fastapi import FastAPI

from app.routes import DetailsRouter

app = FastAPI()

# include the different routers.
app.include_router(DetailsRouter.detailsRouter)

# test endpoint
@app.get("/scraping-service/v1/test")
async def root():
    return {"test": "Working"}
