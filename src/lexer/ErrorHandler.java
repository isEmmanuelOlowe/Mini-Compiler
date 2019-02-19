import java.lang.Exception;
import java.util.ArrayList;

public final class ErrorHandler {

  private static ArrayList<String> errorsOccured = ArrayList<String>;
  //The line number of the current line being read
  private static int currentLine = 0;
  private static String curentString;
  /**
  * Adds an error which has occured to the list of errors
  *
  * @param lineNumber the line number of the error which has occured
  * @param line the code sumbitted to the compiler
  * @param ErrorMessage the errror message that was reported
  */
  public static void addError(String ErrorMessage) {
    String error = ErrorMessage " - LINE " + currentLine ": " + currentString;
    ErrorsOccured.add(error);
  }

  /**
  * Reports in any errors occured during parsing.
  *
  * @return returns true if any errors did occur
  */
  public static boolean areErrors() {
    if (errorsOccured.size() > 1) {
      return true;
    }
  }

  /**
  * Reports all the errors which were reported to user.
  */
  public static void report Errors() {
    for (String error: errorsOccured) {
      System.out.println(error);
    }
  }

  public static void addString(String line) {
    //increments line number
    currentLine++;
    currentString = line;
  }

  public static void getCurrentLine() {
    return currentLine;
  }

  /*
  * Write method which:
  * - chekcs methods called exists
  * - chekck contains PROC MAIN
  */
}
