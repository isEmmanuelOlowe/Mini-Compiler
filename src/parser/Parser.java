package parser;

import lexer.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

/**
* Converts Token to PS file.
*/
public final class Parser {
  //stores all the methods that fun in the logo file
  public static ArrayList<PROCToken> t;
  //stores the different ps commands required to be written to the file
  public static ArrayList<String> lines = new ArrayList<String>();

  /**
  * Generates a list of all the PostScript commands from the token.
  */
  public static void codeGen(){

    //setups the hashmap which will allow for operator conversion
    PSDictionary.setup();
    for (PROCToken token: t) {
      token.printToken();
    }
  }

  /**
  * adds a line of ps to the array of commands that need to be written.
  *
  * @param line the line to be written
  */
  public static void add(String line){

    lines.add(line);
    //sets up hash map in PSDictionary
  }


  /**
  * print all of the ps commands to a file.
  *
  * @param fileName the name of the file to be written to
  */
  public static void printFinal(String fileName){

    try {
      PrintWriter writer = new PrintWriter(fileName);
      for (String line: lines) {
        writer.println(line);
      }
      writer.close();
    }
    catch (IOException ex) {
      System.out.println("Error Occured: " + ex.getMessage());
    }
  }
}
