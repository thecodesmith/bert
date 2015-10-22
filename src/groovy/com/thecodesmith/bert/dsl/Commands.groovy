package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class Command {
    String action
    Duration time

    String toString() { "Command: $action" }
}

@TupleConstructor
class GoCommand extends Command {
    Direction direction
    Speed speed = Robot.DEFAULT_SPEED

    Command at(Speed speed) {
        this.speed = speed
        this
    }

    String toString() {
        super.toString() + " $direction for $time at $speed"
    }
}

@TupleConstructor
class TurnCommand extends Command {
    Direction direction
    RotationalSpeed speed

    TurnCommand at(RotationalSpeed speed) {
        this.speed = speed
        this
    }

    String toString() {
        super.toString() + " $direction for $time at $speed"
    }
}

@TupleConstructor
class ArcCommand extends Command {
    Direction direction
    Speed speed = Robot.DEFAULT_SPEED
    Rotation angle = Robot.DEFAULT_ARC

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

@TupleConstructor
class StopCommand extends Command {
    Duration time = new Duration(0, TimeUnit.second)

    StopCommand over(Duration time) {
        this.time = time
        this
    }

    String toString() {
        super.toString() + (time.amount ? " over $time" : "")
    }
}

@TupleConstructor
class AccelerateCommand extends Command {
    Direction direction
    Speed speed = Robot.DEFAULT_SPEED
    Duration time = new Duration(0, TimeUnit.second)

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
