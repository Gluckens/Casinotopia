package ca.uqam.casinotopia.vue.roulette;

import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

public class ImagePosition {
	private Shape shape;
	//private Point[] arrPoint;
	List<Point> lstPoints;
	
	public ImagePosition(Shape shape) {
		//this(shape, new Point[4]);
		this(shape, new ArrayList<Point>());
	}
	
	public ImagePosition(Shape shape, List<Point> lstPoints) {
		this.shape = shape;
		this.lstPoints = lstPoints;
	}
	
	public void addPoint(Point p) {
		this.lstPoints.add(p);
	}
	
	public void setPoint(int index, Point p) {
		try {
			this.lstPoints.add(index, p);
		} catch(IndexOutOfBoundsException e) {
			
		}
	}
	
	public Point getPoint(int index) {
		try {
			return this.lstPoints.get(index);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public boolean contains(Point p) {
		return shape.contains(p);
	}
	
	/*public void setPoint(int indice, Point point) {
		if(indice >= 0 && indice < 4) {
			this.arrPoint[indice] = point;
		}
	}
	
	public Point getPoint(int indice) {
		return this.arrPoint[indici]
	}*/
}
