public final class PrimaryExpression extends Expression{

  //holds integer or variable
  private String content;

  /**
  * Creates a new primary expression from a variable or nubmer
  *
  * @param content the value or number that is being stored
  */
  public PrimaryExpression(String content) {
    this.content = content;
  }

  /**
  * Adds the content of primary expression to the list of items that is required to be printed.
  */
  public void print() {
    System.out.println(this.content);
  }
}
