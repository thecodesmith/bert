import sys
from SocketServer import TCPServer
from SocketServer import BaseRequestHandler

from motor_driver import motors, MAX_SPEED


class MyHandler(BaseRequestHandler):
    def handle(self):
        print 'Received request'
        BUFSIZE = 128
        command = self.request.recv(BUFSIZE).strip()
        response = self.dispatch(command)
        self.request.send(response + '\n')
        print 'Sent response'

    def dispatch(self, command):
        if 'MAX_SPEED' == command:
            return str(MAX_SPEED)
        else:
            try:
                left, right = command.split(',')
                motors.setSpeeds(left, right)
                return 'Success'
            except:
                return 'Failure'

port = int(sys.argv[1]) if len(sys.argv) > 1 else 4444
print 'Serving on port', port
server = TCPServer(('localhost', port), MyHandler)
server.serve_forever()
