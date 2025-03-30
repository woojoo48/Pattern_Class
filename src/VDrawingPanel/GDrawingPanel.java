package VDrawingPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import Interface.GDrawingTool;
import Interface.ToolBarListener;
import Model.MModel;
import Model.MOval;
import Model.MRectangle;
import Model.MShapeType;
import Model.MTextBox;
import Model.MTriangle;

public class GDrawingPanel extends JPanel implements ToolBarListener {
    private static final long serialVersionUID = 1L;

    private MModel model;
    protected GDrawingTool currentTool;
    protected String selectedShape;
    protected String selectedAction;

    protected MouseEventHandler mouseEventHandler;

    public GDrawingPanel(MModel model) {
    	//components
        this.model = model;
        
        //attributes
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.mouseEventHandler = new MouseEventHandler();
        this.addMouseListener(mouseEventHandler);
        this.addMouseMotionListener(mouseEventHandler);
        
        this.selectedShape = "rectangle";
        this.selectedAction = "Draw";
    }
    
    public void initialize() {
    	 this.selectedShape = "rectangle";
    	 this.selectedAction = "Draw";
        setCurrentTool();
        repaint();
    }
    
    protected void setCurrentTool() {
    	 if ("Draw".equals(selectedAction)) {
    	        this.currentTool = new GDrawTool(selectedShape, model);
    	    } else if ("Move".equals(selectedAction)) {
    	        this.currentTool = new GMoveTool(model);
    	    }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (model != null) {
        	 for (MShapeType shape : model.getShapes()) {
                 drawShape(g, shape);
             }
        }
        
        if (currentTool != null) {
            currentTool.draw(g);
        }
    }
    
    private void drawShape(Graphics g, MShapeType shape) {
        g.setColor(shape.getColor());
        
        if (shape instanceof MRectangle) {
            if (shape.isFilled()) {
                g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            } else {
                g.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            }
        } else if (shape instanceof MTriangle) {
            MTriangle triangle = (MTriangle) shape;
            Point[] points = triangle.getAbsolutePoints();
            
            int[] xPoints = new int[3];
            int[] yPoints = new int[3];
            
            for (int i = 0; i < 3; i++) {
                xPoints[i] = points[i].x;
                yPoints[i] = points[i].y;
            }
            
            if (shape.isFilled()) {
                g.fillPolygon(xPoints, yPoints, 3);
            } else {
                g.drawPolygon(xPoints, yPoints, 3);
            }
        } else if (shape instanceof MOval) {
            if (shape.isFilled()) {
                g.fillOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            } else {
                g.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            }
        } else if (shape instanceof MTextBox) {
            MTextBox textBox = (MTextBox) shape;

            g.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());

            Font originalFont = g.getFont();
            g.setFont(textBox.getFont());
            g.drawString(textBox.getText(), shape.getX() + 5, shape.getY() + shape.getHeight()/2);
            g.setFont(originalFont);
        }
    }
    
    @Override
    public void onStateChange(String selectedDiagram, String selectedAction) {
    	this.selectedShape = selectedDiagram;
        this.selectedAction = selectedAction;
        
        setCurrentTool();
        repaint();
    }
    
    protected class MouseEventHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (currentTool != null) {
                currentTool.mousePressed(e.getPoint());
                repaint();
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentTool != null) {
                currentTool.mouseReleased(e.getPoint());
                repaint();
            }
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentTool != null) {
                currentTool.mouseDragged(e.getPoint());
                repaint();
            }
        }
    }
}
