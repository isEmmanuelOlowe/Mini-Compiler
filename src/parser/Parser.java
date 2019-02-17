package logoCompiler.parser;
import  logoCompiler.lexer.*;

public final class Parser {
  public static ProcToken[] t;
  public static boolean error = false;

  public static void codeGen(String fileName){
    for (ProcToken token: t) {
      token.printToken();
    }
  }

}
