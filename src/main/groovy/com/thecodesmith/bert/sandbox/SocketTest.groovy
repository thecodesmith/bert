@Grapes(
    @Grab(group='org.spockframework', module='spock-core', version='1.0-groovy-2.4')
)

package com.thecodesmith.bert

import spock.lang.*

class SocketTest extends Specification {

    def 'can receive data via socket from python'() {

        given:
        def server = new Server(4444)
        server.start()

        when:
        def p = 'python PyClient.py'.execute()
        p.waitFor()

        then:
        p.in.text.contains 'Received: Hello'
    }
}
