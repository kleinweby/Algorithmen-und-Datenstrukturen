package blatt05;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aud.BinaryTree;

public class MyBinTree<T> extends BinaryTree<T> {
	public MyBinTree(T data) {
		super(data);
	}

	public MyBinTree(T data, MyBinTree<T> left, MyBinTree<T> right) {
		super(data, left, right);
	}

	public int maxWidth() {
		List<Integer> widths = this.widths();
		
		Collections.sort(widths);
		
		return widths.get(widths.size() - 1);
	}
	
	public List<Integer> widths() {
		List<Integer> widths = new ArrayList<Integer>();
		List<Integer> leftWidths = new ArrayList<Integer>();
		List<Integer> rightWidths = new ArrayList<Integer>();
		
		{
			int width = 0;

			if (this.getLeft() != null) {
				width++;
				leftWidths = ((MyBinTree<T>)this.getLeft()).widths();
			}
			if (this.getRight() != null) {
				width++;
				rightWidths = ((MyBinTree<T>)this.getRight()).widths();
			}
			
			widths.add(width);
		}
		
		for (int i = 0; i < Math.max(leftWidths.size(), rightWidths.size()); i++) {
			int width = 0;
			
			if (i < leftWidths.size())
				width += leftWidths.get(i);
			if (i < rightWidths.size())
				width += rightWidths.get(i);
			
			widths.add(width);
		}
		
		return widths;
	}

	public static void main(String[] args) {
		MyBinTree<Integer> eleven = new MyBinTree<Integer>(-11);
		MyBinTree<Integer> four = new MyBinTree<Integer>(4, null, eleven);
		MyBinTree<Integer> five = new MyBinTree<Integer>(5);
		MyBinTree<Integer> seven = new MyBinTree<Integer>(7);
		MyBinTree<Integer> one = new MyBinTree<Integer>(1, five, seven);
		MyBinTree<Integer> eight = new MyBinTree<Integer>(-8, four, one);
		
		MyBinTree<Integer> tree = eight;
		
		System.out.println("Widths " + tree.widths());
		System.out.println("Max width " + tree.maxWidth());
	}
}
