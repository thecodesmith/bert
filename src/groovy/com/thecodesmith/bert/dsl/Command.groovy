package com.thecodesmith.bert.dsl

import groovy.transform.TupleConstructor

@TupleConstructor
class Command {
    String action
    Duration time

    String toString() { "Command: $action" }
}
