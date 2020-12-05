import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.coobird.thumbnailator.Thumbnails;


public class Main {
	public static void main(String[] args) {		
//		Input myInput = new Input(8000); // Creates an input of the given size
//
//		long initialTime = System.nanoTime();
//		int[] myInputMaxSumSubarray = algorithm2(myInput.getArray()); // Choose an algorithm of your liking
//		long finalTime = System.nanoTime();
//		long duration = (finalTime - initialTime); // Duration in nanoseconds
//
//		System.out.println("Maximum sum: " + myInputMaxSumSubarray[0] + ", first index: " + myInputMaxSumSubarray[1]
//				+ ", last index: " + myInputMaxSumSubarray[2]);
//		System.out.println("Duration: " + duration / Math.pow(10, 9) + "s"); // Show result in seconds

		
//		  ArrayList<int[]> myInputMaxSumAllSubarrays=new ArrayList<int[]>();
//		  algorithm7(myInput.getArray(), myInputMaxSumAllSubarrays);
//		 
//		  System.out.println("All maximum subarrays:"); for(int[] tuple:
//		  myInputMaxSumAllSubarrays) { System.out.println(Arrays.toString(tuple) ); }
		
		
//		Input2D myInput = new Input2D(2000,2000); 
//		
//		long initialTime = System.nanoTime();
//		int[] myInputMaxSumSubarray = algorithm10( myInput.getMatrix() );
//		long finalTime = System.nanoTime();
//		long duration = (finalTime - initialTime); // Duration in nanoseconds
//		
//		System.out.println("Maximum sum: " + myInputMaxSumSubarray[0] + ", RI: " + myInputMaxSumSubarray[1] + ", RF: " + myInputMaxSumSubarray[2] + ", CI: " + myInputMaxSumSubarray[3] + ", CF: " + myInputMaxSumSubarray[4]);
//		System.out.println("Duration: " + duration / Math.pow(10, 9) + "s"); // Show result in seconds
		
//		InputProtein givenProtein= new InputProtein(args[0]);
//
//		ArrayList<double[]> myInputMaxSumAllSubarrays=new ArrayList<double[]>();
//		algorithm7 ( givenProtein.getHydropathyArray(), myInputMaxSumAllSubarrays);
//		 
//		System.out.println("All maximum subarrays:"); 
//		for(double[] tuple: myInputMaxSumAllSubarrays) { 
//			System.out.println(Arrays.toString(tuple) ); 
//		}
		
		ImageInput img= new ImageInput( "forest500.png" );
		double[] imgAnalysed = algorithm10( img.getScoreMatrix() );
		System.out.println(Arrays.toString( imgAnalysed) ); 
		
		Graphics2D g2=img.getImg().createGraphics();
		
		Rectangle2D.Double myRect = new Rectangle2D.Double(imgAnalysed[3],  imgAnalysed[1],   (imgAnalysed[4]-imgAnalysed[3]),  (imgAnalysed[2]-imgAnalysed[1]) );
		g2.setColor(new Color(244, 67, 54, 100));
		g2.fill( myRect );
		
		double scaleFactor = (double) 1000/Math.max( img.getImg().getHeight(),  img.getImg().getWidth() );
		System.out.println(scaleFactor);
		try {
			BufferedImage scaledImg = Thumbnails.of(img.getImg()).scale(scaleFactor).asBufferedImage();
			
			JFrame myFrame = new JFrame();
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			myFrame.setVisible(true);
			myFrame.setTitle("Hello darkness my old friend...");
			
			ImageIcon icon=new ImageIcon(scaledImg);
			JLabel lbl=new JLabel(icon);
			
			myFrame.setSize( scaledImg.getHeight(), scaledImg.getWidth() );
	        myFrame.add(lbl);
	        
		} catch (IOException e) {}
		
		
//		double[][] lol= { {Double.NEGATIVE_INFINITY, 2 , -4 },
//									  {4, -2, 8 },
//									  {5, Double.NEGATIVE_INFINITY, 7} };
//		
//		double[] sol= algorithm10(lol);
//		System.out.println(Arrays.toString(sol) ); 
		
		
		
	}

