package main;

import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import data.tree.DataListener;
import data.tree.EntityDataModel;
import data.tree.EntityNode;
import data.tree.Field;
import data.tree.FieldEditor;
import data.tree.FieldRenderer;
import data.tree.IntegerField;
import data.tree.Party;
import data.tree.Root;
import data.tree.Section;

/**
 * Screen used to display {@link Entity Entity's} data in a {@link JTree} using
 * a custom {@link EntityDataModel}.
 * 
 * @author Michael
 *
 */
public class DataScreen extends JPanel {
	JTree tree;

	/**
	 * Method to initialize the Screen, calls {@link #setupTree() setupTree}.
	 */
	public DataScreen() {
		super(null, true);
		setBounds(0, 0, 300, 400);
		setupTree();
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setBounds(new Rectangle(0, 0, 300, 400));
		add(treeView);
	}

	/**
	 * Initializes the {@link JTree} by activating the {@link FieldRenderer
	 * renderer} and {@link FieldEditor editor} for the {@link EntityDataModel},
	 * and calling {@link #updateNodes(Root) updateNodes} on the root node.
	 */
	public void setupTree() {
		DataListener listener = new DataListener();
		Root top = new Root("Parties");
		EntityDataModel treeModel = new EntityDataModel(top);
		treeModel.addTreeModelListener(listener);

		updateNodes(top);

		tree = new JTree(treeModel);
		tree.setRowHeight(0);
		FieldRenderer renderer = new FieldRenderer();
		tree.setCellRenderer(renderer);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		FieldEditor editor = new FieldEditor(tree);
		editor.addCellEditorListener(listener);
		tree.setCellEditor(editor);
		tree.setEditable(true);
	}

	/**
	 * Method for updating the tree when the Entity list is updated.
	 * 
	 * @param top
	 *            the root node of the tree.
	 */
	public void updateNodes(Root top) {
		Party party = null;
		Section category = null;
		EntityNode entity = null;
		Field field = null;

		party = new Party("Players");
		top.addParty(party);

		for (Entity e : Main.entities) {
			entity = new EntityNode(e.Name);
			party.addEntity(entity);

			category = new Section("Ability Scores");
			entity.addSection(category);

			field = new IntegerField("Str", e.Str, 0, 20);
			category.addField(field);
			field = new IntegerField("Con", e.Con, 0, 20);
			category.addField(field);
			field = new IntegerField("Dex", e.Dex, 0, 20);
			category.addField(field);
			field = new IntegerField("Int", e.Int, 0, 20);
			category.addField(field);
			field = new IntegerField("Wis", e.Wis, 0, 20);
			category.addField(field);
			field = new IntegerField("Cha", e.Cha, 0, 20);
			category.addField(field);
		}
	}
}