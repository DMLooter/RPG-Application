package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Input implements KeyListener, MouseListener, ActionListener {
	public static Input main = new Input();

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (((JMenuItem) e.getSource()).getText().equals("Create Server")) {
			new Thread() {
				public void run() {
					Main.mainScreen.logScreen.host();
				}
			}.start();
		} else if (((JMenuItem) e.getSource()).getText().equals("Join Server")) {
			new Thread() {
				public void run() {
					String ip = (String) JOptionPane.showInputDialog(Main.frame, "Input a Hostname/IP", "Join a Server", JOptionPane.PLAIN_MESSAGE);
					System.out.println(ip);
					if (ip != null) {
						Socket so = null;
						try {
							so = new Socket(ip, 13579);
						} catch (IOException e) {
							e.printStackTrace();
						}
						Main.mainScreen.logScreen.join(so);
					}
				}
			}.start();
		}
	}

}
