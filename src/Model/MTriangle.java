package Model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class MTriangle extends MShapeType {
    private static final long serialVersionUID = 1L;
    
    private Point[] relativePoints;
    
    public MTriangle() {
        super();
    }
    
    public MTriangle(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLACK, false, 1);
    }
    
    public MTriangle(int x, int y, int width, int height, 
                    Color color, boolean filled, int lineWidth) {
        super(x, y, width, height, color, filled, lineWidth);
    }
    
    public Point[] getAbsolutePoints() {
        Point[] absolutePoints = new Point[3];
        absolutePoints[0] = new Point(getX() + getWidth()/2, getY());          
        absolutePoints[1] = new Point(getX(), getY() + getHeight());       
        absolutePoints[2] = new Point(getX() + getWidth(), getY() + getHeight());
        return absolutePoints;
    }
    
    // move
    public boolean contains(int px, int py) {
        Point[] points = getAbsolutePoints();
        Polygon polygon = new Polygon();
        
        for (Point p : points) {
            polygon.addPoint(p.x, p.y);
        }
        
        return polygon.contains(px, py);
    }
    
}
