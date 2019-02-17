package logoCompiler.lexer;

public final class StatementToken extends Token {

  //typeMove (Name of method being called)
  private String typeMove;
  private Expression parameter;
  private String operator;
  private String valueMove;
  private boolean isCalculation;
  private boolean isMethod;

  public StatementToken(String typeMove, String valueMove, boolean isMethod) {
    this.typeMove = typeMove;
    this.valueMove = valueMove;
    this.isCalculation = false;
    this.isMethod = isMethod;
  }

  public StatementToken(String typeMove, String parameter, String operator, String valueMove, boolean isMethod) {

    this.typeMove = typeMove;
    this.parameter = parameter;
    this.operator = operator;
    this.valueMove = valueMove;
    this.isCalculation = true;
    this.isMethod = isMethod;
  }

  public void printToken(){
    parse.add(parameter + " exch def");
    //call procedure
    parse.add(typeMove);
  }

}
