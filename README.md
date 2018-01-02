# Chess Project

[![Build Status](https://travis-ci.com/fzxt/Chess.svg?token=TT9qSQ7v8Pwz8MWFLCnw&branch=master)](https://travis-ci.com/fzxt/Chess)

## Prerequisites

- You'll need Java 8 installed on your machine

## About

AI vs Human 2D Chess written in Java with Swing. AI based on Alpha Beta Pruning

## Notes

- This game does not support loading custom boards.

### Rules

- AI is Team Black, User is Team White

## Compiling, running

- There are two primary routes, with IDE and with command line.

## With IDE

- You'll need to import the project in your editor
- Right click `src/Game.java`, and run the file.

## With Command line

### Building

#### macOS and Linux

- In your terminal, navigate to the root of the project (location of this readme)
- Enter the following commands in order:

```sh
$ mkdir bin
$ javac -d bin -sourcepath src/ src/com/company/Game.java
```

#### Windows

- Open the command prompt, and navigate to the location of this README
- Enter the following commands:

```sh
$ md bin
$ javac -d bin -sourcepath src/ src/com/company/Game.java
```

### Running (macOS, Linux, Windows)

- Once the project has been built, navigate to the location of this README in your terminal
- Enter the following in your terminal / command prompt:

```sh
$ java -cp bin/ com.company.Game
```
