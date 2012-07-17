package blatt05;
import aud.BinaryTree;
import aud.example.expr.AtomicExpression;
import aud.example.expr.ExpressionParser;
import aud.example.expr.ExpressionTree;
import aud.util.DotViewer;


public class ExampleExpression {
	
	
	public static void main(String[] args) {
		ExpressionParser parser = new ExpressionParser();
		ExpressionTree tree = parser.parse("2/2+3*(8+3/2)");
				
		System.out.print("Inorder ");// Note brackets are missing
		for (BinaryTree<AtomicExpression> s: tree.inorder()) {
			System.out.print(s.getData());
		}
		System.out.print("\nPreorder ");
		for (BinaryTree<AtomicExpression> s: tree.preorder()) {
			System.out.print(s.getData());
		}
		System.out.print("\nPostorder ");
		for (BinaryTree<AtomicExpression> s: tree.postorder()) {
			System.out.print(s.getData());
		}
		
		// Would be to calculate the value
		// Sadly a bug causes that getValue is not visible
		// System.out.println("Value " + tree.getValue());
		
		DotViewer.displayWindow(tree, "");
	}
}
