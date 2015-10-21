package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class Duration {
    double amount
    TimeUnit unit

    String toString() { "$amount $unit" }
}
