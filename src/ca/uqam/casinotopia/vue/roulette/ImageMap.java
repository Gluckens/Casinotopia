package ca.uqam.casinotopia.vue.roulette;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageMap<T> {
	private Map<T, ImagePosition> imageMapsElements;
	private Map<ImagePosition, T> imageMapsShapes;
	private int childImageWidth;
	private int childImageHeight;
	
	public ImageMap(int childImageWidth, int childImageHeight) {
		this.imageMapsElements = new HashMap<T, ImagePosition>();
		this.imageMapsShapes = new HashMap<ImagePosition, T>();
		
		this.childImageWidth = childImageWidth;
		this.childImageHeight = childImageHeight;
	}
	
	public void addMapping(T element, Shape shape) {
		List<Point> lstPoints = new ArrayList<Point>();
		Rectangle rect = shape.getBounds();
		lstPoints.add(new Point(rect.x, rect.y));
		lstPoints.add(new Point(rect.x + rect.width - this.childImageWidth, rect.y));
		lstPoints.add(new Point(rect.x + rect.width - this.childImageWidth, rect.y + rect.height - this.childImageHeight));
		lstPoints.add(new Point(rect.x, rect.y + rect.height - this.childImageHeight));
		
		this.addMapping(element, new ImagePosition(shape, lstPoints));
		
		/*Collections.addAll(lstPoints, new Point(rect.x, rect.y));
		
		this.imageMapsElements.put(element, imgPosition);
		this.imageMapsShapes.put(imgPosition, element);*/
	}
	
	public void addMapping(T element, ImagePosition imgPosition) {
		this.imageMapsElements.put(element, imgPosition);
		this.imageMapsShapes.put(imgPosition, element);
	}
	
	public T getElementAt(Point p) {
		T element = null;
		for (Map.Entry<ImagePosition, T> map : this.imageMapsShapes.entrySet()) {
			if (map.getKey().contains(p)) {
				element = map.getValue();
				break;
			}
		}
		return element;
	}
	
	public Point getPositionAt(T element, int index) {
		return this.imageMapsElements.get(element).getPoint(index);
	}
}
