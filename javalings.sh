#!/bin/bash
echo "Starting javalings..."

./gradlew run --console=plain --args="$1 $2" -q