package lexer;

public class ProcCompilationErrorException extends Exception {

  /**
  * Error has occured in the instaniation of the procToken
  *
  * @param msg the error that occured in the instanciation
  */
  public ProcCompilationErrorException(String msg) {
    super(msg);
  }
}
