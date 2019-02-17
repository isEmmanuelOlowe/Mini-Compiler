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
        try {
          if(!line.isEmpty()){
            //makes sure there is space between brackets and splits by white space
            line = line.replace("(", " ( ").replace(")", " ) ")("\t", "").split(" ");
            //All logo code must run in method so if first word is not PROC there is an error
            if (cuurentLine == 0 && sLine[0] != sLine[0]) {
              procTokens.add(new PROCToken());
            }

            //Check that first word in line is PROC and return error if not
            //Chec that 0 index is equal to PROC
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
      //Error catching for file
    }
    catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    }
    catch (IOException ex) {
      System.out.println("Error readin file '" + fileName + "'");
    }
  }

}
