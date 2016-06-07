package data.tree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class FieldRenderer implements TreeCellRenderer {
	JLabel nameLabel = new JLabel(" ");
	JTextPane valueLabel = new JTextPane();
	FieldPanel renderer = new FieldPanel();
	NamePanel nameRenderer = new NamePanel();
	JSpinner spinner;

	Font fField = new Font("Arial", 0, 12);
	Font fSec = new Font("Arial", 0, 16);
	Font fHead = new Font("Arial", 1, 16);
	FontMetrics fm = renderer.getFontMetrics(fField);

	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
	Color backgroundSelectionColor;
	Color backgroundNonSelectionColor;

	protected FieldPanel getLeafRenderer() {
		return renderer;
	}

	public FieldRenderer() {
		renderer.add(nameLabel);
		renderer.add(valueLabel);
		renderer.setFont(fField);
		nameRenderer.setFont(fHead);
		nameLabel.setFont(fField);
		valueLabel.setFont(fField);
		backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
		backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		Component returnValue = null;
		if ((value != null) && (value instanceof Node)) {
			Node userObject = ((Node) value);
			renderer.reset();
			nameRenderer.reset();
			if (userObject instanceof IntegerField) {
				IntegerField f = (IntegerField) userObject;
				nameLabel.setFont(fField);
				nameLabel.setText(f.name + ":");
				valueLabel.setText("" + f.value);
				nameLabel.setPreferredSize(new Dimension(fm.stringWidth(f.name + ":"), 16));
				valueLabel.setPreferredSize(new Dimension(fm.stringWidth(f.value + "") + 10, 16));

				renderer.params.add(f);
				renderer.params.add(f.name);
				renderer.params.add(f.value);
				renderer.params.add(f.min);
				renderer.params.add(f.max);

				renderer.add(nameLabel);
				renderer.add(valueLabel);
				renderer.setPreferredSize(new Dimension(fm.stringWidth(f.name + ":" + f.value) + 60, 30));
				if (selected) {
					valueLabel.setBackground(backgroundSelectionColor);
					renderer.setBackground(backgroundSelectionColor);
				} else {
					valueLabel.setBackground(backgroundNonSelectionColor);
					renderer.setBackground(backgroundNonSelectionColor);
				}

				renderer.setEnabled(tree.isEnabled());
				returnValue = renderer;
			} else if (userObject instanceof StringField) {
				StringField f = (StringField) userObject;
				nameLabel.setFont(fField);
				nameLabel.setText(f.name + ":");
				valueLabel.setText("" + f.value);
				nameLabel.setPreferredSize(new Dimension(fm.stringWidth(f.name + ":"), 16));
				valueLabel.setPreferredSize(new Dimension(fm.stringWidth(f.value) + 10, 16));

				renderer.params.add(f);
				renderer.params.add(f.name);
				renderer.params.add(f.value);

				renderer.add(nameLabel);
				renderer.add(valueLabel);
				// renderer.setPreferredSize(new Dimension(fm.stringWidth(f.name
				// + ":" + f.value) + 60, 30));
				if (selected) {
					valueLabel.setBackground(backgroundSelectionColor);
					renderer.setBackground(backgroundSelectionColor);
				} else {
					valueLabel.setBackground(backgroundNonSelectionColor);
					renderer.setBackground(backgroundNonSelectionColor);
				}

				renderer.setEnabled(tree.isEnabled());
				returnValue = renderer;
			} else if (userObject instanceof Section) {
				nameLabel.setFont(fSec);
				nameLabel.setText(userObject.name);
				nameLabel.setPreferredSize(
						new Dimension(nameRenderer.getFontMetrics(fSec).stringWidth(userObject.name), 20));

				nameRenderer.params.add(userObject);
				nameRenderer.params.add(userObject.name);

				nameRenderer.add(nameLabel);
				// renderer.setPreferredSize(new Dimension(fm.stringWidth(f.name
				// + ":" + f.value) + 60, 30));
				if (selected) {
					nameRenderer.setBackground(backgroundSelectionColor);
				} else {
					nameRenderer.setBackground(backgroundNonSelectionColor);
				}

				nameRenderer.setEnabled(tree.isEnabled());
				returnValue = nameRenderer;
			} else if (userObject instanceof EntityNode) {
				nameLabel.setFont(fHead);
				nameLabel.setText(userObject.name);
				nameLabel.setPreferredSize(
						new Dimension(nameRenderer.getFontMetrics(fHead).stringWidth(userObject.name), 20));

				nameRenderer.params.add(userObject);
				nameRenderer.params.add(userObject.name);

				nameRenderer.add(nameLabel);
				// renderer.setPreferredSize(new Dimension(fm.stringWidth(f.name
				// + ":" + f.value) + 60, 30));
				if (selected) {
					nameRenderer.setBackground(backgroundSelectionColor);
				} else {
					nameRenderer.setBackground(backgroundNonSelectionColor);
				}

				nameRenderer.setEnabled(tree.isEnabled());
				returnValue = nameRenderer;
			}
		}
		if (returnValue == null) {
			returnValue = defaultRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row,
					hasFocus);
		}
		return returnValue;
	}
}