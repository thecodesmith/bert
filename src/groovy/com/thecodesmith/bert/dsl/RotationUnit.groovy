package com.thecodesmith.bert.dsl

enum RotationUnit implements Unit {
    degree('deg', 1)

    RotationUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}
