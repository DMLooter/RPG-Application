package main;

import java.awt.Dimension;

import javax.swing.JPanel;

import chat.LogScreen;

/**
 * Main holder class for all the screens needed for the application.
 * 
 * @see {@link DataScreen}, {@link GameScreen}, {@link chat.LogScreen}
 * @author Michael
 *
 */
public class Screen extends JPanel {
	/**
	 * {@link GameScreen} for showing the in-use game map.
	 */
	public GameScreen gameScreen;
	/**
	 * {@link chat.LogScreen} for communicating with people on a server via chat.
	 * Also used to issue commands to bots.
	 */
	public LogScreen logScreen;
	/**
	 * {@link DataScreen} used to display and change values for {@link Entity Entities}.
	 */
	public DataScreen dataScreen;
	
	/**
	 * Initialize main Screen and call constructors to set up the three contained screen.
	 */
	public Screen() {
		super(null, true);
		setPreferredSize(new Dimension(800, 600));
		gameScreen = new GameScreen();
		logScreen = new LogScreen();
		dataScreen = new DataScreen();
		add(gameScreen);
		add(logScreen);
		add(dataScreen);
	}
}