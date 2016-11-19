package chat.client;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chat.LogScreen;

public class Join extends JPanel implements ActionListener {

	public JTextField IP = new JTextField();
	public JTextField Port = new JTextField();
	public JButton Done = new JButton("Join");

	public Join() {
		super(null, true);
		setSize(600, 600);
		IP.setBounds(200, 100, 200, 25);
		Port.setBounds(250, 150, 100, 25);
		Done.setBounds(200, 500, 200, 50);
		Done.addActionListener(this);

		add(IP);
		add(Port);
		add(Done);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(Done)) {
			try {
				Socket s = new Socket(IP.getText(), Integer.parseInt(Port.getText()));
				LogScreen.clientRoom = new ChatRoomClient("Name", s);
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
