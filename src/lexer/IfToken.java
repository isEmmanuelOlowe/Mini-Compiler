package lexer;

import parser.Parser;
import parser.PSDictionary;
import parser.PSDictionary;
import java.util.Arrays;
import java.util.ArrayList;

/**
* Tokenised form of IfStatements in Logo
*/
public final class IfToken extends Token {
  private Expression compared1;
  private Expression compared2;
  private String operator;
  private boolean isMetComplete = false;
  private boolean isFailedComplete = false;
  private ArrayList<Token> conditionMet = new ArrayList<Token>();
  private ArrayList<Token> conditionFailed = new ArrayList<Token>();

  /**
  * Converts a line to If statement token.
  *
  * @param line the current line being processed
  */
  public IfToken(String line[]) {

    if (isValidIf(line)) {
      int operatorIndex = findComparison(line);
      this.operator = line[operatorIndex];
      //first index is ignored since it equals "IF"
      String[] sCompared1 = Arrays.copyOfRange(line, 1, operatorIndex);
      this.compared1 = Tokeniser.addExpression(sCompared1);
      //last index is ignored since it equals "THEN"
      String[] sCompared2 = Arrays.copyOfRange(line, operatorIndex + 1, line.length - 1);
      this.compared2 = Tokeniser.addExpression(sCompared2);
    }
  }

  /**
  * Determines if an IF statement is valid.
  *
  * @param line the line of file being tokenised.
  * @return true if the if statement is valid.
  */
  private boolean isValidIf(String[] line) {

    boolean valid = true;
    //First index must be if to enter this proc so it doesnt need to be verified
    if(line[line.length - 1].equals("THEN")) {
      String operator;
      //for checking there is only one comparison operator
      int counter = 0;
      for (String item: line) {
        if (Tokeniser.isComparisonOperator(item)){
          counter += 1;
        }
      }
      if(counter != 1) {
        ErrorHandler.addError("LOGO only supports one comparison operator");
        valid = false;
      }
    }
    else {
      ErrorHandler.addError("IF statement must end in then");
      valid = false;
    }
    return valid;
  }

  /**
  * Finding the comparison operator in the line of code being tokenised.
  *
  * @param line the line of code being tokenised.
  * @return the index of the comparison operator
  */
  private int findComparison(String[] line) {

    //will store index of comparison operator
    int index = -1;
    for (int i = 0; i < line.length; i++) {
      if (Tokeniser.isComparisonOperator(line[i])) {
        index = i;
      }
    }
    return index;
  }

  /**
  * Determines if else statement has been called before if statement is ended.
  */
  private void isElseFound() {
    
    if(!isMetComplete) {
      ErrorHandler.addError("IF Statements Must contain ELSE command");
    }
    this.isFailedComplete = true;
  }

  /**
  * Adds statements to the If token.
  *
  * @param line the line of code being read.
  */
  public void addStatement(String line[]) {
    //checks which part of the if statement they shall be declared to
    if(!isMetComplete) {
      newStatement(line, conditionMet);
    }
    else {
      newStatement(line, conditionFailed);
    }
  }

  /**
  * Adds a new statement to a list of statements
  *
  * @param line the line of code being tokenised
  * @param statements the statement set that the new tokenised statement is being added to.
  */
  private void newStatement(String line[], ArrayList<Token> statements) {

    //determines if the last statement is an if token
    if (!this.lastIfCompleted(statements)) {
      //adds the statement to the last incomplete if statement
      IfToken lastIfToken = (IfToken) statements.get(statements.size() - 1);
      lastIfToken.addStatement(line);
    }
    else if (line[0].equals("ELSE")) {
      if (line.length != 1) {
        ErrorHandler.addError("Unexpected Tokens");
      }
      this.isMetComplete = true;
    }
    else if (line[0].equals("ENDIF")){
      if (line.length != 1) {
        ErrorHandler.addError("Unexpected Tokens");
      }
      isElseFound();
    }
    //checks if current line is an if statement
    else if (line[0].equals("IF")) {
      statements.add(new IfToken(line));
    }
    //checks if current line is a movement statement or method call
    else {
      statements.add(new StatementToken(line));
    }
  }

  /**
  * Determines if last if statement within if token was completed.
  *
  * @param statements the statement list that is being checked for if statements.
  * @return if the last IfStatements is the list statements was completed.
  */
  private boolean lastIfCompleted(ArrayList<Token> statements) {

    boolean complete = true;
    if(statements.size() >= 1){
      if(statements.get(statements.size() - 1) instanceof IfToken){
        IfToken lastIf = (IfToken) statements.get(statements.size() - 1);
        if(!lastIf.isComplete()){
          complete = false;
        }
      }
    }
    return complete;
  }

  /**
  * Whether last statement in if token was completed.
  *
  * @return true if last if statement was completed.
  */
  public boolean isComplete(){

    return isFailedComplete;
  }

  /**
  * in the event an error occurs in the compilation of the if statement.
  */
  public void invalid(){

    isFailedComplete = true;
  }

  /**
  * Adds the to PostScript form to list of items that are require to be printed.
  */
  public void printToken(){

    this.compared1.print();
    this.compared2.print();
    Parser.add(PSDictionary.convertToPSOperator(this.operator));
    Parser.add("{");
    for (Token met: conditionMet) {
      met.printToken();
    }
    Parser.add("} {");
    for (Token failed: conditionFailed) {
      failed.printToken();
    }
    Parser.add("} ifelse");
  }
}
