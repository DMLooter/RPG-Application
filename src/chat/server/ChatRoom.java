package chat.server;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JPanel;

import chat.Bot;
import main.Main;

public class ChatRoom extends JPanel implements Runnable {

	public static ArrayList<Bot> Bots = new ArrayList<Bot>();

	public String Name;
	public boolean Private;
	public String Password;
	public int MaximumPlayers;
	public boolean Logging;
	public File LogFile;
	public int Port;

	public ServerSocket serverSocket;
	public Thread T;
	public InputStream in;
	public OutputStream out;

	public ChatRoom() {
		this("DND", false, "", 100, false, null, 13579);
	}

	public ChatRoom(String Name, boolean Vis, String Pass, int max, boolean Log, File LogF, int p) {
		super(null, true);
		setSize(800, 200);
		this.Name = Name;
		Private = Vis;
		Password = Pass;
		MaximumPlayers = max;
		Logging = Log;
		LogFile = LogF;
		Port = p;

		Bots.add(new Bot("NightBot"));
		T = new Thread(this);
		T.start();
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
		for (int i = 0; i < Bots.size(); i++) {
			Bots.get(i).message(s);
		}
	}

	@Override
	public void run() {
		Server.ServerRoom = this;
		try {
			serverSocket = new ServerSocket(Port);
			newMessage((Private ? "Private" : "Public") + " server " + Name + " started on port " + Port + ".");
			new Server(1, null);
			new Server(3, null);
			while (true) {

				Thread.sleep(500);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}