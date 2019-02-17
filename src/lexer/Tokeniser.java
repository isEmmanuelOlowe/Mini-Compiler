package logoCompiler.lexer;

import java.util.Map;
import java.util.HashMap;

public class Tokeniser {

  //remeber to cast before use
  //private Map<String, Token> keywords = new HashMap<String, Token>();

  private String[] keywords = {"PROC", "IF", "ELSE", "ENDIF", "THEN", "FORWARD", "LEFT", "RIGHT"};
  private String[] comparisonOperators = {"==", "!=", ">" "<", "<=", ">="};
  private String[] operators = {"+", "-", "/", "*"};
  public static void Tokeniser() throws InvalidToken {

  }

  public static Token procTokeniser() {

  }

  public static Token statementTokeniser(String[] line, String parameter) {
    //run calculation once
    if(isMove(line[0])){
      if(isCalculation(line, parameter, false)){

      }
    }
  }

  public static String isKeyword(String word) {
    String found = isInList(word, keywords);
    return found;
  }

  //checks if an list contains an item
  public static boolean isInList(String parameter, String list) {
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
    boolean hasMoveKeyword = (isInList(word, Arrays.copyOfRange(keywords, 5 ,7)))? true: false;
    return hasMoveKeyword;
  }

  public static boolean isInteger(String number) {
    isNumber = false;
    try {
      //checks if it can be processed as an integer
      int value = Integer.parseInt(number);

      this.statements.add(new StatementToken(number));
      isNumber = true;
    }
    catch (NumberFormatException e) {
      System.out.println("Cannot parse as Integer" + number);
    }
    return isNumber;
  }

  //can generalise for loop linear search to method
  public static boolean isComparisonOperator(String word) {
    String[] comparisonOperators = {"==", "!=", ">" "<", "<=", ">="};
    boolean isOperand = false;
    for (String operator: operators) {
      if (operator.equals(word)) {
        isOperand = true;
      }
    }
    return isOperand;
  }

  public static boolean isMathOperator(String word) {
    String[] operators = {"+", "-", "/"};
    boolean isOperand = false;
    for (String operator: operators) {
      if (operator.equals(word)) {
        isOperand = true;
      }
    }
    return isOperand;

  }

  //parameter checking required to be added.
  public static isMethod(String[] line) {

    boolean method = false;
    if (line[1].equals("(") && (line[3].equals("(") || line[5].equals(")"))){
      method = true;
    }
    return method;
  }


  //Implement Syntax checking
  public static boolean isIfStatement(String[] line, String parameter) {
    boolean isIfStatement = false;
    if (line[0].equals("IF") && line[4].equals("THEN")) {
      //You can call methods in if statement
      if (line[1].equals(parameter) && isNumber(line[3])) {
        if (isComparisonOperator(line[2])) {
          isIfStatement = true;
        }
      }
    }
    return isIfStatement;
  }

  //Implement Syntax checking
  public static boolean isCalculation(String[] line, String parameter, boolean isMethod) throw InvalidSyntax {
    boolean calculation = false;
    if (line[1].equals("(") ) {
      if (line[line.length() - 1]) {
        if (isInteger(line[4]) && isMathOperator(line[3])) {
          //need way to check if parameter
          calculation = true;
        }
      }
      else {
        throw new UnpairedDelimiterException("Missing - )");
      }
    }
  //checks user has used brackets w
  else if (line[1] "(" && isMethod(line)) {
    throw new InvalidMethodCall("( - Delimiter Expected");
  }
    return calculation;
  }
}
