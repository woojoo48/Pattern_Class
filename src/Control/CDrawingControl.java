package Control;

import java.awt.Color;
import java.awt.Point;
import Model.MShapeType;
import Model.MRectangle;
import Model.MTriangle;
import Model.MOval;

public class CDrawingControl extends CControl {
    private MShapeType currentShape; 
    private Point startPoint;
    
    public CDrawingControl() {
        super();
    }
    
    public void mousePressed(Point p) {
        if (!"Draw".equals(selectedAction)) return;
        
        startPoint = p;
        
        if ("rectangle".equals(selectedShape)) {
            currentShape = new MRectangle(p.x, p.y, 0, 0, Color.BLACK, false, 1);
        } else if ("triangle".equals(selectedShape)) {
            currentShape = new MTriangle(p.x, p.y, 0, 0, Color.BLACK, false, 1);
        } else if ("oval".equals(selectedShape)) {
            currentShape = new MOval(p.x, p.y, 0, 0, Color.BLACK, false, 1);
        }
        // 다른 도형 유형도 필요시 추가
    }
    
    public void mouseDragged(Point p) {
        if (!"Draw".equals(selectedAction) || currentShape == null || startPoint == null) return;
        
        int x = Math.min(startPoint.x, p.x);
        int y = Math.min(startPoint.y, p.y);
        int width = Math.abs(p.x - startPoint.x);
        int height = Math.abs(p.y - startPoint.y);
        
        currentShape.setX(x);
        currentShape.setY(y);
        currentShape.setWidth(width);
        currentShape.setHeight(height);
    }
    
    public void mouseReleased(Point p) {
        if (!"Draw".equals(selectedAction) || currentShape == null) return;
        
        mouseDragged(p);
        
        model.addShape(currentShape);
        currentShape = null;
        startPoint = null;
    }
    
    public void setShapeColor(Color color) {
        if (currentShape != null) {
            currentShape.setColor(color);
        }
    }
    
    public MShapeType getCurrentShape() {
        return currentShape;
    }
}