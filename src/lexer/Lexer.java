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
  * @param seperated indicates wether commands are sepeated by line
  * @return the list of procTokens which all logo code runs
  */
  public ArrayList<PROCToken> lex(String logoFile, boolean seperated) {

    //stores the proc Tokens
    try {
      //For reading the file
      BufferedReader logoBReader = new BufferedReader(new FileReader(logoFile));

      if (seperated == true) {
        String line;
        while ((line = logoBReader.readLine()) != null) {
          process(line);
        }
      }
      else {
        String line;
        String file = "";
        while ((line = logoBReader.readLine()) != null) {
          file += line + " ";
        }
        ArrayList<String[]> lines = stream(file);
        for (String[] newLine: lines) {
          String sLine = String.join(" ", newLine);
          process(sLine);
        }
      }
      logoBReader.close();
    //Error catching for file
    }
    catch (FileNotFoundException ex) {
      procTokens = null;
      System.out.println("Unable to open file '" + logoFile + "'");
    }
    catch (IOException ex) {
      procTokens = null;
      System.out.println("Error reading file '" + logoFile + "'");
    }
    return procTokens;
  }

  /**
  * Processes the current line being read.
  *
  * @param sLine the string of the current line being read.
  */
  private void process(String sLine) {

    //checks line is not empty
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

  /**
  * Seperates a streamed logo file into seperate lines.
  *
  * @param file the complete text in the logo file.
  */
  private static ArrayList<String[]> stream(String file){
    //splits by whitespace
    String[] seperate = file.replace("(", " ( ").replace(")", " ) ").replace("\t", "").trim().split("\\s+");
    ArrayList<String[]> lines = new ArrayList<String[]>();
    for(int i = 0; i < seperate.length; i++){
      if (seperate[i].equals("PROC")) {
        //PROC include 4 things as seen from grammar
        if (i + 5 <= seperate.length) {
          lines.add(Arrays.copyOfRange(seperate, i, i + 5));
          i += 4;
        }
        else {
          ErrorHandler.addError("FILE ENDED BEFORE COULD PARSE.");
        }
      }
      else if (seperate[i].equals("IF")) {
        //will increment until it finds a then statement
        int j = i;
        boolean found = false;
        while (j < seperate.length && found == false) {
          if (seperate[j].equals("THEN")){
            found = true;
            lines.add(Arrays.copyOfRange(seperate, i, j + 1));
            i += (j - i);
          }
          j += 1;
        }
        if (found == false){
          ErrorHandler.addError("NO THEN FOUND FOR IF STATEMENT");
        }
      }
      else if (seperate[i].equals("ELSE")) {
        lines.add(Arrays.copyOfRange(seperate, i, i + 1));
      }
      else if (seperate[i].equals("ENDIF")) {
        lines.add(Arrays.copyOfRange(seperate, i, i + 1));
      }
      //means it must be a method
      else {
        if (i + 1 < seperate.length && seperate[i + 1].equals("(")) {
          int depth = 1;
          int j = i + 2;
          while (depth > 0 && j < seperate.length) {
            if (seperate[j].equals("(")) {
              depth += 1;
            }
            else if (seperate[j].equals(")")) {
              depth -= 1;
            }
            j += 1;
          }
          if (depth == 0) {
            lines.add(Arrays.copyOfRange(seperate, i, j));
            i += (j - i) - 1;
          }
          else {
            ErrorHandler.addError("Unmatched delimiter");
          }
        }
        else {
          if (i + 2 <= seperate.length){
            lines.add(Arrays.copyOfRange(seperate, i, i + 2));
            i += 1;
          }
          else {
            ErrorHandler.addError("FILE ENDED BEFORE COULD PARSE.");
          }
        }
      }
    }
    return lines;
    }
}
