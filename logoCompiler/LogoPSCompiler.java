package logoCompiler;

import logoCompiler.lexer.*;
import logoCompiler.parser.*;
/**
* The main class of the compiler program, will execute high level instructions.
*
* @param args only takes one argument which is the filename of logo file you whish to compile
*/
public class LogoPSCompiler {
  public static void main(String[] args) {

    String logoFileName;
    String psFileName;

    //Lexical Analyser is Object is created
    Lexer lexer = new Lexer();

    //Command line arguments are processed.
    if (args.length >= 1) {
      boolean seperated = true;
      logoFileName = args[0];
      if (args.length == 2 && args[1].equals("-n")){
        seperated = false;
      }
      psFileName = logoFileName.split("\\.")[0] + ".ps";
      //Tokenises The whole logo file
      //If the file is not found or there is some IOException lex returns null
      Parser.t = lexer.lex(logoFileName, seperated);
      //checks for file error
      //checks if any errors occured in the parsing of the file.
      if (ErrorHandler.areErrors() || Parser.t == null) {
        //reports the errors occured to the user
        ErrorHandler.reportErrors();
      }
      //add some way to parse the file
      //only parse the file if no errors have occured
      else {
        //adds the prologue to the top of commands to be written
        psPrologue();
        //adds only the methods in the PostScript file to be written
        Parser.codeGen();
        //adds the epilogue to the list of commands to be written
        psEpilogue();
        //prints the ps to a file
        Parser.printFinal(psFileName);
      }
    }
    else {
      //USAGE Information for program
      System.out.println("USAGE: java LogoPSCompiler <logo_file> [-n](- if commands are seperated by new lines)");
    }
  }

  public static void psPrologue() {
    Parser.add("%!PS-Adobe-3.0");	// Adobe header
    /* rest of prologue ... */
    Parser.add("/Xpos    { 300 } def");
    Parser.add("/Ypos    { 500 } def");
    Parser.add("/Heading { 0   } def");
    Parser.add("/Arg     { 0   } def");
    //Implementation of Right, Left and Forward procedures in PostScript
    Parser.add("/Right   {");
    Parser.add("Heading exch add Trueheading");
    Parser.add("/Heading exch def");
    Parser.add("} def");
    Parser.add("/Left {");
    Parser.add("Heading exch sub Trueheading");
    Parser.add("/Heading exch def");
    Parser.add("} def");
    Parser.add("/Trueheading {");
    Parser.add("360 mod dup");
    Parser.add("0 lt { 360 add } if");
    Parser.add("} def");
    Parser.add("/Forward {");
    Parser.add("dup  Heading sin mul");
    Parser.add("exch Heading cos mul");
    Parser.add("2 copy Newposition");
    Parser.add("rlineto");
    Parser.add("} def");
    Parser.add("/Newposition {");
    Parser.add("Heading 180 gt Heading 360 lt");
    Parser.add("and { neg } if exch");
    Parser.add("Heading  90 gt Heading 270 lt");
    Parser.add("and { neg } if exch");
    Parser.add("Ypos add /Ypos exch def");
    Parser.add("Xpos add /Xpos exch def");
    Parser.add("} def");
	  }

	  public static void psEpilogue() {
	    /* epilogue ... */
	    Parser.add("Xpos Ypos moveto");
	    //Call Main to start
	    Parser.add("MAIN");
	    Parser.add("stroke");
	    Parser.add("showpage");
	  }
}
