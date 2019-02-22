package lexer;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
public class Tokeniser {

  //remeber to cast before use
  //private Map<String, Token> keywords = new HashMap<String, Token>();

  private static String activeParameter;
  private static String[] keywords = {"PROC", "IF", "ELSE", "ENDIF", "THEN", "FORWARD", "LEFT", "RIGHT"};
  private static String[] comparisonOperators = {"==", "!=", ">", "<", "<=", ">="};
  private static String[] operators = {"+", "-", "/", "*"};


  public static boolean isActiveParameter(String item) {
    boolean isParameter = true;
    if (activeParameter != null){
      if(!activeParameter.equals(item)){
        isParameter = false;
      }
    }
    return isParameter;
  }

  public static void setActiveParameter(String item) {
    activeParameter = item;
  }

  public static String isKeyword(String word) {
    String found = isInList(word, keywords);
    return found;
  }

  //checks if an list contains an item
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

  public static boolean isMove(String word) {
    boolean hasMoveKeyword = (isInList(word, Arrays.copyOfRange(keywords, 5 ,8))).equals("none")? false: true;
    return hasMoveKeyword;
  }

  //checks a number is a valid integer
  public static boolean isInteger(String number) {
    boolean isNumber = false;
    try {
      //checks the number does not start with zero unless its value is zero
      if (number.charAt(0) == '0' && !number.equals("0")) {
        isNumber = false;
      }
      else {
        //checks if it can be processed as an integer
        int value = Integer.parseInt(number);
        isNumber = true;
      }
    }
    catch (NumberFormatException e) {
      ErrorHandler.addError("Cannot parse as Integer: " + number);
    }
    return isNumber;
  }


  public static boolean isComparisonOperator(String word) {
    boolean isOperand = isInList(word, comparisonOperators).equals("none")? false: true;
    return isOperand;
  }

  public static boolean isMathOperator(String word) {
    boolean isOperand = isInList(word, operators).equals("none")? false: true;
    return isOperand;
  }

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
}
