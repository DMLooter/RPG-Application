package main;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import deprecated.main.DataListener;

public class DataScreen extends JPanel {
	JTree tree;

	public DataScreen() {
		super(null, true);
		setBounds(0, 0, 300, 400);
		setupTree();
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setBounds(0, 0, 300, 400);
		add(treeView);
	}

	public void setupTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Entities");
		DefaultTreeModel treeModel = new DefaultTreeModel(top);
		treeModel.addTreeModelListener(new DataListener());

		updateNodes(top);
		tree = new JTree(treeModel);
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
	}

	public void updateNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode entity = null;
		DefaultMutableTreeNode field = null;

		category = new DefaultMutableTreeNode("Players");
		top.add(category);

		for (Entity e : Main.entities) {
			entity = new DefaultMutableTreeNode(e.Name);
			category.add(entity);

			field = new DefaultMutableTreeNode("Str: " + e.str);
			entity.add(field);
			field = new DefaultMutableTreeNode("Con: " + e.con);
			entity.add(field);
			field = new DefaultMutableTreeNode("Dex: " + e.dex);
			entity.add(field);
			field = new DefaultMutableTreeNode("Int: " + e.inte);
			entity.add(field);
			field = new DefaultMutableTreeNode("Wis: " + e.wis);
			entity.add(field);
			field = new DefaultMutableTreeNode("Cha: " + e.cha);
			entity.add(field);
		}
	}
}