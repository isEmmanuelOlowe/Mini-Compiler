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
    boolean isMove;
    //determines if the last statement is an if token
    if (this.statements.get(this.statements.size() - 1) instanceof IfToken) {


    }
    if (Tokeniser.isIfStatement(line)) {
      this.statements.add(this.statements.addStatement(new IfToken(line[1], line[2], line[3])));
    }
    else if (isMove = Tokeniser.isMove(line) || Tokeniser.isMethod(line)) {
      //so it can be used in constructor it is inverted
      isMove = !isMove;
      if (Tokeniser.isCalculation(line)) {
        this.statements.add(new StatementToken(line[0], line[2], line[3], line[4], false));
      }
      else {
        this.statements.add(new StatementToken(line[0], line[1], false));
      }
    }
  }
}
