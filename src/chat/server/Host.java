package chat.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chat.LogScreen;

public class Host extends JPanel implements ItemListener, ActionListener {
	public JTextField Name = new JTextField();
	public boolean NameShort = false;

	public String PP[] = { "Public", "Private" };
	public String L[] = { "No", "Yes" };
	public JComboBox<String> Visibility = new JComboBox<String>(PP);
	public JTextField Pass = new JTextField();
	public boolean PassShort = false;

	public JTextField Num = new JTextField();
	public boolean NumToBig = false;

	public JComboBox<String> Log = new JComboBox<String>(L);
	public JTextField LogFile = new JTextField();
	public boolean LogFileNotRight = false;

	public JButton Browse = new JButton("Browse");
	JFileChooser fc = new JFileChooser();
	public JTextField Port = new JTextField();
	public boolean PortWrong = false;

	public JButton Done = new JButton("Done");

	public Host() {
		super(null, true);
		setPreferredSize(new Dimension(600, 600));
		Pass.setEnabled(false);
		Browse.setEnabled(false);
		LogFile.setEnabled(false);
		Name.setBounds(200, 100, 200, 25);
		Visibility.setBounds(200, 150, 200, 25);
		Pass.setBounds(200, 200, 200, 25);
		Num.setBounds(250, 250, 100, 25);
		Log.setBounds(150, 300, 50, 25);
		LogFile.setBounds(210, 300, 200, 25);
		Browse.setBounds(410, 300, 100, 25);
		Port.setBounds(250, 350, 100, 25);
		Done.setBounds(200, 500, 200, 50);
		add(Name);
		add(Visibility);
		add(Pass);
		add(Num);
		add(Log);
		add(LogFile);
		add(Browse);
		add(Port);
		add(Done);
		Visibility.addItemListener(this);
		Log.addItemListener(this);
		Browse.addActionListener(this);
		Done.addActionListener(this);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", 0, 20));
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.white);
		g.drawString("Name: ", 198 - fm.stringWidth("Name: "), 120);
		g.drawString("Visibility: ", 198 - fm.stringWidth("Visibility: "), 170);
		g.drawString("Password: ", 198 - fm.stringWidth("Password: "), 220);
		g.drawString("Max Number of People: ", 248 - fm.stringWidth("Max Number of People: "), 270);
		g.drawString("Logging/LogFile:", 148 - fm.stringWidth("Logging/LogFile:"), 320);
		g.drawString("Port (1025-49151): ", 248 - fm.stringWidth("Port (1025-49151): "), 370);
		g.setFont(new Font("Arial", 0, 15));
		fm = g.getFontMetrics();
		if (NameShort) {
			g.drawString("Name must be more than 5 characters",
					300 - fm.stringWidth("Name must be more than 5 characters") / 2, 140);
		}
		if (PassShort && Visibility.getSelectedIndex() == 1) {
			g.drawString("Password must be more than 4 characters",
					300 - fm.stringWidth("Password must be more than 4 characters") / 2, 240);
		}
		if (NumToBig) {
			g.drawString("Must be an Integer between 2 and 100",
					300 - fm.stringWidth("Must be an Integer between 2 and 100") / 2, 290);
		}
		if (LogFileNotRight && Log.getSelectedIndex() == 1) {
			g.drawString("LogFile must be a valid Text file",
					300 - fm.stringWidth("LogFile must be a valid Text file") / 2, 340);
		}
		if (PortWrong) {
			g.drawString("Must be an Integer between 1025 and 49151",
					300 - fm.stringWidth("Must be an Integer between 1025 and 49151") / 2, 390);
		}
	}

	public String getExtension(String s) {
		int i = s.lastIndexOf('.');
		if (i > 0) {
			return s.substring(i + 1);
		} else {
			return "";
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(Visibility)) {
			if (Visibility.getSelectedIndex() == 1) {
				Pass.setEnabled(true);
			} else {
				Pass.setEnabled(false);
			}
		} else if (e.getSource().equals(Log)) {
			if (Log.getSelectedIndex() == 1) {
				Browse.setEnabled(true);
				LogFile.setEnabled(true);
			} else {
				Browse.setEnabled(false);
				LogFile.setEnabled(false);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Browse) {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				LogFile.setText(file.getAbsolutePath());
			}
		} else if (e.getSource() == Done) {
			if (Name.getText().length() < 6) {
				NameShort = true;
			} else {
				NameShort = false;
			}
			if (Visibility.getSelectedIndex() == 1) {
				if (Pass.getText().length() < 5) {
					PassShort = true;
				} else {
					PassShort = false;
				}
			}
			try {
				int i = Integer.parseInt(Num.getText());
				if (i > 100 || i < 2) {
					NumToBig = true;
				} else {
					NumToBig = false;
				}
			} catch (NumberFormatException nfe) {
				NumToBig = true;
			}
			if (Log.getSelectedIndex() == 1) {
				if (!new File(LogFile.getText()).exists() || !getExtension(LogFile.getText()).equals("txt")) {
					LogFileNotRight = true;
				} else {
					LogFileNotRight = false;
				}
			}
			try {
				int i = Integer.parseInt(Port.getText());
				if (i > 49151 || i < 1025) {
					PortWrong = true;
				} else {
					PortWrong = false;
				}
			} catch (NumberFormatException nfe) {
				PortWrong = true;
			}
			if (!PortWrong && !LogFileNotRight && !NumToBig && !PassShort && !NameShort) {
				LogScreen.serverRoom = new ChatRoom(Name.getText(), Visibility.getSelectedIndex() == 1, Pass.getText(),
						Integer.parseInt(Num.getText()), Log.getSelectedIndex() == 1, new File(LogFile.getText()),
						Integer.parseInt(Port.getText()));
				LogScreen.creationThread.interrupt();
				return;
			}
			repaint();
		}
	}
}