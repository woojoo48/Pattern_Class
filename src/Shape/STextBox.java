package Shape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class STextBox extends STypeManager {
    private static final long serialVersionUID = 1L;
    
    private String text;
    private Font font;
    
    public STextBox() {
        super();
        this.text = "";
        this.font = new Font("SansSerif", Font.PLAIN, 12);
    }
    
    public STextBox(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLACK, false, 1);
        this.text = "";
        this.font = new Font("SansSerif", Font.PLAIN, 12);
    }
    
    public STextBox(int x, int y, int width, int height, String text) {
        super(x, y, width, height, Color.BLACK, false, 1);
        this.text = text;
        this.font = new Font("SansSerif", Font.PLAIN, 12);
    }
    
    public STextBox(int x, int y, int width, int height, 
                   Color color, boolean filled, int lineWidth, 
                   String text, Font font) {
        super(x, y, width, height, color, filled, lineWidth);
        this.text = text;
        this.font = font;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Font getFont() {
        return font;
    }
    
    public void setFont(Font font) {
        this.font = font;
    }
    
    public void setFont(String fontName, int style, int size) {
        this.font = new Font(fontName, style, size);
    }
    
    @Override
    public boolean contains(int px, int py) {
        return px >= getX() && px <= getX() + getWidth() &&
               py >= getY() && py <= getY() + getHeight();
    }

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
}