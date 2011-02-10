package fer.sortko.com;

/*Insertion Sort
1.	Krenite u 1. prolaz polja poèevši s 1. elementom. Pri tome koristite pomoænu varijablu. 
2.	Pomièite elemente u desno  kreæuæi se prema poèetku polja dok ne pronaðete odgovarajuæe mjesto za pomoænu varijablu.
3.	Završite 1. prolaz polja koristeæi pomoænu varijablu.
Moguæe poruke greške:
1.	Pomoæna varijabla nije dobro izabrana.
2.	Redoslijed kopiranja nije poštivan.
3.	Pomoæna varijabla nije dobro vraæena.
*/

public class InsertionSortPosition  extends AlgorithmPosition{
	private static boolean checkOrder = true;
	private static int helpVariableIndex = 8;
	private int outerLoopIndex;
	
	public InsertionSortPosition(int i, int j, int[] currentNumbersList, int help, int outerLoopIndex) {
		super(i, j, currentNumbersList, checkOrder, help);
		this.outerLoopIndex  = outerLoopIndex;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition){
		String startMessage = "Krenite u "+ this.outerLoopIndex +". prolaz polja poèevši s "+ (this.outerLoopIndex + 1) +". elementom. Pri tome koristite pomoænu varijablu.";
		String copyFirstMessage = "Pomièite elemente u desno (od"+ (this.outerLoopIndex + 1) +". elementa) kreèuæi se prema poèetku polja dok ne pronaðete odgovarajuæe mjesto za pomoænu varijablu.";
		String copyMessage = "Nastavite pomicati elemente u desno kreèuæi se prema poèetku polja dok ne pronaðete odgovarajuæe mjesto za pomoænu varijablu.";
		// moguæe dodati da samo kao prvo ovo ispisuje, inaèe ne
		String endMessage = "Završite "+ this.outerLoopIndex +". prolaz polja koristeæi pomoænu varijablu.";
		String copyErrorMessage = "Elementi se pomièu tako da se lijevi element kopira u njemu susjedni desni element ukoliko je veæi od pomoæne varijable.";
		String copyOrderErrorMessage = "Prilikom kopiranja elementa paziti na redoslijed kopiranja. (npr. element 1 se kopira na mjesto elementa 2)";
		
		if (isOrderError(userAlgorithmPosition)){
			return copyOrderErrorMessage;
		}
		else {
			if (super.getAlgorithmIndexJ() == helpVariableIndex){
				return startMessage;
			}
			else if (super.getAlgorithmIndexI() == helpVariableIndex){
				return endMessage;
			}
			else {
				//ako je sad tek došao u petlju
				if (super.getPreviousAlgorithmPosition().getAlgorithmIndexJ() == helpVariableIndex){
					return copyFirstMessage;
				}
				//ako nisu susjedni indexi
				else if (Math.abs(userAlgorithmPosition.getAlgorithmIndexI() - userAlgorithmPosition.getAlgorithmIndexJ()) > 1){
					return copyErrorMessage;
				}
				else {
					return copyMessage;
				}
			}
		}		
	}
	
	private boolean isOrderError(AlgorithmPosition userAlgorithmPosition){
		return (userAlgorithmPosition.getAlgorithmIndexI() == super.getAlgorithmIndexJ() && userAlgorithmPosition.getAlgorithmIndexJ() == super.getAlgorithmIndexI());
	}
}
