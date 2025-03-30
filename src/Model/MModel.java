package Model;

import java.io.Serializable;
import java.util.Vector;

public class MModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Vector<MShapeType> shapes; 
    
    public MModel() {
        this.shapes = new Vector<MShapeType>();
    }
    
    public void addShape(MShapeType shape) {
        shapes.add(shape);
    }
    
    public void removeShape(MShapeType shape) {
        shapes.remove(shape);
    }
    
    public Vector<MShapeType> getShapes() {
        return shapes;
    }
    
    public MShapeType findShapeAt(int x, int y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            MShapeType shape = shapes.get(i);
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }
}