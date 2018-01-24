Expression Evaluator
====================
*by Shazz Amin*

Parses and evaluates mathematical expressions containing basic arithmetic operators (+, -, \*, /, %).

### Features
* Supports prefix, postfix and infix notation

### Notes
* Expression fragments (numbers, operators, brackets/parentheses) must be separated by whitespace (e.g. `( 1 + 3.14 ) * 2` is valid whereas `(1+3.14)*2` is not)

### Build
###### Prerequisites:
* Java Development Kit (>= 9.0.4)

`./build.sh`

### Run
###### Prerequisites:
* Java Runtime Environment (>= 9.0.4)

`java -jar bin/ExpressionEvaluator.jar`
