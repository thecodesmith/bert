import logging
import sys
from SocketServer import TCPServer
from SocketServer import BaseRequestHandler

from motor_driver import motors, MAX_SPEED

logging.basicConfig(
    level=logging.DEBUG,
    format='%(asctime)s %(levelname)-8s %(message)s',
    datefmt='%H:%M:%S',
)


class MyHandler(BaseRequestHandler):
    def handle(self):
        logging.info('Received request')
        BUFSIZE = 128
        command = self.request.recv(BUFSIZE).strip()
        response = self.dispatch(command)
        self.request.send(response + '\n')
        logging.info('Sent response')

    def dispatch(self, command):
        logging.info('Dispatching command: %s' % command)
        if 'MAX_SPEED' == command:
            return str(MAX_SPEED)
        else:
            try:
                left, right = command.split(',')
                motors.setSpeeds(left, right)
                logging.info('Set speeds - Left: %s, Right: %s' % (left, right))
                return 'Success'
            except Exception as e:
                logging.error('Failure: %s' % e)
                return 'Failure: %s' % e

def run_server():
    try:
        port = int(sys.argv[1]) if len(sys.argv) > 1 else 4444

        logging.info('Serving on port %s' % port)
        TCPServer.allow_reuse_address = True
        server = TCPServer(('localhost', port), MyHandler)

        server.serve_forever()
    finally:
        server.server_close()

run_server()
