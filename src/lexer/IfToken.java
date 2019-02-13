package logoCompiler.lexer;

public final class IfToken extends Token {
  private String compared1;
  private String compared2;
  private String operator;
  private boolean isMeetComplete;
  private boolean isFailedComplete;
  private ArrayList<StatementToken> conditionMeet = new ArrayList<StatementToken>();
  private ArrayList<StatementToken> conditionFailed = new ArrayList<StatementToken>();

  public IfToken(String compared1, String operator, Strign compared2) {
    this.compared1 = compared1;
    this.operator = operator;
    this.compared2 = compared2;
    this.isMeetComplete = false;
    this.isFailedComplete = false;
  }
}
