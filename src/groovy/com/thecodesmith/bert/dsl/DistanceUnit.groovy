package com.thecodesmith.bert.dsl

enum DistanceUnit implements Unit {
    foot('ft', 12),
    inch('in', 1)

    DistanceUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}
