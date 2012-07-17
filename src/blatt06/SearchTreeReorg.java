package blatt06;
import java.util.ArrayList;
import java.util.List;

import aud.BinarySearchTree;
import aud.util.DotViewer;

public class SearchTreeReorg<Key extends Comparable<Key>, Value> extends
		BinarySearchTree<Key, Value> {
		
	public SearchTreeReorg() {
		super();
	}

	public String levelOrder() {
		// do not change because of backend-control
		return head_.getRight().levelorder().toString();
	}

	public SearchTreeReorg<Key,Value> reorganize() {
	   List<Cursor> list = new ArrayList<Cursor>();
	   SearchTreeReorg<Key, Value> newTree = new SearchTreeReorg<Key, Value>();
	   
	   for (Cursor c : this) {
		   list.add(c);
		   System.out.print(c.getKey() + ",");
	   }
	   System.out.println();
	   
	   rebuildFromList(list, newTree);
	   
	   return newTree;
	}
	
	private void rebuildFromList(List<Cursor> list, SearchTreeReorg<Key,Value> tree) {
		int middle = (list.size() - 1)/2;
		
		if (list.size() < 1)
			return;

		tree.insert(list.get(middle).getKey(), list.get(middle).getValue());
		System.out.print("Middle " + middle + " Size " + list.size() + " List ");
		for (Cursor c : list)
			System.out.print(c.getKey() + ",");
		System.out.println("Inserted " + list.get(middle).getKey());
		if (0 < middle) {
			rebuildFromList(list.subList(0, middle), tree);
		}
		if (middle + 1 < list.size()) {
			rebuildFromList(list.subList(middle + 1, list.size()), tree);
		}
	}

	public static void main(String[] args) {
		SearchTreeReorg<Integer, Integer> tree = new SearchTreeReorg<Integer, Integer>();
		
		tree.insert(20, 20);
		tree.insert(5, 5);
		tree.insert(11, 11);
		tree.insert(10, 10);
		tree.insert(14, 14);
		tree.insert(17, 17);
		tree.insert(25, 25);
		tree.insert(22, 22);
		tree.insert(27, 27);
		tree.insert(30, 30);
		tree.insert(33, 33);
		
		DotViewer.displayWindow(tree, "Before");
//		tree.reorganize();
		DotViewer.displayWindow(tree.reorganize(), "After");
	}
}
