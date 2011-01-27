package fer.sortko.com;

public class MergeSort extends Algorithm{
	
	// zapisati prvu promjenu

	public MergeSort (int numberOfElements){
		super(numberOfElements);
	}

	// switchNumber u intervalu [1,n]

	public AlgorithmPosition findSwitch(){
		int N = super.getNumbersCopy().length;
		int[] A = super.getNumbersCopy();
		int[] pomPolje = new int[N];

		MrgSort (A, pomPolje, 0, N - 1);
		
		return new AlgorithmPosition(0, 0, A);
	}

	void Merge (int[] A , int[] pomocnoPolje, int LPoz, int DPoz, int DesniKraj) {
		int i, LijeviKraj, BrojClanova, PomPoz;
		LijeviKraj = DPoz - 1;
		PomPoz = LPoz;
		BrojClanova = DesniKraj - LPoz + 1;
		// glavna pelja
		while (LPoz <= LijeviKraj && DPoz <= DesniKraj) {
			if (A [LPoz] <= A [DPoz])
				pomocnoPolje [PomPoz++] = A [LPoz++];
			else 
				pomocnoPolje [PomPoz++] = A [DPoz++];
		}
		while (LPoz <= LijeviKraj)
			// Kopiraj ostatak prve polovice
			pomocnoPolje [PomPoz++] = A [LPoz++];
		while (DPoz <= DesniKraj)
			// Kopiraj ostatak druge polovice
			pomocnoPolje [PomPoz++] = A [DPoz++];
		for (i = 0; i < BrojClanova; i++, DesniKraj--)
			// Kopiraj PomPolje natrag
			A [DesniKraj] = pomocnoPolje [DesniKraj];
	}

	// MergeSort - rekurzivno sortiranje podpolja
	void MrgSort (int[] A , int[] pomocnoPolje, int lijevo, int desno ) {
		int sredina;
		if (lijevo < desno) {
			sredina = (lijevo + desno) / 2;
			MrgSort (A, pomocnoPolje, lijevo, sredina);
			MrgSort (A, pomocnoPolje, sredina + 1, desno);
			Merge (A, pomocnoPolje, lijevo, sredina + 1, desno);
		}
	}
}
