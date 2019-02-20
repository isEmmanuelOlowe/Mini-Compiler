package logoCompiler.lexer;

public class IfCompilationException extends Exception {

  /**
  * For If Statement Errors which occur during the compilation of the program
  *
  * @param msg the compilation error that occured
  */
  public IfCompilationException(String msg) {
    super(msg);
  }
}
