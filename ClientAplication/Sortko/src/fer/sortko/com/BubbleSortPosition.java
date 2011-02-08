package fer.sortko.com;

public class BubbleSortPosition extends AlgorithmPosition{
	
	private int outerLoopIndex;

	public BubbleSortPosition(int i, int j, int[] currentNumbersList, int indexOuterLoop) {
		super(i, j, currentNumbersList);
		this.outerLoopIndex = indexOuterLoop;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition){
		
		String pocetak = "Krenite u " + Integer.toString(this.outerLoopIndex + 1)+ ". prolaz polja s lijeva. Mijenjajte susjedne znamenke ako je lijeva znamenka ve�a od desne.";
		String uspjeh = "Zadnja uspje�na zamjena bili su indexi "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexI())
			+" i "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexJ())+". Nastavite na desno uspore�ivati brojeve.";
		String greska = "Kod Bubble sorta se uvijek mijenjaju susjedni elementi. " + uspjeh;
		
		if(Math.abs(userAlgorithmPosition.getAlgorithmIndexI()-userAlgorithmPosition.getAlgorithmIndexJ()) > 1){
			return greska;
		}
		else if (this.outerLoopIndex != ((BubbleSortPosition)this.getPreviousAlgorithmPosition()).outerLoopIndex ||
				(((BubbleSortPosition)this.getPreviousAlgorithmPosition()).outerLoopIndex == 0 && 
						((BubbleSortPosition)this.getPreviousAlgorithmPosition()).getAlgorithmIndexI() == 0)){
			return pocetak;
		} else {
			return uspjeh;
		}
	}
}
