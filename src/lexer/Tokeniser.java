package lexer;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

/**
* Helper class with methods with static methds which are used across multiple classes.
*/
public class Tokeniser {

  private static String activeParameter;
  private static String[] keywords = {"PROC", "IF", "ELSE", "ENDIF", "THEN", "FORWARD", "LEFT", "RIGHT"};
  private static String[] comparisonOperators = {"==", "!=", ">", "<", "<=", ">="};
  private static String[] operators = {"+", "-", "/", "*"};


  /**
  * determines if identifier is identical to the formal parameter of the PROC method.
  *
  * @param item the current identifier being compared.
  * @return true if active parameter is identical.
  */
  public static boolean isActiveParameter(String item) {

    boolean isParameter = true;
    if (activeParameter != null){
      if(!activeParameter.equals(item)){
        isParameter = false;
      }
    }
    return isParameter;
  }

  /**
  * Changes the active parameter to the one being used in PROC method being processed currently.
  *
  * @param item sets a new active parameter.
  */
  public static void setActiveParameter(String item) {

    activeParameter = item;
  }

  /**
  * if parameter is a keyword.
  *
  * @param word the string identifier that is being determined to be a keyword.
  * @return the keyword found or "none" if it is not.
  */
  public static String isKeyword(String word) {

    String found = isInList(word, keywords);
    return found;
  }

  /**
  * checks if an list contains an item
  *
  * @param paramter the parameter that is being determined to be in the list.
  * @param list the list of items being searched.
  * @return the item that was found in the list or "none" if nothing was found.
  */
  public static String isInList(String parameter, String[] list) {

    //default value
    String found = "none";
    for (String item: list) {
      if (parameter.equals(item)) {
        found = item;
      }
    }
    return found;
  }

  /**
  * Determines if a movement statement being called is a type of move.
  *
  * @param word the item being compared to the movement types.
  * @return true if it is a move statement.
  */
  public static boolean isMove(String word) {

    boolean hasMoveKeyword = (isInList(word, Arrays.copyOfRange(keywords, 5 ,8))).equals("none")? false: true;
    return hasMoveKeyword;
  }

  /**
  * Determines if a string is a valid number.
  *
  * @param number the potential number being compared.
  * @return true if it is a valid number.
  */
  public static boolean isInteger(String number) {

    boolean isNumber = false;
    try {
      //checks the number does not start with zero unless its value is zero
      if (number.charAt(0) == '0' && !number.equals("0")) {
        isNumber = false;
      }
      else {
        //checks if it can be processed as an integer
        Integer.parseInt(number);
        isNumber = true;
      }
    }
    catch (NumberFormatException e) {
      ErrorHandler.addError("Cannot parse as Integer: " + number);
    }
    return isNumber;
  }

  /**
  * Determines if a item is a comparison operator.
  *
  * @param word the item being compared to a comparison operator.
  * @return true if it is a valid comparison operator.
  */
  public static boolean isComparisonOperator(String word) {

    boolean isOperand = isInList(word, comparisonOperators).equals("none")? false: true;
    return isOperand;
  }

  /**
  * Determines if a item is a mathematical operator.
  *
  * @param word the item being compared to a mathematical operator.
  * @return true if it is a valid mathematical operator.
  */
  public static boolean isMathOperator(String word) {

    boolean isOperand = isInList(word, operators).equals("none")? false: true;
    return isOperand;
  }

  /**
  * Determines if a calculation is valid
  *
  * @param line the calculation being processed.
  * @return true if it is a valid calculation.
  */
  public static boolean validCalculation(String[] line) {

    boolean valid = true;
    int noOpenBrackets = 0;
    int noCloseBrackets = 0;
    ArrayList<String> elements = new ArrayList<String>();

    for (String item: line) {
      if (item.equals("(")){
        noOpenBrackets++;
      }
      else if(item.equals(")")){
        noCloseBrackets++;
      }
      else {
        //adds all non-bracekts to arraylist for further processing
        elements.add(item);
      }
    }

    //caculations must have odd number of elements
    if(elements.size() %  2 == 1){
      for (int i = 0; i < elements.size(); i++) {
        if (i % 2 == 0){
          //cehcks wether the item is a parameter
          if (!isActiveParameter(elements.get(i))){
            if(!isInteger(elements.get(i))){
              valid = false;
              ErrorHandler.addError("Invalid number : " + elements.get(i));
            }
          }
        }
        else if (!isMathOperator(elements.get(i))){
          valid = false;
          ErrorHandler.addError("Invalid Operator: " + elements.get(i));
        }
      }
    }
    else {
      valid = false;
      ErrorHandler.addError("Invalid Calculation");
    }
    //makes sure all brackets are close in calculations
    if (noOpenBrackets != noCloseBrackets) {
      ErrorHandler.addError("EXISTS UNPAIRED Brackets");
      valid = false;
    }
    return valid;
  }

  /**
  * Converts a calculation to expression object.
  *
  * @param expression the string being converted to an expression.
  * @return Expression
  */
  public static Expression addExpression(String[] expression) {
    
    Expression compared;
    //if expression only contains one element it must be primary statement
    if (expression.length == 1) {
      compared = new PrimaryExpression(expression[0]);
    }
    else if(expression.length == 3 && expression[0].equals("(") && expression[2].equals(")")) {
      compared = new PrimaryExpression(expression[1]);
    }
    else {
      compared = new BinaryExpression(expression);
    }
    return compared;
  }
}
