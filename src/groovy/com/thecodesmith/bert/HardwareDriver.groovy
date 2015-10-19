class HardwareDriver {

    static DEFAULT_PORT = 4444

    int getMaxSpeed() {
        return issueCommand('MAX_SPEED') as int
    }

    String setSpeeds(left, right) {
        return issueCommand("${left},${right}")
    }

    String issueCommand(String command) {
        try {
            Socket socket = new Socket('localhost', DEFAULT_PORT)

            socket.withStreams { input, output ->
                output << command + '\n'
                return input.newReader().readLine()
            }
        } catch (e) {
            return 'Failed'
        }
    }
}
