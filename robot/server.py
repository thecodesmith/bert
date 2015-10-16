from flask import (Flask, request, session, redirect, url_for, jsonify,
        render_template)
from motor_driver import MAX_SPEED

import actions
from auth import authenticated, valid_login

app = Flask(__name__)
app.config.from_object('settings')

@app.route('/')
def index():
    if authenticated():
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

@app.route('/config', methods=['GET'])
def configuration():
    config = {'MAX_SPEED': MAX_SPEED}
    return jsonify(**config)

@app.route('/dashboard')
def dashboard():
    return render_template('dashboard.html')

@app.route('/command', methods=['GET', 'POST'])
def command():
    if request.method == 'POST':
        run_command(request.form)
        return redirect(url_for('dashboard'))

def run_command(data):
    command_data = parse_command(data)
    return execute(command_data)

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
