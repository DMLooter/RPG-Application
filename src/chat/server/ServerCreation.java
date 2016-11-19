package chat.server;

import javax.swing.JFrame;

import main.Main;

public class ServerCreation extends JFrame {
	static Host hostPanel;
	static ServerCreation createDialog;
	static ChatRoom created;

	public ServerCreation() {
		super("Create Server");

		System.out.println(this);
		hostPanel = new Host();
		add(hostPanel);
		setSize(600, 600);
		setVisible(true);
		pack();
		System.out.println(this);
	}

	public static void createServer() {
		new Thread() {
			public void run() {
				createDialog = new ServerCreation();
				System.out.println(createDialog);
			}
		}.start();
		while (true) {
			if (Thread.interrupted()) {
				break;
			}
		}
		createDialog.dispose();
	}
}
