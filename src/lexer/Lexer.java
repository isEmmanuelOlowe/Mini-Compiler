package logoCompiler.lexer;
import java.util.*;


public final class Lexer {
  static int ch = ' ';

  public static Token lex(FileReader logoFile) throws InvalidTokenException{

    //stores the proc Tokens
    ArrayList<PROCToken> procTokens = new ArrayList<PROCToken>();
    int currentProc = 0;
    //For reading the file
    BufferedReader logoBReader = new BufferedReader(logoFile);

    while(line = BufferedReader.readLine() != null){
      if(!line.isEmpty()){
        line = line.replace("\t", "").split(" ");

        //Check that first word in line is PROC and return error if not
        //Chec that 0 index is equal to PROC


        while(line[0] != "PROC"){
          procTokens[currentProc].addStatement(line);
        }
        currentProc++;
        procTokens.add(Tokeniser.procTokeniser(line));
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
