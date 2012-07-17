package blatt09;

public class Food {
	String _category;
	String _name;
	
	public Food(String category, String name) {
		this._category = category;
		this._name = name;
	}
	
	public String getName() {
		return this._name;
	}
	
	public String getCategory() {
		return this._category;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s)", this._name, this._category);
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
}
