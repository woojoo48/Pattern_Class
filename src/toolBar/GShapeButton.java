package toolBar;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class GShapeButton extends JPanel {
	private static final long serialVersionUID = 1L;

	private JToggleButton drawButton;
	private JToggleButton moveButton;
	private JToggleButton resizeButton;
	private JToggleButton rotateButton;
	private ButtonGroup ShapeGroup;
	
	public GShapeButton() {
		this.drawButton = new JToggleButton("Drow");
		this.add(drawButton);
		
		this.moveButton = new JToggleButton("Move");
		this.add(moveButton);
		
		this.resizeButton = new JToggleButton("Resize");
		this.add(resizeButton);
		
		this.rotateButton = new JToggleButton("Rotate");
		this.add(rotateButton);
		
		this.ShapeGroup = new ButtonGroup();
		this.ShapeGroup.add(this.drawButton);
		this.ShapeGroup.add(this.moveButton);
		this.ShapeGroup.add(this.resizeButton);
		this.ShapeGroup.add(this.rotateButton);
	}
	
	public void initialize() {
		this.drawButton.setSelected(true);
	}
	
	public void setButtonListener(ActionListener listener) {
        drawButton.addActionListener(listener);
        moveButton.addActionListener(listener);
        resizeButton.addActionListener(listener);
        rotateButton.addActionListener(listener);
    }
	
	public void setSelectedButton(String buttonName) {
        if ("Draw".equals(buttonName)) {
            drawButton.setSelected(true);
        } else if ("Move".equals(buttonName)) {
            moveButton.setSelected(true);
        } else if ("Resize".equals(buttonName)) {
            resizeButton.setSelected(true);
        } else if ("Rotate".equals(buttonName)) {
            rotateButton.setSelected(true);
        }
    }
	
}
