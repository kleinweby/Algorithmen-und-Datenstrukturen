package blatt10;

public class GraphConnectivity {
	public static int[][] matrixMult(int[][] a, int[][] b) {
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

	private static void printMatrix(int[][] m) {
		for (int a[] : m) {
			for (int b : a) {
				System.out.print(b + " ");
			}
			
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int m[][] = {
				{0, 1, 0, 0, 0, 0},
				{1, 0, 1, 1, 0, 0},
				{0, 1, 0, 0, 1, 0},
				{0, 1, 0, 0, 1, 0},
				{0, 0, 1, 1, 0, 1},
				{0, 0, 0, 0, 1, 0}
		};
		int result[][];
		
		System.out.println("m");
		printMatrix(m);
		
		result = matrixMult(m, m);
		System.out.println("m^2");
		printMatrix(result);
		
		result = matrixMult(result, m);
		System.out.println("m^3");
		printMatrix(result);
	}
}
