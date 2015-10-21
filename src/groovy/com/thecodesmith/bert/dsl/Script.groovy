import groovy.transform.Canonical
import groovy.transform.TupleConstructor

Number.metaClass.getSecond  = { return new Duration(delegate, TimeUnit.second) }
Number.metaClass.getSeconds = { return new Duration(delegate, TimeUnit.second) }
Number.metaClass.getFt      = { return new Distance(delegate, DistanceUnit.foot) }
Number.metaClass.getDeg     = { return new Rotation(delegate, RotationUnit.degree) }
Number.metaClass.getDegrees = { return new Rotation(delegate, RotationUnit.degree) }

s = TimeUnit.second

class Robot {
    static DEFAULT_SPEED = new Speed(new Distance(2, DistanceUnit.foot), TimeUnit.second)
    static DEFAULT_ARC = 30.0.degrees
}

enum Action { go, turn, arc, stop, slow, speed }

enum Direction { forward, backward, left, right, up, down }

trait Unit {
    String abbreviation
    double multiplier

    void initialize(String abbreviation, double multiplier) {
        this.abbreviation = abbreviation
        this.multiplier = multiplier
    }

    String toString() { abbreviation }
}

enum TimeUnit implements Unit {
    minute('min', 60),
    second('s',    1)

    TimeUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}

enum DistanceUnit implements Unit {
    foot('ft', 12),
    inch('in', 1)

    DistanceUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}

enum RotationUnit implements Unit {
    degree('deg', 1)

    RotationUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}

@TupleConstructor
class Distance {
    double amount
    DistanceUnit unit

    Speed div(TimeUnit t) {
        new Speed(this, t)
    }

    String toString() { "$amount $unit" }
}

@TupleConstructor
class Duration {
    double amount
    TimeUnit unit

    String toString() { "$amount $unit" }
}

@TupleConstructor
class Rotation {
    double amount
    RotationUnit unit

    RotationalSpeed div(TimeUnit t) {
        new RotationalSpeed(this, t)
    }

    String toString() { "$amount $unit" }
}

@TupleConstructor
class Speed {
    Distance distance
    TimeUnit unit

    String toString() { "$distance/$unit" }
}

@TupleConstructor
class RotationalSpeed {
    Rotation rotation
    TimeUnit unit

    String toString() { "$rotation/$unit" }
}

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

@TupleConstructor
class AccelerateCommand extends Command {
    Direction direction
    Speed speed = Robot.DEFAULT_SPEED

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

forward = Direction.forward
backward = Direction.backward
left = Direction.left
right = Direction.right
down = Direction.down
up = Direction.up

go = GoCommand.&go
turn = TurnCommand.&turn
arc = ArcCommand.&arc
slow = AccelerateCommand.&slow
def getStop() { StopCommand.stop() }
def stop(args) { StopCommand.stop().over(args.over) }

cmd = go forward, 5.seconds at 2.5.ft/s
println cmd

/* cmd = go backward, 3.2.seconds */
/* println cmd */

cmd = turn left, 2.seconds at 15.deg/s
println cmd

cmd = arc left, 4.seconds at 45.degrees at 1.5.ft/s
println cmd

cmd = arc left, 3.seconds at 60.degrees
println cmd

cmd = arc right, 15.seconds
println cmd

cmd = arc right, 12.seconds at 2.2.ft/s
println cmd

cmd = stop
println cmd

cmd = stop over: 1.second
println cmd

cmd = slow down to 1.5.ft/s
println cmd

cmd = slow down to 1.8.ft/s over 2.seconds
println cmd
