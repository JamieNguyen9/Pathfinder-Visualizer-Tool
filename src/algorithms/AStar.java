package algorithms;

import main.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.HashMap;

public class AStar extends Algorithm {

	public AStar(Window w, int size) {
		super(w, size);
		name = "AStar";
	}
	
	public AStar(int size) {
		super(size);
		name = "AStar";
		
	}
	
	public void start(Node s, Node e, ArrayList<Node> walls) {
		startNode = s;
		endNode = e;
		
		frontier = new PriorityQueue<>(10000, new NodeComparator());
		explored = new HashSet<>();
		HashMap<Node, Node> parent = new HashMap<>();
		frontier.add(s);
		
		while(!frontier.isEmpty()) {
			Node currState = frontier.poll();
			
			if(currState.equals(e)) {
				System.out.println("goal");
				path = backtrack(startNode, currState, parent);
				isFound = true;
				break;
			}
			explored.add(currState);
			
			for(Node n: getNeighbors(currState)) {
				int h = manhattanCost(n);
					
				// check in walls
				boolean contains = false;
				for(Node e1 : walls) {
					if(e1.equals(n)) {
						contains = true;
					}
				}
				
				// check in explored
				for(Node e1 : explored) {
					if(e1.equals(n)) {
						contains = true;
					}
				}
				
				if(contains) {
					continue;
				}
				
				
				if(!frontier.contains(n)) {
					parent.put(n, currState);
					int g = backtrack(startNode, n, parent).size();
					n.setValue(h + g);
					frontier.add(n);
				}
				else if(frontier.contains(n)) {
					HashMap<Node, Node> temp_parent = new HashMap<>(parent);
					temp_parent.put(n, currState);
					int g = backtrack(startNode, n, temp_parent).size();
					n.setValue(g+h);
					
					ArrayList<Node> tempList = new ArrayList<>(frontier);
					Node fNode = null;
					for(Node temp: tempList) {
						fNode = temp;
						if(fNode.equals(n)) {
							break;
						}
					}
					System.out.println(fNode);
					if(fNode.getValue() > n.getValue()) {
						parent.put(n, currState);
						frontier.remove(fNode);
						frontier.add(n);
					}
					
				}
			}
		}
		if(!isFound) {
			fail = true;
			System.out.println("No solution");
		}
	}
	


}
