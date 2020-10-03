package algorithms;

import java.util.ArrayList;

/*
 * Object prepresenting blocks in the grid. This includes start and end node,
 * walls and open spaces. Allows the node to store calculations and position data.
 */

public class Node {
	
	private int x, y;
	private Node parent;
	private int value;
	private int g;
	
	// Constructor
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		value = 10000;
		g = 0;
	}
	
	// Accessors and Mutators
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int[] getCoord() {
		int[] res = new int[2];
		res[0] = this.x;
		res[1] = this.y;
		return res;
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
	
	public int getValue() {
		return this.value;
	}
	
	public int getG() {
		return this.g;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public void setValue(int n) {
		this.value = n;
	}
	
	public boolean equals(Node n) {
		return ( this.getX() == n.getX() ) && ( this.getY() == n.getY() );
	}
	
	public String toString() {
		ArrayList<Integer> res = new ArrayList<>();
		res.add(this.x);
		res.add(this.y);
		return res.toString();
	}

}

