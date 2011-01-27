package fer.sortko.com;

public class BubbleSort extends Algorithm{
	
	public BubbleSort (int numberOfElements){
		super(numberOfElements);
	}
	
	public AlgorithmPosition findSwitch(){
		int switchCount = 0;
		int N = super.getNumbersCopy().length;
		int[] A = super.getNumbersCopy();
		int i = 0, j = 0, pom = 0;

		for (i = 0; i < N-1; i++) {
			for (j = 0; j < N-1-i; j++) {
				if (A[j+1] < A[j]){
					switchCount++;
					if (super.switchNumber == switchCount){
						return new AlgorithmPosition(j, j+1, A);
					}
					pom = A[j];
					A[j] = A[j+1];
					A[j+1] = pom;
				}
			}
		}
		
		// nema zamjena, algoritam završen
		return new AlgorithmPosition(0, 0, A);
	}
}
