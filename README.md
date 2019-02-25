# Mini Compiler

Allows for conversion of Logo (`.t` files) to postscript (`.ps` adobe 3.0)

## Usage

To compile the classes

```bash
javac LogoPSCompiler.java
```

To run the program with a logo file in which all the commands are on new lines.

```bash
java LogoPSCompiler <logo_file>
```

To run the program with a logo file in which all the commands are not on new lines.

```bash
java LogoPSCompiler <logo_file> -n
```

### Output

Will output the file with same file name but with different extension.

