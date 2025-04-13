package Shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class SPolygon extends STypeManager {
    private static final long serialVersionUID = 1L;
    
    private List<Point> points;
    
    public SPolygon() {
        super();
        points = new ArrayList<>();
    }
    
    public SPolygon(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLACK, false, 1);
        points = new ArrayList<>();
    }
    
    public SPolygon(int x, int y, int width, int height, 
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
        this.points.clear();
        
        for (Point p : points) {
            this.points.add(new Point(p));
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
    
    public Polygon getPolygon() {
        Polygon polygon = new Polygon();
        
        for (Point p : points) {
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
    
    @Override
    public void draw(Graphics2D g2d) {
        if (points.size() < 3) return;
        
        Color originalColor = g2d.getColor();
        
        g2d.setColor(getColor());
        
        Polygon polygon = getPolygon();

        if (isFilled()) {
            g2d.fillPolygon(polygon);
        } else {
            g2d.drawPolygon(polygon);
        }

        g2d.setColor(originalColor);
    }

    //생성중인 폴리곤에 관한 메서드, 위의 메서드는 이미 생성된 것
    public void drawTemporary(Graphics2D g2d, int currentX, int currentY) {
        if (points.isEmpty()) return;

        Color originalColor = g2d.getColor();
        
        g2d.setColor(getColor());
        
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        if (!points.isEmpty()) {
            Point lastPoint = points.get(points.size() - 1);
            g2d.drawLine(lastPoint.x, lastPoint.y, currentX, currentY);
        }
        
        g2d.setColor(originalColor);
    }
}