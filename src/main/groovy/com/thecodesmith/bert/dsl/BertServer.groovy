package com.thecodesmith.bert.dsl

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.*
import groovy.servlet.*

class BertServer {

    static String basePath = System.getProperty('user.dir') + '/src/main/groovy/com/thecodesmith/bert/dsl'

    static void main(args) {
        startServer()
    }

    static startServer() {
        println 'Starting server ...'

        def port = 8080
        def server = new Server(port)

        def handler = new ServletContextHandler(ServletContextHandler.SESSIONS)
        handler.contextPath = '/'
        handler.resourceBase = '.'
        handler.addServlet(GroovyServlet, basePath + '/commands/*')

        def filesHolder = handler.addServlet(DefaultServlet, '/')
        filesHolder.setInitParameter('resourceBase', basePath + '/public')

        server.handler = handler
        server.start()

        println "Server running at port $port, press Ctrl+C to stop"
    }
}
