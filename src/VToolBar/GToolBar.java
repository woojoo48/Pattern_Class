package VToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import Interface.ToolBarListener;


public class GToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    private GShapeButton shapeButton;
    private GActionButton actionButton;
    
    private String selectedShape;
    private String selectedAction;

    private ToolBarListener toolBarListener;

    public GToolBar() {

        this.shapeButton = new GShapeButton();
        this.add(this.shapeButton);
        
        this.actionButton = new GActionButton();
        this.add(this.actionButton);
        
        selectedShape = "rectangle";
        selectedAction = "Draw";   
    }

    public void initialize() {
        this.shapeButton.initialize();
        this.actionButton.initialize();
        
        this.setShapeListeners();
        this.setActionListeners();
        
        this.shapeButton.setSelectedButton("rectangle");
        this.actionButton.setSelectedButton("Draw");
    }

    public void setToolBarListener(ToolBarListener toolBarListener) {
        this.toolBarListener = toolBarListener;
    }
    
    private void setShapeListeners() {
        shapeButton.setButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = e.getActionCommand();
                notifyStateChange();
            }
        });
    }
    
    private void setActionListeners() {
        actionButton.setButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAction = e.getActionCommand();
                notifyStateChange();
            }
        });
    }
    
    private void notifyStateChange() {
        if (toolBarListener != null) {
            toolBarListener.onStateChange(selectedShape, selectedAction);
        }
    }

    public String getSelectedShape() {
        return selectedShape;
    }
    
    public String getSelectedAction() {
        return selectedAction;
    }
}