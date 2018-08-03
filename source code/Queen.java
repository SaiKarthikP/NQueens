
public class Queen {

	private int row;
	private int column;
	
	
	public Queen(int r, int c){
		this.row = r;
		this.column = c;
	}
	
	public boolean attacking(Queen q){
		if (this.row==q.getRow()|| this.column==q.getColumn()||Math.abs(column-q.getColumn())==Math.abs(row-q.getRow()))
			return true;
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void move(int j) {
		// TODO Auto-generated method stub
		this.row = (this.row+j)%Node.getSize();
	}
	public String toString(){
		return "(" + this.row + "\t" + this.column + ")";
	}
}

