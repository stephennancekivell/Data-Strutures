package info.stephenn.datastructures;

import info.stephenn.datastructures.KDTree.Node.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KDTree {
	
	public static Node build(List<Point> points, int depth){
		if (points.isEmpty()) return null;
		
		final int axis = depth % (points.get(0).values.length);
		
		Collections.sort(points, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if (o1.values[axis] < o2.values[axis])
					return -1;
				else if (o1.values[axis] == o2.values[axis])
					return 0;
				else return 1;
			}
		});
		
		int median = points.size() / 2;
		
		Node node =  new Node(points.get(median), axis);
		node.left = build(points.subList(0, median), depth+1);
		node.right = build(points.subList(median+1, points.size()), depth+1);
		return node;
	}
	
	public static class Node {
		final Point point;
		final int axis;
		Node left = null;
		Node right = null;
		
		public Node(Point point, int axis){
			this.point = point;
			this.axis = axis;
		}
		
		public Node getChild(Point lookup) {
			if (point.values[axis] < lookup.values[axis] && right != null)
					return right.getChild(lookup);
			else if (Arrays.equals(point.values, lookup.values))
				return this;
			else if ( point.values[axis] > lookup.values[axis] && left != null)
				return left.getChild(lookup);
			else
				return null;
		}
		
		public Node findClosest(Node currentBest, Point toPoint){
			if (currentBest == null) currentBest = this;
			
			// consider current.
			if (point.squaredDistanceTo(toPoint) < currentBest.point.squaredDistanceTo(toPoint))
				currentBest = this;
			
			// the near child.
			Node nearestChild = this.getChildNear(toPoint);
			if (nearestChild != null)
				currentBest = nearestChild.findClosest(currentBest, toPoint);
			
			// the far child. maybe.
			if (this.point.squaredAxisDistanceTo(toPoint, axis) <= currentBest.point.squaredDistanceTo(toPoint)){
				Node childFar = this.getChildFar(toPoint);
				if (childFar != null)
					currentBest = childFar.findClosest(currentBest, toPoint);
			}	
			
			return currentBest;
		}
		
		/*
		 * Returns the closest point. left on equal.
		 */
		public Node getChildNear(Point toPoint){
			if (isRightChildNearest(toPoint))
				return right;
			else return left;
		}
		
		private boolean isRightChildNearest(Point toPoint){
			return point.values[axis] < toPoint.values[axis];
		}
		
		public Node getChildFar(Point fromPoint){
			if (!isRightChildNearest(fromPoint))
				return right;
			else return left;
		}
		
		public static class Point {
			public final int[] values;
			public Point(int[] _values){
				this.values = _values;
			}
			
			public int squaredDistanceTo(Point p){
				int distance = 0;
				for (int i=0; i < values.length; i++){
					int diff = values[i]-p.values[i];
					distance += diff*diff;
				}
				return distance;
			}
			public int squaredAxisDistanceTo(Point p, int axis){
				int diff = this.values[axis] - p.values[axis];
				return diff*diff;
			}
		}

		@Override
		public String toString() {
			String ret = "("+this.axis+","+ Arrays.toString(point.values);
			if (left != null)
				ret += left;
			
			ret += ",";
			if (right != null)
				ret += right;
			
			ret += ") ";
			return ret;
		}
	}
}