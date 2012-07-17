package blatt07;
import java.util.Iterator;

public class RangeTree<T extends Comparable<T>> extends SimpleTree<T> implements
		Iterable<T> {
	public T begin_, end_;

	public RangeTree(T b, T e) {
		super();
		begin_ = b;
		end_ = e;
	}

	public void setRange(T b, T e) {
		// Do not change (used for backend control)
		begin_ = b;
		end_ = e;
	}

	public class TreeIterator implements Iterator<T> {
		T _end;
		Node _nextNode;
		boolean _downwards;
		
		public TreeIterator(RangeTree<T> rangeTree) {
			this._end = rangeTree.end_;
			
			this._nextNode = rangeTree.getRoot();
			
			while (true) {
				int result = this._nextNode.getKey().compareTo(rangeTree.begin_);
				if (result < 0) {
					if (this._nextNode.getRight() == null) {
						if (this._nextNode.getParent() != null)
							this._nextNode = this._nextNode.getParent();
						break;
					}
					else {
						this._nextNode = this._nextNode.getRight();
					}
				}
				else if (result > 0) {
					if (this._nextNode.getLeft() == null) {
						break;
					}
					else {
						this._nextNode = this._nextNode.getLeft();
					}
				}
			}
		}

		@Override
		public boolean hasNext() {
			return this._nextNode != null;
		}

		@Override
		public T next() {
			T data = this._nextNode.getKey();
			
			if (this._downwards) {
				if (this._nextNode.getRight() != null) {
					this._nextNode = this._nextNode.getRight();
				}
				else {
					this._downwards = false;
				}
			}
			else {
				this._nextNode = this._nextNode.getParent();

				if (this._nextNode.getLeft() != null) {
					this._nextNode = this._nextNode.getLeft();
					this._downwards = true;
				}
			}
			
			return data;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
		// TODO: implementation

	}

	public Iterator<T> iterator() {
		return new TreeIterator(this);
	}

	public static void main(String[] args) {
		// TODO: test
	}
}
