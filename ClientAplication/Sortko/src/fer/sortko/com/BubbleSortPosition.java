package fer.sortko.com;

public class BubbleSortPosition extends AlgorithmPosition{
	
	private int outerLoopIndex;

	public BubbleSortPosition(int i, int j, int[] currentNumbersList, int indexOuterLoop) {
		super(i, j, currentNumbersList);
		this.outerLoopIndex = indexOuterLoop;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition){
		
		String startMessage = "Krenite u " + Integer.toString(this.outerLoopIndex + 1)+ ". prolaz polja s lijeva. Mijenjajte susjedne znamenke ako je lijeva znamenka veæa od desne.";
		String sortMessage = "Zadnja uspješna zamjena bili su indexi "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexI())
			+" i "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexJ())+". Nastavite na desno usporeðivati brojeve.";
		String errorMessage = "Kod Bubble sorta se uvijek mijenjaju susjedni elementi. " + sortMessage;
		
		if(Math.abs(userAlgorithmPosition.getAlgorithmIndexI()-userAlgorithmPosition.getAlgorithmIndexJ()) > 1){
			return errorMessage;
		}
		else if (this.outerLoopIndex != ((BubbleSortPosition)this.getPreviousAlgorithmPosition()).outerLoopIndex ||
				(((BubbleSortPosition)this.getPreviousAlgorithmPosition()).outerLoopIndex == 0 && 
						((BubbleSortPosition)this.getPreviousAlgorithmPosition()).getAlgorithmIndexI() == 0)){
			return startMessage;
		} else {
			return sortMessage;
		}
	}
}
