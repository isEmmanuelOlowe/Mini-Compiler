package logoCompiler.lexer;

public final class IfToken extends Token {
  private Expression compared1;
  private Expression compared2;
  private String operator;
  private boolean isMetComplete = false;
  private boolean isFailedComplete = false;
  private ArrayList<Token> conditionMet = new ArrayList<Token>();
  private ArrayList<Token> conditionFailed = new ArrayList<Token>();

  public IfToken(String compared1, String operator, Strign compared2) {


    this.compared1 = compared1;
    this.operator = operator;
    this.compared2 = compared2;
    this.isMeetComplete = false;
    this.isFailedComplete = false;
  }

  public void endIfFound() throws CompilationErrorException {
    if(!isMetComplete) {
      ErrorHandler("IF Statements Must contain ELSE command");
    }
    this.isFailedComplete = true;
  }
  //adds statements to the if token
  public void addStatement(String line[]) {
    //checks which part of the if statement they shall be declared to
    if(!isMetComplete) {
      newStatement(line, coniditionMet);
    }
    else {
      newStatement(line, conditionFailed);
    }
  }

  private void newStatement(String line[], Arraylist<Token> statements) {

    //determines if the last statement is an if token
    if (!this.lastIfCompleted()) {
      //adds the statement to the last incomplete if statement
      statements.get(statements.size() - 1).addStatement(line);
    }
    else if (line[0].equals("ELSE")) {
      if (line.length != 1) {
        ErrorHandler.add("Unexpected Tokens");
      }
      this.isMetComplete = true;
    }
    else if (line[0].equals("END") && line[1].equals("IF")){
      if (line.length != 2) {
        ErrorHandler.add("Unexpected Tokens");
      }
      this.isFailedComplete = true;
    }
    //checks if current line is an if statement
    else if (Tokeniser.isIfStatement(line)) {
      statements.add(statements.addStatement(new IfToken(line[1], line[2], line[3])));
    }
    //checks if current line is a movement statement or method call
    else if (Tokeniser.isMove(line)) {
      //if fist word is movement keyword or else it is a parameter call
      boolean isMethod = !Tokeniser.isMove();
      //checks if the statement was calculation
      if (Tokeniser.isCalculation(line)) {
        statements.add(new StatementToken(line[0], line[2], line[3], line[4], isMethod);
      }
      else {
        statements.add(new StatementToken(line[0], line[1], isMethod));
        }
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

  //determines if an if statement has been completed
  public void isComplete(){
    return isFailedComplete;
  }

  //in the event an error occurs in the compilation of the if statement
  public void invalid(){
    isFailedComplete = true;
  }

  public void printToken(){
    this.compared1.printToken();
    this.compared2.printToken();
    parse.add(PSDictionary.convertToPSOperator(this.operator));
    parse.add("{");
    for (Token met: conditionMet) {
      parse.add(met);
    }
    parse.add("} {");

    for (Token failed: conditionFailed) {
      parse.add(failed);
    }
    parse.add("} ifelse");
  }

}
