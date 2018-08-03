import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Node implements Comparable<Node> {

	static private int n;
	Queen[] state;
	private ArrayList<Node> neighbors;
	private int conflicts;

	public Node(){
		state = new Queen[n];
		neighbors = new ArrayList<Node>();
		conflicts = 0;
	}
	
	public Node(Queen[] queens){
		state = new Queen[n];
		for (int i = 0; i < n; i++) {
			state[i] = queens[i];
		}		
		neighbors = this.getNeighbors();
		conflicts= this.getConflicts();
	
	}


	public static int getSize() {
		return n;
	}

	public Queen[] getState() {
		return state;
	}

	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}

	public int getConflicts() {
		return conflicts;
	}

	public static void setSize(int n) {
		Node.n = n;
	}

	public void setState(Queen[] state) {
		Queen[] stateTemp = new Queen[n];
		for(int i=0;i<n;i++){
			stateTemp[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
		this.state = stateTemp;
	}

//	public void setNeighbors(ArrayList<Node> neighbors) {
//		this.neighbors = neighbors;
//	}

	public void setConflicts(int conflicts) {
		this.conflicts = conflicts;
	}
	public void specifiedState(int[] array){
		for(int i=0; i<Node.getSize();i++){
			this.state[i] = new Queen(array[i], i); 
		}
//		this.setState(state);
	}
	public void randomState(){
//		Integer[] data = new Integer[n];
//		for (int i=0; i<n; i++) {
//		    data[i] = i;
//		}
//		Collections.shuffle(Arrays.asList(data));
//		for (int i=0; i<n; i++) {
//		    this.state[i] = new Queen(data[i],i);
//		}
		Random rn = new Random();
		for (int i=0; i<n; i++) {
			this.state[i] = new Queen (rn.nextInt(n-1),i);
		}
	}

	public void calcHeuristic() {
		conflicts =0;
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				if(state[i].attacking(state[j])){
					conflicts++;
				}
			}
		}
	}
	
	public void findNeighbors(){
		this.neighbors = new ArrayList<Node>();
		int index=0;
		for(int i=0;i<n;i++){
			for(int j=1;j<n;j++){
				Node temp = new Node();
				temp.state = new Queen[n];
				temp.neighbors = new ArrayList<Node>();
				for (int k=0;k<n;k++){
					temp.state[k] = new Queen(this.state[k].getRow(), this.state[k].getColumn());
				}
				temp.state[i].move(j);
				temp.calcHeuristic();
				neighbors.add(index, temp);
				index++;
				
			}
		}
	}

	@Override
	public int compareTo(Node n){
		if (this.conflicts < n.conflicts)
			return -1;
		else if (this.conflicts > n.conflicts)
			return 1;
		return 0; 
	}

	public Node getRandomneighbor(Node node) {
		Random rn = new Random();
		int column = rn.nextInt(n);
		int j = rn.nextInt(n - 1) + 1;
		Node neighbor = new Node(node.getState());
		neighbor.getState()[column].move(j);
		neighbor.calcHeuristic();
		return neighbor;
	}
	
	
}
