s = new Socket('localhost', 4444)

s.withStreams { inStream, outStream ->
    outStream << "MAX_SPEED\n"
    def buffer = inStream.newReader().readLine()
    println "Received: ${buffer}"
}

s.close()
