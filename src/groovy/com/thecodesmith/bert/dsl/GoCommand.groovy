package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class GoCommand extends Command {
    Direction direction
    Speed speed = Robot.DEFAULT_SPEED

    static GoCommand go(Direction direction, Duration time) {
        new GoCommand([action: Action.go, direction: direction, time: time])
    }

    Command at(Speed speed) {
        this.speed = speed
        this
    }

    String toString() {
        super.toString() + " $direction for $time at $speed"
    }
}
