package Main;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import Shape.ShapeManager;
import VDrawingPanel.GDrawingPanel;
import VMenuBar.GMenuBar;
import VToolBar.GToolBar;

public class GMainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private GMenuBar menuBar;
    private GToolBar toolBar;
    private GDrawingPanel drawingPanel;

    private ShapeManager shapeManager;

    public GMainFrame() {
        this.setTitle("Drawing Application");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.shapeManager = new ShapeManager();

        this.menuBar = new GMenuBar();
        this.setJMenuBar(menuBar);
  
        this.toolBar = new GToolBar();
        this.add(toolBar);

        this.drawingPanel = new GDrawingPanel(shapeManager);
        this.drawingPanel.setPreferredSize(new Dimension(700, 550));
        this.add(drawingPanel);
        
        this.toolBar.setToolBarListener(this.drawingPanel);
        
        this.setLocationRelativeTo(null);
    }
    
    public void initialize() {
        this.menuBar.initialize();
        this.toolBar.initialize();
        this.drawingPanel.initialize();
    }
}