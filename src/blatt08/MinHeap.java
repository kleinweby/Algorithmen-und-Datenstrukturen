package blatt08;

import java.util.*;

public class MinHeap<T extends Comparable<T>> {
   private ArrayList<T> heap;
   
   public MinHeap() {
      this.heap = new ArrayList<T>();
   }
 
   public ArrayList<T> getHeap(){
      // do not change because of backend-control
      return heap;
   }
   public int getSize() {
      return this.heap.size();
   }
   public boolean isEmpty() {      
      return this.heap.isEmpty();
   }
   public void downHeap(int k) {
	   if (2*k >= this.getSize())
		   return;
	   
      T element = this.heap.get(k);
      int leftIndex = k*2; // Left child index
            
      // If the other child is smaller, use this
      if (leftIndex + 1 < this.getSize() && this.heap.get(leftIndex).compareTo(this.heap.get(leftIndex + 1)) > 0)
    	  leftIndex += 1;
      
      // Order already acomplished
      if (element.compareTo(this.heap.get(leftIndex)) <= 0)
    	  return;
      
      // Swap them downwards
      this.heap.set(k, this.heap.get(leftIndex));
      this.heap.set(leftIndex, element);
      
      downHeap(leftIndex);
   }
   
   public void insert(T obj) {
      this.heap.add(obj);
      this.upHeap(this.heap.size() - 1);
   }
   
   public void upHeap(int k) {
	   if (k >= this.getSize() || k == 0)
		   return;

	   System.out.println("up ~>" + heap);
	   
      T element = this.heap.get(k);
      int parentIndex = (k - 1)/2; // Parent index Note: -1 for the 0 indexed array
      
      // Current element >= parent -> order ensured
      if (element.compareTo(this.heap.get(parentIndex)) >= 0)
    	  return;
      
      // Swap them upwards
      this.heap.set(k, this.heap.get(parentIndex));
      this.heap.set(parentIndex, element);
      
      upHeap(parentIndex);
   }
   
   public String toString() {
      // do not change because of backend-control
      return heap.toString();
   }
   public static void main(String[] args) {
      int[] values = {8, 3, 7, 1, 5, 6};
      MinHeap<Integer> heap = new MinHeap<Integer>();
      
      for (int c : values) {
    	  System.out.println("insert " + c);
    	  heap.insert(c);
    	  System.out.println(heap);
      }
   }
}

