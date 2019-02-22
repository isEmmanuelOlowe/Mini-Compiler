package lexer;

import java.lang.Exception;
import java.util.ArrayList;

public final class ErrorHandler {

  private static ArrayList<String> errorsOccured = new ArrayList<String>();
  private static ArrayList<String> methodCalls = new ArrayList<String>();
  private static ArrayList<String> methodDetails = new ArrayList<String>();
  private static ArrayList<String> PROCNames = new ArrayList<String>();
  //The line number of the current line being read
  private static int currentLine = 0;
  private static String currentString;
  /**
  * Adds an error which has occured to the list of errors
  *
  * @param lineNumber the line number of the error which has occured
  * @param line the code sumbitted to the compiler
  * @param ErrorMessage the errror message that was reported
  */

  public static void addName(String name){
    PROCNames.add(name);
  }

  public static void addError(String ErrorMessage) {
    String error = ErrorMessage +  " - LINE " + currentLine + ": " + currentString;
    errorsOccured.add(error);
  }

  public static void addMethodCall(String name){
    methodCalls.add(name);
    methodDetails.add("Line " + currentLine + ": " + currentString);
  }
  /**
  * Reports in any errors occured during parsing.
  *
  * @return returns true if any errors did occur
  */
  public static boolean areErrors() {
    //check that methods have corresponding method calls
    for (int i = 0; i < methodCalls.size(); i++) {
      //import list of proc tokens from
      //compare to declared methods
      String[] sPROCNames = new String [PROCNames.size()];
      sPROCNames = PROCNames.toArray(sPROCNames);
      if (Tokeniser.isInList(methodCalls.get(i), sPROCNames).equals("none")) {
        errorsOccured.add("The method called: " + methodCalls.get(i) + " has not been declared " + methodDetails.get(i));
      }
    }

    //
    boolean errors = false;
    if (errorsOccured.size() > 1) {
      errors = true;
    }
    return errors;
  }

  /**
  * Reports all the errors which were reported to user.
  */
  public static void reportErrors() {
    for (String error: errorsOccured) {
      System.out.println(error);
    }
  }

  public static void addString(String line) {
    //increments line number
    currentLine++;
    currentString = line;
  }

  public static int getCurrentLine() {
    return currentLine;
  }

  /*
  * Write method which:
  * - chekcs methods called exists
  * - chekck contains PROC MAIN
  */
}
