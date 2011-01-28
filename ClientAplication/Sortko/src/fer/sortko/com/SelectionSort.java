package fer.sortko.com;

public class SelectionSort extends Algorithm {

	int N = super.getNumbersCopy().length;
	
	public SelectionSort (int numberOfElements){
		super(numberOfElements);
	}

	public AlgorithmPosition findSwitch(){
		int switchCount = 0;
		int[] A = super.getNumbersCopy();
		int i, j, min, pom;

		for (i = 0; i < N; i++) {
			min = i;
			for (j = i+1; j < N; j++) {
				if (A[j] < A[min]) min = j;
			}
			switchCount++;
			if (super.switchNumber == switchCount){
				return new AlgorithmPosition(i, min, A);
			}
			pom = A[i];
			A[i] = A[min];
			A[min] = pom;
		}

		// nema zamjena, algoritam završen
		return new AlgorithmPosition(0, 0, A);
	}
}
