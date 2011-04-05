package fer.sortko.com;

public class InsertionSortPosition  extends AlgorithmPosition{
	private static boolean checkOrder = true;
	private static int helpVariableIndex = 8;
	private int outerLoopIndex;
	private int i;
	private int j;
	
	public InsertionSortPosition(int i, int j, int[] currentNumbersList, int help, int outerLoopIndex) {
		super(i, j, currentNumbersList, checkOrder, help);
		this.i = i;
		this.j = j;
		this.outerLoopIndex  = outerLoopIndex;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition, boolean isSwitchSuccessful){
		String sortStartMessage = "Zapo�nite algoritam tako da spremite drugi element polja u pomo�nu varijablu u doljnjem desnom kutu ekrana.";
		
		String startMessage = "Krenite u "+ this.outerLoopIndex +". prolaz polja po�ev�i s "+ (this.outerLoopIndex + 1) +". elementom. Koristite pomo�nu varijablu kako bi pohranili taj element.";
		String endMessage = "Zavr�ite "+ this.outerLoopIndex +". prolaz polja koriste�i pomo�nu varijablu.";
		String copyFirstMessage = "Pomi�ite elemente u desno (od "+ (this.outerLoopIndex + 1) +". elementa) kre�u�i se prema po�etku polja dok ne prona�ete odgovaraju�e mjesto za pomo�nu varijablu.";
		String copyMessage = "Nastavite pomicanje s "+(i+1)+". elementom i pomaknite ga udesno kako bi napravili mjesta za element u pomo�noj varijabli. ";
		
		String errorMessage = "Pogre�an korak algoritma. Sljede�i korak algoritma bi trebao biti ";
		String copyErrorMessage = "Elementi se pomi�u tako da se lijevi element kopira u njemu susjedni desni element ukoliko je lijevi ve�i od elementa u pomo�noj varijabli.";
		String copyOrderErrorMessage = "Prilikom kopiranja elementa morate paziti na redoslijed kopiranja. Ako kopiramo element iz polja 1 u polje 2 najprije selektiramo polje 1 pa onda polje 2.";
		
		if (userAlgorithmPosition == null){
			return sortStartMessage;
		}
		if (isSwitchSuccessful){
			if (super.getAlgorithmIndexJ() == helpVariableIndex){
				return startMessage;
			}
			else if (super.getAlgorithmIndexI() == helpVariableIndex){
				return endMessage;
			}
			else {
				int lastIndex = super.getPreviousAlgorithmPosition().getAlgorithmIndexI();
				if (lastIndex == helpVariableIndex){
					return copyFirstMessage;
				}
				else{
					return copyMessage;
				}
			}		
		}
		else {
			if (isOrderError(userAlgorithmPosition)){
				return copyOrderErrorMessage;
			}
			else if ((Math.abs(userAlgorithmPosition.getAlgorithmIndexI() - userAlgorithmPosition.getAlgorithmIndexJ()) > 1)
						&& (userAlgorithmPosition.getAlgorithmIndexI()!= helpVariableIndex && userAlgorithmPosition.getAlgorithmIndexJ()!=helpVariableIndex)){
				return copyErrorMessage;
			}
			else {
				if (this.i == 8){
					return errorMessage + "kopiranje pomo�ne varijable na "+(i+1)+". element niza.";
				} else if (this.j == 8){
					return errorMessage + "kopiranje "+(i+1)+". elementa niza u pomo�nu varijablu.";
				} else {
					return errorMessage + "kopiranje "+(i+1)+". elementa niza na "+(j+1)+". element niza.";
				}
			}
		}
	}
	
	private boolean isOrderError(AlgorithmPosition userAlgorithmPosition){
		return ((userAlgorithmPosition.getAlgorithmIndexI() == super.getAlgorithmIndexJ()) && (userAlgorithmPosition.getAlgorithmIndexJ() == super.getAlgorithmIndexI()));
	}
}
