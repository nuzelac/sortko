package fer.sortko.com;

import java.util.Random;

import android.text.format.Time;

public abstract class Algorithm {
	// *shell, *bubble, *insertion, *selection, merge, quick
	private int[] numbers;
	public int help;
	int switchNumber = 1;
	int numberOfElements = 0;
	public static int NEGATIVE_POINTS = 1000;
	public boolean NEEDS_HELP_VARIABLE = false;
	private Time switchTime = new Time();

	public Algorithm(int numberOfElements) {
		this.setNumbers(randomList(numberOfElements));
		this.numberOfElements = numberOfElements;
		this.switchTime.setToNow();
		
	}
	public abstract AlgorithmPosition findSwitch();
	
	public boolean isNextPosition(AlgorithmPosition positionToCheck){
		return findSwitch().equals(positionToCheck);
	}
	public boolean isFinished(){
		AlgorithmPosition ap = this.findSwitch();
		return (ap.getAlgorithmIndexI() == ap.getAlgorithmIndexJ());
	}
	
	public long goToNextPosition(AlgorithmPosition userPosition){
		
		if(isNextPosition(userPosition)){
			switchNumber++;
			return calculatePoints();
		}
		else return 0;
	}
	private long calculatePoints(){
		// 10000 * 1/1.5^(x) + 1000
		// preinaèiti da svaki algoritam može maximalno 999 999 / 6 osvojiti
		// i to ukoliko ima maximalni moguæi broj promjena
		
		Time currentTime = new Time();
		currentTime.setToNow();
		double sec =  (currentTime.toMillis(true) - switchTime.toMillis(true))/1000;
    	switchTime.setToNow();
		double x = 1000 + 10000 * 1./Math.pow(1.5,sec);

    	return (long)Math.floor(x);
		
	}
	
	protected void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	protected int[] getNumbersCopy(){
		int[] a = new int[numberOfElements];
		for(int i=0; i < numberOfElements; i++ ){
			a[i] = numbers[i];
		}
		return a;
	}
	
	private int[] randomList(int numberOfElements){
    	
    	Random randomGenerator = new Random();
    	int[] list = new int[8];
    	
    	for (int i = 0; i < numberOfElements; i++){
          list[i] = randomGenerator.nextInt(10);
        }
        return list;
    }
	
	public void reset(){
		this.switchNumber = 1;
		setNumbers(randomList(numberOfElements));
	}
}
