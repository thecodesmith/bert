package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class Rotation {
    double amount
    RotationUnit unit

    RotationalSpeed div(TimeUnit t) {
        new RotationalSpeed(this, t)
    }

    String toString() { "$amount $unit" }
}
