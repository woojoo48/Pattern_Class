package Model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class MPolygon extends MShapeType {
    private static final long serialVersionUID = 1L;
    
    private List<Point> points;
    
    public MPolygon() {
        super();
        points = new ArrayList<>();
    }
    
    public MPolygon(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLACK, false, 1);
        points = new ArrayList<>();
    }
    
    public MPolygon(int x, int y, int width, int height, 
                   Color color, boolean filled, int lineWidth) {
        super(x, y, width, height, color, filled, lineWidth);
        points = new ArrayList<>();
    }
    
    public void addPoint(Point p) {
    	 points.add(new Point(p));
         updateBounds();
    }
    
    public List<Point> getPoints() {
        return new ArrayList<>(points);
    }
    
    public void setPoints(List<Point> points) {
        points.clear();
        
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for (Point p : points) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }
        
        // 원점 설정
        setX(minX);
        setY(minY);
        
        for (Point p : points) {
            points.add(new Point(p.x - minX, p.y - minY));
        }
        
        updateBounds();
    }
    
    private void updateBounds() {
    	if (points.isEmpty()) return;
        
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        
        for (Point p : points) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
            maxX = Math.max(maxX, p.x);
            maxY = Math.max(maxY, p.y);
        }
        
        setX(minX);
        setY(minY);
        setWidth(maxX - minX);
        setHeight(maxY - minY);
    }
    
    public List<Point> getAbsolutePoints() {
        List<Point> absolutePoints = new ArrayList<>();
        
        for (Point rp : points) {
            absolutePoints.add(new Point(getX() + rp.x, getY() + rp.y));
        }
        
        return absolutePoints;
    }
    
    public Polygon getPolygon() {
        Polygon polygon = new Polygon(); 
        for (Point p : getAbsolutePoints()) {
            polygon.addPoint(p.x, p.y);
        }
        return polygon;
    }
    
    public int getPointCount() {
        return points.size();
    }
    
    @Override
    public boolean contains(int px, int py) {
        if (getPointCount() < 3) return false;
        return getPolygon().contains(px, py);
    }
}