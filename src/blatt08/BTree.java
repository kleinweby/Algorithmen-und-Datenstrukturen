package blatt08;

import aud.util.DotViewer;
import aud.util.Graphvizable;
import aud.util.SingleStepper;

public class BTree<Key extends Comparable<Key>> implements Graphvizable {
	KTreeNode<Key> root_;
	int m_;

	/** create an empty tree of order 2*m+1 */
	public BTree(int m) {
		root_ = new KTreeNode<Key>();
		m_ = m;
		assert (m_ > 0);
	}

	/** get order of tree (maximum number of children) */
	int getOrder() {
		return 2 * m_ + 1;
	}

	/** insert entry     
	@return {@code true} if {@code key} was not an entry of child before
	 */
	public boolean insert(Key key) {
		return insert(root_, key);
	}

	protected boolean insert(KTreeNode<Key> node, Key key) {
		int i;
		
		for (i = 1; i < node.getK(); i++) {
			Key k = node.getKey(i);
			int comparasionResult = k.compareTo(key);
			
			if (comparasionResult > 0)
				break;
			else if (comparasionResult == 0)
				return false;
		}
		
		i -= 1;
		
		if (i >= node.getK() || node.getChild(i) == null) {
			node.insert(key, i);
			split(node);
			return true;
		}
		else {
			return insert(node.getChild(i), key);
		}
	}

	public int height() {
		int height = 0;
		
		for (KTreeNode<Key> n = root_; n != null; height++, n = n.getChild(0));
		
		return height;
	}

	public static void main(String[] args) {
		int values[] = {7,19, 23, 4, 12, 17, 8, 11, 2 , 9, 13};
		
		SingleStepper s = new SingleStepper("");
		BTree<Integer> tree = new BTree<Integer>(2);
		DotViewer d = DotViewer.displayWindow(tree, "Tree");
		
		for (int v : values) {
			s.halt("Insert " + v);
			tree.insert(v);
			d.display(tree);
			System.out.println("Height " + tree.height());
		}
	}
	
	
	
	
	/** find key in tree     
	@return found key, note that this is generally a <em>different</em>
	instance than {@code key}! (We have "only" {@code compareKeys()==0}.)
	 */
	public Key find(Key key) {
		return root_.find(key);
	}

	public void split(KTreeNode<Key> node) {
		if (node.getK() > 2 * m_ + 1) { // CHEATING: we created and split a
			// (2*m+2)-node!
			node.split(m_ + 1);
			if (node.parent_ != null) {
				int i = node.parent_.getIndexOfChild(node);
				assert (i >= 0);
				node.parent_.mergeChild(i);
				node.parent_.checkConsistency();
				split(node.parent_); // eventually split parent
			}
		}
	}

	public String toDot() {
		return root_.toDot();
	}

	private String toString(KTreeNode<Key> _node) {
		String res = "";
		for (int i = 0; i < _node.getK(); ++i) {
			if (_node.getChild(i) != null) {
				res += toString(_node.getChild(i));
			}
		}
		return "[" + _node.toString() + " " + res + "] ";
	}

	public String toString() {
		return toString(root_);
	}
}
