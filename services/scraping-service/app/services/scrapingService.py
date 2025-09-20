from bs4 import BeautifulSoup
import bcrypt

soup = BeautifulSoup()

# function used to encrypt the users password before storing it in the database.
async def encryptPassword(password: str) -> bytes:
    pwd_bytes = password.encode('utf-8')
    salt = bcrypt.gensalt()
    hashed_password = bcrypt.hashpw(password=pwd_bytes, salt=salt)
    return hashed_password

# function used to check if the provided password matches the hashed password.
def verifyPassword(passwordHash, plainPassword) -> bool:
    password_byte_enc = plainPassword.encode('utf-8')
    return bcrypt.checkpw(password= password_byte_enc, hashed_password = passwordHash)
