package data.tree;

import java.util.Vector;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class EntityDataModel implements TreeModel {
	private Vector<TreeModelListener> treeModelListeners = new Vector<TreeModelListener>();
	private Node root;

	public EntityDataModel(Node root){
		this.root = root;
	}
	
	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.addElement(l);
	}

	@Override
	public Object getChild(Object parent, int index) {
		return ((Node) parent).getChild(index);
	}

	@Override
	public int getChildCount(Object parent) {
		return ((Node) parent).childrenNum();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return ((Node) parent).childIndex((Node) child);
	}

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public boolean isLeaf(Object node) {
		return ((Node) node).isLeaf();
	}

	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.removeElement(l);
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		System.out.println("*** valueForPathChanged : " + path + " --> " + newValue);
	}

}