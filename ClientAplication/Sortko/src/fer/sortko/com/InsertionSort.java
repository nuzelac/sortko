package fer.sortko.com;

public class InsertionSort extends Algorithm {

	private static boolean checkOrder = true;
	
	public InsertionSort (int numberOfElements){
		super(numberOfElements);
		super.NEEDS_HELP_VARIABLE = true;
	}

	public AlgorithmPosition findSwitch(){
		int switchCount = 0;
		int N = super.getNumbersCopy().length;
		int[] A = super.getNumbersCopy();
		int i = 0, j = 0;
		this.help = 0;

		for (i = 1; i < N; i++) {

			switchCount++;
			if (super.switchNumber == switchCount){
				// 8 oznaka za pomocnu varijablu, hardkodirano
				return new AlgorithmPosition(i, 8, A, checkOrder);
			}
			this.help = A[i];

			for (j = i; j >= 1 && A[j-1] > help; j--){
				switchCount++;
				if (super.switchNumber == switchCount){
					return new AlgorithmPosition(j-1, j, A, checkOrder);
				}
				A[j] = A[j-1];
			}
			switchCount++;
			if (super.switchNumber == switchCount){
				return new AlgorithmPosition(8, j, A, checkOrder);
			}
			A[j] = help;
		}
		// nema zamjena, algoritam završen
		return new AlgorithmPosition(0, 0, A, checkOrder);
	}
}
