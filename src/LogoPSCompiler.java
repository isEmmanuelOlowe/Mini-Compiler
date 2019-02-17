package logoCompiler;

import logoCompiler.lexer.Lexer;
import logoCompiler.parser.*;

/**
* The main class of the compiler program, will execute high level instructions.
*
* @param args only takes one argument which is the filename of logo file you whish to compile
*/
public class LogoPSCompiler {
  public static void main(String[] args) {

    String logFileName;
    String psFileName;

    if (args.length == 1) {
      logFileName = args[0];
      psFileName = logoFileName.split("\\.")[0] + ".ps";
      //Tokenises The whole logo file
      Parser.t = Lexer.lex(logoFileName);
      //checks if any errors occured in the parsing of the file.
      if (ErrorHandler.areErrors()) {
        //reports the errors occured to the user
        ErrorHandler.reportErrors();
      }
      //add some way to parse the file
      //only parse the file if no errors have occured
      else if(!Parser.error) {
        psPrologue();
        Parser.codeGen(psFileName);
        psEpilogue();
        Parser.printFinal();
      }
    }
    else {
      //USAGE Information for program
      System.out.println("USAGE: java LogoPSCompiler <logo_file>");
    }
  }

  public static void psPrologue() {
	    parser.add("%!PS-Adobe-3.0");	// Adobe header
	    /* rest of prologue ... */
	    parser.add("/Xpos    { 300 } def");
	    parser.add("/Ypos    { 500 } def");
	    parser.add("/Heading { 0   } def");
	    parser.add("/Arg     { 0   } def");
	    //Implementation of Right, Left and Forward procedures in PostScript
	    parser.add("/Right   {");
	    parser.add("Heading exch add Trueheading");
	    parser.add("/Heading exch def");
	    parser.add("} def");
	    parser.add("/Left {");
	    parser.add("Heading exch sub Trueheading");
	    parser.add("/Heading exch def");
	    parser.add("} def");
	    parser.add("/Trueheading {");
	    parser.add("360 mod dup");
	    parser.add("0 lt { 360 add } if");
	    parser.add("} def");
	    parser.add("/Forward {");
	    parser.add("dup  Heading sin mul");
	    parser.add("exch Heading cos mul");
	    parser.add("2 copy Newposition");
	    parser.add("rlineto");
	    parser.add("} def");
	    parser.add("/Newposition {");
	    parser.add("Heading 180 gt Heading 360 lt");
	    parser.add("and { neg } if exch");
	    parser.add("Heading  90 gt Heading 270 lt");
	    parser.add("and { neg } if exch");
	    parser.add("Ypos add /Ypos exch def");
	    parser.add("Xpos add /Xpos exch def");
	    parser.add("} def");
	  }

	  public static void psEpilogue() {
	    /* epilogue ... */
	    parser.add("Xpos Ypos moveto");
	    //Call Main to start
	    parser.add("MAIN");
	    parser.add("stroke");
	    parser.add("showpage");
	  }
}
