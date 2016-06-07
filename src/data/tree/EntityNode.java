package data.tree;

import java.util.ArrayList;

public class EntityNode extends Node {
	public ArrayList<Section> sections;

	public EntityNode(String name) {
		super(name);
		sections = new ArrayList<Section>();
	}

	public void addSection(Section f) {
		sections.add(f);
	}

	@Override
	public Node getChild(int index) {
		return sections.get(index);
	}

	@Override
	public int childIndex(Node f) {
		return sections.indexOf(f);
	}
	
	@Override
	public int childrenNum() {
		return sections.size();
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}