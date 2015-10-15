from flask import (Flask, request, session, redirect, url_for, jsonify, render_template)
from motor_driver import MAX_SPEED

import actions

app = Flask(__name__)

app.config.update(dict(
    SECRET_KEY='dev key',
    USERNAME='bert',
    PASSWORD='test',
))

@app.route('/')
def index():
    if session.get('authenticated', False):
        return redirect(url_for('dashboard'))
    else:
        return redirect(url_for('login'))

@app.route('/login', methods=['GET', 'POST'])
def login():
    error = None
    if request.method == 'POST':
        if valid_login(request.form['username'], request.form['password']):
            session['authenticated'] = True
            return redirect(url_for('dashboard'))
        else:
            error = 'Invalid username or password'
    return render_template('login.html', error=error)

@app.route('/dashboard')
def dashboard():
    return render_template('dashboard.html')

@app.route('/authenticate', methods=['GET', 'POST'])
def authenticate():
    if request.method == 'POST':
        if valid_login(request.form['username'], request.form['password']):
            session['authenticated'] = True
            message = {'status': 'success', 'message': 'Success'}
        else:
            message = {
                'status': 'failure',
                'message': 'Invalid username or password',
            }
        return jsonify(**message)

@app.route('/config', methods=['GET'])
def configuration():
    config = {'MAX_SPEED': MAX_SPEED}
    return jsonify(**config)

@app.route('/command', methods=['GET', 'POST'])
def command():
    if request.method == 'POST':
        command_data = parse_command(request.form)
        execute(command_data)
        message = {'status': 'queued', 'message': 'Command executed'}
        return jsonify(**message)

def valid_login(username, password):
    if username == app.config['USERNAME'] and password == app.config['PASSWORD']:
        return True
    else:
        return False

def parse_command(data):
    return {
        'action': data['action'],
        'direction': data['direction'],
        'time': float(data['time']),
    }

def execute(command):
    function = getattr(actions, command['action'])
    return function(command)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8001)
