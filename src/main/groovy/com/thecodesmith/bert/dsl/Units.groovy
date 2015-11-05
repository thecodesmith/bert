package com.thecodesmith.bert.dsl

trait Unit {
    String abbreviation
    double multiplier

    void initialize(String abbreviation, double multiplier) {
        this.abbreviation = abbreviation
        this.multiplier = multiplier
    }

    String toString() { abbreviation }
}

enum TimeUnit implements Unit {
    minute('min', 60),
    second('s',    1)

    TimeUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}

enum DistanceUnit implements Unit {
    foot('ft', 12),
    inch('in', 1)

    DistanceUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}

enum RotationUnit implements Unit {
    degree('deg', 1)

    RotationUnit(String abbreviation, double multiplier) {
        initialize(abbreviation, multiplier)
    }
}
