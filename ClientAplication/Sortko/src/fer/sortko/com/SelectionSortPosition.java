package fer.sortko.com;
/*
 * 1.	Kreni u iti korak algoritma i traži najmanji element polja od desno od itog elementa.
 * 2.	Zamjeni najmanji element polja desno od i-tog elementa s i-tim elementom.
 * Moguæe poruke pogreške
 * 1.	Mjenja se s prvim najmanjim brojem.
 */

public class SelectionSortPosition extends AlgorithmPosition{
	private int outerLoopIndex;
	
	public SelectionSortPosition(int i, int j, int[] currentNumbersList) {
		super(i, j, currentNumbersList);
		this.outerLoopIndex  = i;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition, boolean isSwitchSuccessful){
		String sortStartMessage = "Zapoènite sortiranje zamjenom koja postavlja najmanji element na poèetak polja.";
		String mainMessage = "Zamjenite najmanji element polja desno od "+ (this.outerLoopIndex + 1) +". elementa s "+ (this.outerLoopIndex + 1) +". elementom.";		
		String errorMessage = "Polje je sada sortirano do "+ (this.outerLoopIndex + 1) +". elementa. "+
		"Nastavite sortiranje zamjenom koja postavlja najmanji element desno od "+ (this.outerLoopIndex + 1) 
		+". elementa na mjesto "+ (this.outerLoopIndex + 1)+". elementa.";
		
		if (userAlgorithmPosition == null){
			return sortStartMessage;
		}
		else if (isSwitchSuccessful){
			return mainMessage;
		}
		else {
			return errorMessage;
		}
	}
}
