package Model;

import java.awt.Color;

public class MRectangle extends MShapeType{
	private static final long serialVersionUID = 1L;

	public MRectangle() {
        super();
    }
    
    public MRectangle(int x, int y, int width, int height) {
        super(x, y, width, height, null, false, 1); 
    }
    
    public MRectangle(int x, int y, int width, int height, 
                     Color color, boolean filled, int lineWidth) {
        super(x, y, width, height, color, filled, lineWidth);
    }
    
    //move
    public boolean contains(int px, int py) {
        return px >= getX() && px <= getX() + getWidth() &&
               py >= getY() && py <= getY() + getHeight();
    }
    
}