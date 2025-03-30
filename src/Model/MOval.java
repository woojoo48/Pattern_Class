package Model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class MOval extends MShapeType{
	private static final long serialVersionUID = 1L;

	public MOval() {
        super();
    }
    
    public MOval(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLACK, false, 1);
    }

    public MOval(int x, int y, int width, int height, 
               Color color, boolean filled, int lineWidth) {
        super(x, y, width, height, color, filled, lineWidth);
    }
    
    //move
    public boolean contains(int px, int py) {
        Ellipse2D.Double ellipse = new Ellipse2D.Double(
            getX(), getY(), getWidth(), getHeight()
        );
        return ellipse.contains(px, py);
    }
    
}
