package fer.sortko.com;
/*
 * 1.	Kreni u iti korak algoritma i traži najmanji element polja od desno od itog elementa.
 * 2.	Zamjeni najmanji element polja desno od i-tog elementa s i-tim elementom.
 * Moguæe poruke pogreške
 * 1.	Mjenja se s prvim najmanjim brojem.
 */

public class SelectionSortPosition extends AlgorithmPosition{
	private static boolean checkOrder = true;
	private static int helpVariableIndex = 8;
	private int outerLoopIndex;
	
	public SelectionSortPosition(int i, int j, int[] currentNumbersList) {
		super(i, j, currentNumbersList);
		this.outerLoopIndex  = i;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition){
		String mainMessage = "Zamjeni najmanji element polja desno od "+ (this.outerLoopIndex + 1) +". elementa s "+ (this.outerLoopIndex + 1) +". elementom.";		
		return mainMessage;
	}
}
