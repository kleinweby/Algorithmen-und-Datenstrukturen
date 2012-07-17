package blatt07;
public class SimpleTree<T extends Comparable<T>>{
	public class Node {
		protected T key;
		protected Node left, right, parent;
		public Node(T x, Node _parent) {
			key = x;
			left = null;
			right = null;
			parent = _parent;
		}
		public T getKey() {
			return key;
		}

		public Node getLeft() {
			return left;
		}

		public String toString() {
			return key + " ";
		}

		public Node getRight() {
			return right;
		}
		
		public Node getParent() {
			return parent;
		}

		public void setLeft(Node l) {
			l.parent = this;
			left = l;
		}

		public void setRight(Node r) {
			r.parent = this;
			right = r;
		}

		public String inorder() {
			String result = "";
			if (left != null)
				result = left.inorder();
			result += key.toString() + "  ";
			if (right != null)
				result += right.inorder();
			return result;
		}
		protected void insert(T obj) {
			if (obj.compareTo(getKey()) < 0 ) {
				if (getLeft() == null) {
					setLeft(new Node(obj, this));
				}
				getLeft().insert(obj);
			} else if (obj.compareTo(getKey()) > 0) {
				if (getRight() == null) {
					setRight(new Node(obj, this));
				}
				getRight().insert(obj);
			}
		}
		protected boolean search(T obj) {
			if (obj.compareTo(getKey()) < 0) {
				if (getLeft() == null) {
					return false;  // not found
				}
				return getLeft().search(obj);
			} else if (obj.compareTo(getKey()) > 0) {
				if (getRight() == null) {
					return false;  // not found
				}
				return getRight().search(obj);
			}
			return true;
		}
	}
	private Node root;

	public SimpleTree() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Node getRoot() {
		return root;
	}

	public void insert(T obj) {
		if (isEmpty()) {
			root = new Node(obj, null);
		}
		root.insert(obj);
	}
	public boolean search(T obj) {
		if (isEmpty()) {
			return false;
		}
		return root.search(obj);
	}
	public String toString() {
		if (this.isEmpty()) return "null";
		return root.inorder();
	}

	public static void main(String[] args) {
		System.out.println("Test SimpleTree");
		SimpleTree<Integer> tree = new SimpleTree<Integer>();
		System.out.println("search(3) in empty Tree: " + tree.search(3));
		tree.insert(4);
		tree.insert(2);
		tree.insert(4);
		tree.insert(6);
		System.out.println("tree inorder: " + tree.toString());
		for(int i=0; i<=7; i++){			
		          System.out.println("search("+i+"): " + tree.search(i));
		}
	}
}