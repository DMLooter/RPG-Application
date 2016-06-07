package deprecated.main;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeListener implements TreeModelListener, TreeSelectionListener {

	public static TreeListener main = new TreeListener();
	public static Field Selected;

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		//Returns the last path element of the selection.
		//This method is useful only when the selection model allows a single selection.
		//		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getSource();
		if (node == null)
			//Nothing is selected.     
			return;
		if(node.isLeaf()){
			//Object nodeInfo = node.getUserObject();
			
		}

	}

	@Override
	public void treeNodesChanged(TreeModelEvent e) {}

	@Override
	public void treeNodesInserted(TreeModelEvent e) {}

	@Override
	public void treeNodesRemoved(TreeModelEvent e) {}

	@Override
	public void treeStructureChanged(TreeModelEvent e) {}

}