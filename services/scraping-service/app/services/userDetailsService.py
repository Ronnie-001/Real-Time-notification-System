import bcrypt
import jwt

# function used to encrypt the users password before storing it in the database.
async def encryptPassword(password: str) -> bytes:
    pwd_bytes = password.encode('utf-8')
    salt = bcrypt.gensalt()
    hashed_password = bcrypt.hashpw(password=pwd_bytes, salt=salt)
    return hashed_password

# function used to check if the provided password matches the hashed password.
async def verifyPassword(passwordHash, plainPassword) -> bool:
    password_byte_enc = plainPassword.encode('utf-8')
    return bcrypt.checkpw(password= password_byte_enc, hashed_password = passwordHash)

# TODO: Implement the parsing of the JWT in a separate functions.
async def getEmailFromJwt(authHeader: str) -> str:

    return ""

async def getIdFromJwt(authHeader) -> int:
    token = authHeader[1]    
    decodedToken = jwt.decode(token, options={"verify_signature": False}) 
    users_id = decodedToken["ID"]
    return users_id