	static public double[] algorithm1(double[] in) { // Gets an double array as input and returns a three element array,
																		// where the first element is the maximum sum,
																		// the second one is the index of the first element of the maximum sum,
																		// and the third element the index of the last element
		double[] out = new double[3];

		double curMaxSum = 0; // I start with the assumption that the solution is the empty array
		double curSum; 	// This will be progressively incremented until it becomes equal with the sum of
										// a continuous subarray
		int curI = -1; // Convention: I=F=-1<=>Empty array
		int curF = -1;

		for (int I = 0; I < in.length; I++) { // Considering every index as a starting index
			for (int F = I; F < in.length; F++) { // Considering every index after the starting index as an ending index
				// For each subarray calculate its sum
				curSum = 0;
				for (int i = I; i <= F; i++) {
					curSum = curSum + in[i];
				}

				// If this subarray has bigger sum than the previously known maximum sum, the
				// currently known maximum sum changes, along with the indexes
				if (curSum > curMaxSum) {
					curI = I;
					curF = F;
					curMaxSum = curSum;
				}
			}
		}

		// In the end every subarray will have been compared, the solution will have
		// been found and the output is created
		out[0] = curMaxSum;
		out[1] = curI;
		out[2] = curF;

		return out;
	}

	static public double[] algorithm2(double[] in) {
		double[] out = new double[3];

		double curMaxSum = 0;
		double curSum;
		int curI = -1;
		int curF = -1;

		for (int I = 0; I < in.length; I++) {
			curSum = 0;
			for (int F = I; F < in.length; F++) {
				curSum = curSum + in[F];

				if (curSum > curMaxSum) {
					curI = I;
					curF = F;
					curMaxSum = curSum;
				}
			}
		}

		out[0] = curMaxSum;
		out[1] = curI;
		out[2] = curF;

		return out;
	}

	static public double[] algorithm3(double[] in) {
		double[] out = new double[3];

		// We create the needed preprocessed data
		double[] sumArray = new double[in.length];
		sumArray[0] = in[0];
		for (int i = 1; i < in.length; i++) {
			sumArray[i] = sumArray[i - 1] + in[i];
		}

		double curMaxSum = 0; // I start with the assumption that the solution is the empty array
		double curSum;
		int curI = -1; // Convention: I=F=-1<=>Empty array
		int curF = -1;

		for (int I = 0; I < in.length; I++) {
			for (int F = I; F < in.length; F++) {
				curSum = sumArray[F] - sumArray[I] + in[I]; // Why we do not replace: -sumArray[I]+in[I]=-sumArray[I-1]?
															// Because then we would get an index out of bounds
															// exception when I=0

				if (curSum > curMaxSum) {
					curI = I;
					curF = F;
					curMaxSum = curSum;
				}
			}
		}

		out[0] = curMaxSum;
		out[1] = curI;
		out[2] = curF;

		return out;
	}

	static public double[] algorithm4(double[] in) {
		double[] out = algorithm4Recursive(0, in.length - 1, in);

		return out;
	}

