package VDrawingPanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import Interface.GDrawingTool;
import Model.MModel;
import Model.MShapeType;

public class GMoveTool implements GDrawingTool {
	
	//components
	private Point lastPoint;
	//model
    private MModel model;
    private MShapeType selectedShape;
  
    
    public GMoveTool(MModel model) {
        this.model = model;
    }
    
    @Override
    public void mousePressed(Point p) {
        selectedShape = model.findShapeAt(p.x, p.y);
        lastPoint = p;
    }
    
    @Override
    public void mouseDragged(Point p) {
        if (selectedShape != null && lastPoint != null) {
            int dx = p.x - lastPoint.x;
            int dy = p.y - lastPoint.y;
            
            selectedShape.setX(selectedShape.getX() + dx);
            selectedShape.setY(selectedShape.getY() + dy);
            
            lastPoint = p;
        }
    }
    
    @Override
    public void mouseReleased(Point p) {
        lastPoint = null;
    }
    
    @Override
    public void draw(Graphics g) {
        if (selectedShape != null) {
            Color originalColor = g.getColor();
            g.setColor(Color.BLUE);
            g.drawRect(
                selectedShape.getX() - 2, 
                selectedShape.getY() - 2, 
                selectedShape.getWidth() + 4, 
                selectedShape.getHeight() + 4
            );
            
            g.setColor(originalColor);
        }
    }
    
    public void clearSelection() {
        selectedShape = null;
    }
    
    public MShapeType getSelectedShape() {
        return selectedShape;
    }
}