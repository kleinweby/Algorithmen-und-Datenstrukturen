package blatt10;

import java.util.*;

public class AdjMatrix {
	/* assumtion: matrix[source][destination] */
	
	public static int inDegree(int k, int[][] m) {
		int count = 0;
		
		// For the inbound edges we leave the destination
		// fixed and check all possible sources
		for (int i = 0; i < m.length; i++) {
			count += m[i][k];
		}
		
		return count;
	}

	public static int outDegree(int k, int[][] m) {
		int count = 0;
		
		// For the outbound edges we leave the source
		// fixed and check all possible destinations
		for (int i = 0; i < m.length; i++) {
			count += m[k][i];
		}
		
		return count;
	}

	public static List<Integer> adjacent(int k, int[][] m) {
		List<Integer> edges = new LinkedList<Integer>();
		
		for (int i = 0; i < m.length; i++) {
			boolean connected = false;
			
			if (m[k][i] == 1)
				connected = true;
			else if (m[i][k] == 1)
				connected = true;
			
			if (connected)
				edges.add(i);
		}
		
		return edges;
	}
	
	public static int[][] multMatrix(int[][] a, int[][] b) {
		int result[][] = new int[a.length][a.length];
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				for (int k = 0; k < a.length; k++) {
					result[i][j] += a[i][k]*b[k][j];
				}
			}
		}
		
		return result;
	}

	public static boolean hasTriangle(int[][] m) {
		// Calculate m^3
		int transformedMatrix[][] = multMatrix(multMatrix(m, m), m);
		
		/* Idea
		 * We calculate m^3 and then check the diagonal
		 * elements if there are edges to get to a node
		 * itself after 3 steps (a triangle) if so,
		 * we have tirangles in this matrix
		 */
		
		for (int i = 0; i < m.length; i++) {
			if (transformedMatrix[i][i] > 0)
				return true;
		}
		
		return false;
	}
	
	public static void main(String args[]) {
		int matrix[][] = {
				{0, 1, 0, 0, 1},
				{0, 0, 0, 1, 0},
				{0, 1, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 1, 0}
		};
		
		System.out.println(hasTriangle(matrix));
	}
}
