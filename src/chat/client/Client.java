package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import chat.LogScreen;

class Client extends Thread {
	public static ArrayList<String> Messages = new ArrayList<String>(10);

	public Socket c;
	public PrintWriter out;
	public BufferedReader in;
	public int index;

	public Client(Socket c) {
		this.c = c;
		try {
			out = new PrintWriter(c.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	@Override
	public void run() {
		try {
			Listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Listen() throws IOException {
		String i;
		while ((i = in.readLine()) != null) {
			LogScreen.clientRoom.newMessage(i);
		}
	}

}