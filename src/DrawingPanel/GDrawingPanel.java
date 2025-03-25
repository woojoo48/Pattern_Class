package DrawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Interface.GDrawingTool;
import Interface.ToolBarListener;

public class GDrawingPanel extends JPanel implements ToolBarListener {
	private static final long serialVersionUID = 1L;
	
	private GDrawingTool selectedTool;
	private MouseEventHandler mouseEventHandler;
	
	public GDrawingPanel() {
		//바탕화면 밑 테두리 설정
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}

	public void initialize() {
		//초기값 사각형으로 줌.
		this.selectedTool = new GDrawTool("rectangle");
		repaint();	
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (selectedTool != null) {
        	selectedTool.draw(g);
        }
    }
	
	@Override
	public void onStateChange(String selectedDiagram, String selectedShape) {
		selectedTool = new GDrawTool(selectedDiagram);		
		repaint();		
	}
	
	private class MouseEventHandler extends MouseAdapter {
	    @Override
	    public void mousePressed(MouseEvent e) {
	        if (selectedTool != null) {
	            selectedTool.mousePressed(e.getPoint());
	            repaint();
	        }
	    }
	    
	    @Override
	    public void mouseReleased(MouseEvent e) {
	        if (selectedTool != null) {
	            selectedTool.mouseReleased(e.getPoint());
	            repaint();
	        }
	    }
	    
	    @Override
	    public void mouseDragged(MouseEvent e) {
	        if (selectedTool != null) {
	            selectedTool.mouseDragged(e.getPoint());
	            repaint();
	        }
	    }
	}
}
