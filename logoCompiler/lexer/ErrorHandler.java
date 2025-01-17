package logoCompiler.lexer;

import java.lang.Exception;
import java.util.ArrayList;

/**
* This class Handles Errors found when attempting compilation of the logo code.
* It has a ledger of all the errors with the errors which have occured when attempting analysis
*/

public final class ErrorHandler {

  //The ledger for errors which have occured
  private static ArrayList<String> errorsOccured = new ArrayList<String>();
  //For storing the methods which have been called to ensure methods which do not have PROC declarations are not called.
  private static ArrayList<String> methodCalls = new ArrayList<String>();
  //Stores corresponding data to methodCalls incase they have not been decalred so the error can been printed at line.
  private static ArrayList<String> methodDetails = new ArrayList<String>();
  //Stores the name of any PROC statements which have been declared.
  private static ArrayList<String> PROCNames = new ArrayList<String>();
  //The line number of the current line being read
  private static int currentLine = 0;
  //Stores the line data for the line currently being proceeded
  private static String currentString;

  /**
  * adds the name of the PROC method to list of PROC method names.
  *
  * @param name the name of PROC method
  */
  public static void addName(String name){

    PROCNames.add(name);
  }

  /**
  * Adds an error which has occured to the list of errors
  *
  * @param ErrorMessage the errror message that was reported
  */
  public static void addError(String ErrorMessage) {
    //constructs the error message that will be printed
    String error = ErrorMessage +  " - LINE " + currentLine + ": " + currentString;
    //add the error message to the list of errors
    errorsOccured.add(error);
  }

  /**
  * Adds the name of a called method to the list of called methods.
  *
  * @param name the name of the called method.
  */
  public static void addMethodCall(String name){
    //Adds the methods name to the list of Methods called in program.
    methodCalls.add(name);
    //Adds corresponsing information about the line in case of error.
    methodDetails.add("Line " + currentLine + ": " + currentString);
  }

  /**
  * Reports in any errors occured during lexical analysis.
  *
  * @return true if any errors did occur
  */
  public static boolean areErrors() {

    //check that methods have corresponding method calls
    for (int i = 0; i < methodCalls.size(); i++) {
      //import list of proc tokens from
      //compare to declared methods
      String[] sPROCNames = new String [PROCNames.size()];
      //Determines if any methods which do no exist have been called.
      sPROCNames = PROCNames.toArray(sPROCNames);
      if (Tokeniser.isInList(methodCalls.get(i), sPROCNames).equals("none")) {
        errorsOccured.add("The method called: " + methodCalls.get(i) + " has not been declared " + methodDetails.get(i));
      }
    }
    boolean errors = false;
    //checks no errors have occured.
    if (errorsOccured.size() >= 1) {
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

  /**
  * current line of code being edited.
  *
  * @param line the line being read.
  */
  public static void addString(String line) {

    //increments line number
    currentLine++;
    currentString = line;
  }

  /**
  * Retrieves the current line of file being read.
  *
  * @return the line number of current line being read.
  */
  public static int getCurrentLine() {

    return currentLine;
  }

  /*
  * Write method which:
  * - chekcs methods called exists
  * - chekck contains PROC MAIN
  */
}
