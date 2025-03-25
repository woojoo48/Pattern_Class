package menuBar;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.constant.EditMenu;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private ArrayList<JMenuItem> editMenus;
	
	public GEditMenu() {
		super("Edit");
		this.editMenus = new ArrayList<>();
		
		for(EditMenu editmenu : EditMenu.values()) {
			JMenuItem item = new JMenuItem(editmenu.getText());
			editMenus.add(item);
			this.add(item);
		}
		
	}

	public void initialize() {
		
	}

	
}