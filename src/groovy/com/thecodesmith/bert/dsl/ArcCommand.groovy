package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class ArcCommand extends Command {
    Direction direction
    Speed speed = Robot.DEFAULT_SPEED
    Rotation angle = Robot.DEFAULT_ARC

    static ArcCommand arc(Direction direction, Duration time) {
        new ArcCommand([action: Action.arc, direction: direction, time: time])
    }

    ArcCommand at(Rotation angle) {
        this.angle = angle
        this
    }

    ArcCommand at(Speed speed) {
        this.speed = speed
        this
    }

    String toString() {
        super.toString() + " $direction for $time at $angle at $speed"
    }
}
