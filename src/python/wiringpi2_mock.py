# This is a mock version of the wiringpi2 API for
# testing on platforms other than the Raspberry Pi

class GPIO():
    PWM_OUTPUT = 0
    PWM_MODE_MS = 1
    OUTPUT = 2

def digitalWrite(pin, value):
    return 'digitalWrite %s, %s' % (pin, value)

def pwmWrite(pin, value):
    return 'pwmWrite %s, %s' % (pin, value)

def wiringPiSetupGpio():
    pass

def pinMode(pin, mode):
    pass

def pwmSetMode(mode):
    pass

def pwmSetRange(speed):
    pass

def pwmSetClock(time):
    pass
