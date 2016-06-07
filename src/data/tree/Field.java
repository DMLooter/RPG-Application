package data.tree;


public class Field extends Node{
	public String type;
	public Field(String name, String type) {
		super(name);
		this.type = type;
	}

	@Override
	public Node getChild(int index) {
		return null;
	}

	@Override
	public int childIndex(Node f) {
		return -1;
	}

	@Override
	public int childrenNum() {
		return 0;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}
	
	public String toString(){
		return name;
	}
}