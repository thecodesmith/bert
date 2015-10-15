import time

from motor_driver import motors, MAX_SPEED

_max = MAX_SPEED / 4

def go(params):
    speed = params.get('speed', _max)
    if params['direction'] == 'forward':
        motors.setSpeeds(speed, speed)
    elif params['direction'] == 'backward':
        motors.setSpeeds(-speed, -speed)
    _sleep(params['time'])
    _stop()

def turn(params):
    speed = params.get('speed', _max)
    if params['direction'] == 'left':
        _right(speed)
        _left(-speed)
    elif params['direction'] == 'right':
        _right(-speed)
        _left(speed)
    _sleep(params['time'])
    _stop()

def arc(params):
    pass

def stop(params):
    pass

def slow(params):
    pass

def speed(params):
    pass

def _right(speed):
    motors.right.setSpeed(speed)

def _left(speed):
    motors.left.setSpeed(speed)

def _stop():
    motors.stop()

def _sleep(duration):
    time.sleep(duration)
