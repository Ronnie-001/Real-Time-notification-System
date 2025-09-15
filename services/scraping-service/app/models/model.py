from sqlalchemy import Column, Date, Integer, String
from sqlalchemy.orm import DeclarativeBase

class Base(DeclarativeBase):
    pass

class Data(Base):
    __tablename__ = "monitored_users"
    
    id = Column(Integer, primary_key=True, nullable=False, autoincrement=True)
    user_id = Column(Integer, unique=True, nullable=False)
    email = Column(String(100), unique=True, nullable=False)
    password = Column(String, unique=False, nullable=False) 
    base_timetable = Column(String)   
    latest_timetable = Column(String)
    created_at = Column(Date)
    updated_at = Column(Date)
