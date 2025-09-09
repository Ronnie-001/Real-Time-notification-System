from fastapi import FastAPI, Request

"""
middleware router used for recieving incoming requests containing the
JWT, will need to be parsed for the users ID.
"""
middleware = FastAPI()


# Get the users id from the JWT token 
def parseJWTforUserId():
    pass
