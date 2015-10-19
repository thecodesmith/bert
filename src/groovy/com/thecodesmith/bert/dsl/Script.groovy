/* import groovy.time.TimeCategory */
import groovy.transform.Canonical
import groovy.transform.TupleConstructor

enum Action { go, turn, stop }

enum Direction { forward, backward, left, right, up, down }

enum TimeUnit {
    minute('min', 60),
    second('s',    1)

    String abbreviation
    double multiplier

    TimeUnit(abbreviation, multiplier) {
        this.abbreviation = abbreviation
        this.multiplier = multiplier
    }

    String toString() { abbreviation }
}

enum DistanceUnit {
    foot('ft', 12),
    inch('in', 1)

    String abbreviation
    double multiplier

    DistanceUnit(String abbreviation, double multiplier) {
        this.abbreviation = abbreviation
        this.multiplier = multiplier
    }

    String toString() { abbreviation }
}

@TupleConstructor
class Distance {
    double amount
    DistanceUnit unit

    Speed div(Duration t) {
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
class Speed {
    Distance distance
    Duration duration

    String toString() {
        "$distance/$duration"
    }
}

@Canonical
class Command {
    String action
    Direction direction
    Speed speed
    Duration time

    Command at(Speed speed) {
        this.speed = speed
        this
    }
}

Number.metaClass.getSeconds = { return new Duration(delegate, TimeUnit.second) }
Number.metaClass.getFt = { return new Distance(delegate, DistanceUnit.foot) }

forward = Direction.forward
s = new Duration(1, TimeUnit.second)

def go(Direction direction, Duration time) {
    new Command([action: Action.go, direction: direction, time: time])
}

cmd = go forward, 5.seconds at 2.5.ft/s
println 'Command created:'
println cmd
