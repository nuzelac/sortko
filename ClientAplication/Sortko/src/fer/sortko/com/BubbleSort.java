package fer.sortko.com;

public class BubbleSort extends Algorithm{
	private BubbleSortPosition lastChangePosition = null;
	private BubbleSortPosition positionToReturn = null;
	private int switchCount = 0;
	private Boolean positionReturned = false;
	int N = super.getNumbersCopy().length;
	
	public BubbleSort (int numberOfElements){
		super(numberOfElements);
	}
	
	public AlgorithmPosition findSwitch(){
		positionReturned = false;
		switchCount = 0;
		int[] A = super.getNumbersCopy();
		int i = 0, j = 0, pom = 0;
		
		this.lastChangePosition = new BubbleSortPosition(0, 0, A, 0);
		this.positionToReturn = new BubbleSortPosition(0, 0, A, 0);
		this.positionToReturn.setPreviousAlgorithmPosition(lastChangePosition);
		
		for (i = 0; i < N-1; i++) {
			for (j = 0; j < N-1-i; j++) {
				if (A[j+1] < A[j]){					
					SetAlgorithmPosition(j,j+1,A,i);					
					pom = A[j];
					A[j] = A[j+1];
					A[j+1] = pom;
				}
			}
		}
		
		return this.positionToReturn;
	}
	//TODO: promjeniti u outerLoopIndex
	private void SetAlgorithmPosition(int i, int j, int[]A, int indexOuterLoop){
		switchCount++;
		if (super.switchNumber == switchCount){
			this.positionToReturn = new BubbleSortPosition(i, j, A.clone(), indexOuterLoop);
			positionToReturn.setPreviousAlgorithmPosition(this.lastChangePosition);
			positionReturned = true;
			
		}
		if(!positionReturned){
			this.lastChangePosition = new BubbleSortPosition(i, j, A.clone(), indexOuterLoop);
		}
	}
}
