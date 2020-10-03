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
	PriorityQueue<Node> frontier;
	HashSet<Node> explored;	
	boolean isFound;
	boolean fail;
	
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
	}
	
	public Algorithm(int size) {
		this.size = size;
		startNode = null;
		endNode = null;
	}
	
	public void addWindow(Window w) {
		this.w = w;
	}
	
	public String getName() {
		return name;
	}
	
	public void start(Node s, Node e, ArrayList<Node> walls) {
		
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
	
	public void reset() {
		startNode = null;
		endNode = null;
		frontier = new PriorityQueue<>();
		explored = new HashSet<>();
		path = new ArrayList<>();
		isFound = false;
		fail = false;
	}
	
	public boolean isFail() {
		return fail;
	}
	
	public int manhattanCost(Node n) {
		int[] endCoords = endNode.getCoord();
		int[] currCoords = n.getCoord();
		
		int dist = Math.abs(endCoords[0] - currCoords[0]) + Math.abs(endCoords[1] - currCoords[1]);
		return dist;
	}
	
	public ArrayList<Node> backtrack(Node n, Node e, HashMap<Node, Node> parent) {
		ArrayList<Node> res = new ArrayList<>();
		res.add(e);
		while(!res.get(0).equals(n)) {
			res.add(0, parent.get(res.get(0)));
		}
		
		return res;
	}
	
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
