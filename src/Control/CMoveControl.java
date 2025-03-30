package Control;

import java.awt.Point;
import Model.MShapeType;

public class CMoveControl extends CControl {
	    private MShapeType selectedObject; 
	    private Point lastPoint;
	    
	    public CMoveControl() {
	        super();
	    }
	    
	    public void mousePressed(Point p) {
	        if (!"Move".equals(selectedAction)) return;
	        
	        selectedObject = model.findShapeAt(p.x, p.y);
	        lastPoint = p;
	    }
	    
	    public void mouseDragged(Point p) {
	        if (!"Move".equals(selectedAction) || selectedObject == null || lastPoint == null) return;
	        
	        int dx = p.x - lastPoint.x;
	        int dy = p.y - lastPoint.y;
	
	        selectedObject.setX(selectedObject.getX() + dx);
	        selectedObject.setY(selectedObject.getY() + dy);
	
	        lastPoint = p;
	    }
	    
	    public void mouseReleased(Point p) {
	        if (!"Move".equals(selectedAction)) return;
	
	        lastPoint = null;
	    }
	
	    public void clearSelection() {
	        selectedObject = null;
	    }
	
	    public void setSelectedObjectColor(java.awt.Color color) {
	        if (selectedObject != null) {
	            selectedObject.setColor(color);
	        }
	    }
	    
	
	    public MShapeType getSelectedObject() {
	        return selectedObject;
	    }
}
