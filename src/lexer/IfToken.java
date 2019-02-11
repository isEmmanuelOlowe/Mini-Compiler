package logoCompiler.lexer;

public final class IfToken extends Token {
  private String compared1;
  private String compared2;
  private String operator;
  private ArrayList<StatementToken> conditionMeet = new ArrayList<StatementToken>();
  private ArrayList<StatementToken> conditionFailed = new ArrayList<StatementToken>();
}
