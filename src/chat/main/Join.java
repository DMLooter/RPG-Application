package chat.main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chat.client.ChatRoomClient;


public class Join extends JPanel implements ActionListener{
	
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
		if(e.getSource().equals(Done)){
			try {
				Socket s = new Socket(IP.getText(), Integer.parseInt(Port.getText()));
				Start.ClientRoom = new ChatRoomClient("Name", s);
				Start.Frame.revalidate();
				Start.Frame.repaint();
				Start.Frame.getContentPane().removeAll();
				Start.Frame.add(Start.ClientRoom);
				Start.Frame.revalidate();
				Start.Frame.repaint();
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
