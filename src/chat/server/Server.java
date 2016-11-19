package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import chat.main.MultiArray;
import chat.main.Start;

public class Server extends Thread {
	public static ChatRoom ServerRoom;
	public static MultiArray<Socket, PrintWriter, BufferedReader> Clients = new MultiArray<Socket, PrintWriter, BufferedReader>(50);
	public static ArrayList<String> Messages = new ArrayList<String>(10);

	public int mode;
	public Socket c;
	public PrintWriter out;
	public BufferedReader in;
	public int index;

	public Server(int mode, Socket c) {
		if (mode != 1 && mode != 2 && mode != 3) {
			return;
		}
		this.mode = mode;
		if (mode == 2) {
			this.c = c;
			index = Clients.find(c);
			out = Clients.get2(index);
			in = Clients.get3(index);
		}
		start();
	}

	@Override
	public void run() {
		try {
			if (mode == 1) {
				while (true) {
					Accept();
					Thread.sleep(1000);
				}
			} else if (mode == 2) {
				Listen();
			} else if (mode == 3) {
				while (true) {
					Send();
					Thread.sleep(1000);
				}
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public void Accept() throws IOException {
		Socket client = ServerRoom.serverSocket.accept();
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		Clients.add(client, out, in);
		new Server(2, client);
	}

	public void Listen() throws IOException {
		String i;
		while ((i = in.readLine()) != null) {
			Messages.add(i);
			Start.ServerRoom.newMessage(i);
		}
	}

	public void Send() {
		for (int i = 0; i < Messages.size(); i++) {
			String s = Messages.get(i);
			for (int f = 0; f < Clients.size(); f++) {
				Clients.get2(f).println(s);
			}
			Messages.remove(i);
			i--;
		}
	}
}