package VDrawingPanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import Interface.GDrawingTool;
import Model.MModel;
import Model.MOval;
import Model.MPolygon;
import Model.MRectangle;
import Model.MShapeType;
import Model.MTextBox;
import Model.MTriangle;

public class GDrawTool implements GDrawingTool {

	//components
	protected Point startPoint;
    protected Point endPoint;
    protected String selectedShape;
    protected String textBox;
    protected List<Point> polygonPoints;
    //model
    protected MModel model; 
    protected MShapeType currentShape;
    
    public GDrawTool() {
    	
    }
    public GDrawTool(String selectedShape) {
        this.selectedShape = selectedShape;
        this.polygonPoints = new ArrayList<>();
        this.textBox = "텍스트";
        this.model = null;
    }
    
    public GDrawTool(String selectedDiagram, MModel model) {
        this(selectedDiagram);
        this.model = model;
    }
    
    @Override
    public void mousePressed(Point p) {
        startPoint = p;
        endPoint = p;
        
        if (model != null) {
            if ("rectangle".equals(selectedShape)) {
                currentShape = new MRectangle(p.x, p.y, 0, 0);
                currentShape.setColor(Color.BLACK);
            } else if ("triangle".equals(selectedShape)) {
                currentShape = new MTriangle(p.x, p.y, 0, 0);
                currentShape.setColor(Color.BLACK);
            } else if ("oval".equals(selectedShape)) {
                currentShape = new MOval(p.x, p.y, 0, 0);
                currentShape.setColor(Color.BLACK);
            } else if ("polygon".equals(selectedShape)) {
                MPolygon polygon = new MPolygon(p.x, p.y, 0, 0);
                if (currentShape == null || !(currentShape instanceof MPolygon)) {
                    currentShape = new MPolygon(p.x, p.y, 0, 0);
                    currentShape.setColor(Color.BLACK);
                }
                ((MPolygon) currentShape).addPoint(p);
            }  else if ("text Box".equals(selectedShape)) {
                MTextBox textBoxShape = new MTextBox(p.x, p.y, 0, 0);
                textBoxShape.setColor(Color.BLACK);
                textBoxShape.setText(textBox);
                currentShape = textBoxShape;
            }
        
        }
        
        if ("polygon".equals(selectedShape)) {
            polygonPoints.add(new Point(p));
        }
    }
    
    @Override
    public void mouseDragged(Point p) {
        endPoint = p;
        
        if (currentShape != null && !(currentShape instanceof MPolygon)	) {
            int x = Math.min(startPoint.x, p.x);
            int y = Math.min(startPoint.y, p.y);
            int width = Math.abs(p.x - startPoint.x);
            int height = Math.abs(p.y - startPoint.y);
            
            currentShape.setX(x);
            currentShape.setY(y);
            currentShape.setWidth(width);
            currentShape.setHeight(height);
        }
    }
    
    @Override
    public void mouseReleased(Point p) {
    	 if (currentShape != null && model != null  && !(currentShape instanceof MPolygon)) {
             mouseDragged(p);
             model.addShape(currentShape);

             currentShape = null;
             startPoint = null;
             endPoint = null;
         }
    }
    
    @Override
    public void draw(Graphics g) {
    	if (currentShape != null) {
            // 현재 그리고 있는 도형 그리기
            g.setColor(currentShape.getColor());
            
            if (currentShape instanceof MRectangle) {
                g.drawRect(currentShape.getX(), currentShape.getY(), 
                          currentShape.getWidth(), currentShape.getHeight());
            } else if (currentShape instanceof MTriangle) {
                MTriangle triangle = (MTriangle) currentShape;
                Point[] points = triangle.getAbsolutePoints();
                
                int[] xPoints = new int[3];
                int[] yPoints = new int[3];
                
                for (int i = 0; i < 3; i++) {
                    xPoints[i] = points[i].x;
                    yPoints[i] = points[i].y;
                }
                
                g.drawPolygon(xPoints, yPoints, 3);
            } else if (currentShape instanceof MOval) {
                g.drawOval(currentShape.getX(), currentShape.getY(), 
                          currentShape.getWidth(), currentShape.getHeight());
            } else if (currentShape instanceof MPolygon) {
            	MPolygon polygon = (MPolygon) currentShape;
                Polygon javaPolygon = polygon.getPolygon();
                
                if (polygon.isFilled()) {
                    g.fillPolygon(javaPolygon);
                } else {
                    g.drawPolygon(javaPolygon);
                }
            } else if (currentShape instanceof MTextBox) {
                MTextBox textBoxShape = (MTextBox) currentShape;
                g.drawRect(textBoxShape.getX(), textBoxShape.getY(), 
                          textBoxShape.getWidth(), textBoxShape.getHeight());
                
                Font originalFont = g.getFont();
                g.setFont(textBoxShape.getFont());
                g.drawString(textBoxShape.getText(), 
                            textBoxShape.getX() + 5, 
                            textBoxShape.getY() + textBoxShape.getHeight()/2);
                g.setFont(originalFont);
            }
        } else if (startPoint != null && endPoint != null) {
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(endPoint.x - startPoint.x);
            int height = Math.abs(endPoint.y - startPoint.y);
            
            if ("rectangle".equals(selectedShape)) {
                drawRectangle(g, x, y, width, height);
            } else if ("triangle".equals(selectedShape)) {
                drawTriangle(g, x, y, width, height);
            } else if ("oval".equals(selectedShape)) {
                drawOval(g, x, y, width, height);
            } else if ("polygon".equals(selectedShape)) {
                drawPolygon(g);
            } else if ("text Box".equals(selectedShape)) {
                drawTextBox(g, x, y, width, height);
            }
        }
    }
    
    protected void drawRectangle(Graphics g, int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }
    
    protected void drawTriangle(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x + width/2, x, x + width};
        int[] yPoints = {y, y + height, y + height};
        g.drawPolygon(xPoints, yPoints, 3);
    }
    
    protected void drawOval(Graphics g, int x, int y, int width, int height) {
        g.drawOval(x, y, width, height);
    }
    
    protected void drawPolygon(Graphics g) {
        if (polygonPoints.size() >= 2) {
            Polygon polygon = new Polygon();
            
            for (Point p : polygonPoints) {
                polygon.addPoint(p.x, p.y);
            }
            polygon.addPoint(endPoint.x, endPoint.y);
            
            g.drawPolygon(polygon);
        }
    }
    
    protected void drawTextBox(Graphics g, int x, int y, int width, int height) {
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