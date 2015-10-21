package com.thecodesmith.bert.dsl

class Robot {
    static DEFAULT_SPEED = new Speed(new Distance(2, DistanceUnit.foot), TimeUnit.second)
    static DEFAULT_ARC = new Rotation(30, RotationUnit.degree)
}
