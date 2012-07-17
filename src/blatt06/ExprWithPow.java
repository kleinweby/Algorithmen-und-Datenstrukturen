package blatt06;
import aud.example.expr.Divide;
import aud.example.expr.ExpressionParser;
import aud.example.expr.ExpressionTree;
import aud.example.expr.Power;
import aud.example.expr.Times;
import aud.example.expr.Tokenizer;
import aud.util.DotViewer;

public class ExprWithPow extends ExpressionParser {

	// maybe overwrite methods
	
	@Override
	protected ExpressionTree product(int level) {
		verbose(level, "<my product>");

		ExpressionTree left = power(level + 1);

		if (lookahead() == Tokenizer.TIMES) {
			nextToken();
			ExpressionTree right = power(level + 1);
			return new ExpressionTree(new Times(), left, right);
		} else if (lookahead() == Tokenizer.DIVIDE) {
			nextToken();
			ExpressionTree right = power(level + 1);
			return new ExpressionTree(new Divide(), left, right);
		}

		return left;
	}

	public ExpressionTree power(int level) {
		verbose(level, "<power>");

		ExpressionTree left = factor(level + 1);

		if (lookahead() == Tokenizer.POWER) {
			nextToken();
			ExpressionTree right = factor(level + 1);
			return new ExpressionTree(new Power(), left, right);
		}

		return left;
	}

	public static void main(String[] args) {
		ExpressionParser parser = new ExprWithPow();
		parser.setVerbose(true);
		ExpressionTree tree = parser.parse("2+5^(3-2)*3");
		
		DotViewer.displayWindow(tree, "Power");
	}
}