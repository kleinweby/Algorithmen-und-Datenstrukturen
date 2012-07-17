package blatt09;

import java.util.ArrayList;
import java.util.LinkedList;

public class Icebox {
	LinkedList<Food> _content[];
	int _capacity;
	
	@SuppressWarnings("unchecked")
	public Icebox(int capacity) {
		this._content = new LinkedList[capacity];
		this._capacity = capacity;
	}
	
	public void add(Food f) {
		int hashCode = f.hashCode();
		int position;
		
		if (hashCode < 0)
			hashCode *= -1;
		
		position = hashCode % this._capacity;
		
		
		if (this._content[position] == null) {
			this._content[position] = new LinkedList<Food>();
		}
		
		this._content[position].add(f);
	}
	
	public boolean contains(Food f) {
		int hashCode = f.hashCode();
		int position;
		
		if (hashCode < 0)
			hashCode *= -1;
		
		position = hashCode % this._capacity;
		
		if (this._content[position] == null) {
			return false;
		}
		
		return this._content[position].contains(f);
	}
	
	@Override
	public String toString() {
		String s = "[";
		
		for (LinkedList<Food> l : this._content) {
			if (l == null)
				s += "null";
			else {
				s += "[";
				for (Food f : l)
					s += f.toString() + ",";
				s += "]";
			}
			
			s += ",";
		}
		
		return s + "]";
			
	}
	
	public static void main(String[] args) {
		Icebox box = new Icebox(5);
		
		box.add(new Food("Obst", "Apfel"));
		box.add(new Food("Obst", "Birne"));
		box.add(new Food("Fastfood", "Pizza"));
		box.add(new Food("Fastfood", "Apfel"));
		box.add(new Food("Fleisch", "Bratwurst"));
		box.add(new Food("Fleisch", "Leberkaese"));
		box.add(new Food("Fleisch", "Schwein"));
		box.add(new Food("", "Ei"));
		box.add(new Food("", "Pudding"));
		
		System.out.println(box);
	}
}
