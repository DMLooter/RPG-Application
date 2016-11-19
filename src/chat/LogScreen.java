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
import chat.server.ChatRoom;
import chat.server.Server;
import chat.server.ServerCreation;

/**
 * Screen used for displaying chat or loged events. Contains the Client and
 * Server chatrooms.
 * 
 * @author Michael
 *
 */
public class LogScreen extends JPanel implements ActionListener {
	public static ChatRoom serverRoom;
	public static ChatRoomClient clientRoom;

	public static Thread creationThread;

	public static String userName = "Bob";

	public JTextArea chat = new JTextArea();
	public JScrollPane cont = new JScrollPane(chat);

	public JTextPane Input = new JTextPane();
	public JButton Submit = new JButton("Send");

	/**
	 * Initializes a Log Screen with a input box, submit button, and message
	 * box.
	 */
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

	/**
	 * Calls the ServerCreator to create a new Server with the specified
	 * settings. Also sets the client to null to avoid using both client and
	 * server at the same time.
	 */
	public void host() {
		clientRoom = null;
		ServerCreation.createServer();
	}

	/**
	 * Quickly creates a server using default settings. Also sets the client to
	 * null to avoid using both client and server at the same time.
	 */
	public void quickHost() {
		clientRoom = null;
		serverRoom = new ChatRoom("RPG", false, "", 100, false, null, 13579);
	}

	/**
	 * Joins a Server using the specified Socket to start a Client. Also sets
	 * the server to null to avoid using both client and server at the same
	 * time.
	 * 
	 * @param s
	 *            The socket, created by Input, to be used to connect to the
	 *            server.
	 */
	public void join(Socket s) {
		serverRoom = null;
		clientRoom = new ChatRoomClient("RPG", s);
		add(clientRoom);
		repaint();
	}

	/**
	 * Gets messages from the Server and Client Chat Rooms.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (clientRoom != null) {
			if (e.getSource().equals(Submit)) {
				String i;
				if ((i = Input.getText()).length() > 0) {
					String s = userName + ": " + i;
					clientRoom.out.println(s);
					Input.setText("");
				}
			}
		} else if (serverRoom != null) {
			if (e.getSource().equals(Submit)) {
				String i;
				if ((i = Input.getText()).length() > 0) {
					String s = userName + ": " + i;
					serverRoom.newMessage(s);
					Server.Messages.add(s);
					Input.setText("");
				}
			}
		} else {
			if (e.getSource().equals(Submit)) {
				String i;
				if ((i = Input.getText()).length() > 0) {
					String s = userName + ": " + i;
					chat.append(s + "\n");
					Input.setText("");
				}
			}
		}
	}
}