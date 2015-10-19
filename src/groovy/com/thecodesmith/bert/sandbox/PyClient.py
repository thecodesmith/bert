# Echo client program
import socket
import sys

port = int(sys.argv[1]) if len(sys.argv) > 1 else 4444
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('localhost', port))
print 'Sending'
s.send('MAX_SPEED\n')
print 'Received', s.recv(1024)
s.close()
