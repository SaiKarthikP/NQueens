import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int numOfQueens=0; //updated by user
		int instances = 100;
		double schedule = .95;

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of Queens: ");
		numOfQueens = sc.nextInt();
		sc.nextLine();

		System.out.println("Menu: \n\t1.Show successful solutions of both methods(Steepest HillClimbing and Simulated Annealing)" +
							"\n\t2.Show analysis of both methods");
		int input = sc.nextInt();
		sc.nextLine();
		
		if(input==2){
			System.out.println("Computing...");
			
		}
		
		Node.setSize(numOfQueens);
		HillClimbing hc = new HillClimbing();
		SimulatedAnnealing sa = new SimulatedAnnealing();
		
		HillClimbing.setSolvedHC(0);
		HillClimbing.setNodeCounterHC(0);
		SimulatedAnnealing.setSolvedSA(0);
		SimulatedAnnealing.setNodeCounterSA(0);
		
		long totalTimeHC=0;
		long totalTimeSA=0;
		long successTotalTimeHC=0;
		long successTotalTimeSA=0;
		Node initialNode = new Node();
		Node initialNodeSA = new Node();
		int initTemp=1;	//updated below

		
		for(int i=0;i<instances;i++){
			initTemp=1;	//updated below

			initialNode.randomState();
			initialNodeSA.setState(initialNode.getState());
//			int[] array = {2,0,1,3};
//			initialNode.specifiedState(array);
			initialNode.calcHeuristic();
			initialNodeSA.calcHeuristic();
//			initialNode.findNeighbors();
			
			Node HillClimbSolution = new Node();
			long startTimeHC=0;
			long endTimeHC=0;
			if(initialNode.getConflicts()!=0){
				startTimeHC = System.nanoTime();
				HillClimbSolution = hc.SteepestHillClimbing(initialNode);
				endTimeHC   = System.nanoTime();
				totalTimeHC += endTimeHC-startTimeHC;
				
			}
			if (HillClimbSolution.getConflicts()==0){
				successTotalTimeHC+= endTimeHC-startTimeHC;
			}
	        for(int j=1; j<=numOfQueens; j++)
	        {
	            initTemp = initTemp*j;
	        }
	        
			long startTimeSA =0;
			long endTimeSA = 0;
			Node simulatedSolution = new Node();
			
			if(initialNodeSA.getConflicts()!=0){
				startTimeSA = System.nanoTime();
				simulatedSolution = sa.simulatedAnnealing(initialNodeSA, initTemp, schedule);
				endTimeSA = System.nanoTime();
				totalTimeSA += endTimeSA - startTimeSA;	
			}
			
			if (simulatedSolution.getConflicts()==0){
				successTotalTimeSA+= endTimeSA-startTimeSA;
			}
			if(input==1){ 
				
				if (HillClimbSolution.getConflicts()==0){
					System.out.println("initial State:");
					System.out.println(Arrays.toString(initialNode.getState()));
					System.out.println("Number of attacking pairs: "+ initialNode.getConflicts());

					System.out.println("Steepest HillClimbing solution: ");
					System.out.println(Arrays.toString(HillClimbSolution.getState()));
					System.out.println();
				}
				if (simulatedSolution.getConflicts()==0){
					System.out.println("initial State:");
					System.out.println(Arrays.toString(initialNodeSA.getState()));
					System.out.println("Number of attacking pairs: "+ initialNodeSA.getConflicts());

					System.out.println("Simulated Annealing Solution: ");
					System.out.println(Arrays.toString(simulatedSolution.getState()));
					System.out.println();
				}
			}
		}
		if (input==2){
			System.out.println("Number of Queens:" + numOfQueens + ", Number of instances: " + instances + ", Temperature: " + initTemp + ", Schedule:  " + schedule);
			float percentSuccessHC = HillClimbing.getSolvedHC()*100/instances;
			float percentSuccessSA = SimulatedAnnealing.getSolvedSA()*100/instances;
			System.out.println("Percentage solved by Steepest HillClimbing algorithm: " + percentSuccessHC + "%");	
			System.out.println("Percentage solved by Simulated Annealing: " + percentSuccessSA + "%");
			System.out.println("Average searchCost for all attempts of HillClimbing: " + HillClimbing.getNodeCounterHC()/Node.getSize());
			System.out.println("Average searchCost for successful attempts of HillClimbing: " + HillClimbing.getSuccessNodeCounterHC()/HillClimbing.getSolvedHC());
			System.out.println("Average Time (in Nanoseconds) for all attempts of HillClimbing: " + totalTimeHC/100 +" ns.");
			System.out.println("Average Time (in Nanoseconds) for successful attempts of HillClimbing: " + successTotalTimeHC/HillClimbing.getSolvedHC() +" ns.");
			System.out.println("Average searchCost for all attempts of Simulated Annealing: " + SimulatedAnnealing.getNodeCounterSA()/Node.getSize());
			System.out.println("Average searchCost for successful attempts of Simulated Annealing: " + SimulatedAnnealing.getSuccessNodeCounterSA()/SimulatedAnnealing.getSolvedSA());
			System.out.println("Average Time (in Nanoseconds) for all attempts of Simulated Annealing: " + totalTimeSA/instances + " ns.");
			System.out.println("Average Time (in Nanoseconds) for successful attempts of Simulated Annealing: " + successTotalTimeSA/SimulatedAnnealing.getSolvedSA() +" ns.");
			
		}
	}
}