	static public double[] algorithm4Recursive(int I, int F, double[] in) { // Returns the solution to the MSP for the array
																		// starting at I and ending at F of in.
		double[] out = new double[3];

		// First we have the termination conditions (the conditions that if fulfilled
		// signal the termination of the recursion).
		if (I == -1) { // If the initial array is the empty array then the solution is simply the empty
						// array
			out[0] = 0;
			out[1] = -1;
			out[2] = -1;

			return out;

		} else if (I == F) { // If the initial array is an 1-element array then the solution is...

			if (in[I] > 0) { // ...the whole array if the element is positive
				out[0] = in[I];
				out[1] = I;
				out[2] = I;

				return out;
			} else { // ...the empty array if the element is negative
				out[0] = 0;
				out[1] = -1;
				out[2] = -1;

				return out;
			}

		}

		// If we do not have the termination condition
		int M = (int) Math.floor((I + F) / 2); // We find the index at the middle of I and F. If the middle of I and F
												// is not an integer we floor it to and integer.

		// We find the LEFT part of the max subarray that crosses the middle of the
		// initial array
		double curMaxSum = in[M]; // At the very least this part has in[M] in it.
		double curSum = in[M]; // Question: Cannot this array be the empty array? Of course not! Then the max
							// subarray that crosses the middle of the initial array would not cross it,
							// wouldn't it?
		int crossI = M;
		for (int i = M; i >= I; i--) { // Moves from the right to the left, starting from the middle
			curSum = curSum + in[i]; // Finds the curSum

			if (curSum > curMaxSum) { // Compares it to the previous maximum curSum, the curMaxSum
				crossI = i;
				curMaxSum = curSum;
			}
		}
		double maxSumCrossLeft = curMaxSum;

		// We find the RIGHT part of the max subarray that crosses the middle of the
		// initial array
		curSum = in[M + 1]; // At the very least this part has in[M+1] in it
		curMaxSum = in[M + 1];
		int crossF = M + 1;
		for (int i = M + 1; i <= F; i++) { // Moves from the left to the right, starting from the middle
			curSum = curSum + in[i]; // Finds the curSum

			if (curSum > curMaxSum) { // Compares it to the previous maximum curSum, the curMaxSum
				crossF = i;
				curMaxSum = curSum;
			}
		}
		double maxSumCrossRight = curMaxSum;

		double maxSumCross = maxSumCrossLeft + maxSumCrossRight; // The indexes of this sum are crossI and crossF

		double[] leftSolution = algorithm4Recursive(I, M, in);
		double[] rightSolution = algorithm4Recursive(M + 1, F, in);

		if (maxSumCross >= leftSolution[0] && maxSumCross >= rightSolution[0]) { // If the max subarray that crosses the
																					// middle is the real max subarray
																					// return the appropriate solution
			out[0] = maxSumCross;
			out[1] = crossI;
			out[2] = crossF;

			return out;

		} else if (leftSolution[0] >= maxSumCross && leftSolution[0] >= rightSolution[0]) { // If the max subarray on
																							// the left is the real max
																							// subarray return the left
																							// solution
			return leftSolution;

		} else { // If the max subarray on the right is the real max subarray return the right
					// solution
			return rightSolution;
		}

	}

	static public double[] algorithm5(double[] in) {
		double[] out = new double[3];

		double curMaxSum = 0;			//Previous solution-initially empty
		int IcurMaxSum = -1;		//Indexes of the previous solution
		int FcurMaxSum = -1;		//
		
		double maxEndingHere = 0;			//Max subarray that ends in in[i]-initially empty
		int ImaxEndingHere = -1;		//Indexes of the max subarray that ends in in[i]
		int FmaxEndingHere = -1;		//

		for (int i = 0; i < in.length; i++) {		//In each iteration we find the solution to A[0...i] by extending the previous solution
			
			//We first determine maxEndingHere
			if (maxEndingHere + in[i] < 0 || in[i]==Double.NEGATIVE_INFINITY ) {		//maxEndingHere can be the empty array. 
				maxEndingHere = 0;
				ImaxEndingHere = -1;
				FmaxEndingHere = -1;
			} else {	// If maxEndingHere isn't the empty array
				if (ImaxEndingHere == -1) {	//If the previous maxEndingHere is the empty array  
					ImaxEndingHere = i;			
				}
				maxEndingHere = maxEndingHere + in[i];
				FmaxEndingHere = i;
			}

			if (maxEndingHere > curMaxSum) {	//If maxEndingHere is greater than the previous solution, change the current solution
				curMaxSum = maxEndingHere;
				IcurMaxSum = ImaxEndingHere;
				FcurMaxSum = FmaxEndingHere;
			}
			// Else do nothing. The above if condition is logically equivalent to: next solution=max(previous solution, maxEndingHere)
		}

		out[0] = curMaxSum;
		out[1] = IcurMaxSum;
		out[2] = FcurMaxSum;

		return out;
	}

