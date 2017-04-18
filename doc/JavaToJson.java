import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.github.haynesgt.intertool.java.*;

import java.lang.*;
import java.io.*;

public class JavaToJson {
  public static void main(String[] args) throws Exception {
    // Convert Java to Json!
    String inputFile = null;
    if ( args.length>0 ) inputFile = args[0];
    InputStream is = System.in;
    if ( inputFile!=null ) {
      is = new FileInputStream(inputFile);
    }
    ANTLRInputStream input = new ANTLRInputStream(is);
    JavaLexer lexer = new JavaLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    JavaParser parser = new JavaParser(tokens);
    JavaParser.CompilationUnitContext ctx = parser.compilationUnit();

    // System.out.println(parser.getTokenStream().getText());
    // System.out.println(ctx.toStringTree(parser));
    /*
    System.out.println(
      parser.getTokenStream().getText(
        ctx.getRuleContext(ParserRuleContext.class, 1)
      )
    );
    */
    // System.out.println(parser.getRuleNames()[ctx.getRuleIndex()]);
    ParseTreeWalker walker = new ParseTreeWalker();
    JavaToJsonListener extractor = new JavaToJsonListener(parser);
    walker.walk(extractor, ctx);
  }
}
