package Main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;

import DrawingPanel.GDrawingPanel;
import menuBar.GMenuBar;
import toolBar.GToolBar;

public class GMainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		//attributes
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//components
		//메뉴바만 set임 다른건 다 add 사용함. 이 클래스에서 aggregation hierachy를 만든 것.
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//flowLayout은 순서대로 쌓아서 만듦.
		//LayoutManager layoutManager = new FlowLayout();
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		this.toolBar = new GToolBar();
		this.add(toolBar);
		
		this.drawingPanel = new GDrawingPanel();
		this.drawingPanel.setPreferredSize(new Dimension(700,550));
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
