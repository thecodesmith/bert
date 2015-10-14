# Robot Command Spec

This document specifies the commands and syntax available to control the
robot's actions.

## Supported Movements

* Move forward or reverse
* Turn left or right
* Arc left or right at a specified angle
* Stop immediately
* Slow down (or speed up) to a specified speed (immediately or in a specified
amount of time)

### Implementation Notes

Each of these commands should accept some quantifier specifying how long
to continue the same command. This can be done by specifying either time or
distance (or angle, in the case of turning). Using time as the quantifier is
the easiest to implement, and so will be the first method implemented. Distance
makes more sense spatially, but requires some calibration of the motors and
tracks, to convert time spent powering the motors to distance traveled (or
angle turned). This could also be accomplished with an accelerometer and
gyroscope, but this would introduce additional complexity.

### Commands

Available commands:

    go forward <time> [at <speed>]
    go backward <time> [at <speed>]
    turn left <time> [at <speed>]
    turn right <time> [at <speed>]
    arc left <time> [at <angle>] [at <speed>]
    arc right <time> [at <angle>] [at <speed>]
    stop [over <time>]
    slow down to <speed> [over <time>]
    speed up to <speed> [over <time>]

Examples:

    go forward 5 seconds
    go backward 3.2 seconds at 20
    turn left 1.5 sec
    turn right 4 s
    arc left 10 seconds
    arc right 10 seconds at 30 degrees
    stop
    stop over 1 second
    slow down to 20
    speed up to 45 over 2 seconds

Considerations:

* Should time be given a "for" keyword in front? E.g., "arc left for 2 sec".
    - Probably
* Should speed up/slow down be changed to accelerate/decelerate?
* Should speed be given units? Perhaps calibrate "feet per second", which would
allow simple distance to be calculated (e.g., "go forward 2 seconds at 3 fps"
would result in traveling 6 ft). Another option would be to use fictional
units such as "miles per hour" and use a simple scale from 0 to 100 to
represent zero to max speed. This seems less desirable and harder to work with.
