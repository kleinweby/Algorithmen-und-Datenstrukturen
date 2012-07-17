package blatt04;
import aud.BinaryTree;
import aud.util.DotViewer;


public class BuildBinTree {
	public static void main(String[] args) {
		BinaryTree<Integer> six = new BinaryTree<Integer>(6);
		BinaryTree<Integer> eleven = new BinaryTree<Integer>(-11);
		BinaryTree<Integer> four = new BinaryTree<Integer>(4, six, eleven);
		BinaryTree<Integer> five = new BinaryTree<Integer>(5);
		BinaryTree<Integer> seven = new BinaryTree<Integer>(7);
		BinaryTree<Integer> one = new BinaryTree<Integer>(1, five, seven);
		BinaryTree<Integer> eight = new BinaryTree<Integer>(-8, four, one);
		
		BinaryTree<Integer> tree = eight;
		
		System.out.print("Preorder: ");
		
		for (BinaryTree<Integer> node : tree.preorder()) {
			System.out.print("" + node.getData() + ',');
		}
		
		System.out.print("\nPostorder: ");
		
		for (BinaryTree<Integer> node : tree.postorder()) {
			System.out.print("" + node.getData() + ',');
		}
		
		System.out.print("\nInorder: ");
		
		for (BinaryTree<Integer> node : tree.inorder()) {
			System.out.print("" + node.getData() + ',');
		}
		
		System.out.print("\nLeveorder: ");
		
		for (BinaryTree<Integer> node : tree.levelorder()) {
			System.out.print("" + node.getData() + ',');
		}
		
		DotViewer.displayWindow(tree, "My tree");
	}
}
