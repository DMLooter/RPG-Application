package chat.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Start extends JPanel implements ActionListener{
	public static chat.server.ChatRoom ServerRoom;
	public static chat.client.ChatRoomClient ClientRoom;
	
	public static String UserName;
	
	static JFrame Frame;
	private static Start s = new Start();
	private static JButton Join = new JButton("Join a Chat Room");
	private static JButton Host = new JButton("Host a Chat Room");

	public Start() {
		super(null, true);
		setMinimumSize(new Dimension(600, 600));
		setSize(600, 600);
	}

	public static void start(String ss) {
		UserName = ss;
		Join.setBounds(50, 300, 500, 50);
		Join.setFont(new Font("Arial", 0, 30));
		Join.addActionListener(s);
		Host.setBounds(50, 400, 500, 50);
		Host.setFont(new Font("Arial", 0, 30));
		Host.addActionListener(s);
		s = new Start();
		Frame = new JFrame("Chat Room");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.getContentPane().add(s);
		Frame.setMinimumSize(new Dimension(s.getWidth(), s.getHeight()));
		Frame.pack();
		Insets i = Frame.getInsets();
		int l = i.left;
		int r = i.right + 1;
		int t = i.top;
		int b = i.bottom + 1;
		Frame.setPreferredSize(new Dimension(s.getMinimumSize().width + l + r, s.getMinimumSize().height + t + b));
		Frame.pack();
		System.out.println(Frame.getSize().toString());
		s.add(Join);
		s.add(Host);
		Frame.setVisible(true);
		//Frame.setL
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(0, 0, 600, 600);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(Host)){
			Frame.revalidate();
			Frame.repaint();
			Frame.getContentPane().removeAll();
			Frame.add(new Host());
			Frame.revalidate();
			Frame.repaint();
		}else if(e.getSource().equals(Join)){
			Frame.revalidate();
			Frame.repaint();
			Frame.getContentPane().removeAll();
			Frame.add(new Join());
			Frame.revalidate();
			Frame.repaint();
		}
	}
}