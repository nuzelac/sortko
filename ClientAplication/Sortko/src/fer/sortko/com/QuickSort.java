package fer.sortko.com;

public class QuickSort extends Algorithm {
	private QuickSortPosition lastChangePosition = null;
	private QuickSortPosition positionToReturn = null;
	private int N = super.getNumbersCopy().length;
	private static boolean checkOrder = true;
	private static int cutoff = 3;
	private int[] A = null;
	int switchCount = 0;

	private boolean positionChanged = false;
	
	public QuickSort (int numberOfElements){
		super(numberOfElements);
		super.NEEDS_HELP_VARIABLE = true;
	}

	public AlgorithmPosition findSwitch(){
		A = super.getNumbersCopy();
		this.help = 0;
		positionChanged = false;
		switchCount = 0;
		positionToReturn = new QuickSortPosition(0, 0, A, false, 0, QsPosition.median);
		
		QSort (0, N - 1);

		return positionToReturn;
	}
	
	private void setAlgorithmPosition(int i,int j, boolean checkOrder, QsPosition qsPosition){
		switchCount++;
		if (super.switchNumber == switchCount && !positionChanged){			
			this.positionToReturn = new QuickSortPosition(i, j, A.clone(), checkOrder, this.help, qsPosition);
			positionChanged = true;
		}
	}

	private void QSort (int lijevo, int desno) {
		int i, j;
		int stozer;
		if (lijevo + cutoff <= desno) {
			stozer = Medijan3 (lijevo, desno);
			i = lijevo; j = desno - 1;
			while (true) {
				while (A[++i] < stozer);
				while (A[--j] > stozer);
				if (i < j){
					setAlgorithmPosition(i, j, false, QsPosition.regularqs);
					Zamijeni(i, j);
				}
				else {
					break;
				}
			} 
			// obnovi stozer, zamjena
			setAlgorithmPosition(i, desno - 1, false, QsPosition.regularqs);
			Zamijeni (i, desno - 1);
			
			QSort (lijevo, i - 1);
			QSort (i + 1, desno);
		} 
		else {
			InsertionSort (lijevo, desno);
		}
	}
	private int Medijan3(int lijevo, int desno){
		int sredina = (lijevo + desno) / 2;
		if (A [lijevo] > A  [sredina]){
			setAlgorithmPosition(lijevo, sredina, false,QsPosition.median);
			Zamijeni (lijevo, sredina);
		}
		if (A [lijevo] > A [desno]){
			setAlgorithmPosition(lijevo, desno, false, QsPosition.median);
			Zamijeni (lijevo, desno);
		}
		if (A [sredina] > A [desno]){
			setAlgorithmPosition(sredina, desno, false, QsPosition.median);
			Zamijeni(sredina, desno);
		}
		// Sada je: A[lijevo]<=A[sredina]<=A[desno]
		// Sakrij stozer
		setAlgorithmPosition(sredina, desno-1, false, QsPosition.hidden);
		Zamijeni (sredina, desno-1);
		return A[desno - 1];
	}
	
	private void InsertionSort(int lijevo, int desno){
		int N = desno - lijevo + 1; 
		for (int i = 1 + lijevo; i < N + lijevo; i++){
			// 8 oznaka za pomocnu varijablu, hardkodirano
			setAlgorithmPosition(i, 8, checkOrder,QsPosition.insertion);
			this.help = A[i];

			int j;
			for (j = i; j >= 1 && A[j-1] > help; j--){
				setAlgorithmPosition(j-1, j, checkOrder,QsPosition.insertion);
				A[j] = A[j-1];
			}
			setAlgorithmPosition(8, j, checkOrder,QsPosition.insertion);
			A[j] = help;
		}
	}
	private void Zamijeni(int indexLijevo, int indexDesno){
		int pom = A[indexLijevo];
		A[indexLijevo] = A[indexDesno];
		A[indexDesno] = pom;
	}
	
	public enum QsPosition {
		median,hidden,regularqs,insertion
	}
}