	static public void algorithm6(double[] in, ArrayList<double[]> out) {
		algorithm6Recursive(0, in.length - 1, in, out);
	}

	static public void algorithm6Recursive(int I, int F, double[] in, ArrayList<double[]> out) { // Gets an array as input and edits the ArrayList "out" so it contains all the maximum subarrays in the order they appear in the input.The subarrays are
																																					// represented with the 3-tuple used everywhere else
		
		double[] nextMaxSubarrayTuple = algorithm4 (Arrays.copyOfRange(in, I, F + 1));  // Choose the MSP algorithm of your liking. In the argument of that algorithm we pass the subarray of the initial array that starts in I(inclusive) and ends in F(inclusive)
		
		//Next max subarray is an empty array, which means all the next max subarrays in the subarray from I to F have lower or equal sums and thus we stop.
		if (nextMaxSubarrayTuple[1] == -1) {	
			return; 													
		}

		// Finding the real indexes of the max subarray just found
		nextMaxSubarrayTuple[1] = nextMaxSubarrayTuple[1] + I;
		nextMaxSubarrayTuple[2] = nextMaxSubarrayTuple[2] + I;
		
		if(I<nextMaxSubarrayTuple[1]) {		//We check if there is any remaining subarray on the left
			algorithm6Recursive(I, (int) (nextMaxSubarrayTuple[1]-1), in, out);
		}
		out.add(nextMaxSubarrayTuple);
		if(F>nextMaxSubarrayTuple[2]) {		//We check if there is any remaining subarray on the right
			algorithm6Recursive((int) (nextMaxSubarrayTuple[2]+1), F, in, out);
		}
		
	}

	static public void algorithm7(double[] in, ArrayList<double[]> out) {
		
		double[] nextMaxSubarrayTuple = algorithm5(in); // Choose the MSP algorithm of your liking
		
		//Next max subarray is an empty array, which means all the next max subarrays  have lower or equal sums and thus we stop.
		if (nextMaxSubarrayTuple[1] == -1) {	
			return; 													
		}
		
		for (int i = (int) nextMaxSubarrayTuple[1]; i <= nextMaxSubarrayTuple[2]; i++) {
			in[i] = Double.NEGATIVE_INFINITY;		//Writes the minimum integer value to in[i]. Conceptually equivalent to -inf.
		}
		
		out.add(nextMaxSubarrayTuple);
		algorithm7(in, out);
	}

	static public double[] algorithm8(double[][] in) {	 // Gets an double matrix as input and returns a five-element array, where the first element is the maximum sum,
																					// and the next elements are the first row index, last row index, first column index and last column index respectively.

		double[] out = new double[5];
		
		double curMaxSum = 0;		// I start with the assumption that the solution is the empty array
		double curSum;
		
		int curRI = -1;
		int curRF = -1;
		int curCI = -1;
		int curCF = -1;
		
		//We first iterate over each possible row limits
		for( int topRow=0; topRow< in.length ; topRow++) {
			for( int bottomRow=topRow; bottomRow< in.length ; bottomRow++) {
				//We then iterate over each possible column limits
				for(int leftColumn=0; leftColumn<in[0].length; leftColumn++) {
					for(int rightColumn=leftColumn; rightColumn<in[0].length; rightColumn++) {
						
						//Because of the above for loops we will iterate over each possible submatrix of the original matrix.
						//For each submatrix we calculate its sum
						curSum = 0;
						for(int i=topRow; i<=bottomRow; i++) {
							for(int j=leftColumn; j<=rightColumn; j++) {
								curSum = curSum + in[i][j];
							}
						}
						
						// If this submatrix has bigger sum than the previously known maximum sum, the
						// currently known maximum sum changes, along with the indexes
						if (curSum > curMaxSum) {
							curRI = topRow;
							curRF = bottomRow;
							curCI = leftColumn;
							curCF = rightColumn;
							
							curMaxSum = curSum;
						}
						
						
					}
				}
			}
		}
		
		out[0] = curMaxSum;
		out[1] = curRI;
		out[2] = curRF;
		out[3] = curCI;
		out[4] = curCF;

		return out;
		
	}
	
