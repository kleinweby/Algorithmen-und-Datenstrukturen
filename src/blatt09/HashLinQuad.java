package blatt09;

import java.util.Random;

public class HashLinQuad {
	// simple Hashtable with linear/quadratic probing
	// capacity is big enough
	private int[] table;
	private int size; // current number of elements
	private int capacity;

	public HashLinQuad(int capacity) {
		this.table = new int[capacity];
		for (int i = 0; i < capacity; i++)
			this.table[i] = Integer.MIN_VALUE;
		this.capacity = capacity;
		this.size = 0;
	}

	public int addLin(int obj) {
		int hash = (obj < 0 ? -obj : obj) % this.capacity;
		int collisions;
		int position = hash;

		for (collisions = 0; this.table[position] != obj && this.table[position] != Integer.MIN_VALUE; 
				collisions++, position = (hash + collisions) % this.capacity);
		
		this.table[position] = obj;
		this.size++;
		
		return collisions;
	}

	public int addQuad(int obj) {
		int hash = (obj < 0 ? -obj : obj) % this.capacity;
		int collisions;
		int position = hash;

		for (collisions = 0; this.table[position] != obj && this.table[position] != Integer.MIN_VALUE; 
				collisions++, position = (hash + collisions*collisions) % this.capacity);
		
		this.table[position] = obj;
		this.size++;
		
		return collisions;
	}

	public String toString() {
		return this.table.toString();
	}

	public static void main(String[] args) {
		HashLinQuad linTable = new HashLinQuad(1259);
		HashLinQuad quadTable = new HashLinQuad(1259);

		Random generator = new Random();
		int numbers[] = new int[1000];
		int linCol = 0;
		int quadCol = 0;
		
		for (int i = 0; i < 1000; i++)
			numbers[i] = generator.nextInt();
		
		for (Integer i : numbers) {
			linCol += linTable.addLin(i);
			quadCol += quadTable.addQuad(i);
		}
		
		System.out.println("Lin = " + linCol);
		System.out.println("Quad = " + quadCol);
	}
}