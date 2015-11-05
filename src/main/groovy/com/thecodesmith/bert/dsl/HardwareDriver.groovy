package com.thecodesmith.bert.dsl

class HardwareDriver {

    static DEFAULT_PORT = 4444

    static int getMaxSpeed() {
        return issueCommand('MAX_SPEED') as int
    }

    static String setSpeeds(int left, int right) {
        return issueCommand("${left},${right}")
    }

    static String issueCommand(String command) {
        Socket socket

        try {
            socket = new Socket('localhost', DEFAULT_PORT)

            socket.withStreams { input, output ->
                output << command + '\n'
                return input.newReader().readLine()
            }

        } catch (e) {
            return 'Failed: ' + e
        } finally {
            socket?.close()
        }
    }
}
