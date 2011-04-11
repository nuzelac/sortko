package fer.sortko.com;

public class BubbleSortPosition extends AlgorithmPosition{
	
	private int outerLoopIndex;

	public BubbleSortPosition(int i, int j, int[] currentNumbersList, int indexOuterLoop) {
		super(i, j, currentNumbersList);
		this.outerLoopIndex = indexOuterLoop;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition, boolean isSwitchSuccessful){
		String sortStartMessage = "Zapoènite algoritam tako da najprije usporedite prva dva elementa. Ako je lijevi veæi od desnog zamijenite ih te nastavite usporeðivati udesno.";
		
		String bubbleMessage = "Zamijenite susjedne elemente ako je lijevi veæi od desnog.";
		String startMessage = "Krenite u " + Integer.toString(this.outerLoopIndex + 1)+ ". prolaz polja s lijeva." + bubbleMessage;
		String sortErrorMessage = "Zadnja uspješna zamjena bila je na indexima "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexI())
			+" i "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexJ())+". Nastavite usporeðivati brojeve udesno.";
		String bubbleErrorMessage = "Kod Bubble sorta se uvijek mijenjaju susjedni elementi. " + sortErrorMessage;
		
		// if (userAP == null) poèetak sortiranja - ispisati poèetnu poruku
		// if (isSwitchSuccessful) ispisati poruku za sljedeæi potez
		// else poruka otkriva u èemu je greška
		
		if(userAlgorithmPosition == null){
			return sortStartMessage;
		}
		else if (isSwitchSuccessful){
			if (this.outerLoopIndex != ((BubbleSortPosition)this.getPreviousAlgorithmPosition()).outerLoopIndex ||
					(((BubbleSortPosition)this.getPreviousAlgorithmPosition()).outerLoopIndex == 0 && 
							((BubbleSortPosition)this.getPreviousAlgorithmPosition()).getAlgorithmIndexI() == 0)){
				return startMessage;
			}else {
				return bubbleMessage;
			}
		}
		else {
			if(Math.abs(userAlgorithmPosition.getAlgorithmIndexI()-userAlgorithmPosition.getAlgorithmIndexJ()) > 1){
				return bubbleErrorMessage;
			}
			else{
				return sortErrorMessage;
			}
		}
	}
}
