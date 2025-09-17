from pydantic import BaseModel

class LoginDetailsModel(BaseModel):
    username: str   
    password: str

    class Config:
        from_attributes = True
