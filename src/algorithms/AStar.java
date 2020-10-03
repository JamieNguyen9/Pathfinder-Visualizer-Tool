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
		
		frontier = new PriorityQueue<>(5000, new NodeComparator());
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
					
				// check in walls and explored
				boolean contains = false;
				for(Node e1 : walls) {
					if(e1.equals(n)) {
						contains = true;
					}
				}
				for(Node e2 : explored) {
					if(e2.equals(n)) {
						contains = true;
					}
				}
				
				if(contains) {
					continue;
				}
							
				// check if n is in frontier
				boolean inFrontier = false;
				ArrayList<Node> t_front = new ArrayList<>(frontier);
				for(Node e3: t_front) {
					if(n.equals(e3)) {
						inFrontier = true;
						break;
					}
				}
				
				// if it is not in frontier set value and add to frontier
				if(!inFrontier) {
					parent.put(n, currState);
					int g = backtrack(startNode, n, parent).size();
					n.setG(g);
					n.setValue(h + g);
					frontier.add(n);
				}
				else if(inFrontier) {
					// if it is in frontier check if f value is less than that in the frontier
					HashMap<Node, Node> temp_parent = new HashMap<>(parent);
					for(Node e1: parent.keySet()) {
						if(e1.equals(n)) {
							temp_parent.put(n, currState);
						}
						else {
							temp_parent.put(e1, parent.get(e1));
						}
					}
					
					temp_parent.put(n, currState);
					int g = backtrack(startNode, n, temp_parent).size();
					n.setG(g);
					n.setValue(g+h);
					
					ArrayList<Node> tempList = new ArrayList<>(frontier);
					Node fNode = null;
					for(Node temp: tempList) {
						fNode = temp;
						if(fNode.equals(n)) {
							break;
						}
					}
					
					if(fNode.getValue() > n.getValue()) {
						// update parent
						parent = new HashMap<>(temp_parent);
						
						// add to frontier
						frontier.clear();
						for(int i = 0; i < tempList.size(); i ++) {
							if(tempList.get(i).equals(n)) {
								frontier.add(n);
							}
							else {
								frontier.add(tempList.get(i));
							}
						}
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
