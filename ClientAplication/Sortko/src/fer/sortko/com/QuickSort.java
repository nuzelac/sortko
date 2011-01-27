package fer.sortko.com;

public class QuickSort extends Algorithm{
	


	private static int cutoff = 1;
	int switchCount = 0;
	int N = super.getNumbersCopy().length;
	int[] A = super.getNumbersCopy();
	private AlgorithmPosition algorithmPosition = new AlgorithmPosition(0,0,A);
	private boolean positionChanged = false;
	
	public QuickSort (int numberOfElements){
		super(numberOfElements);
	}

	public AlgorithmPosition findSwitch(){
		QSort (A, 0, N - 1);
		return algorithmPosition;
	}
	
	private void setAlgorithmPosition(int i,int j){
		if (!positionChanged){
			this.algorithmPosition.setAlgorithmIndexI(i);
			this.algorithmPosition.setAlgorithmIndexJ(j);
			positionChanged = true;
		}
	}

	private void QSort (int[] A, int lijevo, int desno) {
		int i, j;
		int stozer;
		if (lijevo + cutoff <= desno) {
			stozer = Medijan3 (A, lijevo, desno);
			i = lijevo; j = desno - 1;
			while (true) {
				while (A [++i] < stozer);
				while (A [--j] > stozer);
				if (i < j){
					switchCount++;
					if (super.switchNumber == switchCount){
						setAlgorithmPosition(i, j);
					}
					Zamijeni(i, j);
				}
				else {
					break;
				}
			} 
			// obnovi stozer, zamjena
			switchCount++;
			if (super.switchNumber == switchCount){
				setAlgorithmPosition(i, desno - 1);
			}
			Zamijeni (i, desno - 1);
			QSort (A, lijevo, i - 1);
			QSort (A, i + 1, desno);
		} 
		else {
			// Sortiraj podpolje, zakomentirano zbog cutoff == 1
			// InsertionSort (A + lijevo, desno - lijevo + 1);
		}
	}
	private int Medijan3(int[] A, int lijevo, int desno){
		int sredina = (lijevo + desno) / 2;
		if (A [lijevo] > A  [sredina]){
			switchCount++;
			if (super.switchNumber == switchCount){
				setAlgorithmPosition(lijevo, sredina);
			}
			Zamijeni (lijevo, sredina);
		}
		if (A [lijevo] > A [desno]){
			switchCount++;
			if (super.switchNumber == switchCount){
				setAlgorithmPosition(lijevo, desno);
			}
			Zamijeni (lijevo, desno);
		}
		if (A [sredina] > A [desno]){
			switchCount++;
			if (super.switchNumber == switchCount){
				setAlgorithmPosition(sredina, desno);
			}
			Zamijeni (sredina, desno);
		}
		// Sada je: A[lijevo]<=A[sredina]<=A[desno]
		// Sakrij stozer
		switchCount++;
		if (super.switchNumber == switchCount){
			setAlgorithmPosition(sredina, desno-1);
		}	
		Zamijeni (sredina, desno-1);
		return A [desno - 1];                
	}
	private void Zamijeni(int indexLijevo, int indexDesno){
		int pom = A[indexLijevo];
		A[indexLijevo] = A[indexDesno];
		A[indexDesno] = pom;
	}
}