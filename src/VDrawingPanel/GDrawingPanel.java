package VDrawingPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
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
    protected String selectedShape;
    protected String selectedAction;
    
    public GDrawingPanel(MModel model) {
        // components
        this.model = model;
        
        // attributes
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        
        this.selectedShape = "rectangle";
        this.selectedAction = "Draw";
    }
    
    public void initialize() {
        this.selectedShape = "rectangle";
        this.selectedAction = "Draw";
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //기존의 그림들을 repaint
        if (model != null) {
            for (MShapeType shape : model.getShapes()) {
                drawShape(g, shape);
            }
        }
    }
    
    //model의 그림을 불러와서 그리는 것
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
    
    //xor모드로 리팩토링
    public void draw(int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setXORMode(getBackground());
        
        if ("rectangle".equals(selectedShape)) {
            g.drawRect(x, y, width, height);
        } else if ("oval".equals(selectedShape)) {
            g.drawOval(x, y, width, height);
        } else if ("triangle".equals(selectedShape)) {
            int[] xPoints = {x + width/2, x, x + width};
            int[] yPoints = {y, y + height, y + height};
            g.drawPolygon(xPoints, yPoints, 3);
        } else if ("text Box".equals(selectedShape)) {
            g.drawRect(x, y, width, height);
        }
        
        g.dispose();
    }
    
    @Override
    public void onStateChange(String selectedDiagram, String selectedAction) {
        this.selectedShape = selectedDiagram;
        this.selectedAction = selectedAction;
        repaint();
    }
    
    //mouseAdapter -> listener와 motionlistener로 변경
    //한 곳에 모든 기능 부분을 처리함 -> drawtool과 movetool, drawingtool 인터페이스 필요없어짐
    //그러나 너무 한 곳에 책임이 과하진 않은지...분산을 시켜야하는건 아닌지 고민이 좀 더 필요해보임
    private class MouseHandler implements MouseListener, MouseMotionListener {
        private int x1, y1;      
        private int x2, y2;      
        private MShapeType selectedObject; 
        
        @Override
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            x2 = x1;
            y2 = y1;
            
            if ("Move".equals(selectedAction)) {
                selectedObject = model.findShapeAt(x1, y1);
            }
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            if ("Draw".equals(selectedAction)) {
                draw(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                
                x2 = e.getX();
                y2 = e.getY();
                
                draw(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
            } 
            else if ("Move".equals(selectedAction) && selectedObject != null) {
                int dx = e.getX() - x2;
                int dy = e.getY() - y2;
                
                draw(selectedObject.getX(), selectedObject.getY(), 
                     selectedObject.getWidth(), selectedObject.getHeight());
                
                selectedObject.setX(selectedObject.getX() + dx);
                selectedObject.setY(selectedObject.getY() + dy);
                
                draw(selectedObject.getX(), selectedObject.getY(), 
                     selectedObject.getWidth(), selectedObject.getHeight());
                
                x2 = e.getX();
                y2 = e.getY();
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if ("Draw".equals(selectedAction)) {
                draw(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                
                x2 = e.getX();
                y2 = e.getY();
                
                int x = Math.min(x1, x2);
                int y = Math.min(y1, y2);
                int width = Math.abs(x2 - x1);
                int height = Math.abs(y2 - y1);
                
                if (width < 5 || height < 5) {
                    return;
                }
                
                MShapeType shape = null;
                if ("rectangle".equals(selectedShape)) {
                    shape = new MRectangle(x, y, width, height, Color.BLACK, false, 1);
                } else if ("oval".equals(selectedShape)) {
                    shape = new MOval(x, y, width, height, Color.BLACK, false, 1);
                } else if ("triangle".equals(selectedShape)) {
                    shape = new MTriangle(x, y, width, height, Color.BLACK, false, 1);
                } else if ("text Box".equals(selectedShape)) {
                    MTextBox textBox = new MTextBox(x, y, width, height);
                    textBox.setText("텍스트");
                    shape = textBox;
                }
                
                if (shape != null) {
                    model.addShape(shape);
                }
                
                repaint();
            } 
            else if ("Move".equals(selectedAction)) {
                selectedObject = null;
                repaint();
            }
            
            x1 = 0;
            y1 = 0;
            x2 = 0;
            y2 = 0;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {

        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
 
        }
        
        @Override
        public void mouseExited(MouseEvent e) {

        }
        
        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
