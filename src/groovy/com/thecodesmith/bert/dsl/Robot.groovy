package com.thecodesmith.bert.dsl

abstract class Robot extends Script {

    static s = TimeUnit.second
    static DEFAULT_SPEED = new Speed(new Distance(2, DistanceUnit.foot), TimeUnit.second)
    static DEFAULT_ARC = new Rotation(30, RotationUnit.degree)

    abstract void scriptBody()

    def run() {
        addNumberUnits()
        scriptBody()
    }

    void addNumberUnits() {
        Number.metaClass.getSecond  = { return new Duration(delegate, TimeUnit.second) }
        Number.metaClass.getSeconds = { return new Duration(delegate, TimeUnit.second) }
        Number.metaClass.getFt      = { return new Distance(delegate, DistanceUnit.foot) }
        Number.metaClass.getDeg     = { return new Rotation(delegate, RotationUnit.degree) }
        Number.metaClass.getDegrees = { return new Rotation(delegate, RotationUnit.degree) }
    }

    static GoCommand go(Direction direction, Duration time) {
        new GoCommand([action: Action.go, direction: direction, time: time])
    }

    static TurnCommand turn(Direction direction, Duration time) {
        new TurnCommand([action: Action.turn, direction: direction, time: time])
    }

    static ArcCommand arc(Direction direction, Duration time) {
        new ArcCommand([action: Action.arc, direction: direction, time: time])
    }

    static AccelerateCommand slow(Direction direction) {
        new AccelerateCommand([action: Action.slow, direction: direction])
    }

    static AccelerateCommand speed(Direction direction) {
        new AccelerateCommand([action: Action.speed, direction: direction])
    }

    static StopCommand stop(Map args) {
        StopCommand cmd = new StopCommand([action: Action.stop])
        cmd.time = args?.over ?: cmd.time
        cmd
    }

    static StopCommand getStop() { stop() }
}
