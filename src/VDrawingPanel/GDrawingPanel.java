package VDrawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Interface.ToolBarListener;
import Mediator.STransformer;
import Shape.STypeManager;
import Shape.ShapeManager;

public class GDrawingPanel extends JPanel implements ToolBarListener {
    private static final long serialVersionUID = 1L;

    private ShapeManager shapeManager;
    private STransformer transformer;
    
    protected String selectedShape;
    protected String selectedAction;
    

    public GDrawingPanel(ShapeManager shapeManager) {

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.shapeManager = shapeManager;
        
        this.transformer = new STransformer();
        
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        

        this.selectedShape = "rectangle";
        this.selectedAction = "Draw";
        this.transformer.setShapeType(selectedShape);
    }

    public void initialize() {
        this.selectedShape = "rectangle";
        this.selectedAction = "Draw";
        this.transformer.setShapeType(selectedShape);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Vector<STypeManager> shapes = shapeManager.getShapes();
        for (STypeManager shape : shapes) {
            shape.draw((Graphics2D) g);
        }
    }
    
    @Override
    public void onStateChange(String selectedShape, String selectedAction) {
        this.selectedShape = selectedShape;
        this.selectedAction = selectedAction;
        
        transformer.setShapeType(selectedShape);
        
        repaint();
    }
    

    private class MouseHandler implements MouseListener, MouseMotionListener {
        
        @Override
        public void mousePressed(MouseEvent e) {
            Graphics2D g2d = (Graphics2D) getGraphics();
            g2d.setXORMode(getBackground());
            
            if ("Draw".equals(selectedAction)) {
                if (!"polygon".equals(selectedShape)) {
                    transformer.start(g2d, e.getX(), e.getY());
                }
            } else if ("Move".equals(selectedAction)) {
                STypeManager selectedObject = shapeManager.findShapeAt(e.getX(), e.getY());
                transformer.select(g2d, e.getX(), e.getY(), selectedObject);
            }
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            Graphics2D g2d = (Graphics2D) getGraphics();
            g2d.setXORMode(getBackground());
            
            transformer.drag(g2d, e.getX(), e.getY());
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            Graphics2D g2d = (Graphics2D) getGraphics();
            g2d.setXORMode(getBackground());
            
            STypeManager shape = transformer.finish(g2d, e.getX(), e.getY());
            
            if (shape != null && shape.getWidth() > 5 && shape.getHeight() > 5) {
                shapeManager.addShape(shape);

            }
            repaint();
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if ("Draw".equals(selectedAction) && "polygon".equals(selectedShape)) {
                Graphics2D g2d = (Graphics2D) getGraphics();
                g2d.setXORMode(getBackground());
                
                STypeManager shape = transformer.click(g2d, e.getX(), e.getY(), e.getClickCount());
                
                if (shape != null) {
                    shapeManager.addShape(shape);
                    repaint();
                }
            }
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {
            if ("Draw".equals(selectedAction) && "polygon".equals(selectedShape) &&
                    transformer.getState() == STransformer.DrawingState.POLYGON_DRAWING) {
                Graphics2D g2d = (Graphics2D) getGraphics();
                g2d.setXORMode(getBackground());
                
                transformer.move(g2d, e.getX(), e.getY());
            }
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}