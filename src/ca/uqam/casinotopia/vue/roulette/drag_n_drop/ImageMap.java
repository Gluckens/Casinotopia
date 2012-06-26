package ca.uqam.casinotopia.vue.roulette.drag_n_drop;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe permettant de simuler un areaMap (HTML) afin de découper une image en petite zone.
 * Chacune des zones est lié à un objet T
 */
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
	
	/**
	 * Ajouter un mapping entre un element T et une section de l'image
	 * 
	 * @param element L'élément à mapper
	 * @param shape La shape correspondant à l'élément
	 */
	public void addMapping(T element, Shape shape) {
		List<Point> lstPoints = new ArrayList<Point>();
		Rectangle rect = shape.getBounds();
		lstPoints.add(new Point(rect.x, rect.y));
		lstPoints.add(new Point(rect.x + rect.width - this.childImageWidth, rect.y));
		lstPoints.add(new Point(rect.x + rect.width - this.childImageWidth, rect.y + rect.height - this.childImageHeight));
		lstPoints.add(new Point(rect.x, rect.y + rect.height - this.childImageHeight));
		
		this.addMapping(element, new ImagePosition(shape, lstPoints));
	}
	
	/**
	 * Ajouter un mapping entre un element T et une position dans l'image
	 * 
	 * @param element L'élément à mapper
	 * @param imgPosition L'instance ImagePosition correspond à la section désiré
	 */
	public void addMapping(T element, ImagePosition imgPosition) {
		this.imageMapsElements.put(element, imgPosition);
		this.imageMapsShapes.put(imgPosition, element);
	}
	
	/**
	 * Récupérer l'élément correspondant à un point dans l'image
	 * 
	 * @param p Le point demandé
	 * @return L'élément dont la shape mappé contient le point
	 */
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
	
	/**
	 * Récupérer une position prédéfini dans le map.
	 * 
	 * @param element L'élément recherché
	 * @param index L'index de la position demandé
	 * @return La position dans l'image sous forme de point
	 */
	public Point getPositionAt(T element, int index) {
		return this.imageMapsElements.get(element).getPoint(index);
	}
}