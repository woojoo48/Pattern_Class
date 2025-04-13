package Mediator;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import Shape.SOval;
import Shape.SPolygon;
import Shape.SRectangle;
import Shape.STriangle;
import Shape.STypeManager;

public class STransformer {

    private String shapeType;
    
    private STypeManager currentShape;
    private List<Point> polygonPoints;

    private Point lastPoint;

    private DrawingState state;
    
    public STransformer() {
        this.shapeType = "rectangle";
        this.polygonPoints = new ArrayList<>();
        this.state = DrawingState.IDLE;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public void start(Graphics2D g2d, int x, int y) {
        if ("polygon".equals(shapeType)) {
            if (state == DrawingState.IDLE) {

                polygonPoints.clear();
                polygonPoints.add(new Point(x, y));
                state = DrawingState.POLYGON_DRAWING;

                currentShape = new SPolygon();
                ((SPolygon) currentShape).addPoint(new Point(x, y));
            }
        } else {
            createShape(x, y);
            state = DrawingState.SHAPE_DRAWING;
        }
        
        lastPoint = new Point(x, y);
    }
    
    public void drag(Graphics2D g2d, int x, int y) {
        if (state == DrawingState.SHAPE_DRAWING && currentShape != null) {
            currentShape.draw(g2d);
            currentShape.setPoint2(x, y);
            
            currentShape.draw(g2d);
        } else if (state == DrawingState.POLYGON_DRAWING && currentShape instanceof SPolygon) {
            SPolygon polygon = (SPolygon) currentShape;
            polygon.drawTemporary(g2d, lastPoint.x, lastPoint.y);
            polygon.drawTemporary(g2d, x, y);
        } else if (state == DrawingState.MOVING && currentShape != null) {
        	currentShape.draw(g2d);
            
            int dx = x - lastPoint.x;
            int dy = y - lastPoint.y;
            
            currentShape.setX(currentShape.getX() + dx);
            currentShape.setY(currentShape.getY() + dy);
            
            currentShape.draw(g2d);
            
            lastPoint = new Point(x, y);
        }
        
        lastPoint = new Point(x, y);
    }

    public void move(Graphics2D g2d, int x, int y) {
        if (state == DrawingState.POLYGON_DRAWING && currentShape instanceof SPolygon) {
            SPolygon polygon = (SPolygon) currentShape;
            polygon.drawTemporary(g2d, lastPoint.x, lastPoint.y);
            
            polygon.drawTemporary(g2d, x, y);
            
            lastPoint = new Point(x, y);
        }
    }

    public STypeManager click(Graphics2D g2d, int x, int y, int clickCount) {
        if (!"polygon".equals(shapeType)) {
            return null;
        }
        
        if (state == DrawingState.IDLE) {
            start(g2d, x, y);
            return null;
        } else if (state == DrawingState.POLYGON_DRAWING) {
            if (clickCount == 2) {
                if (polygonPoints.size() >= 3) {
                    STypeManager finishedShape = currentShape;
                    currentShape = null;
                    polygonPoints.clear();
                    state = DrawingState.IDLE;
                    return finishedShape;
                }
            } else {
                polygonPoints.add(new Point(x, y));
                ((SPolygon) currentShape).addPoint(new Point(x, y));
            }
        }
        
        return null;
    }

    public STypeManager finish(Graphics2D g2d, int x, int y) {
        if (state == DrawingState.SHAPE_DRAWING && currentShape != null) {
            currentShape.draw(g2d);
            
            currentShape.setPoint2(x, y);
            
            STypeManager finishedShape = currentShape;
            currentShape = null;
            state = DrawingState.IDLE;
            
            return finishedShape;
        } else if (state == DrawingState.MOVING) {
            state = DrawingState.IDLE;
        }
        
        return null;
    }

    public void select(Graphics2D g2d, int x, int y, STypeManager shape) {
        currentShape = shape;
        if (currentShape != null) {
            state = DrawingState.MOVING;
            lastPoint = new Point(x, y);
        } else {
            state = DrawingState.IDLE;
        }
    }

    private void createShape(int x, int y) {
        switch (shapeType) {
            case "rectangle":
                currentShape = new SRectangle();
                break;
            case "oval":
                currentShape = new SOval();
                break;
            case "triangle":
                currentShape = new STriangle();
                break;
            default:
                currentShape = new SRectangle();
                break;
        }
        
        currentShape.setPoint1(x, y);
    }
    
    public DrawingState getState() {
        return state;
    }

    public enum DrawingState {
        IDLE,              
        SHAPE_DRAWING,     
        POLYGON_DRAWING,   
        MOVING            
    }
}