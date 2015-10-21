package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class RotationalSpeed {
    Rotation rotation
    TimeUnit unit

    String toString() { "$rotation/$unit" }
}
