package Shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class STriangle extends STypeManager {
    private static final long serialVersionUID = 1L;
    
    public STriangle() {
        super();
    }
    
    public STriangle(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLACK, false, 1);
    }
    
    public STriangle(int x, int y, int width, int height, 
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
    
    @Override
    public boolean contains(int px, int py) {
        Point[] points = getAbsolutePoints();
        Polygon polygon = new Polygon();
        
        for (Point p : points) {
            polygon.addPoint(p.x, p.y);
        }
        
        return polygon.contains(px, py);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        Color originalColor = g2d.getColor();

        g2d.setColor(getColor());
        
        Point[] points = getAbsolutePoints();
        
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        
        for (int i = 0; i < 3; i++) {
            xPoints[i] = points[i].x;
            yPoints[i] = points[i].y;
        }
        
        if (isFilled()) {
            g2d.fillPolygon(xPoints, yPoints, 3);
        } else {
            g2d.drawPolygon(xPoints, yPoints, 3);
        }
        
        g2d.setColor(originalColor);
    }
}