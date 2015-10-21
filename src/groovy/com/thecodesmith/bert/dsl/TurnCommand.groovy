package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class TurnCommand extends Command {
    Direction direction
    RotationalSpeed speed

    static TurnCommand turn(Direction direction, Duration time) {
        new TurnCommand([action: Action.turn, direction: direction, time: time])
    }

    TurnCommand at(RotationalSpeed speed) {
        this.speed = speed
        this
    }

    String toString() {
        super.toString() + " $direction for $time at $speed"
    }
}
