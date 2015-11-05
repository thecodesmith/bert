package com.thecodesmith.bert.dsl

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.*
import groovy.servlet.*

def startServer() {
    def port = 8080
    def server = new Server(port)

    def handler = new ServletContextHandler(ServletContextHandler.SESSIONS)
    handler.contextPath = '/'
    handler.resourceBase = '.'
    handler.addServlet(GroovyServlet, '/commands/*')

    def filesHolder = handler.addServlet(DefaultServlet, '/')
    filesHolder.setInitParameter('resourceBase', './public')

    server.handler = handler
    server.start()

    println "Server running at port $port, press Ctrl+C to stop"
}

println 'Starting server ...'
startServer()
