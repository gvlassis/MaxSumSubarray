import java.util.Random;

public class Input1D {
	static private int seed=1053555;	//Seed used in the pseudorandom generator
	static private Random myRandGen=new Random(seed);
	
	private int[] array;
	
	public int[] getArray() {
		return array;
	}
	
	
	public Input1D(int size) {
		array=new int[size];
		
		for(int i=0; i<array.length; i++) {
			array[i]= myRandGen.nextInt(201);	//array[i]=random number from 0 up to, and not including, 201 / ie[0,200] (e denotes subset)
			array[i]-=100;										//array[i]e[-100,100]
		}
		
	}
	
	
}

