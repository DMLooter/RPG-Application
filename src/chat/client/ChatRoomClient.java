package chat.client;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

import javax.swing.JPanel;

import main.Main;

public class ChatRoomClient extends JPanel{

	public Socket Socket;

	public BufferedReader in;
	public PrintWriter out;

	public ChatRoomClient(String Name, Socket s) {
		super(null, true);
		setSize(800, 200);

		Socket = s;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Client(s);
		newMessage("You joined chatroom " + Name + " at " + Calendar.getInstance().getTime());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new java.awt.Font("Arial", 0, 20));
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.white);
		FontMetrics fm = g.getFontMetrics();
		g.drawString("Font: ", 99 - fm.stringWidth("Font: "), 570);
	}

	public void newMessage(String s) {
		Main.mainScreen.logScreen.chat.append(s + "\n");
	}
}