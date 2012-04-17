package info.stephenn.datastructures;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KDTree {
	
	public static Node build(List<List<Integer>> values, int depth){
		if (values.isEmpty()) return null;
		for(List<Integer> keys : values){
			assert(keys.size() == values.size());
		}
		
		final int axis = depth % (values.get(0).size());
		
		Collections.sort(values, new Comparator<List<Integer>>() {
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.get(axis).compareTo(o2.get(axis));
			}
		});
		
		int median = values.size() / 2;
		
		Node n =  new Node(values.get(median), axis);
		n.left = build(values.subList(0, median), depth+1);
		n.right = build(values.subList(median+1, values.size()), depth+1);
		return n;
	}
	
	public static class Node {
		final List<Integer> keys;
		int axis =0;
		Node left = null;
		Node right = null;
		public Node(List<Integer> keys, int axis){
			this.keys = keys;
			this.axis = axis;
		}
		
		public Node getChild(List<Integer> lookup) {
			int comp = keys.get(axis).compareTo(lookup.get(axis));
			if (comp == 0) {
				return this;
			} else if (comp < 0 && right != null) {
				return right.getChild(lookup);
			} else if (comp > 0 && left != null) {
				return left.getChild(lookup);
			} else {
				return null; // doesnt exist
			}
		}
		
		public Node getNearest(List<Integer> lookup){
			return null;
			//TODO
		}
		
		public Node fildClosest(Node currentBest, List<Integer> toKeys){
			if (currentBest == null) return this;
			
			
			return null;
		}
		
		public int squaredDistanceTo(Node n){
			assert(n.keys.size()== this.keys.size());
			int distance =0;
			for (int i=0; i < n.keys.size(); i++){
				int diff = (keys.get(i)-n.keys.get(i));
				distance += diff * diff;
			}
			return distance;
		}
		
		public static class point{
			public final int[] values;
			public point(int[] _values){
				this.values = _values;
			}
		}
	}
	
}