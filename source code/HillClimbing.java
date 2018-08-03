import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HillClimbing {

	private static int solvedHC;
	private static int nodeCounterHC;
	private static int successNodeCounterHC;
	
	public Node SteepestHillClimbing(Node n){
		Node initialNode = new Node();
		initialNode.setState(n.getState());
		initialNode.calcHeuristic();
		
		Node betterNode = new Node();
		betterNode.setState(n.getState());
		betterNode.calcHeuristic();
		int tempsuccessNodeCounter=0;
		
		
		while(true){
			initialNode.findNeighbors();
			ArrayList<Node> neighbors = initialNode.getNeighbors();
			nodeCounterHC +=neighbors.size();
			tempsuccessNodeCounter+=neighbors.size();
			
			boolean existBetter=false;
			
			for(int i=0;i<neighbors.size();i++){
				if(neighbors.get(i).compareTo(initialNode)<0){
					betterNode.setState(neighbors.get(i).getState());
					betterNode.calcHeuristic();
					existBetter=true;
				}
			}
			if (!existBetter){
				if (betterNode.getConflicts()==0){
					solvedHC++;
					successNodeCounterHC+=tempsuccessNodeCounter;
				}
				return betterNode;
			}
			
			initialNode.setState(betterNode.getState());
			initialNode.setConflicts(betterNode.getConflicts());
		}
	}
	public static int getSolvedHC() {
		return solvedHC;
	}
	public static int getNodeCounterHC() {
		return nodeCounterHC;
	}
	
	public static void setSolvedHC(int n) {
		solvedHC = n;
	}
	public static void setNodeCounterHC(int n) {
		nodeCounterHC = n;
	}
	public static int getSuccessNodeCounterHC() {
		return successNodeCounterHC;
	}
	public static void setSuccessNodeCounterHC(int n) {
		successNodeCounterHC = n;
	}
	
}
