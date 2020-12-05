
public class InputProtein {
	private String sequence;
	private double[] hydropathyArray;
	
	public double[] getHydropathyArray() {
		return hydropathyArray;
	}
	
	
	public InputProtein(String passedSequence) {
		
		sequence= passedSequence;
		
		hydropathyArray=new double[passedSequence.length()];
		
		for(int i=0; i<hydropathyArray.length; i++ ) {
			switch( sequence.charAt(i) ) {
				case 'A':
					hydropathyArray[i]=+1.8;
					break;
				case 'R':
					hydropathyArray[i]=-4.5;
					break;
				case 'N':
					hydropathyArray[i]=-3.5;
					break;
				case 'D':
					hydropathyArray[i]=-3.5;
					break;
				case 'C':
					hydropathyArray[i]=+2.5;
					break;
				case 'Q':
					hydropathyArray[i]=-3.5;
					break;
				case 'E':
					hydropathyArray[i]=-3.5;
					break;
				case 'G':
					hydropathyArray[i]=-0.4;
					break;
				case 'H':
					hydropathyArray[i]=-3.2;
					break;
				case 'I':
					hydropathyArray[i]=+4.5;
					break;
				case 'L':
					hydropathyArray[i]=+3.8;
					break;
				case 'K':
					hydropathyArray[i]=-3.9;
					break;
				case 'M':
					hydropathyArray[i]=+1.9;
					break;
				case 'F':
					hydropathyArray[i]=+2.8;
					break;
				case 'P':
					hydropathyArray[i]=-1.6;
					break;
				case 'S':
					hydropathyArray[i]=-0.8;
					break;
				case 'T':
					hydropathyArray[i]=-0.7;
					break;
				case 'W':
					hydropathyArray[i]=-0.9;
					break;
				case 'Y':
					hydropathyArray[i]=-1.3;
					break;
				case 'V':
					hydropathyArray[i]=+4.2;
					break;
			}
			
			
		}
	
	
	}
	
}