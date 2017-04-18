import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.Interval;
import com.github.haynesgt.intertool.java.*;

public class JavaToJsonListener extends JavaBaseListener {
  JavaParser parser;
  int indentation;
  public JavaToJsonListener(JavaParser parser) {
    this.parser = parser;
    this.indentation = 0;
  }
	@Override public void enterEveryRule(ParserRuleContext ctx) {
    this.printIndentation();
    System.out.println("{");

    this.indentation++;

    this.printIndentation();
    System.out.println(
        "\"type\": \"rule\","
    );

    this.printIndentation();
    System.out.println(
        "\"name\": \"" +
        this.parser.getRuleNames()[ctx.getRuleIndex()] +
        "\","
    );

    if (ctx.getChildCount() > 0) {
      this.printIndentation();
      System.out.println(
          "\"children\": ["
      );
    }

    this.indentation++;

  }
	@Override public void exitEveryRule(ParserRuleContext ctx) {
    this.indentation--;

    if (ctx.getChildCount() > 0) {
      this.printIndentation();
      System.out.println("]");
    }

    this.indentation--;

    this.printIndentation();
    System.out.println("},");
  }

  @Override
  public void visitTerminal(TerminalNode node) {
    this.printIndentation();
    System.out.println("{");

    this.indentation++;

    this.printIndentation();
    System.out.println("\"type\": \"terminal\",");

    this.printIndentation();
    System.out.println("\"text\": \"" + node.getSymbol().getText() + "\"");

    this.indentation--;

    this.printIndentation();
    System.out.println("},");
  }

  private void printIndentation() {
    int i;
    for (i = 0; i < indentation; i++) {
      System.out.print("  ");
    }
  }
}

