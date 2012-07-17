package blatt05;
import aud.BinaryTree;


public class IntBinTree extends BinaryTree<Integer> {
	public IntBinTree(int data) {
		super(data);
	}

	public IntBinTree(int data, IntBinTree left, IntBinTree right) {
		super(data, left, right);
	}

	public int height() {
		int leftHeight = 0;
		int rightHeight = 0;
		
		try {
			leftHeight = ((IntBinTree)this.getLeft()).height();
		}
		catch (NullPointerException e)
		{}
		
		try {
			rightHeight = ((IntBinTree)this.getRight()).height();
		}
		catch (NullPointerException e)
		{}
		
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public int maxSum() {
		int leftSum = 0;
		int rightSum = 0;
		
		try {
			leftSum = ((IntBinTree)this.getLeft()).sum();
		}
		catch (NullPointerException e)
		{}
		
		try {
			rightSum = ((IntBinTree)this.getRight()).sum();
		}
		catch (NullPointerException e)
		{}
		
		return Math.max(leftSum, rightSum);
	}
	
	public int sum() {
		int leftSum = 0;
		int rightSum = 0;
		
		try {
			leftSum = ((IntBinTree)this.getLeft()).sum();
		}
		catch (NullPointerException e)
		{}
		
		try {
			rightSum = ((IntBinTree)this.getRight()).sum();
		}
		catch (NullPointerException e)
		{}
		
		return leftSum + rightSum + this.getData();
	}

	public int maxPath() {
		int leftPath = 0;
		int rightPath = 0;
		
		try {
			leftPath = ((IntBinTree)this.getLeft()).maxPath();
		}
		catch (NullPointerException e)
		{}
		
		try {
			rightPath = ((IntBinTree)this.getRight()).maxPath();
		}
		catch (NullPointerException e)
		{}
		
		return Math.max(leftPath, rightPath) + this.getData();
	}

	public static void main(String[] args) {
		IntBinTree six = new IntBinTree(6);
		IntBinTree eleven = new IntBinTree(-11);
		IntBinTree four = new IntBinTree(4, six, eleven);
		IntBinTree five = new IntBinTree(5);
		IntBinTree seven = new IntBinTree(7);
		IntBinTree one = new IntBinTree(1, five, seven);
		IntBinTree eight = new IntBinTree(-8, four, one);
		
		IntBinTree tree = eight;

		System.out.println("Height " + tree.height());
		System.out.println("Max Sum " + tree.maxSum());
		System.out.println("Max Path " + tree.maxPath());
	}
}

