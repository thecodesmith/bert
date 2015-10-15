from flask import Flask, request, session, redirect, url_for, jsonify
from motor_driver import MAX_SPEED

import actions

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
    config = {'MAX_SPEED': MAX_SPEED}
    return jsonify(**config)

@app.route('/command', methods=['GET', 'POST'])
def command():
    if request.method == 'POST':
        command_data = _parse_command(request.form)
        _execute(command_data)
        message = {'status': 'queued', 'message': 'Command executed'}
        return jsonify(**message)

def _parse_command(data):
    return {
        'action':    data['action'],
        'direction': data['direction'],
        'time':      float(data['time']),
    }

def _execute(command):
    function = getattr(actions, command['action'])
    return function(command)

if __name__ == "__main__":
    app.run()
