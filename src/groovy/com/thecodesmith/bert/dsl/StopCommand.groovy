package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class StopCommand extends Command {
    Duration time = new Duration(0, TimeUnit.second)

    static StopCommand stop(Map args) {
        StopCommand cmd = new StopCommand([action: Action.stop])
        cmd.time = args?.over ?: cmd.time
        cmd
    }

    StopCommand over(Duration time) {
        this.time = time
        this
    }

    String toString() {
        super.toString() + (time.amount ? " over $time" : "")
    }
}
