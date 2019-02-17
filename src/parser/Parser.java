package logoCompiler.parser;
import  logoCompiler.lexer.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public final class Parser {
  public static ProcToken[] t;
  public static boolean error = false;
  public static ArrayList<String> lines = new ArrayList<String>;

  //adds a line to lines of statements
  public static add(String line){
    lines.add(line);
    //sets up hash map in PSDictionary
    PSDictionary.setup();
  }

  //prints all the PostScript code to a file
  public static printFinal(fileName){
    try {
      PrintWriter writer = new PrintWriter(fileName);
      for (String line: lines) {
        writer.println(line);
      }
    }
    catch (IOException ex) {
      System.out.println("Error Occured" + ex.getMessage());
    }
  }
}