	static public double[] algorithm9(double[][] in) {
		double[] out = new double[5];
		
		double curMaxSum = 0;		// I start with the assumption that the solution is the empty array
		
		int curRI = -1;
		int curRF = -1;
		int curCI = -1;
		int curCF = -1;
		
		double[] contributions= new double[in.length];	//This will hold all the rows' equivalent  contributions
		double[] pairSolution;
		
		//For every possible pair of CI and CF
		for(int CI=0; CI<in[0].length; CI++) {
			for(int CF=CI; CF<in[0].length; CF++) {
				
				//Reduce every row to its equivalent contribution
				for(int r=0; r<in.length; r++) {
					contributions[r]=0;
					for(int c=CI; c<=CF; c++) {
						
						if(in[r][c]==Double.NEGATIVE_INFINITY) {
							contributions[r]=Double.NEGATIVE_INFINITY;
							break;
						}
						
						contributions[r]+=in[r][c];
					}
				}
				
				//Then apply the MSP algorithm of your liking
				pairSolution = algorithm5(contributions);
				
				if(pairSolution[0]>curMaxSum) {	
					curMaxSum=pairSolution[0];
					
					curRI=(int) pairSolution[1];
					curRF=(int) pairSolution[2];
					curCI=CI;
					curCF=CF;
				}
				
			}
		}
		
		//The total maximum found will be the solution to the original problem
		out[0] = curMaxSum;
		out[1] = curRI;
		out[2] = curRF;
		out[3] = curCI;
		out[4] = curCF;

		return out;
		
	}

	static public double[] algorithm10(double[][] in) {
		double[] out = new double[5];
		
		double curMaxSum = 0;		// I start with the assumption that the solution is the empty array
		
		int curRI = -1;
		int curRF = -1;
		int curCI = -1;
		int curCF = -1;
		
		double[] contributions= new double[in.length];	//This will hold all the rows' equivalent  contributions
		double[] pairSolution;
		
		//For every possible pair of CI and CF
		for(int CI=0; CI<in[0].length; CI++) {
			
			//Clear the contribution array when CI is incremented by one
			for(int i=0; i<contributions.length; i++) {
				contributions[i]=0;
			}

			for(int CF=CI; CF<in[0].length; CF++) {
				
				//Reduce every row to its equivalent contribution
				for(int r=0; r<in.length; r++) {
					
					if(in[r][CF]==Double.NEGATIVE_INFINITY  || contributions[r]== Double.NEGATIVE_INFINITY ) {
						contributions[r]=Double.NEGATIVE_INFINITY;
					} else {
						contributions[r]+=in[r][CF];		//If I know the contribution of a row that starts at CI and ends at CF, I can easily calculate the 
					}															//contribution of the row that starts at CI and ends at CF+1 by adding the new element
					
				}															
				
				//Then apply the MSP algorithm of your liking
				pairSolution = algorithm5(contributions);
				
				if(pairSolution[0]>curMaxSum) {	
					curMaxSum=pairSolution[0];
					
					curRI=(int) pairSolution[1];
					curRF=(int) pairSolution[2];
					curCI=CI;
					curCF=CF;
				}
				
			}
		}
		
		//The total maximum found will be the solution to the original problem
		out[0] = curMaxSum;
		out[1] = curRI;
		out[2] = curRF;
		out[3] = curCI;
		out[4] = curCF;

		return out;
		
	}
	
}
