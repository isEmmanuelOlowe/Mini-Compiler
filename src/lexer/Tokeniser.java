package logoCompiler.lexer;

import java.util.Map;
import java.util.HashMap;

public class Tokeniser {

  //remeber to cast before use
  //private Map<String, Token> keywords = new HashMap<String, Token>();

  private String[] keywords = {"PROC", "IF", "ELSE", "ENDIF", "THEN", "FORWARD", "LEFT", "RIGHT"};
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

    String foundKeyword = "none";
    for (String keyword: keywords) {
      if (keyword.equals(word)) {
        foundKeyword = keyword;
      }
    }
    return foundKeyword;
  }

  public static boolean isMove(String word) {

    boolean hasMoveKeyword = false;
     //checks is inclusive
    for (String keyword: Arrays.copyOfRange(keywords,  5, 7){
      if (keyword.equals(word)) {
        hasMoveKeyword = true;
      }
    }
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
    String[] operators = {"==", "!=", ">" "<", "<=", ">="};
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

  public static isMethod(String[] line) {

    boolean method = false;
    if (line[1].equals("(") && (line[3].equals("(") || line[5].equals(")"))){
      method = true;
    }
    return method;
  }


  //Implement Syntax checking
  public static isIfStatement(String[] line, String parameter) {
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
    if (line[1].equals("(") && (line[3].equals("(") || line[5].equals(")")) {
      if (isInteger(line[4]) && isMathOperator(line[3])) {
        //need way to check if parameter
        calculation = true;
      }
    }
  else if (isNumber(line[1]) && isMethod) {
    throw new InvalidSyntax("Invalid Statement");
  }
    return calculation;
  }
}
