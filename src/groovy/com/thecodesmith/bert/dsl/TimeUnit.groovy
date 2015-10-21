package com.thecodesmith.bert.dsl

enum TimeUnit implements Unit {
    minute('min', 60),
    second('s',    1)

    TimeUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}
