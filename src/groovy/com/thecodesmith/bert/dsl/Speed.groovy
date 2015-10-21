package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class Speed {
    Distance distance
    TimeUnit unit

    String toString() { "$distance/$unit" }
}
