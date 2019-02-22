package lexer;

public class CompilationErrorException extends Exception {

  /**
  * For general Errors which occur during the compilation of the program
  *
  * @param msg the compilation error that occured
  */
  public CompilationErrorException(String msg) {
    super(msg);
  }
}
