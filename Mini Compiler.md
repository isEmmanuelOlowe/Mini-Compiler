# Mini Compiler

## Overview

The specification required that a program be produced which is able to turn Logo programming language into postscript (`PS-Adobe-3.0`). 

### Assumptions

Here are rules that were extrapolated from the provided Logo Grammar and examples of Logo Code.

1. Indentation does not determine the precedence of a statement. This was extrapolated from `spiral.t`.
2. All code in Logo language is ran inside of a `PROC` method.
3. Parameters are always in circular brackets.
4. All Logo files have a `PROC MAIN (VOID)` from which where all the code executes.
5. All calculation expressions run in brackets. Extrapolated from examples.
6. Logo doesn't support complex comparison since it doesn't support any `and` or `or` operators. Logo can only support `{mathematical expression} comparison_op {mathematical expression}`. So If statements can only contain one comparison operator.
7. Methods cannot return values.
8. You cannot have multiple `PROC` methods with the same name. Just extrapolated from conventional programming.
9. Logo can only support `PROC` methods with one parameter.
10. Since declared by the logo grammar there are only two types of expressions: `primary` and `binary`. Primary expression are: `identifier`, `num` whereas binary expressions have the form: `expression op expression`. This there for means logo supports complex mathematical expressions such as following: `( 3 * 9 + 4 * 2 ) - ( 4 * 2 + 6 * 5) * 4`. Recursive structure as binary expressions can contain binary expressions.



### Problem Decomposition



## Design

## Testing

## Evaluation

## Conclusion



