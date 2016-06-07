package data.tree;

import java.util.ArrayList;

import javax.swing.JPanel;

public class FieldPanel extends JPanel {
	public ArrayList<Object> params = new ArrayList<Object>();
	
	public void reset(){
		removeAll();
		params.clear();
	}
}