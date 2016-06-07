package data.tree;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

class FieldEditor extends AbstractCellEditor implements TreeCellEditor {
	FieldRenderer renderer = new FieldRenderer();
	JSpinner spinner = new JSpinner();
	JTextPane pane = new JTextPane();
	Object value;
	Node n;
	char type;

	Vector listeners = new Vector();

	ChangeEvent changeEvent = null;

	JTree tree;

	public FieldEditor(JTree tree) {
		this.tree = tree;
	}

	public Object getCellEditorValue() {
		return value;
	}

	public void addCellEditorListener(CellEditorListener cel) {
		listeners.addElement(cel);
	}

	public void removeCellEditorListener(CellEditorListener cel) {
		listeners.removeElement(cel);
	}

	public boolean isCellEditable(EventObject event) {
		boolean returnValue = false;
		if (event instanceof MouseEvent) {
			MouseEvent mouseEvent = (MouseEvent) event;
			TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
			if (path != null) {
				Object node = path.getLastPathComponent();
				if ((node != null) && (node instanceof Node)) {
					Node treeNode = (Node) node;
					returnValue = ((treeNode.isLeaf()) && (treeNode instanceof Field));
				}
			}
		}
		return true;
//		return returnValue;
	}

	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row) {
		Component editor = renderer.getTreeCellRendererComponent(tree, value, true, expanded, leaf, row, true);

		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if (stopCellEditing()) {
					fireEditingStopped();
				}
			}
		};
		if (editor instanceof JCheckBox) {
			((JCheckBox) editor).addItemListener(itemListener);
		} else if (editor instanceof FieldPanel) {
			((FieldPanel) editor).remove(1);
			n = ((Node) ((FieldPanel) editor).params.get(0));
			if (((Field) n).type.equals("Integer")) {
				spinner = new JSpinner(new SpinnerNumberModel(((int) ((FieldPanel) editor).params.get(2)),
						((int) ((FieldPanel) editor).params.get(3)), ((int) ((FieldPanel) editor).params.get(4)), 1));
				((FieldPanel) editor).add(spinner);
				type = 'i';
				System.out.println("Integer");
			}else if(((Field) n).type.equals("String")){
				pane = new JTextPane();
				pane.setText((String) ((FieldPanel) editor).params.get(2));
				((FieldPanel) editor).add(pane);
				type = 's';
			}
		} else if (editor instanceof NamePanel){
			n = ((Node) ((NamePanel) editor).params.get(0));
			System.out.println("GO");
			((NamePanel) editor).remove(0);
			pane = new JTextPane();
			pane.setText((String) ((NamePanel) editor).params.get(1));
			((NamePanel) editor).add(pane);
			type = 's';
		}
		return editor;
	}

	public boolean stopCellEditing() {
		System.out.println("Stop");
		try {
			switch (type){
			case 'i':
				value = (int) spinner.getValue();
				return true;
			case 's':
				value = pane.getText();
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void cancelCellEditing() {
		System.out.println("Cancel");
		switch (type){
		case 'i':
			value = (int) spinner.getValue();
			break;
		case 's':
			value = pane.getText();
			break;
		}
		fireEditingStopped();
	}

	protected void fireEditingStopped() {
		if (listeners.size() > 0) {
			ChangeEvent ce = new ChangeEvent(this);
			for (int i = listeners.size() - 1; i >= 0; i--) {
				((CellEditorListener) listeners.elementAt(i)).editingStopped(ce);
			}
		}
	}
}