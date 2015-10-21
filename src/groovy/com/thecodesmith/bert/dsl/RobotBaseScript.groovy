package com.thecodesmith.bert.dsl

abstract class RobotBaseScript extends Script {

    TimeUnit s = TimeUnit.second

    def go = GoCommand.&go
    def turn = TurnCommand.&turn
    def arc = ArcCommand.&arc
    def slow = AccelerateCommand.&slow
    def speed = AccelerateCommand.&speed
    def getStop() { StopCommand.stop() }
    def stop(args) { StopCommand.stop().over(args.over) }

    abstract void scriptBody()

    def run() {
        addNumberUnits()
        scriptBody()
    }

    def addNumberUnits() {
        Number.metaClass.getSecond  = { return new Duration(delegate, TimeUnit.second) }
        Number.metaClass.getSeconds = { return new Duration(delegate, TimeUnit.second) }
        Number.metaClass.getFt      = { return new Distance(delegate, DistanceUnit.foot) }
        Number.metaClass.getDeg     = { return new Rotation(delegate, RotationUnit.degree) }
        Number.metaClass.getDegrees = { return new Rotation(delegate, RotationUnit.degree) }
    }
}
