package fer.sortko.com;

import java.util.Random;

import android.text.format.Time;

public abstract class Algorithm {
	private int[] numbers;
	public int help;
	int switchNumber = 1;
	int numberOfElements = 0;
	public static int NEGATIVE_POINTS = 1000;
	public boolean NEEDS_HELP_VARIABLE = false;
	private boolean addBonus = true;
	private boolean speedBonus = true;
	private int switchAttempt = 0;

	private double algorithmDifficulty = 1;
	private Time lastSwitchTime = new Time();
	private Time startTime = new Time();

	public Algorithm(int numberOfElements) {
		this.setNumbers(randomList(numberOfElements));
		this.numberOfElements = numberOfElements;
		this.startTime.setToNow();
		this.lastSwitchTime.setToNow();
		this.algorithmDifficulty = (double)getAlgorithmDifficulty();
		
	}
	public abstract AlgorithmPosition findSwitch();
	
	public abstract int getAlgorithmDifficulty();
	
	public boolean isNextPosition(AlgorithmPosition positionToCheck){
		return findSwitch().equals(positionToCheck);
	}
	public boolean isFinished(AlgorithmPosition ap){
		return (ap.getAlgorithmIndexI() == 0 && ap.getAlgorithmIndexJ() == 0);
	}
	
	public long goToNextPosition(AlgorithmPosition userPosition){
		
		if(isNextPosition(userPosition)){
			switchNumber++;
			switchAttempt++;
			return calculatePoints();
		}
		else {
			switchAttempt++;
			return 0;
		}
	}
	private long calculatePoints(){
		// TODO: dodati da kao parametar uzima najduži period za uspješan potez i brainstormati ostale promjene
		
		Time currentTime = new Time();
		currentTime.setToNow();
		
		double gameDuration = (currentTime.toMillis(true) - startTime.toMillis(true))/1000;
		
		if (gameDuration > (15 * algorithmDifficulty)){
			addBonus = false;
		}
		
		double switchDuration =  (currentTime.toMillis(true) - lastSwitchTime.toMillis(true))/1000;
		
		if (switchDuration > 10){
			algorithmDifficulty *= 0.9;
		}
		
    	double points = algorithmDifficulty * 1000;
    	
    	if (addBonus){
    		points += points * 10 + algorithmDifficulty * 1./Math.pow(1.5,switchDuration);
    	}
    	
    	points = points / switchAttempt;
    	
    	lastSwitchTime.setToNow();
    	this.switchAttempt = 0;
    	
    	return (long)Math.floor(points);	
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
		int generatedNumbersCount = 0;
		int number;
    	Random randomGenerator = new Random();
    	int[] list = new int[8];
    	int[] digitCount = new int[10];
    	
    	while (generatedNumbersCount < 8){
    		number = randomGenerator.nextInt(10);
    		if (digitCount[number] < 2){
    			list[generatedNumbersCount] = number;
    			digitCount[number]++;
    			generatedNumbersCount++;
    		}
        }
        return list;
    }
}
