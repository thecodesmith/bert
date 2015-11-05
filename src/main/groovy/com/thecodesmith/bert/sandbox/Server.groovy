package com.thecodesmith.bert

import java.net.ServerSocket

class Server {

    ServerSocket serverSocket

    Server(port) {
        serverSocket = new ServerSocket(port)
    }

    def start() {
        while(true) {
            serverSocket.accept { socket ->
                socket.withStreams { input, output ->
                    def reader = input.newReader()
                    def received = reader.readLine()
                    println "Received: ${received}"

                    output.withWriter { out ->
                        println 'Sending response'
                        out << 'Hello, world! from groovy server'
                    }
                }
            }
        }
    }
}
