package blatt04;
import java.util.Stack;


public class ForwardList<T> implements Iterable<T> {
	class Node {
		T data_;
		Node next_ = null;

		public Node(T data, Node next) {
			data_ = data;
			next_ = next;
		}
	}

	Node head_ = null;

	public ForwardList() {
	}

	public void push_front(T obj) {
		this.head_ = new Node(obj, this.head_);
	}

	public void backTraverse() {
		Stack<T> s = new Stack<T>();
		
		Node n = this.head_;
		while (n != null) {
			s.push(n.data_);
			n = n.next_;
		}
		
		while (!s.isEmpty())
			System.out.print(s.pop());
	}

	public class BackIterator implements java.util.Iterator<T> {
		Stack<T> stack;
		
		public BackIterator(Node n) {
			stack = new Stack<T>();
			while (n != null) {
				stack.push(n.data_);
				n = n.next_;
			}
		}
		
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			return stack.pop();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		// TODO: implementation
		
	}

	public BackIterator iterator() {
		return new BackIterator(this.head_);
	}

	public static void main(String[] args) {
		ForwardList<Integer> list = new ForwardList<Integer>();
		list.push_front(1);
		list.push_front(2);
		list.push_front(3);
		
		list.backTraverse();
		
		for (int el : list) {
			System.out.print(el);
		}
	}
}
