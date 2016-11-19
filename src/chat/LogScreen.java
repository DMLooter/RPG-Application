package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import chat.client.ChatRoomClient;
import chat.main.Start;
import chat.server.ChatRoom;
import chat.server.Server;

public class LogScreen extends JPanel implements ActionListener {
	public ChatRoom serverRoom;
	public ChatRoomClient clientRoom;

	public JTextArea chat = new JTextArea();
	public JScrollPane cont = new JScrollPane(chat);

	public JTextPane Input = new JTextPane();
	public JButton Submit = new JButton("Send");

	public LogScreen() {
		super(null, true);
		setBounds(0, 400, 800, 200);
		Input.setEditable(true);
		Input.setBounds(0, 175, 500, 25);
		Submit.setBounds(500, 175, 100, 25);
		Submit.addActionListener(this);

		cont.setBounds(0, 0, 800, 175);
		add(cont);
		add(Input);
		add(Submit);
	}

	public void host() {
		clientRoom = null;
		serverRoom = new ChatRoom();
		add(serverRoom);
		repaint();
	}

	public void join(Socket s) {
		serverRoom = null;
		clientRoom = new ChatRoomClient("DND", s);
		add(clientRoom);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (clientRoom != null) {
			if (e.getSource().equals(Submit)) {
				String i;
				if ((i = Input.getText()).length() > 0) {
					String s = Start.UserName + ": " + i;
					clientRoom.out.println(s);
					Input.setText("");
				}
			}
		}else if(serverRoom != null){
			if (e.getSource().equals(Submit)) {
				String i;
				if ((i = Input.getText()).length() > 0) {
					String s = Start.UserName + ": " + i;
					serverRoom.newMessage(s);
					Server.Messages.add(s);
					Input.setText("");
				}
			}
		}else{
			if (e.getSource().equals(Submit)) {
				String i;
				if ((i = Input.getText()).length() > 0) {
					String s = Start.UserName + ": " + i;
					chat.append(s + "\n");
					Input.setText("");
				}
			}
		}
	}
}