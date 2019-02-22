package lexer;

public class ProcCompilationException extends Exception {

  /**
  * Error has occured in the instaniation of the procToken
  *
  * @param msg the error that occured in the instanciation
  */
  public ProcCompilationException(String msg) {
    super(msg);
  }
}
