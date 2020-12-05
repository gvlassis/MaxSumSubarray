import java.util.Random;

public class Input2D {
	static private int seed=1053555;	//Seed used in the pseudorandom generator
	static private Random myRandGen=new Random(seed);
	
	private int[][] matrix;
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	
	public Input2D(int rows, int columns) {
		matrix=new int[rows][columns];
		
		for(int r=0; r<matrix.length; r++) {
			for(int c=0; c< matrix[0].length; c++) {
				matrix[r][c]= myRandGen.nextInt(201);	//i=random number from 0 up to, and not including, 201 / ie[0,200] (e denotes subset)
				matrix[r][c]-=100;										//ie[-100,100]
			}
		
		}
	
	
	}
	
}
