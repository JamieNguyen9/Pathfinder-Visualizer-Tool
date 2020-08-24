package algorithms;

/*
 * Object prepresenting blocks in the grid. This includes start and end node,
 * walls and open spaces. Allows the node to store calculations and position data.
 */

public class Node {
	
	private int x, y;
	private Node parent;
	
	// Constructor
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Accessors and Mutators
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public void setParent(Node p) {
		this.parent = p;
	}
	
	public static boolean isEqual(Node n1, Node n2) {
		return ( n1.getX() == n2.getX() ) && ( n1.getY() == n2.getY() );
	}
}
