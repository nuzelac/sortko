package fer.sortko.com;

public class ShellSort extends Algorithm{
	
	private static boolean checkOrder = true;
	int N = super.getNumbersCopy().length;
	
	public ShellSort (int numberOfElements){
		super(numberOfElements);
		super.NEEDS_HELP_VARIABLE = true;
	}
	
	public AlgorithmPosition findSwitch(){
		int switchCount = 0;
		int[] A = super.getNumbersCopy();
		int i, j, korak;
		this.help = 0;

		for (korak = N / 2; korak > 0; korak /= 2) {
			for (i = korak; i < N; i++) {
				switchCount++;
				if (super.switchNumber == switchCount){
					return new AlgorithmPosition(i, 8, A, checkOrder, this.help);
				}
				help = A [i];
				
				for (j = i; j >= korak && A[j-korak] > help; j -= korak) {
					switchCount++;
					if (super.switchNumber == switchCount){
						return new AlgorithmPosition(j - korak, j, A, checkOrder, this.help);
					}
					A [j] = A [j - korak];
				}
				
				switchCount++;
				if (super.switchNumber == switchCount){
					return new AlgorithmPosition(8, j, A, checkOrder, this.help);
				}
				A [j] = help;
			}
		}
		
		// nema zamjena, algoritam završen
		return new AlgorithmPosition(0, 0, A);
	}
}
