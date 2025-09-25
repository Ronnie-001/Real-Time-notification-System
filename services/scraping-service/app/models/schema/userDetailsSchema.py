from pydantic import BaseModel

class LoginDetailsModel(BaseModel):
    email: str   
    password: str

    class Config:
        from_attributes = True
