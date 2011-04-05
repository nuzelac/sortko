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
		String sortStartMessage = "Zapoènite algoritam tako da spremite drugi element polja u pomoænu varijablu u doljnjem desnom kutu ekrana.";
		
		String startMessage = "Krenite u "+ this.outerLoopIndex +". prolaz polja poèevši s "+ (this.outerLoopIndex + 1) +". elementom. Koristite pomoænu varijablu kako bi pohranili taj element.";
		String endMessage = "Završite "+ this.outerLoopIndex +". prolaz polja koristeæi pomoænu varijablu.";
		String copyFirstMessage = "Pomièite elemente u desno (od "+ (this.outerLoopIndex + 1) +". elementa) kreèuæi se prema poèetku polja dok ne pronaðete odgovarajuæe mjesto za pomoænu varijablu.";
		String copyMessage = "Nastavite pomicanje s "+(i+1)+". elementom i pomaknite ga udesno kako bi napravili mjesta za element u pomoænoj varijabli. ";
		
		String errorMessage = "Pogrešan korak algoritma. Sljedeæi korak algoritma bi trebao biti ";
		String copyErrorMessage = "Elementi se pomièu tako da se lijevi element kopira u njemu susjedni desni element ukoliko je lijevi veæi od elementa u pomoænoj varijabli.";
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
					return errorMessage + "kopiranje pomoæne varijable na "+(i+1)+". element niza.";
				} else if (this.j == 8){
					return errorMessage + "kopiranje "+(i+1)+". elementa niza u pomoænu varijablu.";
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
