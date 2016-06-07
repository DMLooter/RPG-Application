package data.tree;

import java.util.ArrayList;

public class Section extends Node {
	public ArrayList<Field> fields;

	public Section(String name) {
		super(name);
		fields = new ArrayList<Field>();
	}

	public void addField(Field f) {
		fields.add(f);
	}

	@Override
	public Node getChild(int index) {
		return fields.get(index);
	}

	@Override
	public int childIndex(Node f) {
		return fields.indexOf(f);
	}
	
	@Override
	public int childrenNum() {
		return fields.size();
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}