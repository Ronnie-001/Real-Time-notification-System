from bs4 import BeautifulSoup
from passlib.context import CryptContext

soup = BeautifulSoup()

# BCrypt used to encrypt data
myctx = CryptContext(["bcrypt"])

# function used to encrypt the users password before storing it in the database
async def encryptPassword(password: str) -> str:
    return myctx.hash(password)

def verifyPassword(passwordHash: str, plainPassword: str) -> bool:
    return myctx.verify(passwordHash, plainPassword)
