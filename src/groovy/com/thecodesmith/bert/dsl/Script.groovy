package com.thecodesmith.bert.dsl

Number.metaClass.getSecond  = { return new Duration(delegate, TimeUnit.second) }
Number.metaClass.getSeconds = { return new Duration(delegate, TimeUnit.second) }
Number.metaClass.getFt      = { return new Distance(delegate, DistanceUnit.foot) }
Number.metaClass.getDeg     = { return new Rotation(delegate, RotationUnit.degree) }
Number.metaClass.getDegrees = { return new Rotation(delegate, RotationUnit.degree) }

s = TimeUnit.second

forward = Direction.forward
backward = Direction.backward
left = Direction.left
right = Direction.right
down = Direction.down
up = Direction.up

go = GoCommand.&go
turn = TurnCommand.&turn
arc = ArcCommand.&arc
slow = AccelerateCommand.&slow
speed = AccelerateCommand.&speed
def getStop() { StopCommand.stop() }
def stop(args) { StopCommand.stop().over(args.over) }

cmd = go forward, 5.seconds at 2.5.ft/s
println cmd

cmd = go backward, 3.2.seconds
println cmd

cmd = turn left, 2.seconds at 15.deg/s
println cmd

cmd = arc left, 4.seconds at 45.degrees at 1.5.ft/s
println cmd

cmd = arc left, 3.seconds at 60.degrees
println cmd

cmd = arc right, 15.seconds
println cmd

cmd = arc right, 12.seconds at 2.2.ft/s
println cmd

cmd = stop
println cmd

cmd = stop over: 1.second
println cmd

cmd = slow down to 1.5.ft/s
println cmd

cmd = slow down to 1.8.ft/s over 2.seconds
println cmd

cmd = speed up to 3.0.ft/s
println cmd

cmd = speed up to 3.1.ft/s over 1.second
println cmd
