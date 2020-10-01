package algorithms;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
	@Override
	public int compare(Node n1, Node n2) {
		if(n1.getValue() < n2.getValue() ){
			return -1;
		}
		return 1;
	}
}
