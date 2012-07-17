package blatt10;

import java.util.ArrayList;
import java.util.List;

public class BuildAdjazenz {
	/* nodes will be names 0 based */
	public int[][] buildAdjazenzMatrixEdgeList(int[] edgelist, boolean undirected) {
		/* Element 0 is the count of nodes */
		int matrix[][] = new int[edgelist[0]][edgelist[0]];
		
		/* 1 is the edge count, so start with 2
		 * then go 2 steps (source, direction)
		 */
		for (int i = 2; i < edgelist.length; i += 2) {
			// Mark edge from i to i+1 as present
			matrix[edgelist[i]][edgelist[i+1]] = 1;
			// if undirected add reverse edge as well
			if (undirected)
				matrix[edgelist[i+1]][edgelist[i]] = 1;
		}
		
		return matrix;
	}
	
	/* nodes will be names 0 based */
	public List<List<Integer>> buildAdjazenzListEdgeList(int[] edgelist, boolean undirected) {
		/* Element 0 is the count of nodes */
		int nodeCount = edgelist[0];
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		
		// Create the destination lists
		for (int i = 0; i < nodeCount; i++)
			list.add(new ArrayList<Integer>());
		
		/* 1 is the edge count, so start with 2
		 * then go 2 steps (source, direction)
		 */
		for (int i = 2; i < edgelist.length; i += 2) {
			// Mark edge from i to i+1 as present
			list.get(edgelist[i]).add(edgelist[i+1]);
			// if undirected add reverse edge as well
			if (undirected)
				list.get(edgelist[i+1]).add(edgelist[i]);
		}
		
		return list;
	}
}
