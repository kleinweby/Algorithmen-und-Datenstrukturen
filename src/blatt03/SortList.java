package blatt03;
import aud.SList;

public class SortList<T extends Comparable<T>> {
	SList<T> _list;
	
	public SortList() {
		this._list = new SList<T>();
	}
	
	public boolean insert(T obj) {
		/* Vorbedingung obj != null */
		if (this._list.empty()) {
			this._list.push_front(obj);
			return true;
		}
		
		int insertI = 0;
		
		for (T element: this._list) {
			/* Invariante: i < list.size && list[i] < obj */
			int result = element.compareTo(obj);
			if (result > 0)
				break;
			else if (result == 0)
				return false;
			else
				insertI++;
		}
		
		this._list.insert(insertI, obj);
		
		/* Nachbedingung: Fuer alle i..n-1 gilt list[i] < list[i+1] */
		return true;
	}
	
	@Override
	public String toString() {
		return this._list.toString();
	}
}
