package blatt09;

import java.io.*;
import java.util.HashSet;

public class MyHash {
	   public class BucketArray {
		    // do not change because of Backendtest
			protected String[] arr;
			protected int col;

			public BucketArray(int size) {
				col = 0;
				arr = new String[size];
			}

			public boolean insert(int pos, String s) {
				if (arr[pos] == null || arr[pos] == s) {
					arr[pos] = s;
					return true;
				}
				col++;
				return false;
			}

			public int getCollisions() {
				return col;
			}

			public String toString() {
				String res = new String();

				for (String s : arr)
					res += s + "\n";

				return res;
			}
		   }

   public BucketArray e;  // use only this array!!
   int size;

   public MyHash(int size) {
	this.size = size;
	e = new BucketArray(size);
   }

   public void insert(String s) {
	   HashSet<Integer> prevHashs = new HashSet<Integer>();
	   int hashCode = hash(s);
	   int i = 1;
	   
	   while (!_insert(hashCode, s)) {
		   prevHashs.add(hashCode);

		   hashCode = hash(s) + i*hashCode;
		   i*=i;
		   assert(!prevHashs.contains(hashCode));
	   }
   }
   
   private boolean _insert(int hashCode, String s) {
	   hashCode = hashCode < 0 ? -hashCode : hashCode;
	   
	   return e.insert(hashCode % size, s);
   }
   
   private int hash(String s) {
	   int hash = 0;
	   int i = 0;
	   
	   for (Character c : s.toCharArray()) {
		   hash ^= (c << i);
		   i = (i + s.length()) % 24;
	   }
	   	   
	   return hash;
   }
   
   public static void main(String[] args) {
      // Idea for test
	int size = 1249;  // you have to use this value 
	MyHash hash = new MyHash(size);
	try {
	   DataInput s = new DataInputStream(new FileInputStream("/Users/kleinweby/Downloads/w1.txt"));
	   // use correct Path 
	   String line;
	   while ((line = s.readLine()) != null) {
		hash.insert(line);
	   }
        } catch (IOException e) {
		System.out.println("File not found");
	}
	System.out.println(hash.e.toString());
	System.out.println("Collisions: " + hash.e.getCollisions() + "\n");
   }
}

