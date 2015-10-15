# BERT Earthbound Rover Tech

_A robotics programming platform_

## Introduction

This project begain as an independent study in computer science for
CS 499 at the University of Wisconsin - Eau Claire in the Fall of 2015.
To view the original project proposal, see [docs/proposal.md](docs/proposal.md).

## Development Setup

This project depends on several Python libraries:

* WiringPi
* WiringPi2-Python

Download and install WiringPi:

    git clone git://git.drogon.net/wiringPi
    cd wiringPi
    ./build

Next, download and install WiringPi2-Python:

    git clone https://github.com/Gadgetoid/WiringPi2-Python
    cd WiringPi2-Python
    sudo apt-get install python-dev python-setuptools
    sudo python setup.py install
