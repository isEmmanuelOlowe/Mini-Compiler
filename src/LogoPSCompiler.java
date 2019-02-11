package logoCompiler;

import logoCompiler.lexer.*;
import logoCompiler.parser.*;
import java.io.*;

public class LogoPSCompiler {
  public static void main(String[] args) {


    FileReader logoFile = new FileReader(args[0]);

    //PrintWriter writer = new

    Parser.t = Lexer.lex(logoFile);
    //Parsing disabled
    //Prog prog = Prog.parse();

    if (!Parser.error) {
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
