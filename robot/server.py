from flask import Flask, request, session, redirect, url_for, jsonify
from motor_driver import motors

app = Flask(__name__)

app.config.update(dict(
    SECRET_KEY='dev key',
    USERNAME='bert',
    PASSWORD='test',
))

@app.route("/")
def hello():
    return "Hello, World!"

@app.route('/authenticate', methods=['GET', 'POST'])
def authenticate():
    if request.method == 'POST':
        if (request.form['username'] != app.config['USERNAME'] or
            request.form['password'] != app.config['PASSWORD']):
            message = {
                'status': 'failure',
                'message': 'Invalid username or password',
            }
        else:
            session['authenticated'] = True
            message = {'status': 'success', 'message': 'Success'}
        return jsonify(**message)

@app.route('/config', methods=['GET'])
def configuration():
    config = {'MAX_SPEED': 480}
    return jsonify(**config)

if __name__ == "__main__":
    app.run()
