package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

enum Action { go, turn, arc, stop, slow, speed }

enum Direction { forward, backward, left, right, up, down }

@TupleConstructor
class Distance {
    double amount
    DistanceUnit unit

    Speed div(TimeUnit t) {
        new Speed(this, t)
    }

    double getTotal() { amount * unit.multiplier }

    String toString() { "$amount $unit" }
}

@TupleConstructor
class Duration {
    double amount
    TimeUnit unit

    double getTotal() { amount * unit.multiplier }

    String toString() { "$amount $unit" }
}

@TupleConstructor
class Rotation {
    double amount
    RotationUnit unit

    double getTotal() { amount * unit.multiplier }

    RotationalSpeed div(TimeUnit t) {
        new RotationalSpeed(this, t)
    }

    String toString() { "$amount $unit" }
}

@TupleConstructor
class Speed {
    Distance distance
    TimeUnit unit

    double getTotal() { distance.total * unit.multiplier }
    int getMotorSpeed() { (total * 10) as int }

    String toString() { "$distance/$unit" }
}

@TupleConstructor
class RotationalSpeed {
    Rotation rotation
    TimeUnit unit

    double getTotal() { rotation.amount * unit.multiplier }
    int getMotorSpeed() { (total * 10) as int }

    String toString() { "$rotation/$unit" }
}
