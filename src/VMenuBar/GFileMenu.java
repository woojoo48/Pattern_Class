package VMenuBar;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.constant.FileMenu;

public class GFileMenu extends JMenu{
	private static final long serialVersionUID = 1L;

	private ArrayList<JMenuItem> menuItems;
	
	public GFileMenu() {
		super("File");
		this.menuItems = new ArrayList<>();
		
		for(FileMenu filemenu : FileMenu.values()) {
            JMenuItem item = new JMenuItem(filemenu.getText());
            menuItems.add(item);
            this.add(item);
        }
	}

	public void initialize() {
		
	}

}
