package fer.sortko.com;
/*
 * 1.	Kreni u iti korak algoritma i tra�i najmanji element polja od desno od itog elementa.
 * 2.	Zamjeni najmanji element polja desno od i-tog elementa s i-tim elementom.
 * Mogu�e poruke pogre�ke
 * 1.	Mjenja se s prvim najmanjim brojem.
 */

public class SelectionSortPosition extends AlgorithmPosition{
	private static boolean checkOrder = false;
	private static int helpVariableIndex = 8;
	private int outerLoopIndex;
	
	public SelectionSortPosition(int i, int j, int[] currentNumbersList) {
		super(i, j, currentNumbersList);
		this.outerLoopIndex  = i;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition, boolean isSwitchSuccessful){
		String sortStartMessage = "Zapo�ni algoritam tako postavi� najmanji element na prvo mjesto polja, sljede�i najmanji na drugo mjesto i tako dalje.";
		String mainMessage = "Zamjeni najmanji element polja desno od "+ (this.outerLoopIndex + 1) +". elementa s "+ (this.outerLoopIndex + 1) +". elementom.";		
		String errorMessage = "Polje je sortirano do "+ (this.outerLoopIndex + 1) +". elementa." + mainMessage;
		
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
