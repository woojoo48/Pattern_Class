package toolBar;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import Interface.ToolBarListener;

public class GToolBar extends JToolBar{
	private static final long serialVersionUID = 1L;

	private GDiagramButton diagramButton;
	private GShapeButton shapeButton;
	
	private String selectedDiagram;
	private String selectedShape;
	private ToolBarListener toolBarListener;
	
	public GToolBar() {
		this.diagramButton = new GDiagramButton();
		this.add(this.diagramButton);
		
		this.shapeButton = new GShapeButton();
		this.add(this.shapeButton);
		
		selectedDiagram = "rectangle";
		selectedShape = "Draw";   
	}
	
	public void initialize() {
		this.diagramButton.initialize();
		this.shapeButton.initialize();
		
		this.setDiagramListeners();
		this.setShapeListeners();
		
		this.diagramButton.setSelectedButton("rectangle");
        this.shapeButton.setSelectedButton("Draw");
	}
	
	public void setToolBarListener(ToolBarListener toolBarListener) {
	        this.toolBarListener = toolBarListener;
	}
	
	private void setDiagramListeners() {
        diagramButton.setButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectedDiagram = e.getActionCommand();
                noticeStatusChange();
            }
        });
    }
    
    private void setShapeListeners() {
        shapeButton.setButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectedShape = e.getActionCommand();
                noticeStatusChange();
            }
        });
    }
    
    private void noticeStatusChange() {
        if (toolBarListener != null) {
        	toolBarListener.onStateChange(selectedDiagram, selectedShape);
        }
    }
    
    public String getSelectedShape() {
        return selectedDiagram;
    }
    
    public String getSelectedAction() {
        return selectedShape;
    }
}