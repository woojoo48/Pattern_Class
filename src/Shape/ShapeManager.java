package Shape;

import java.io.Serializable;
import java.util.Vector;

public class ShapeManager implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Vector<STypeManager> shapes; 
    
    public ShapeManager() {
        this.shapes = new Vector<STypeManager>();
    }
    
    public void addShape(STypeManager shape) {
        shapes.add(shape);
    }
    
    public void removeShape(STypeManager shape) {
        shapes.remove(shape);
    }
    
    public Vector<STypeManager> getShapes() {
        return shapes;
    }
    
    public STypeManager findShapeAt(int x, int y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            STypeManager shape = shapes.get(i);
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }
}