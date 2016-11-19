package data.tree;

import java.util.ArrayList;

public class Root extends Node {
	public ArrayList<Party> parties;

	public Root(String name) {
		super(name);
		parties = new ArrayList<Party>();
	}

	public void addParty(Party f) {
		parties.add(f);
	}

	@Override
	public Node getChild(int index) {
		return parties.get(index);
	}

	@Override
	public int childIndex(Node f) {
		return parties.indexOf(f);
	}
	
	@Override
	public int childrenNum() {
		return parties.size();
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}