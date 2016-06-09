package main;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static JFrame frame;
	public static Screen mainScreen;

	public static JMenuBar menuBar;
	public static JMenu menu, submenu;
	public static JMenuItem menuItem;
	public static JRadioButtonMenuItem rbMenuItem;
	public static JCheckBoxMenuItem cbMenuItem;

	public static void main(String[] args) {
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}*/
		entities.add(new Entity("Bob"));
		entities.add(new Entity("Joe"));
		frame = new JFrame("DND");
		mainScreen = new Screen();
		menuBar();
		frame.setJMenuBar(menuBar);
		frame.add(mainScreen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(Input.main);
		frame.addKeyListener(Input.main);
		frame.pack();
		frame.setVisible(true);
	}

	public static void menuBar() {
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Character");
		menu.setMnemonic(KeyEvent.VK_C);
		menu.getAccessibleContext().setAccessibleDescription("Character Menu");
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("Create Character", KeyEvent.VK_C);
		menuItem.addActionListener(Input.main);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Create a new Character");
		menu.add(menuItem);

		// a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		// a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);

		// a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);

		// Build second menu in the menu bar.
		menu = new JMenu("Network");
		menu.setMnemonic(KeyEvent.VK_G);
		menu.getAccessibleContext().setAccessibleDescription("Network Settings");
		menuBar.add(menu);

		menuItem = new JMenuItem("Create Server", KeyEvent.VK_S);
		menuItem.addActionListener(Input.main);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Create a new Server");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Quick Host Server", KeyEvent.VK_Q);
		menuItem.addActionListener(Input.main);
		menuItem.getAccessibleContext().setAccessibleDescription("Quickly create a new Server");
		menu.add(menuItem);

		menuItem = new JMenuItem("Join Server", KeyEvent.VK_J);
		menuItem.addActionListener(Input.main);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Join a Server");
		menu.add(menuItem);
		
		menu.addSeparator();
		menuItem = new JMenuItem("Change Username", KeyEvent.VK_U);
		menuItem.addActionListener(Input.main);
		menuItem.getAccessibleContext().setAccessibleDescription("Change your Username");
		menu.add(menuItem);
	}
}