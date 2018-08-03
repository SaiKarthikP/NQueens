import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		SimulatedAnnealing sa = new SimulatedAnnealing();
		HillClimbing hc = new HillClimbing();
		int numOfQueens=8;
		Node.setSize(numOfQueens);
		int solCounter=0;
		int instances =5;
		for (int i=0;i<instances;i++){
			Node init = new Node();
			init.randomState();
			init.calcHeuristic();
			int initTemp=1;
	        for(int j=1; j<=numOfQueens; j++)
	        {
	            initTemp = initTemp*j;
	        }
	        long startTime = System.nanoTime();
			Node sol = sa.simulatedAnnealing(init, 1000000, .99);
			long endTime = System.nanoTime();
			
//			Node sol = hc.SteepestHillClimbing(init);
			if (sol.getConflicts()==0){
				solCounter++;
				System.out.println("Initial state:");
				System.out.println(Arrays.toString(init.getState()));
				System.out.println("Number of attacking pairs: " +init.getConflicts());

//				System.out.println("Steepest Hill Climbing solution:");
				System.out.println("Simulated Annealing solution: ");
				
		        System.out.println(Arrays.toString(sol.getState()));
				System.out.println("Number of attaching pairs: " + sol.getConflicts());
				System.out.println(endTime-startTime);
				System.out.println();
			}
		}
		System.out.println("Number of queens: " + numOfQueens + " Total attempts: " + instances + " Total solved: " + solCounter);


	}
}
