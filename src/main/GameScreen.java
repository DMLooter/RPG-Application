package main;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * Screen used for displaying the game map or other relevant visual aides.
 * @author Michael
 *
 */
public class GameScreen extends JPanel {
	public GameScreen(){
		super(null, true);
		setBounds(300,0,500,400);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.fillRect(0, 0, 500, 400);
	}
}