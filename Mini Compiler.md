# Mini Compiler

## Overview

The specification required that a program be produced which is able to turn Logo programming language into postscript (`PS-Adobe-3.0`).  A document 

### Assumptions

Here are rules that were extrapolated from the provided Logo Grammar and examples of Logo Code.

1. Indentation does not determine the precedence of a statement. This was extrapolated from `spiral.t`. So when parsing file indentation can be ignored, as it purposes are for convention only.
2. All commands must be separated by new lines.
3. All code in Logo language is ran inside of a `PROC` method. So the first word in any logo file must then be  `PROC`.
4. Parameters are always in circular brackets when declaring method and calling method in statement.
5. All Logo files have a `PROC MAIN (VOID)` from which where all the code executes.
6. All calculation expressions run in brackets. Extrapolated from examples.
7. Logo doesn't support complex comparison since it doesn't support any `and` or `or` operators. Logo can only support `{mathematical expression} comparison_op {mathematical expression}`. So If statements can only contain one comparison operator.
8. Methods cannot return values.
9. Logo only supports identifiers in the form `a-z` or `A-Z` . So Logo only support identifiers in consistent casing.
10. `IF` statements are types of statements and `IF` statements can contain statements therefore Logo must s
11. You cannot have multiple `PROC` methods with the same name. Just extrapolated from conventional programming.
12. Logo can only support `PROC` methods with one parameter.
13. Since declared by the logo grammar there are only two types of expressions: `primary` and `binary`. Primary expression are: `identifier`, `num` whereas binary expressions have the form: `expression op expression`. This there for means logo supports complex mathematical expressions such as following: `( 3 * 9 + 4 * 2 ) - ( 4 * 2 + 6 * 5) * 4`. Recursive structure as binary expressions can contain binary expressions.

### Problem Decomposition

The functionally In which the program requires has been split in to 3 separate sections. 

#### Lexical Analysis

Converts the Logo code to steam of `PROC` tokens. The `PROC` tokens contain the code which runs  the method with it. Each type of logo command shall be made into a type of `Token`.

#### Parsing

Gets all the commands that are required to be printed to the file from all the tokens which have been created.

#### Code Generation

The steam of `PostScript` commands are printed to a `.ps` file with same name as inputted to program.

## Design



## Testing

## Evaluation

## Conclusion



