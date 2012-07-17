package blatt03;

public class MySList implements Iterable<Integer> {
	class Node {
		Integer _data;
		Node _next;
		
		public Node(Integer data) {
			this._data = data;
			this._next = null;
		}
	}
	
	public class Iterator implements java.util.Iterator<Integer> {
		Node _nextNode;
		
		private Iterator(Node _head) {
			this._nextNode = _head;
			
			// Iterate to the next odd one;
			while (this._nextNode != null && this._nextNode._data % 2 == 0)
				this._nextNode = this._nextNode._next;
		}
		
		@Override
		public boolean hasNext() {
			return this._nextNode != null;
		}

		@Override
		public Integer next() {
			Integer currentData = this._nextNode._data;
			
			// Go to the next element
			this._nextNode = this._nextNode._next;
			
			// Make sure it is odd
			while (this._nextNode != null && this._nextNode._data % 2 == 0)
				this._nextNode = this._nextNode._next;
			
			return currentData;
		}

		@Override
		public void remove() {
			// Do nothing
		}
	}
	
	Node _head;
	
	public MySList() {
		this._head = null;
	}
	
	@Override
	public Iterator iterator() {
		return new Iterator(this._head);
	}

	public void push_back(int obj) {
		if (this._head == null)
			this._head = new Node(obj);
		else {
			Node n = this._head;
			
			while(n._next != null)
				n = n._next;
			
			n._next = new Node(obj);
		}
	}
	
	@Override
	public String toString() {
		String description = "[";
		Node n = this._head;
		
		while (n != null) {
			description += n._data.toString();
			
			if (n._next != null)
				description += ",";
			
			n = n._next;
		}
		
		description += "]";
		
		return description;
	}
}
