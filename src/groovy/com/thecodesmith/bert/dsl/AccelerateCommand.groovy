package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class AccelerateCommand extends Command {
    Direction direction
    Speed speed = Robot.DEFAULT_SPEED
    Duration time = new Duration(0, TimeUnit.second)

    static AccelerateCommand slow(Direction direction) {
        new AccelerateCommand([action: Action.slow, direction: direction])
    }

    static AccelerateCommand speed(Direction direction) {
        new AccelerateCommand([action: Action.speed, direction: direction])
    }

    AccelerateCommand to(Speed speed) {
        this.speed = speed
        this
    }

    AccelerateCommand over(Duration time) {
        this.time = time
        this
    }

    String toString() {
        super.toString() + " $direction to $speed over $time"
    }
}
