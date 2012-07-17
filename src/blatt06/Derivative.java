package blatt06;
import aud.BinaryTree;
import aud.example.expr.AtomicExpression;
import aud.example.expr.ExpressionParser;
import aud.example.expr.ExpressionTree;
import aud.example.expr.AtomicExpression.Type;
import aud.example.expr.Number;
import aud.example.expr.Plus;
import aud.example.expr.Symbol;
import aud.example.expr.Times;
import aud.util.DotViewer;


public class Derivative {

	public static ExpressionTree derivate(BinaryTree<AtomicExpression> t, String var) {
		if (t.getData().isTerminal()) {
			if (t.getData().getType() == Type.TSymbol) {
				Symbol s = (Symbol)t.getData();
				
				if (s.toString().equals(var)) {
					return new ExpressionTree(new Number(1));
				}
			}
			return new ExpressionTree(new Number(0));
		}
		else if (t.getData().getType() == AtomicExpression.Type.OpPlus ||
				t.getData().getType() == AtomicExpression.Type.OpMinus) {
			return new ExpressionTree(t.getData(), 
					derivate(t.getLeft(), var), 
					derivate(t.getRight(), var));
		}
		else if (t.getData().getType() == AtomicExpression.Type.OpTimes) {
			return new ExpressionTree(new Plus(),
					new ExpressionTree(new Times(), derivate(t.getLeft(), var), (ExpressionTree)t.getRight()),
					new ExpressionTree(new Times(),(ExpressionTree)t.getLeft(), derivate(t.getRight(), var)));
		}
		
		return null;
	}

	public static void main(String[] args) {
		ExpressionParser parser = new ExpressionParser();
		ExpressionTree tree = parser.parse("x - (1 + x - y)*(y + 5*y)");
		
		DotViewer.displayWindow(tree, "Normal");
		DotViewer.displayWindow(derivate(tree, "y"), "Derived");
	}

}
