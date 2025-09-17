from app.database.dbconn import Session

# create a single session / database connection for each request
async def getDb():
    async with Session() as session:
        yield session

