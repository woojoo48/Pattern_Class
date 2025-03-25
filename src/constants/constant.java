package constants;

public class constant {
	
	public enum FileMenu {
		NEW("new"),
		OPEN("open"),
		SAVE("save"),
		SAVEAS("save as"),
		QUIT("quit");
		
		private String text;
		
		private FileMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}

	public enum EditMenu {
		PROPERTY("property"),
		UNDO("undo"),
		REDO("redo");
		
		private String text;
		
		private EditMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	
	public enum GraphicMenu {
		LINE_THICKNESS("Line thickness"),
		LINE_STYLE("Line Style"),
		FONT_STYLE("Font style"),
		FONT_SIZE("Font size");

		private String text;
		
		private GraphicMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
}
