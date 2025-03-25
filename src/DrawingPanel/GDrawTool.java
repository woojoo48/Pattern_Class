package DrawingPanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import Interface.GDrawingTool;

public class GDrawTool implements GDrawingTool {

    private Point startPoint;
    private Point endPoint;
    private String selectedDiagram;
    private String textBox;
    private List<Point> polygonPoints;
    
    public GDrawTool(String selectedDiagram) {
        this.selectedDiagram = selectedDiagram;
        this.polygonPoints = new ArrayList<>();
        this.textBox = "텍스트";
    }
    
    @Override
    public void mousePressed(Point p) {
        startPoint = p;
        endPoint = p;
        
        if ("polygon".equals(selectedDiagram)) {
            polygonPoints.add(new Point(p));
        }
    }
    
    @Override
    public void mouseDragged(Point p) {
        endPoint = p;
    }
    
    @Override
    public void mouseReleased(Point p) {
    }
    
    @Override
    public void draw(Graphics g) {
        if (startPoint != null && endPoint != null) {
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(endPoint.x - startPoint.x);
            int height = Math.abs(endPoint.y - startPoint.y);
            
            if ("rectangle".equals(selectedDiagram)) {
                drawRectangle(g, x, y, width, height);
            } else if ("triangle".equals(selectedDiagram)) {
                drawTriangle(g, x, y, width, height);
            } else if ("oval".equals(selectedDiagram)) {
                drawOval(g, x, y, width, height);
            } else if ("polygon".equals(selectedDiagram)) {
                drawPolygon(g);
            } else if ("text Box".equals(selectedDiagram)) {
                drawTextBox(g, x, y, width, height);
            }
        }
    }
    
    private void drawRectangle(Graphics g, int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }
    
    private void drawTriangle(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x + width/2, x, x + width};
        int[] yPoints = {y, y + height, y + height};
        g.drawPolygon(xPoints, yPoints, 3);
    }
    
    private void drawOval(Graphics g, int x, int y, int width, int height) {
        g.drawOval(x, y, width, height);
    }
    
    private void drawPolygon(Graphics g) {
        if (polygonPoints.size() >= 2) {
            Polygon polygon = new Polygon();
            
            for (Point p : polygonPoints) {
                polygon.addPoint(p.x, p.y);
            }

            polygon.addPoint(endPoint.x, endPoint.y);
            
            g.drawPolygon(polygon);
        }
    }
    
    private void drawTextBox(Graphics g, int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
        
        Font originalFont = g.getFont();
        Font newFont = new Font(originalFont.getName(), Font.PLAIN, 12);
        g.setFont(newFont);
        
        g.drawString(textBox, x + 5, y + height/2);
        
        g.setFont(originalFont);
    }
    
    public void setText(String text) {
        this.textBox = text;
    }
    
    public void clearPolygonPoints() {
        this.polygonPoints.clear();
    }
}