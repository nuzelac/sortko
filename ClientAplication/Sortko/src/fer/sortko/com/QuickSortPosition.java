package fer.sortko.com;
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
	
	public QuickSortPosition(int i, int j, int[] currentNumbersList, boolean checkOrder, int help, QuickSort.QsPosition qsPosition){
		super(i, j, currentNumbersList, checkOrder, help);
		this.outerLoopIndex  = outerLoopIndex;
		this.qsPosition = qsPosition;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition){
		String median = "Izra�unajte medijan od 1. elementa, (lijevog) srednjeg  i 8. tako da najprije usporedite 1. i srednji i zatim 1. i 8..";
		String hidden = "Sakrijte sto�er.";
		String regularqs = "Prona�ite prvi broj ve�i od sto�era tako da krenete u desno od elementa na poziciji x. Prona�ite i prvi broj manji od sto�era gledano lijevo od elementa na poziciji y. Zamjenite prona�ene brojeve po potrebi ili vratite sto�er.";
		String insertion = "Sortirajte podniz (od 1. do 7. elementa) prema Insertion sort algoritmu.";
		
		if (this.qsPosition == QuickSort.QsPosition.median){
			return median;
		}
		else if (this.qsPosition == QuickSort.QsPosition.hidden){
			return hidden;
		}
		else if (this.qsPosition == QuickSort.QsPosition.insertion){
			return regularqs;
		}
		else{//(this.qsPosition == QuickSort.QsPosition.regularqs)
			return insertion;
		}
	}

}
