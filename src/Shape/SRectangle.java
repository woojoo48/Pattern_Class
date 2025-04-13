package Shape;

import java.awt.Color;
import java.awt.Graphics2D;

public class SRectangle extends STypeManager {
    private static final long serialVersionUID = 1L;

    public SRectangle() {
        super();
    }
    
    public SRectangle(int x, int y, int width, int height) {
        super(x, y, width, height, null, false, 1); 
    }
    
    public SRectangle(int x, int y, int width, int height, 
                     Color color, boolean filled, int lineWidth) {
        super(x, y, width, height, color, filled, lineWidth);
    }
    
    @Override
    public boolean contains(int px, int py) {
        return px >= getX() && px <= getX() + getWidth() &&
               py >= getY() && py <= getY() + getHeight();
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        Color originalColor = g2d.getColor();
        
        g2d.setColor(getColor());
        
        if (isFilled()) {
            g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        } else {
            g2d.drawRect(getX(), getY(), getWidth(), getHeight());
        }
        g2d.setColor(originalColor);
    }
}