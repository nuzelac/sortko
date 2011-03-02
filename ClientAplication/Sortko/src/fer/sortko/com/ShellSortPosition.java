package fer.sortko.com;

import fer.sortko.com.ShellSort.ShellPosition;

public class ShellSortPosition extends AlgorithmPosition{
	private static boolean checkOrder = true;
	private static int helpVariableIndex = 8;
	private int outerLoopIndex;
	private int step = 0;
	private int i,j;
	private ShellPosition sp;
	
	public ShellSortPosition(int i, int j, int[] currentNumbersList, int help, int outerLoopIndex, int step, ShellPosition sp) {
		super(i, j, currentNumbersList, checkOrder, help);
		this.outerLoopIndex  = outerLoopIndex;
		this.i = i;
		this.j = j;
		this.step = step;
		this.sp = sp;
	}
	
	@Override
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition){
		String stepStart = "Zapoènite prolaz niza s korakom hk = " + step + ". Najprije kopirajte "+(i+1)+". element u pomoænu varijblu.";
		String stepContinue = "Nastavite s prolazom niza s korakom hk = " + step + ". Najprije kopirajte "+(i+1)+". element u pomoænu varijblu.";
		String stepCompare = "Usporedite elemente na poziciji " + i + " i " + j + ". Po potrebi napravite pomak s pozicije " + i +" na poziciju " + j +".";
		String stepReturn = "Obavite povratak pomoæne varijable na odgovarajuæu poziciju.";
		String copyOrderErrorMessage = "Prilikom kopiranja elementa paziti na redoslijed kopiranja. (npr. element 1 se kopira na mjesto elementa 2)";
		
		if (isOrderError(userAlgorithmPosition)){
			return copyOrderErrorMessage;
		}
		else if (step == 1) {
			return "Obavite sortiranje insertion sortom. (hk = 1)";
		}
		else {
			if (sp == ShellPosition.stepStart){
				if (outerLoopIndex == this.step){
					return stepStart;
				}
				else {
					return stepContinue;
				}
			}
			if (sp == ShellPosition.stepCompare){
				return stepCompare;
			}
			else {// (sp = ShellPosition.stepReturn){
				return stepReturn;
			}
		}
	}
	private boolean isOrderError(AlgorithmPosition userAlgorithmPosition){
		return (userAlgorithmPosition.getAlgorithmIndexI() == super.getAlgorithmIndexJ() && userAlgorithmPosition.getAlgorithmIndexJ() == super.getAlgorithmIndexI());
	}
}
