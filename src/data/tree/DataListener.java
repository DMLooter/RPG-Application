package data.tree;

import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

public class DataListener implements TreeModelListener, CellEditorListener {

	@Override
	public void treeNodesChanged(TreeModelEvent e) {
		Node node = (Node) (e.getTreePath().getLastPathComponent());
		/*
		 * If the event lists children, then the changed node is the child of
		 * the node we have already gotten. Otherwise, the changed node and the
		 * specified node are the same.
		 */
		try {
			int index = e.getChildIndices()[0];
			node = (Node) (node.getChildAt(index));
		} catch (NullPointerException exc) {
		}

		System.out.println("The user has finished editing the node.");
		if (node.isLeaf()) {
			System.out.println("New value for " + node.name + ": " + ((IntegerField) node).value);
		}
	}

	@Override
	public void treeNodesInserted(TreeModelEvent e) {
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent e) {
	}

	@Override
	public void treeStructureChanged(TreeModelEvent e) {
	}

	@Override
	public void editingCanceled(ChangeEvent e) {
	}

	@Override
	public void editingStopped(ChangeEvent e) {
		FieldEditor f = ((FieldEditor) e.getSource());
		Node n = f.n;
		System.out.println(n.getClass());
		if (n instanceof IntegerField && !(f.value instanceof String)) {
			((IntegerField) n).value = (int) f.value;
			System.out.println(f.value+": i");
		} else if (n instanceof StringField) {
			((StringField) n).value = (String) f.value;
			System.out.println(f.value+": s");
		} else if(n instanceof Section || n instanceof Entity){
			((Node) n).name = (String) f.value;
			System.out.println(f.value+": n");
		}
	}
}