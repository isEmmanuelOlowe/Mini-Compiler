package logoCompiler.lexer;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

public final class Lexer {

  /**
  * Tokenisers the logo file.
  *
  * @param logoFile the name of the logo file
  * @return the list of procTokens which all logo code runs
  */
  public static Token[] lex(String logoFile) throws CompilationErrorException{

    //stores the proc Tokens
    ArrayList<PROCToken> procTokens = new ArrayList<PROCToken>();
    try{
      //For reading the file
      BufferedReader logoBReader = new BufferedReader(new FileReader(logoFile));

      while(line = BufferedReader.readLine() != null){
        ErrorHandler.addString(line);
        process(line);
    //Error catching for file
    }
    catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    }
    catch (IOException ex) {
      System.out.println("Error readin file '" + fileName + "'");
    }
  }

  public static void process(String[] line) {
    try {
      //cheks line is no empty
      if(!line.isEmpty()){
        //makes sure there is space between brackets and splits by white space
        line = line.replace("(", " ( ").replace(")", " ) ")("\t", "").split(" ");
        //All logo code must run in method so if first word must PROC be PROC
        if (ErrorHandler.getCurrentLine() == 1) {
          procTokens.add(new PROCToken(line));
        }
        //checks line is not a proc declaration
        else if (line[0] != "PROC") {
          //adds the statement to a new procTokens statements
          procTokens.get(proTokens.size() - 1).addStatement(line);
        }
        else {
        procTokens.get(procToken.size() - 1).close()
        procTokens.add(new PROCToken(line));
        }
      }
    }
    //catches errors which occured during
    catch (ProcCompilationErrorException ex) {
      procToken.add(new PROCToken())
      ErrorHandler.addError(ex.getMessage());
    }
    //catches errors which occured during lexical analysis
    catch (CompilationErrorException ex ) {
      ErrorHandler.addError(ex.getMessage());
    }
  }
}
