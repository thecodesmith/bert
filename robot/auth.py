from functools import wraps

from flask import request, Response

import settings

def valid_login(username, password):
    return (username == settings.USERNAME and
            password == settings.PASSWORD)

def authenticated():
    return session.get('authenticated', False)

def authenticate(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        authenticated = session.get('authenticated', False)
        if not authenticated:
            return Response('Please Log In', 401, {'WWW-Authenticate': 'Basic realm="Login Required"'})
        return f(*args, **kwargs)
    return decorated
