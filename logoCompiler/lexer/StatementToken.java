package logoCompiler.lexer;

import logoCompiler.parser.Parser;
import java.util.Arrays;

/**
* Describes Statement Token
*/
public final class StatementToken extends Token {

  //typeMove (Name of method being called)
  private String name;
  private Expression parameter;
  private boolean isMove = false;

  /**
  * Tokenises a Statement
  *
  * @param line the line of code being tokenised to statement.
  */
  public StatementToken(String[] line) {

    if (isValidStatement(line)){
      this.name = line[0];
      this.parameter = Tokeniser.addExpression(Arrays.copyOfRange(line, 1, line.length));
    }
  }

  /**
  * Determines if a statement is in a valid.
  *
  * @param line the line of code being tokenised
  * @return true if statement is in valid format.
  */
  private boolean isValidStatement(String[] line) {

    boolean valid = false;
    if (line.length <= 1){
      valid = false;
    }
    else if (Tokeniser.isMove(line[0])) {
      if (Tokeniser.validCalculation(Arrays.copyOfRange(line, 1, line.length))) {
        isMove = true;
        valid = true;
      }
    }
    else {
      if (line[1].equals("(") && line[line.length - 1].equals(")")) {
        if (Tokeniser.validCalculation(Arrays.copyOfRange(line, 1, line.length))) {
          ErrorHandler.addMethodCall(line[0]);
          valid = true;
        }
      }
      else {
        ErrorHandler.addError("Method must be called from within brackets");
      }
    }
    return valid;
  }

  /**
  * Converts a Token to PostScript format.
  */
  public void printToken() {
    //If a function is called then some must be on stack to set the value of Arg to
    if (isMove){
      parameter.print();
      Parser.add(name.substring(0, 1) + name.substring(1, name.length()).toLowerCase());
    } else {
      Parser.add("Arg");
      parameter.print();
      Parser.add("/Arg exch def");
      Parser.add(name);
      Parser.add("/Arg exch def");

    }
  }
}
