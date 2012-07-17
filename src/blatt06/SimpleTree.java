package blatt06;
import java.util.Stack;

public class SimpleTree<T extends Comparable<T>> {
	protected class Node {
		public T data;
		public Node left;
		public Node right;
		
		Node(T _data) {
			this.data = _data;
		}
		
		boolean isLeaf() {
			return this.left == null && this.right == null;
		}
	}
	
	private Node _root;
	
	public SimpleTree() {
		
	}
	
	public void insert(T obj) {
		if (this._root == null)
			this._root = new Node(obj);
		else {
			insertUnderneath(obj, this._root);
		}
	}
	
	private void insertUnderneath(T obj, Node node) {
		int comparasionResult = node.data.compareTo(obj);
		if (comparasionResult < 0) {
			if (node.left == null)
				node.left = new Node(obj);
			else 
				insertUnderneath(obj, node.left);
		}
		else if (comparasionResult > 0) {
			if (node.right == null)
				node.right = new Node(obj);
			else 
				insertUnderneath(obj, node.right);
		}
	}
	
	public boolean search(T obj) {
		if (this._root == null)
			return false;
		
		return searchUnderneath(obj, this._root);
	}
	
	private boolean searchUnderneath(T obj, Node node) {
		int comparasionResult = node.data.compareTo(obj);
		if (comparasionResult < 0) {
			if (node.left == null)
				return false;
			else 
				return searchUnderneath(obj, node.left);
		}
		else if (comparasionResult > 0) {
			if (node.right == null)
				return false;
			else 
				return searchUnderneath(obj, node.right);
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		String s = "[";
		Stack<Node> stack = new Stack<Node>();
		boolean downwards = true;
		
		stack.push(this._root);
		
		while (!stack.isEmpty()) {
			Node n = stack.peek();
			
			if (downwards) {
				if (n.right != null) {
					stack.push(n.right);
				}
				else {
					downwards = false;
				}
			}
			else {
				n = stack.pop();
				s += n.data.toString() + ",";
				if (n.left != null) {
					stack.push(n.left);
					downwards = true;
				}
			}
		}
		
		s += "]";
		return s;
	}
	
	private static void testSearch(SimpleTree<Integer> tree) {
		int numbers[] = {-1, 1, 2, 3};
		
		System.out.print("Contains: ");
		for (int n : numbers) {
			if (tree.search(n))
				System.out.print(n + ",");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		SimpleTree<Integer> tree = new SimpleTree<Integer>();
		
		tree.insert(1);
		testSearch(tree);
		System.out.println(tree);
		tree.insert(3);
		testSearch(tree);
		System.out.println(tree);
		tree.insert(-1);
		testSearch(tree);
		System.out.println(tree);
		tree.insert(2);
		testSearch(tree);
		System.out.println(tree);
	}
}
