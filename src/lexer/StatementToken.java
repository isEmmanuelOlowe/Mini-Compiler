package lexer;

import parser.Parser;
import java.util.Arrays;

public final class StatementToken extends Token {

  //typeMove (Name of method being called)
  private String typeMove;
  private Expression parameter;

  public StatementToken(String[] line) {
    if (isValidStatement(line)){
      this.typeMove = line[0];
      this.parameter = addExpression(Arrays.copyOfRange(line, 1, line.length));
    }
  }

  public boolean isValidStatement(String[] line) {

    boolean valid = false;
    if (line.length <= 1){
      valid = false;
    }
    else if (Tokeniser.isMove(line[0])) {
      if (Tokeniser.validCalculation(Arrays.copyOfRange(line, 1, line.length))) {
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

  public void printToken() {
    parameter.print();
    //call procedure
    Parser.add(typeMove);
  }

  private Expression addExpression(String[] expression) {
    Expression compared;
    //if expression only contains one element it must be primary statement
    if (expression.length == 1 || expression.length == 3) {
      compared = new PrimaryExpression(expression[0]);
    }
    else {
      compared = new BinaryExpression(expression);
    }
    return compared;
  }
}
