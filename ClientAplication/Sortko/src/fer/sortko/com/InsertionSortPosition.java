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
	
	public InsertionSortPosition(int i, int j, int[] currentNumbersList, int help) {
		super(i, j, currentNumbersList, checkOrder, help);
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition){
		
		
		return "There is no help message!";
	}
}
