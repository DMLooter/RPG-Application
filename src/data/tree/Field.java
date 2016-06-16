package data.tree;

import combat.Entity;

public class Field extends Node {
	public String type;
	protected int entityID;
	protected String fieldName;

	public Field(String name, String type, int ent, String fID) {
		super(name);
		this.type = type;
		entityID = ent;
		fieldName = fID;
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

	public String toString() {
		return name;
	}

	public void update(boolean push) {
	}
}