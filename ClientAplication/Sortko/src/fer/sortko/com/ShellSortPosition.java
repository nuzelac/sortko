package fer.sortko.com;

import fer.sortko.com.ShellSort.ShellPosition;

public class ShellSortPosition extends AlgorithmPosition{
	private static boolean checkOrder = true;
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
	public String getHelpMessage(AlgorithmPosition userAlgorithmPosition, boolean isSwitchSuccessful){
		String stepStart = "Zapoènite sortiranje s korakom hk = " + step + ". Najprije kopirajte "+(i+1)+". element u pomoænu varijablu.";
		String stepContinue = "Nastavite sortiranje s korakom hk = " + step + ". Najprije kopirajte "+(i+1)+". element u pomoænu varijablu.";
		String stepCompare = "Usporedite " + (i+1) + ". i " + (j+1) + ". element. Po potrebi napravite pomak " + (i+1) +". elementa na poziciju " + (j+1) +". elementa.";
		String stepReturn = "Obavite povratak pomoæne varijable na odgovarajuæu poziciju.";
		String stepInsertionSort = "Obavite sortiranje Insertion sortom. (hk = 1)";
		String copyOrderErrorMessage = "Prilikom kopiranja elemenata niza paziti na redoslijed kopiranja. Prvo se odabire element koji se kopira, a zatim odredište kopiranja.";
		
		if (userAlgorithmPosition == null){
			return stepStart;
		}
		if (isSwitchSuccessful){
			if (step == 1) {
				return stepInsertionSort;
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
		else {
			if (isOrderError(userAlgorithmPosition)){
				return copyOrderErrorMessage;
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
				else {//(sp = ShellPosition.stepReturn){
					return stepReturn;
				}
			}
		}
	}
	private boolean isOrderError(AlgorithmPosition userAlgorithmPosition){
		return (userAlgorithmPosition.getAlgorithmIndexI() == super.getAlgorithmIndexJ() && userAlgorithmPosition.getAlgorithmIndexJ() == super.getAlgorithmIndexI());
	}
}
