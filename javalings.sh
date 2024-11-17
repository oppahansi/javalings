#!/bin/bash

if [ -t 0 ]; then
    ./gradlew run --console=plain --args="$1 $2" -q
else
    echo "Please run this script in an interactive terminal."
    exit 1
fi