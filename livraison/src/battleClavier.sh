#!/bin/bash
javac -d ../build battle/*.java boat/*.java boat/exceptions/*.java generateur/*.java graphique/*.java graphique/mvc/*.java players/*.java sea/*.java sea/exceptions/*.java

java -cp ../build/ battle.MainClavier
