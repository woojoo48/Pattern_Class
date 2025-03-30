package VMenuBar;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.constant.GraphicMenu;

public class GGraphicMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JMenuItem> graphicMenus;
	
	public GGraphicMenu() {
		super("graphic");
		this.graphicMenus = new ArrayList<>();
		
		for(GraphicMenu graphicmenu : GraphicMenu.values()) {
			JMenuItem item = new JMenuItem(graphicmenu.getText());
			graphicMenus.add(item);
			this.add(item);
		}
		
	}
	
	public void initialize() {
		
	}

}
