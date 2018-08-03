
public class SimulatedAnnealing {
	private static int solvedSA;
	private static int nodeCounterSA;
	private static int successNodeCounterSA;
	
	public Node simulatedAnnealing(Node init, double temp, double schedule){
		Node n = new Node();
		n.setState(init.getState());
		n.setConflicts(init.getConflicts());
		int tempSuccessNodeCounter = 0;
		int hDifference;
		double probability;
		Node nextNode = new Node();
		
		while(n.getConflicts()!=0 && temp>0){
			nextNode = n.getRandomneighbor(n);
			nextNode.calcHeuristic();
			nodeCounterSA++;
			tempSuccessNodeCounter++;
			
			if(nextNode.getConflicts()==0){
				solvedSA++;
				successNodeCounterSA+=tempSuccessNodeCounter;
				return nextNode;
			}
			hDifference = nextNode.getConflicts()-n.getConflicts();
			if (hDifference<0){
				n.setState(nextNode.getState());
				n.setConflicts(nextNode.getConflicts());
			}else{
				probability = Math.exp(hDifference/temp);
				if (Math.random()<=probability){
					n.setState(nextNode.getState());
					n.setConflicts(nextNode.getConflicts());
				}
				
			}
			temp = temp * schedule;
		}
		
		return n;
		
	}

	public static int getNodeCounterSA() {
		return nodeCounterSA;
	}

	public static void setNodeCounterSA(int n) {
		nodeCounterSA = n;
	}

	public static int getSolvedSA() {
		return solvedSA;
	}

	public static void setSolvedSA(int n) {
		solvedSA = n;
	}

	public static int getSuccessNodeCounterSA() {
		return successNodeCounterSA;
	}

	public static void setSuccessNodeCounterSA(int n) {
		successNodeCounterSA = n;
	}
}
