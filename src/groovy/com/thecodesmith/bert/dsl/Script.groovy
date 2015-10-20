/* import groovy.time.TimeCategory */
import groovy.transform.Canonical
import groovy.transform.TupleConstructor

enum Action { go, turn, stop }

enum Direction { forward, backward, left, right, up, down }

trait Unit {
    String abbreviation
    double multiplier

    String toString() { abbreviation }
}

enum TimeUnit implements Unit {
    minute('min', 60),
    second('s',    1)

    TimeUnit(abbreviation, multiplier) {
        this.abbreviation = abbreviation
        this.multiplier = multiplier
    }
}

enum DistanceUnit implements Unit {
    foot('ft', 12),
    inch('in', 1)

    DistanceUnit(String abbreviation, double multiplier) {
        this.abbreviation = abbreviation
        this.multiplier = multiplier
    }
}

enum RotationUnit implements Unit {
    degree('deg', 1)

    RotationUnit(String abbreviation, double multiplier) {
        this.abbreviation = abbreviation
        this.multiplier = multiplier
    }
}

@TupleConstructor
class Distance {
    double amount
    DistanceUnit unit

    Speed div(TimeUnit t) {
        new Speed(this, t)
    }

    String toString() {
        "$amount $unit"
    }
}

@TupleConstructor
class Duration {
    double amount
    TimeUnit unit

    String toString() {
        "$amount $unit"
    }
}

@TupleConstructor
class Rotation {
    double amount
    RotationUnit unit

    RotationalSpeed div(TimeUnit t) {
        new RotationalSpeed(this, t)
    }

    String toString() {
        "$amount $unit"
    }
}

@TupleConstructor
class Speed {
    Distance distance
    TimeUnit unit

    String toString() {
        "$distance/$unit"
    }
}

@TupleConstructor
class RotationalSpeed {
    Rotation rotation
    TimeUnit unit

    String toString() {
        "$rotation/$unit"
    }
}

@TupleConstructor
class Command {
    String action
    Duration time

    String toString() {
        "Command: $action"
    }
}

@TupleConstructor
class GoCommand extends Command {
    Direction direction
    Speed speed = new Speed(new Distance(1, DistanceUnit.foot), TimeUnit.second)

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

@TupleConstructor
class TurnCommand extends Command {
    Direction direction
    RotationalSpeed speed = new RotationalSpeed(new Rotation(5, RotationUnit.degree), TimeUnit.second)

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

@TupleConstructor
class StopCommand extends Command {

    static StopCommand stop() {
        new StopCommand([action: Action.stop])
    }

    StopCommand over(Duration time) {
        this.time = time
        this
    }

    String toString() {
        super.toString() + (time ? " over $time" : "")
    }
}

Number.metaClass.getSecond  = { return new Duration(delegate, TimeUnit.second) }
Number.metaClass.getSeconds = { return new Duration(delegate, TimeUnit.second) }
Number.metaClass.getFt      = { return new Distance(delegate, DistanceUnit.foot) }
Number.metaClass.getDeg     = { return new Rotation(delegate, RotationUnit.degree) }

forward = Direction.forward
backward = Direction.backward
left = Direction.left
right = Direction.right

s = TimeUnit.second

go = GoCommand.&go
turn = TurnCommand.&turn
def getStop() { StopCommand.stop() }
def stop(over) { StopCommand.stop().over(over) }

cmd = go forward, 5.seconds at 2.5.ft/s
println cmd

cmd = go backward, 3.2.seconds
println cmd

cmd = turn left, 2.seconds at 15.deg/s
println cmd

cmd = stop
println cmd

cmd = stop over: 1.second
println cmd
