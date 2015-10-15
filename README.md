# BERT Earthbound Rover Tech

_A robotics programming platform_

## Introduction

This project begain as an independent study in computer science for
CS 499 at the University of Wisconsin - Eau Claire in the Fall of 2015.
To view the original project proposal, see [docs/proposal.md](docs/proposal.md).

## Development Setup

Download the source code:

    git clone https://github.com/thecodesmith/bert
    cd bert

### Python Development (Robot-side)

For developing robot-side code, ensure Python development tools are installed:

* Python 2.7.x
* Virtualenv

Create a virtual environment in the source directory:

    virtualenv venv
    source ./venv/bin/activate

Install dependencies:

    pip install -r requirements.txt

Run tests (Note: sudo is required to access GPIO pins which control the motors):

    sudo python robot/server_tests.py
