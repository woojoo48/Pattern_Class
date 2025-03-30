package VToolBar;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class GActionButton extends JPanel {
	private static final long serialVersionUID = 1L;

	private JToggleButton drawButton;
	private JToggleButton moveButton;
	private JToggleButton resizeButton;
	private JToggleButton rotateButton;
	private ButtonGroup actionGroup;
	
	public GActionButton() {
		this.drawButton = new JToggleButton("Draw");
		this.add(drawButton);
		
		this.moveButton = new JToggleButton("Move");
		this.add(moveButton);
		
		this.resizeButton = new JToggleButton("Resize");
		this.add(resizeButton);
		
		this.rotateButton = new JToggleButton("Rotate");
		this.add(rotateButton);
		
		this.actionGroup = new ButtonGroup();
		this.actionGroup.add(this.drawButton);
		this.actionGroup.add(this.moveButton);
		this.actionGroup.add(this.resizeButton);
		this.actionGroup.add(this.rotateButton);
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
