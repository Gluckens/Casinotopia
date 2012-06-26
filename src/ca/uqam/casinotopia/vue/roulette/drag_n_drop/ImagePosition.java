package ca.uqam.casinotopia.vue.roulette.drag_n_drop;

import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

public class ImagePosition {
	
	/**
	 * La shape contenant les mapping d�finis
	 */
	private Shape shape;
	
	/**
	 * Liste des points dans le mapping de la shape
	 */
	List<Point> lstPoints;
	
	public ImagePosition(Shape shape) {
		this(shape, new ArrayList<Point>());
	}
	
	public ImagePosition(Shape shape, List<Point> lstPoints) {
		this.shape = shape;
		this.lstPoints = lstPoints;
	}
	
	/**
	 * Ajouter un mapping dans la shape
	 * 
	 * @param p Le point associ� au mapping
	 */
	public void addPoint(Point p) {
		this.lstPoints.add(p);
	}
	
	/**
	 * D�finir un nouveau mapping dans la shape � un index donn�
	 * 
	 * @param index L'index du nouveau mapping
	 * @param p Le point associ� au mapping
	 */
	public void setPoint(int index, Point p) {
		try {
			this.lstPoints.add(index, p);
		} catch(IndexOutOfBoundsException e) {
			
		}
	}
	
	/**
	 * R�cup�rer le point � l'index demand� dans la shape
	 * @param index L'index voulu
	 * @return Le point reli� � l'index
	 */
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
}