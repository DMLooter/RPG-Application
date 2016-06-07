package main;
import java.awt.Dimension;

import javax.swing.JPanel;

import chat.LogScreen;

public class Screen extends JPanel {
	public GameScreen gameScreen;
	public LogScreen logScreen;
	public DataScreen dataScreen;
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