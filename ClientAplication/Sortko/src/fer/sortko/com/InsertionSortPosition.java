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
	
	public InsertionSortPosition(int i, int j, int[] currentNumbersList, int help) {
		super(i, j, currentNumbersList, checkOrder, help);
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition){
		
		
		return "There is no help message!";
	}
}
