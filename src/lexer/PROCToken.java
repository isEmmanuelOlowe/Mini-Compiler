package logoCompiler.lexer;

public final class PROCToken extends Token {

  private String name;
  private String parameter;
  private ArrayList<Token> statements = new ArrayList<Token>();
  //private ArrayList<String> methods = new ArrayList<String>();
  //for in the event the class is dumb InvalidToken
  private boolean isInvalid;

  public PROCToken(String[] line) throws ProcCompilationErrorException{

    isValidProc(line);
    //if isValidProc doesnt throw an exception then these parameters are set
    this.name = line[1];
    this.parameter line[3];
    this.isInvalid = true;
  }


  /**
  * Used a dummy proc token class for instanciation of invalid proc token
  */
  public PROCToken() {
    isInvalid = false;
  }

  //identical method should be found in if statement class
  public addStatement(String[] line){

    //determines if the last statement is an if token
    if (!this.lastIfCompleted()) {
      //adds the statement to the last incomplete if statement
      this.statements.get(this.statements.size() - 1).addStatement(line);
    }
    //checks if current line is an if statement
    else if (Tokeniser.isIfStatement(line)) {
      this.statements.add(this.statements.addStatement(new IfToken(line[1], line[2], line[3])));
    }
    //checks if current line is a movement statement or method call
    else if (Tokeniser.isMove(line)) {
      //if fist word is movement keyword or else it is a parameter call
      boolean isMethod = !Tokeniser.isMove();
      //checks if the statement was calculation
      if (Tokeniser.isCalculation(line)) {
        this.statements.add(new StatementToken(line[0], line[2], line[3], line[4], isMethod);
      }
      else {
        this.statements.add(new StatementToken(line[0], line[1], isMethod));
      }
    }
  }

  //checks identifiers are valid
  private static void checkIdentifier(String ident) throws ProcCompilationErrorException{

    //checks identifier is not a keyword
    if(!Tokeniser.isKeyword(ident).equals("none")){
      throw new ProcCompilationErrorException("Parameter cannot be keyword");
    }

    //checks is not a isComparisonOperator
    if(!Tokeniser.isComparisonOperator(line[1])){
      throw new ProcCompilationErrorException("Parameter cannot be keyword");
    }

    //checks is not a math operator
    if(!Tokeniser.isMathOperator(line[1])){
      throw new ProcCompilationErrorException("Parameter cannot be keyword");
    }
    //check not special character
    //check doesnt contain specical characters
  }

  private void isValidProc(String[] line) throws ProcCompilationErrorException {
    //checks if the first line of program is method
    if(!line[0].equals("PROC")) {
      throw new ProcCompilationErrorException("First Line must be contain PROC method");
    }

    //checks for expected brackets in method
    if (line[2].equals("(") {
      if (!line[4].equals(")")) {
        throw new ProcCompilationErrorException(") - Bracket expected");
      }
    }
    else {
      throw new ProcCompilationErrorException("( - Bracket expected")
    }
    //checks the identifiers
    checkIdentifier(line[1]);
    checkIdentifier(line[3]);
    //checks the proc method is the correct length
    if (line.length != 6) {
      throw new ProcCompilationErrorException("Unexpected Tokens");
    }
  }

  //checks the last if statement is complete
  private boolean lastIfCompleted() {
    boolean complete = false;
    if(this.statements.get(this.statements.size() - 1) instanceof IfToken){
      if(this.statements.get(this.statements.size() - 1).isComplete() == true){
        complete = false;
      }
    }
    return complete;
  }

  private void close() throws {
    if(!lastIfCompleted()){
      ErrorHandler.addError("If Statement in previous PROC METHOD incomplete");
    }
  }
}
