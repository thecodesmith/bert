package com.thecodesmith.bert.dsl

abstract class Robot extends Script {

    static s = TimeUnit.second
    static DEFAULT_SPEED = new Speed(new Distance(2, DistanceUnit.foot), TimeUnit.second)
    static DEFAULT_ARC = new Rotation(30, RotationUnit.degree)
    static MAX_SPEED = new Speed(new Distance(4.8, DistanceUnit.foot), TimeUnit.second)

    static commands = []

    abstract void scriptBody()

    def run() {
        addNumberUnits()
        scriptBody()
        runCommands()
    }

    void addNumberUnits() {
        Number.metaClass.getSecond  = { return new Duration(delegate, TimeUnit.second) }
        Number.metaClass.getSeconds = { return new Duration(delegate, TimeUnit.second) }
        Number.metaClass.getFt      = { return new Distance(delegate, DistanceUnit.foot) }
        Number.metaClass.getDeg     = { return new Rotation(delegate, RotationUnit.degree) }
        Number.metaClass.getDegrees = { return new Rotation(delegate, RotationUnit.degree) }
    }

    static GoCommand go(Direction direction, Duration time) {
        queueUp new GoCommand([action: Action.go, direction: direction, time: time])
    }

    static TurnCommand turn(Direction direction, Duration time) {
        queueUp new TurnCommand([action: Action.turn, direction: direction, time: time])
    }

    static ArcCommand arc(Direction direction, Duration time) {
        queueUp new ArcCommand([action: Action.arc, direction: direction, time: time])
    }

    static AccelerateCommand slow(Direction direction) {
        queueUp new AccelerateCommand([action: Action.slow, direction: direction])
    }

    static AccelerateCommand speed(Direction direction) {
        queueUp new AccelerateCommand([action: Action.speed, direction: direction])
    }

    static StopCommand stop(Map args) {
        StopCommand command = new StopCommand([action: Action.stop])
        command.time = args?.over ?: command.time
        queueUp command
    }

    static StopCommand getStop() { stop() }

    static printCommandQueue() {
        commands.each { println it }
    }

    static runCommands() {
        println '========================'
        println '=== Running Commands ==='
        println '========================'
        commands.each { command ->
            println command
            command.execute()
            Thread.sleep((int)(command.time.total * 1000))
        }
    }

    private static queueUp(command) {
        commands.push(command)
        command
    }
}
