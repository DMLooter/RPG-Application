package main;

import javax.swing.tree.DefaultMutableTreeNode;

public class Field {
	public int entityIndex;
	public int fieldIndex;
	public String Entity;
	public String Field;

	public Field(DefaultMutableTreeNode node) {
		DefaultMutableTreeNode n2 = (DefaultMutableTreeNode) node.getParent();
		for(int i = 2; i < node.getLevel(); i++){
			n2 = (DefaultMutableTreeNode) n2.getParent();
		}
	}
}