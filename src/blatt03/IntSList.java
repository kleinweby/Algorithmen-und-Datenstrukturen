package blatt03;
import aud.SList;

public class IntSList {
	SList<Integer> _list;

	public interface Predicate {
		public boolean test(int el);
	}

	public IntSList() {
		this._list = new SList<Integer>();
	}
	
	public String toString() {
		return this._list.toString();
	}
	
	public void push_back(int obj) {
		this._list.push_back(obj);
	}
	
	public IntSList filter(Predicate p) {
		IntSList filteredList = new IntSList();
		
		for (Integer integer : this._list)
			if (p.test(integer))
				filteredList.push_back(integer);
		
		return filteredList;
	}
}
