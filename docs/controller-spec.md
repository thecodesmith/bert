# Hardware Controller Spec

The robot-side code is written in pure Python. The initial implementation will
use a simple HTTP server accepting command requests with parameters. The
commands are added to a queue and executed sequentially.

This could pose several challenges to ultimately making the robot fully
programmable. For one major example, flow control and reaction to events is
impossible with this method.

## Supported Endpoints

    GET /config/max_speed

    POST /command
      params: action=<go|turn|arc|slow|stop>
              direction=<forward|backward|up|down|left|right>
              time=
              speed= (except stop)
              angle= (turn/arc only)
