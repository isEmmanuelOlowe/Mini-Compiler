package lexer;

import java.util.ArrayList;
import parser.Parser;

/**
* Describes Tokenised form of PROC statement
*/
public final class PROCToken extends Token {

  private String name;
  private String parameter;
  private boolean userDefined;
  private ArrayList<Token> statements = new ArrayList<Token>();
  //private ArrayList<String> methods = new ArrayList<String>();
  //for in the event the class is dumb InvalidToken
  private boolean isInvalid;

  /**
  * Identifies a PROC Token and determines it is of a valid form.
  *
  * @param line the current line of Logo file being read
  */
  public PROCToken(String[] line) {

    if (isValidProc(line)) {
      //if isValidProc doesnt throw an exception then these parameters are set
      this.name = line[1];
      this.parameter = line[3];
      this.isInvalid = true;
      Tokeniser.setActiveParameter(this.parameter);
      ErrorHandler.addName(this.name);
    }
    else {
      Tokeniser.setActiveParameter(null);
    }
  }

  /**
  * Adds a statement to the statements contained within a method in PROC method.
  *
  * @param line the line of logo being read containing the statement
  */
  public void addStatement(String[] line) {

    //determines if the last statement is an if token
    if (!this.lastIfCompleted()) {
      //adds the statement to the last incomplete if statement
      IfToken lastIfToken = (IfToken) this.statements.get(this.statements.size() - 1);
      lastIfToken.addStatement(line);
    }
    //checks if current line is an if statement
    else if (line[0].equals("IF")) {
      this.statements.add(new IfToken(line));
    }
    //checks if current line is a movement statement or method call
    else {
      this.statements.add(new StatementToken(line));
    }
  }

  /**
  * Determines if identifier is in a valid format.
  *
  * @param ident the string of identifier being used.
  * @return true if identifier is valid.
  */
  private static boolean checkIdentifier(String ident) {
    //checks first character is in set of letters and following can be numbers and letters
    boolean valid = ident.matches("[a-zA-Z][\\w]*");
    ErrorHandler.addError("The identifier '" + ident + "' does not follow Logo Grammar Naming Conventions.");
    return valid;
  }

  /**
  * determines if the syntax of PROC Statement is correct.
  *
  * @param line the line being tokenised.
  * @return true if proc statement was in correct format
  */
  private boolean isValidProc(String[] line) {

    boolean valid = true;
    //to prevent array out of bounds errors
    if (line.length < 5) {
      ErrorHandler.addError("Missing Elements to PROC declaration");
      valid = false;

    }
    //checks if the first line of program is method
    else if(!line[0].equals("PROC")) {
      ErrorHandler.addError("First Line must be contain PROC method");
      valid = false;
    }

    //checks for expected brackets in method
    else if (line[2].equals("(")) {
      if (!line[4].equals(")")) {
        ErrorHandler.addError(") - Bracket expected");
        valid = false;
      }
    }
    else {
      ErrorHandler.addError("( - Bracket expected");
      valid = false;
    }
    //checks the identifiers
    if (valid) {
      if(!checkIdentifier(line[1]) || !checkIdentifier(line[3])){
        valid = false;
      }
    }
    //checks the proc method is the correct length
    if (line.length != 5) {
      ErrorHandler.addError("Unexpected Tokens");
      valid = false;
    }
    return valid;
  }

  /**
  *checks the last if statement is complete
  *
  * @return true if the last if statement was correctly completed.
  */
  private boolean lastIfCompleted() {

    boolean complete = true;
    if (this.statements.size() >= 1) {
      if (this.statements.get(this.statements.size() - 1) instanceof IfToken) {
        IfToken lastIf = (IfToken) this.statements.get(this.statements.size() - 1);
        if (!lastIf.isComplete()) {
          complete = false;
        }
      }
    }
    return complete;
  }

  /**
  * Determines if the last if statement in the object has been close and
  if it has not then it adds an error that an unended if statement has been found.
  */
  public void close() {

    if (!lastIfCompleted()) {
      ErrorHandler.addError("If Statement in previous PROC METHOD incomplete");
    }
  }

  /**
  * Formats the Token to that of the form in PostScript.
  * Adds result to list of items to be printed.
  */
  public void printToken() {

      Parser.add("/" + name + " {");
      if (!this.name.equals("MAIN")) {
        Parser.add("1 dict begin");
        Parser.add("/" + this.parameter + " exch def");
      }
      //insert method to declare paraameter in method
      for (Token token: statements) {
        token.printToken();
      }
      if (!this.name.equals("MAIN")) {
        Parser.add("end");
      }
      Parser.add("} def");
  }

}
