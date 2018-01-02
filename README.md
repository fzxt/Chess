# Chess Project

[![Build Status](https://travis-ci.com/fzxt/Chess.svg?token=TT9qSQ7v8Pwz8MWFLCnw&branch=master)](https://travis-ci.com/fzxt/Chess)

## About
AI vs Human 2D Chess written in Java with Swing. 
AI based on Alpha Beta Pruning

## Notes
- This game does not support loading custom boards.

### Rules
- AI is Team Black, User is Team White

## Compiling, running
- There are two primary routes, with IDE and with the JAR executable.
 
### With IDE
- You'll need to import the project (`src folder`)
- You'll then need to mark the `src/resources` folder as resources
    - [How to do this with Intellij](https://stackoverflow.com/questions/21722657/how-to-mark-package-as-a-resource-folder)
    - [How to do this with NetBeans](http://www.javaquery.com/2015/10/how-to-create-resource-folder-in.html)
- Right click `src/Game.java`, and run the file.

### With JAR (easiest)
- Simply double click the JAR file, ensuring that you have Java 8 installed.
