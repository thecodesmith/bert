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
