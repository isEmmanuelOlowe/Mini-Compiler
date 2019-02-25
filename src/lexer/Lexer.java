package lexer;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
* Tokenises each line of the file.
*/
public final class Lexer {

  private static ArrayList<PROCToken> procTokens = new ArrayList<PROCToken>();

  /**
  * Tokenisers the logo file.
  *
  * @param logoFile the name of the logo file
  * @return the list of procTokens which all logo code runs
  */
  public ArrayList<PROCToken> lex(String logoFile) {

    //stores the proc Tokens
    try {
      //For reading the file
      BufferedReader logoBReader = new BufferedReader(new FileReader(logoFile));
      String line;
      while ((line = logoBReader.readLine()) != null) {
        process(line);
    //Error catching for file
      }
    }
    catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + logoFile + "'");
    }
    catch (IOException ex) {
      System.out.println("Error readin file '" + logoFile + "'");
    }
    return procTokens;
  }

  /**
  * Processes the current line being read.
  *
  * @param sLine the string of the current line being read.
  */
  private void process(String sLine) {

    //cheks line is no empty
    if ((!sLine.isEmpty() && !sLine.trim().equals("") && !sLine.trim().equals("\n"))) {
      ErrorHandler.addString(sLine);
      //makes sure there is space between brackets and splits by white space
      String[] line = sLine.replace("(", " ( ").replace(")", " ) ").replace("\t", "").trim().split("\\s+");
      //All logo code must run in method so if first word must PROC be PROC
      if (ErrorHandler.getCurrentLine() == 1) {
        procTokens.add(new PROCToken(line));
      }
      //checks line is not a proc declaration
      else if (!line[0].equals("PROC")) {
        //adds the statement to a new procTokens statements
        procTokens.get(procTokens.size() - 1).addStatement(line);
      }
      else {
      procTokens.get(procTokens.size() - 1).close();
      procTokens.add(new PROCToken(line));
      }
    }
  }
}
