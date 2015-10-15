# Project Log

## Project Proposal

Original proposal document can be found at [proposal.md](proposal.md).

## Acquisition of Parts

2015-09-15

### Parts list:

Item                                                                      | Vendor | Cost
------------------------------------------------------------------------- | ------ | ----
Raspberry Pi 2 Model B                                                    | Amazon | $41.61
Edimax EW-7811Un 150Mbps 11n Wi-Fi USB Adapter                            | Amazon | $9.99
Anker 2nd Generation Astro Mini 3350mAh Portable Charger                  | Amazon | $11.99
Kingston Digital 16 GB microSDHC Flash Card with SD Adapter               | Amazon | $5.95
4x AA rechargeable batteries                                              | Amazon | $11.99
Zumo Chassis Kit                                                          | Pololu | $19.95
75:1 Micro Metal Gearmotor HP (x2)                                        | Pololu | $15.95 (each)
Pololu DRV8835 Dual Motor Driver Kit for Raspberry Pi (x2)\*              | Pololu | $7.49 (each)\*
Pololu Carrier with Sharp GP2Y0A60SZLF Analog Distance Sensor 10-150cm 5V | Pololu | $11.95

* The second board was a replacement of the first one that I mangled. See below.

## Sprint 1

Goals:

* Acquire all required parts, excluding potential sensors that may be
incorporated later
* Construct initial robot
* Get WiFi working
* Demonstrate basic operation including motion

## Sprint 2

Goals:

* Fix motor driver board (correctly solder new one once it arrives)
* Get motors operational and controlled from the Pi
* Demonstrate programmatic control of motors for basic movement

## Sprint 3

Goals:

* Get WiFi hotspot working for external control
* Construct a permanent body for the robot
* Complete code for commands listed in spec
* Complete web server code to accept all available commands from a remote machine
