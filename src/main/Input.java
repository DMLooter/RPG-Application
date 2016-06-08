package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import chat.LogScreen;

public class Input implements KeyListener, MouseListener, ActionListener {
	public static Input main = new Input();

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (((JMenuItem) e.getSource()).getText().equals("Create Server")) {
			LogScreen.creationThread = new Thread() {
				public void run() {
					Main.mainScreen.logScreen.host();
				}
			};
			LogScreen.creationThread.start();
		} else if (((JMenuItem) e.getSource()).getText().equals("Quick Host Server")) {
			LogScreen.creationThread = new Thread() {
				public void run() {
					Main.mainScreen.logScreen.quickHost();
				}
			};
			LogScreen.creationThread.start();
		} else if (((JMenuItem) e.getSource()).getText().equals("Join Server")) {
			new Thread() {
				public void run() {

					JTextField ipF = new JTextField();
					JTextField portF = new JTextField("13579");
					Object[] message = { "HostName/IP:", ipF, "Port:", portF };

					int option = JOptionPane.showConfirmDialog(null, message, "Join Server",
							JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						String ip = ipF.getText();
						int port;
						try {
							port = Integer.parseInt(portF.getText());
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Port number invalid.", "Parse error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						System.out.println(ip);
						if (ip != null) {
							Socket so = null;
							try {
								so = new Socket(ip, port);
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, "Connection Failed.", "Connection error",
										JOptionPane.ERROR_MESSAGE);
								Main.mainScreen.logScreen.chat
										.append("Connection to server " + ip + ":" + port + " failed." + "\n");
								return;
							}
							Main.mainScreen.logScreen.join(so);
						}
					} else {
						System.out.println("Login canceled");
					}

				}
			}.start();
		}
	}

}
