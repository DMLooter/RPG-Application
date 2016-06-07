package data.tree;

import java.util.ArrayList;

public class Party extends Node {
	public ArrayList<EntityNode> entities;

	public Party(String name) {
		super(name);
		entities = new ArrayList<EntityNode>();
	}

	public void addEntity(EntityNode f) {
		entities.add(f);
	}

	@Override
	public Node getChild(int index) {
		return entities.get(index);
	}

	@Override
	public int childIndex(Node f) {
		return entities.indexOf(f);
	}
	
	@Override
	public int childrenNum() {
		return entities.size();
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}