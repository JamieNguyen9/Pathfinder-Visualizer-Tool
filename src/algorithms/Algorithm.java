package algorithms;

import main.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public abstract class Algorithm {

	Node startNode;
	Node endNode;
	String name;
	Window w;
	int size;
	ArrayList<Node> path;
	ArrayList<Node> walls;
	PriorityQueue<Node> frontier;
	HashMap<Node, Node> parent;
	HashSet<Node> explored;	
	boolean isFound;
	boolean fail;
	boolean setRun;
	
	public Algorithm(Window w, int size) {
		this.w = w;
		this.size = size;
		startNode = null;
		endNode = null;
		frontier = new PriorityQueue<>();
		explored = new HashSet<>();
		path = new ArrayList<>();
		isFound = false;
		fail = false;
		setRun = false;
	}
	
	
	public boolean isRunning() {
		return setRun;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setup(Node s, Node e, ArrayList<Node> walls) {
		
	}
	
	public void start() {
		
	}
	
	public ArrayList<Node> getPath() {
		return this.path;
	}
	
	public ArrayList<Node> getExplored() {
		return new ArrayList<>(this.explored);
	}
	
	public ArrayList<Node> getFrontier() {
		return new ArrayList<>(this.frontier);
	}
	
	public boolean isFound() {
		return isFound;
	}
	
	// clears the board
	public void reset() {
		startNode = null;
		endNode = null;
		frontier = new PriorityQueue<>();
		explored = new HashSet<>();
		path = new ArrayList<>();
		isFound = false;
		fail = false;
		setRun = false;
	}
	
	public boolean isFail() {
		return fail;
	}
	
	// calculates the manhattan distance (not diagonally)
	public int manhattanCost(Node n) {
		int[] endCoords = endNode.getCoord();
		int[] currCoords = n.getCoord();
		
		int dist = Math.abs(endCoords[0] - currCoords[0]) + Math.abs(endCoords[1] - currCoords[1]);
		return dist;
	}
	
	// returns the path from the node to the root
	public ArrayList<Node> backtrack(Node n, Node e, HashMap<Node, Node> parent) {
		ArrayList<Node> res = new ArrayList<>();
		res.add(e);
		while(!res.get(0).equals(n)) {
			res.add(0, parent.get(res.get(0)));
		}
		
		return res;
	}
	
	
	// retrieves the neighbors of that node
	public ArrayList<Node> getNeighbors(Node n) {
		ArrayList<Node> res = new ArrayList<>();
		int[] coords = n.getCoord();
		
		if(coords[0] - 25 >= 0) {
			res.add(new Node(coords[0] - 25, coords[1]));
		}
		if(coords[1] - 25 >= 0) {
			res.add(new Node(coords[0], coords[1] - 25));
		}
		if(coords[0] + 25 < w.getWidth()) {
			res.add(new Node(coords[0] + 25, coords[1]));
		}
		if(coords[1] + 25 < w.getHeight()) {
			res.add(new Node(coords[0], coords[1] + 25));
		}
		
		return res;
	}
}
