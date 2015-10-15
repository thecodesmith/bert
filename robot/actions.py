import time

from motor_driver import motors, MAX_SPEED

def go(params):
    pass

def turn(params):
    if params.direction == 'left':
        motors.right.setSpeed(MAX_SPEED)
        motors.left.setSpeed(-MAX_SPEED)
    elif params.direction == 'right':
        motors.right.setSpeed(-MAX_SPEED)
        motors.left.setSpeed(MAX_SPEED)
    time.sleep(params.time)

def arc(params):
    pass

def stop(params):
    pass

def slow(params):
    pass

def speed(params):
    pass
