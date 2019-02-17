package logoCompiler;

import logoCompiler.lexer.Lexer;
import logoCompiler.parser.*;

/**
* The main class of the compiler program, will execute high level instructions.
*
* @param args only takes one argument which is the filename of logo file you which to compile
*/
public class LogoPSCompiler {
  public static void main(String[] args) {

    if (args.length == 1) {
      String logFileName = args[0];
    }
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
      //Codegen disabled
      //prog.codegen();
      psEpilogue();
    }
  }

  public static void psPrologue() {
	    writer.println("%!PS-Adobe-3.0");	// Adobe header
	    /* rest of prologue ... */
	    writer.println("/Xpos    { 300 } def");
	    writer.println("/Ypos    { 500 } def");
	    writer.println("/Heading { 0   } def");
	    writer.println("/Arg     { 0   } def");
	    //Implementation of Right, Left and Forward procedures in PostScript
	    writer.println("/Right   {");
	    writer.println("Heading exch add Trueheading");
	    writer.println("/Heading exch def");
	    writer.println("} def");
	    writer.println("/Left {");
	    writer.println("Heading exch sub Trueheading");
	    writer.println("/Heading exch def");
	    writer.println("} def");
	    writer.println("/Trueheading {");
	    writer.println("360 mod dup");
	    writer.println("0 lt { 360 add } if");
	    writer.println("} def");
	    writer.println("/Forward {");
	    writer.println("dup  Heading sin mul");
	    writer.println("exch Heading cos mul");
	    writer.println("2 copy Newposition");
	    writer.println("rlineto");
	    writer.println("} def");
	    writer.println("/Newposition {");
	    writer.println("Heading 180 gt Heading 360 lt");
	    writer.println("and { neg } if exch");
	    writer.println("Heading  90 gt Heading 270 lt");
	    writer.println("and { neg } if exch");
	    writer.println("Ypos add /Ypos exch def");
	    writer.println("Xpos add /Xpos exch def");
	    writer.println("} def");
	  }

	  public static void psEpilogue() {
	    /* epilogue ... */
	    writer.println("Xpos Ypos moveto");
	    //Call Main to start
	    writer.println("MAIN");
	    writer.println("stroke");
	    writer.println("showpage");
	  }
}
