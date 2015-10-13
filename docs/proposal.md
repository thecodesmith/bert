# CS 499 - Programming for Robotics

Fall 2015

The purpose of this project is to discover and explore the elements of
creating and programming a mobile robot system. The project consists of
three phases:

1) Design and construction of a mobile robot
2) Creation of a mini-language (DSL) for programming the robot's actions
3) Building a real-time interface for interacting with the robot via the
DSL and receiving robot sensor feedback

### Phase 1 - Design and construct robot

The Raspberry Pi, a low-cost, credit-card sized computer, will be the
central platform for constructing the robot. The Raspberry Pi is often
used for teaching and learning, so many resources are available for
documentation, sample projects, and peripherals like robot wheels and
sensors. The robot itself will be mobile, having wheels (or tracks) and
sensors such as a proximity sensor, light sensor, camera, et cetera.
This phase will include designing the complete system, sourcing and
acquiring all materials, physically constructing the robot, installing
necessary drivers and software, and getting basic motion functionality
working.

### Phase 2 - Create robot DSL

Software that interacts with hardware systems (like a robot) is typically
written in low-level languages like C/C++, which makes it difficult to program
a simple robot in a way that is easy to learn or understand. Since the ultimate
goal of this project is to make the robot’s movements and actions programmable
(and scriptable), a higher-level domain-specific language will be created. This
language can then be used to program the robot in an expressive and elegant way.

### Phase 3 - Build control dashboard

The final phase of the project will tie together everything that has been
created so far. A custom control dashboard will be built that will allow for
two-way communication with the robot. It will enable the programmer to issue
commands directly to the robot, create and run full scripts, and view sensory
data returned from the robot, which may include location, orientation, camera
view, or others depending on the sensors available. A learning opportunity in
creating this dashboard will be to explore concepts behind single-page web
applications and real-time communication using web sockets.
