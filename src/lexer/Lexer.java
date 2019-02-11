package logoCompiler.lexer;
import java.util.*;


public final class Lexer {
  static int ch = ' ';

  public static Token lex(FileReader logoFile) {

    //stores the precedence of tokens
    int precedence = 0;
    //For reading the file
    BufferedReader logoBReader = new BufferedReader(logoFile);

    while(line = BufferedReader.readLine() != null){
      if(!line.isEmpty()){
        line = line.replace("\t", "");
        
      }
    }
    catch(FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    }
    catch(IOException ex){
      System.out.println("Error readin file '" + fileName + "'");
    }

    //skip the white space
    while (ch == ' ' || ch == '\n' || ch == '\t') {
 	ch = getChar();
    }

    //identify new character and return correct token
    switch (ch) {
    	case -1 : {
	        return new EOIToken();
    	}
    	default : {
	        return new EOIToken();
    	}
    }
  }


  //this reads chars from stdin. You can read in files any way you want, using FileReader etc.
  static int getChar() {
    try {
      ch = System.in.read();
    } catch (Exception e) {
      System.out.println(e); System.exit(1);
    }
    return ch;
  }
}
