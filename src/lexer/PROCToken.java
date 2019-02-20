package logoCompiler.lexer;

import java.util.ArrayList;
import logoCompiler.parser.Parser;

public final class PROCToken extends Token {

  private String name;
  private String parameter;
  private ArrayList<Token> statements = new ArrayList<Token>();
  //private ArrayList<String> methods = new ArrayList<String>();
  //for in the event the class is dumb InvalidToken
  private boolean isInvalid;

  public PROCToken(String[] line) {

    if (isValidProc(line)) {
      //if isValidProc doesnt throw an exception then these parameters are set
      this.name = line[1];
      this.parameter = line[3];
      this.isInvalid = true;
      Tokeniser.setActiveParameter(this.parameter);
      ErrorHandler.addName(this.name);
    }
    else {
      Tokeniser.setActiveParameter(null);
    }
  }


  /**
  * Used a dummy proc token class for instanciation of invalid proc token
  */
  public PROCToken() {
    isInvalid = false;
    Tokeniser.setActiveParameter(null);
  }

  //identical method should be found in if statement class
  public void addStatement(String[] line){

    //determines if the last statement is an if token
    if (!this.lastIfCompleted()) {
      //adds the statement to the last incomplete if statement
      IfToken lastIfToken = (IfToken) this.statements.get(this.statements.size() - 1);
      lastIfToken.addStatement(line);
    }
    //checks if current line is an if statement
    else if (line[0].equals("IF")) {
      this.statements.add(new IfToken(line));
    }
    //checks if current line is a movement statement or method call
    else {
      this.statements.add(new StatementToken(line));
    }
  }

  //checks identifiers are valid
  private static boolean checkIdentifier(String ident) {
    boolean valid = true;
    //checks identifier is not a keyword
    if(!Tokeniser.isKeyword(ident).equals("none")){
      ErrorHandler.addError("Identifier cannot be keyword");
      valid = false;
    }

    //checks is not a isComparisonOperator
    if(!Tokeniser.isComparisonOperator(ident)){
      ErrorHandler.addError("Identifier cannot be Comparison Operator");
      valid = false;
    }

    //checks is not a math operator
    if(!Tokeniser.isMathOperator(ident)){
      ErrorHandler.addError("Identifier cannot be Mathematical Operator");
      valid = false;
    }
    //check not special character
    //check doesnt contain specical characters

    return valid;
  }

  private boolean isValidProc(String[] line) {
    boolean valid = true;
    //to prevent array out of bounds errors
    if (line.length < 5 ){
      ErrorHandler.addError("Missing Elements to PROC declaration");
      valid = false;

    }
    //checks if the first line of program is method
    if(!line[0].equals("PROC")) {
      ErrorHandler.addError("First Line must be contain PROC method");
      valid = false;
    }

    //checks for expected brackets in method
    if (line[2].equals("(")) {
      if (!line[4].equals(")")) {
        ErrorHandler.addError(") - Bracket expected");
        valid = false;
      }
    }
    else {
      ErrorHandler.addError("( - Bracket expected");
      valid = false;
    }
    //checks the identifiers
    if (valid){
      if(!checkIdentifier(line[1]) || !checkIdentifier(line[3])){
        valid = false;
      }
    }
    //checks the proc method is the correct length
    if (line.length != 6) {
      ErrorHandler.addError("Unexpected Tokens");
      valid = false;
    }
    return false;
  }

  //checks the last if statement is complete
  private boolean lastIfCompleted() {
    boolean complete = false;
    if(this.statements.get(this.statements.size() - 1) instanceof IfToken){
      IfToken lastIf = (IfToken) this.statements.get(this.statements.size() - 1);
      if(lastIf.isComplete() == true){
        complete = false;
      }
    }
    return complete;
  }

  public void close() {
    if(!lastIfCompleted()){
      ErrorHandler.addError("If Statement in previous PROC METHOD incomplete");
    }
  }

  public void printToken(){
      Parser.add("/" + name + " {" );
      //insert method to declare paraameter in method
      for (Token token: statements){
        token.printToken();
      }

      Parser.add("} def");
  }

}
