package chat;

import java.util.ArrayList;
import java.util.HashMap;

import chat.server.Server;
/**
 * Simple bot class to regulate chat through commands and filtering. Made to be expandable through custom commands and extension of mods.
 * @author Michael
 *
 */
public class Bot {
	public String Name;
	ArrayList<String> buffer = new ArrayList<String>();
	HashMap<String, String> Commands = new HashMap<String, String>();
	
	/**
	 * Initiates a bot with the specified name, and has it say hello to the chat.
	 * @param name name of the bot.
	 */
	public Bot(String name) {
		Name = name;
		System.out.println("hi");
	}
	
	/**
	 * All messages going through the server are sent through the bot, which compares it to command strings and banned words.
	 * @param s message to be read over.
	 */
	public void message(String s) {
		compare(s);
	}

	/**
	 * Compares The passed string to Commands and Banned words.
	 * @param s string to parse
	 */
	public void compare(String s) {
		// System.out.println(s.indexOf("!"));
		String user;
		if (s.contains(":")) {
			user = s.substring(0, s.indexOf(":"));
		} else {
			return;
		}
		String d = s.substring(s.indexOf(": ") + 2);
		if (d.indexOf("!") == 0) {
			String command;
			if (d.indexOf(" ") == -1) {
				command = d.substring(1);
			} else {
				command = d.substring(1, d.indexOf(" "));
			}
			if (command.equals("cmdadd")) {
				String newCommand = d.substring(d.indexOf(" ") + 1).substring(0,
						d.substring(d.indexOf(" ") + 1).indexOf(" "));
				String output = d.substring(d.indexOf(" ") + 1)
						.substring(d.substring(d.indexOf(" ") + 1).indexOf(" ") + 1);
				Commands.put(newCommand, output);
				Server.Messages.add(Name + ": " + user + " added Command: " + newCommand);
				LogScreen.serverRoom.newMessage(Name + ": " + user + " added Command: " + newCommand);
			}
			if (Commands.containsKey(command)) {
				Server.Messages.add(Name + ": " + user + " - " + Commands.get(command));
				LogScreen.serverRoom.newMessage(Name + ": " + user + " - " + Commands.get(command));
			}
		} else if (d.toLowerCase().replaceAll("\\s+", "").contains("shit")
				|| d.toLowerCase().replaceAll("\\s+", "").contains("piss")
				|| d.toLowerCase().replaceAll("\\s+", "").contains("fuck")
				|| d.toLowerCase().replaceAll("\\s+", "").contains("cunt")
				|| d.toLowerCase().replaceAll("\\s+", "").contains("cocksucker")
				|| d.toLowerCase().replaceAll("\\s+", "").contains("motherfucker")
				|| d.toLowerCase().replaceAll("\\s+", "").contains("tits")) {
			Server.Messages.add(Name + ": " + user + "has been timed out for being non-FCC approved.");
			LogScreen.serverRoom.newMessage(Name + ": " + user + " has been timed out for being non-FCC approved.");
		}
	}
}