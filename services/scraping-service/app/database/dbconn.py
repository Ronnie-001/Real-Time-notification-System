import os
from sqlalchemy.ext.asyncio import async_sessionmaker, create_async_engine
from sqlalchemy.orm import declarative_base

# get the database url from the .env file
DATABASE_URL = os.getenv("DATABASE_URL")

# Check that the DB connection string has been set as an evironment variable
if DATABASE_URL is None:
    raise ValueError(
        "DATABASE_URL is not set."
        "Please check that the .env file exists at the correct path "
        "and contains the DATABASE_URL variable."
    )

engine = create_async_engine(DATABASE_URL, echo=True)
Session = async_sessionmaker(engine, expire_on_commit=False)
Base = declarative_base()
