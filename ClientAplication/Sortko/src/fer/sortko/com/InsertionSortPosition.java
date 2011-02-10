package fer.sortko.com;

/*Insertion Sort
1.	Krenite u 1. prolaz polja po�ev�i s 1. elementom. Pri tome koristite pomo�nu varijablu. 
2.	Pomi�ite elemente u desno  kre�u�i se prema po�etku polja dok ne prona�ete odgovaraju�e mjesto za pomo�nu varijablu.
3.	Zavr�ite 1. prolaz polja koriste�i pomo�nu varijablu.
Mogu�e poruke gre�ke:
1.	Pomo�na varijabla nije dobro izabrana.
2.	Redoslijed kopiranja nije po�tivan.
3.	Pomo�na varijabla nije dobro vra�ena.
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
		String startMessage = "Krenite u "+ this.outerLoopIndex +". prolaz polja po�ev�i s "+ (this.outerLoopIndex + 1) +". elementom. Pri tome koristite pomo�nu varijablu.";
		String copyFirstMessage = "Pomi�ite elemente u desno (od"+ (this.outerLoopIndex + 1) +". elementa) kre�u�i se prema po�etku polja dok ne prona�ete odgovaraju�e mjesto za pomo�nu varijablu.";
		String copyMessage = "Nastavite pomicati elemente u desno kre�u�i se prema po�etku polja dok ne prona�ete odgovaraju�e mjesto za pomo�nu varijablu.";
		// mogu�e dodati da samo kao prvo ovo ispisuje, ina�e ne
		String endMessage = "Zavr�ite "+ this.outerLoopIndex +". prolaz polja koriste�i pomo�nu varijablu.";
		String copyErrorMessage = "Elementi se pomi�u tako da se lijevi element kopira u njemu susjedni desni element ukoliko je ve�i od pomo�ne varijable.";
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
				//ako je sad tek do�ao u petlju
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
