package Model;

import java.awt.Color;
import java.io.Serializable;

public abstract class MShapeType extends MModel{
	private static final long serialVersionUID = 1L;
	
	private int x;      
    private int y;         
    private int width;     
    private int height;  
    private Color color;    
    private boolean filled;
    private int lineWidth; 
    
    public MShapeType() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.color = Color.BLACK;
        this.filled = false;
        this.lineWidth = 1;
    }
    
    public MShapeType(int x, int y, int width, int height, Color color, boolean filled, int lineWidth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.filled = filled;
        this.lineWidth = lineWidth;
    }
    

    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Color getColor() {return color;}
    public int getLineWidth() {return lineWidth;}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setColor(Color color) {this.color = color;}
    public void setFilled(boolean filled) {this.filled = filled;}
    public void setLineWidth(int lineWidth) {this.lineWidth = lineWidth;}

    public boolean isFilled() {return filled;}
    
    public abstract boolean contains(int px, int py);
}