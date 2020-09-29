package algorithms;

import main.*;
import java.util.ArrayList;

public abstract class Algorithm {

	Node startNode;
	Node endNode;
	String name;
	Window w;
	int size;
	
	public Algorithm(Window w, int size) {
		this.w = w;
		this.size = size;
		startNode = null;
		endNode = null;
		
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
	
}
