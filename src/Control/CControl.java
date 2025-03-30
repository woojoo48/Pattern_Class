package Control;

import Model.MModel;

public class CControl {
    protected MModel model;
    protected String selectedShape; 
    protected String selectedAction; 
    
    public CControl() {
        this.model = new MModel();
        this.selectedShape = "rectangle";
        this.selectedAction = "Draw";
    }
    
    public void setSelectedTool(String shape, String action) {
        this.selectedShape = shape;
        this.selectedAction = action;
    }
    
    public MModel getModel() {
        return model;
    }
    
    public void initialize() {
    }
}