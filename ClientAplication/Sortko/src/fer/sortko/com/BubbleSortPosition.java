package fer.sortko.com;

public class BubbleSortPosition extends AlgorithmPosition{
	
	private int indexOuterLoop;

	public BubbleSortPosition(int i, int j, int[] currentNumbersList, int indexOuterLoop) {
		super(i, j, currentNumbersList);
		this.indexOuterLoop = indexOuterLoop;
	}
	
	@Override
	public String getHelpMessage(){
		String s = "Krenite u " + Integer.toString(this.indexOuterLoop + 1)+ ". prolaz polja s lijeva. Mijenjajte susjedne znamenke ako je lijeva znamenka veæa od desne.";
		String k = "Zadnja uspješna zamjena bili su indexi "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexI())
			+" i "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexJ())+". Nastavite na desno usporeðivati susjedne brojeve.";
		
		// pp(0,0);
		// kada je pp outerindex razlièit od trenutnog outerindexa
		
		if (this.indexOuterLoop != ((BubbleSortPosition)this.getPreviousAlgorithmPosition()).indexOuterLoop ||
				(((BubbleSortPosition)this.getPreviousAlgorithmPosition()).indexOuterLoop == 0 && 
						((BubbleSortPosition)this.getPreviousAlgorithmPosition()).getAlgorithmIndexI() == 0)){
			return s;
		} else {
			return k;
		}
	}
}
