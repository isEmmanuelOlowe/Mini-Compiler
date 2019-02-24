package lexer;

import java.util.Arrays;
import parser.Parser;
import parser.PSDictionary;

/**
* Turns string calculations into binary expressions.
*/
public final class BinaryExpression extends Expression {

  //the name of the operator being stored
  private String operator;
  //The expression left of operator
  private Expression comparedLeft;
  //The expression right of operator
  private Expression comparedRight;

  /**
  * Converts calcualtion to Binary Expression Tree.
  *
  * @param line the line of calculation.
  */
  public BinaryExpression(String line[]) {

    /*
    * conditions[0] returns the index location of operator.
    * conditions[1] returns the depth of mathematical statment in brackets so they
    can be ignored.
    e.g. ( ( ( 4 * 5 ) ) ) depth willl be 3
    */
    int[] conditions = findOperator(line);
    //sets the operator
    operator = line[conditions[0]];
    //returns expressions left and right of operator removing unneccesary brackets
    String[] left = Arrays.copyOfRange(line, conditions[1], conditions[0]);
    String[] right = Arrays.copyOfRange(line, conditions[0] + 1, line.length - conditions[1]);
    //creates new expression
    comparedLeft = newExpression(left);
    comparedRight = newExpression(right);
  }

  /**
  * adds the expression to list of items that needs to be printed.
  * returns expression in a stack based order using recursion.
  */
  public void print() {

    //adds both left and right expression to list of items that needs to be printed
    comparedLeft.print();
    comparedRight.print();
    //adds the operator to list of items to list of things that need to be printed
    Parser.add(PSDictionary.convertToPSOperator(this.operator));
  }

  /**
  * Gets the index and depth of operator in mathematical statement.
  *
  * @param line the split line of calculation
  * @return the location of operator and its depth in brackets.
  */
  private int[] findOperator(String line[]) {

    boolean found = false;
    int index = 0;
    //give a default value to lowest
    int lowest = -1;
    int ignore = 0;
    /*for when the statement is enclosed in brackets so lowest precedence
    * operator cannot be found.
    */
    int depth = 0;
    //loops until it found the lowest index and index is at the end of valid statements
    while (found == false || index != (line.length - depth)) {
      //ignores statements in brackets
      if (line[index].equals("(")) {
        //increases level of ignorance
        ignore += 1;
      }
      if (line[index].equals(")")) {
        //reduces level of ignorance
        ignore -= 1;
      }
      //only considers statements which arent being ignore
      if (ignore == 0) {
        //sets lowest operator to the first operator found
        if (lowest == -1) {
          if (precedence(line[index]) != 5) {
            lowest = index;
            found = true;
          }
        }
        //checks to see if current operator is lower than the lowest operator
        else if (lowest(line[lowest], line[index])) {
          lowest = index;
        }
      }
      index++;
      //if no operator if found then valid mathematical statements must be in brackets
      //so increases depth
      if (found == false && index == (line.length - depth)){
        depth++;
        index = depth;
      }
    }
    //returns the lowest precedence operator and depth of statements
    int[] data = {lowest, depth};
    return data;
  }

  /**
  * determines which two operators have the lowest precedence.
  *
  * @param currentLow the current lowest precedence operator.
  * @param operator the operator being compared.
  * @return returns true if the operator being compared is lower than the lowest.
  */
  private boolean lowest(String currentLow, String operator) {

    boolean newLow = false;
    if (precedence(currentLow) > precedence(operator)) {
      newLow = true;
    }
    return newLow;
  }

  /**
  * creates new expressions dependent on their length.
  *
  * @param expression the string calculation segement being converted to an expression.
  * @return Expression object which has been created.
  */
  private static Expression newExpression(String[] expression) {

    Expression compared;
    //if expression only contains one element it must be primary statement
    if (expression.length == 1) {
      compared = new PrimaryExpression(expression[0]);
    }
    else {
      compared = new BinaryExpression(expression);
    }
    return compared;
  }

  /**
  * give operators precedence a numerical values.
  *
  * @param operator the operator being assigned a value.
  * @return numberal value fo precedence.
  */
  private static int precedence(String operator) {

    int value;
    if (operator.equals("+")) {
      value = 1;
    }
    else if (operator.equals("-")) {
      value = 2;
    }
    else if (operator.equals("*")) {
      value = 3;
    }
    else if (operator.equals("/")) {
      value = 4;
    }
    else {
      value = 5;
    }
    return value;
  }
}
