package logoCompiler.lexer;

public final class PROCToken extends Token {
  private String name;
  private String parameter;
  private ArrayList<Token> statements = new ArrayList<Token>();
  private ArrayList<String> methods = new ArrayList<String>();

  public PROCToken(String[] line){
    //check not special character
    if(Tokeniser.isKeyword(line[1]).equals("none")){
      this.name = line[1];
    }
    //check not special character
    if (line[2].equals("(") && Tokeniser.isKeyword(line[3]).equals("none")) {
      if (line[4].equals(")")) {
        this.parameter = line[3];
      }
    }
  }

  public addStatement(String[] line){
    //check if last statement was if statement and that is complete

    //check the length of lines
    //checks for FOWARD, LEFT, RIGHT
    if (Tokeniser.isMove(line[0])) {
      if (line[1].equals("(") && line[5].equals(")")){
        //complete
        //methods some kind of calculation
        if (line[2].equals(this.parameter) && Tokeniser.isMathOperator(line[3])) {
            if (Tokeniser.isInteger(line[4])) {
              this.statements.add(new StatementToken(line[0], line[2], line[3], line[4], false);
            }
        }
      }
      else {
        if (Tokeniser.isNumber(line[1])) {
          this.statements.add(new StatementToken(line[0], line[1], false));
        }
      }
    }

    if (line[0].equals("IF") && line[4].equals("THEN")) {
      //You can call methods in if statement
      if (line[1].equals(this.parameter) && Tokeniser.isNumber(line[3])) {
        if (Tokeniser.isComparisonOperator(line[2])) {
          //This can be Method or Statement
          this.statements.addStatement(new IfToken(line[1], line[2], line[3]));
        }
      }
    }

    //find some way of checking method has been declared
    //check if number
    if(line[1].equals("(") && (line[3].equals("(") || line[5].equals(")"))){
      this.methods.add(line[0]);
      this.statements.add(new Statement(line[0], line[]))
    }
  }
}
