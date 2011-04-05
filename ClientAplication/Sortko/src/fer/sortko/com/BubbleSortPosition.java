package fer.sortko.com;

public class BubbleSortPosition extends AlgorithmPosition{
	
	private int outerLoopIndex;

	public BubbleSortPosition(int i, int j, int[] currentNumbersList, int indexOuterLoop) {
		super(i, j, currentNumbersList);
		this.outerLoopIndex = indexOuterLoop;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition, boolean isSwitchSuccessful){
		String sortStartMessage = "Zapo�nite algoritam tako da najprije usporedite prva dva elementa. Ukoliko je lijevi ve�i od desnog zamjenite im mjesta a zatim nastavite uspore�ivati sa sljede�im elementom niza.";
		
		String bubbleMessage = "Mijenjajte susjedne elemente ako je lijeva znamenka ve�a od desne.";
		String startMessage = "Krenite u " + Integer.toString(this.outerLoopIndex + 1)+ ". prolaz polja s lijeva." + bubbleMessage;
		String sortErrorMessage = "Zadnja uspje�na zamjena bili je na indexima "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexI())
			+" i "+Integer.toString(this.getPreviousAlgorithmPosition().getAlgorithmIndexJ())+". Nastavite uspore�ivati brojeve udesno.";
		String bubbleErrorMessage = "Kod Bubble sorta se uvijek mijenjaju susjedni elementi. " + sortErrorMessage;
		
		// if (userAP == null) po�etak sortiranja - ispisati po�etnu poruku
		// if (isSwitchSuccessful) ispisati poruku za sljede�i potez
		// else poruka otkriva u �emu je gre�ka
		
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
