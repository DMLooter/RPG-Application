package data.tree;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class Node extends DefaultMutableTreeNode {
	public String name;

	public Node(String name) {
		this.name = name;
	}

	public abstract Node getChild(int index);

	public abstract int childIndex(Node f);

	public abstract int childrenNum();

	public abstract boolean isLeaf();
}