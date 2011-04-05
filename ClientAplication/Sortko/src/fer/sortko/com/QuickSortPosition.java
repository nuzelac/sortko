package fer.sortko.com;

import fer.sortko.com.QuickSort.QsPosition;

/*
Quicksort
1.	Izra�unajte medijan od 1. elementa, (lijevog) srednjeg  i 8. tako da najprije usporedite 1. i srednji i zatim 1. i 8..
2.	Sakrijte sto�er.
3.	Prona�ite prvi broj ve�i od sto�era tako da krenete u desno od elementa na poziciji x. Prona�ite i prvi broj manji od sto�era gledano lijevo od elementa na poziciji y. Zamjenite prona�ene brojeve po potrebi ili vratite sto�er.
4.	Sortirajte podniz (od 1. do 7. elementa) prema Insertion sort algoritmu.
*/

public class QuickSortPosition extends AlgorithmPosition{
	private static int helpVariableIndex = 8;
	private int outerLoopIndex;
	private QuickSort.QsPosition qsPosition;
	private QuickSort.QsPosition previousQsPosition;
	private int lijevo = 0;
	private int desno = 0;
	
	public QuickSortPosition(int i, int j, int[] currentNumbersList, boolean checkOrder, int help, QuickSort.QsPosition qsPosition, int lijevo, int desno, QuickSort.QsPosition previousQsposition){
		super(i, j, currentNumbersList, checkOrder, help);
		this.setQsPosition(qsPosition);
		this.setPreviousQsPosition(previousQsPosition);
		this.lijevo = lijevo;
		this.desno = desno;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition, boolean isSwitchSuccessful){
		String sortStartMessage = "Zapo�nite algoritam ra�unaju�i medijan od 1., 4. i 8. elementa tako da ih usporedite i po potrebi zamijenite.";
		String median = "Izra�unajte medijan od "+(lijevo+1)+". elementa, (lijevog) srednjeg  i " + (desno+1) + ". elementa tako da ih usporedite i po potrebi zamijenite.";
		String hidden = "Sakrijte sto�er na predzadnju poziciju.";//dodati poruku (promatramo polje od tog do tog elementa)
		String regularQs = "Prona�ite broj ve�i ili jednak sto�eru gledano na desno od "+ (lijevo + 1) +". elementa."
							+" Prona�ite broj manji ili jednak sto�eru na lijevo od "+ desno +". elementa."
							+" Napravite zamjenu ili vratite sto�er.";
		String returnSt = "Vratite sto�er. ("+desno+". element)";
		String insertion = "Sortirajte podniz od "+(lijevo+1)+". do "+(desno+1)+". elementa prema Insertion sort algoritmu.";
		
		if (userAlgorithmPosition == null){
			return sortStartMessage;
		}
		else {
			switch(this.qsPosition){
				case start:
					return sortStartMessage;
				case median12 :
					if (!isSwitchSuccessful){
						return median + " (prvi i srednji element)";
					} else return median;
				case median13 :
					if (!isSwitchSuccessful){
						return median + " (prvi i zadnji element)";
					} else return median;
				case median23 :
					if (!isSwitchSuccessful){
						return median + " (srednji i zadnji element)";
					} else return median;
				case hidden :
					if (!isSwitchSuccessful){
						return hidden + " (zamjenite ga s "+desno+". elementom)";
					} else return hidden;
				case insertion:
					return insertion;
				case regularQs:
					if (this.previousQsPosition == QsPosition.regularQs){
						return "Nastavite dalje. " + regularQs;
					} else {
						return regularQs;
					}
				default:
					if (!isSwitchSuccessful){
					return returnSt;
					}
					else{
						if (this.previousQsPosition == QsPosition.regularQs){
							return returnSt;
						} else {
							return regularQs;
						}
					}
		    }
		}
	}

	private void setQsPosition(QuickSort.QsPosition qsPosition) {
		this.qsPosition = qsPosition;
	}

	public QuickSort.QsPosition getQsPosition() {
		return qsPosition;
	}

	private void setPreviousQsPosition(QuickSort.QsPosition previousQsPosition) {
		this.previousQsPosition = previousQsPosition;
	}

	public QuickSort.QsPosition getPreviousQsPosition() {
		return previousQsPosition;
	}
}
