package Shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class SOval extends STypeManager {
    private static final long serialVersionUID = 1L;

    public SOval() {
        super();
    }
    
    public SOval(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLACK, false, 1);
    }

    public SOval(int x, int y, int width, int height, 
               Color color, boolean filled, int lineWidth) {
        super(x, y, width, height, color, filled, lineWidth);
    }
    
    @Override
    public boolean contains(int px, int py) {
        Ellipse2D.Double ellipse = new Ellipse2D.Double(
            getX(), getY(), getWidth(), getHeight()
        );
        return ellipse.contains(px, py);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        Color originalColor = g2d.getColor();
        
        g2d.setColor(getColor());
        
        if (isFilled()) {
            g2d.fillOval(getX(), getY(), getWidth(), getHeight());
        } else {
            g2d.drawOval(getX(), getY(), getWidth(), getHeight());
        }
        
        g2d.setColor(originalColor);
    }
}