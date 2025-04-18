package VToolBar;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GShapeButton extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JRadioButton rectangleButton;
	private JRadioButton triangleButton;
	private JRadioButton ovalButton;
	private JRadioButton polygonButton;
	private JRadioButton textboxButton;
	private ButtonGroup shapeGroup;
	
	public GShapeButton() {
		//components
		this.rectangleButton = new JRadioButton("rectangle");
		this.add(this.rectangleButton);
		
		this.triangleButton = new JRadioButton("triangle");
		this.add(this.triangleButton);
		
		this.ovalButton = new JRadioButton("oval");
		this.add(this.ovalButton);
		
		this.polygonButton = new JRadioButton("polygon");
		this.add(this.polygonButton);
		
		this.textboxButton = new JRadioButton("text Box");
		this.add(this.textboxButton);
		
		//그룹화 - 한번에 하나만 선택
		this.shapeGroup = new ButtonGroup();
		this.shapeGroup.add(rectangleButton);
		this.shapeGroup.add(triangleButton);
		this.shapeGroup.add(ovalButton);
		this.shapeGroup.add(polygonButton);
		this.shapeGroup.add(textboxButton);
	}

	public void initialize() {
		this.rectangleButton.setSelected(true);
	}
	
	public void setButtonListener(ActionListener actionListener) {
		rectangleButton.addActionListener(actionListener);
        triangleButton.addActionListener(actionListener);
        ovalButton.addActionListener(actionListener);
        polygonButton.addActionListener(actionListener);
        textboxButton.addActionListener(actionListener);
	}
	
	public void setSelectedButton(String buttonName) {
        if ("rectangle".equals(buttonName)) {
            rectangleButton.setSelected(true);
        } else if ("triangle".equals(buttonName)) {
            triangleButton.setSelected(true);
        } else if ("oval".equals(buttonName)) {
            ovalButton.setSelected(true);
        } else if ("polygon".equals(buttonName)) {
            polygonButton.setSelected(true);
        } else if ("text Box".equals(buttonName)) {
            textboxButton.setSelected(true);
        }
    }
}
