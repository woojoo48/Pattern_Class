package Interface;
import java.awt.Graphics;
import java.awt.Point;
	
public interface GDrawingTool {
	    void mousePressed(Point p);
	    void mouseDragged(Point p);
	    void mouseReleased(Point p);
	    void draw(Graphics g);
}
