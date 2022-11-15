package RDP_Client;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class CreateFrame extends Thread {
	String Width, Hight;
	Socket soc;
	ObjectInputStream ois;
	private JFrame frame = new JFrame();
	private JDesktopPane desktop = new JDesktopPane();
	private JInternalFrame interFrame = new JInternalFrame("Client Screen", true, true, true);
	private JPanel panel = new JPanel();

	public CreateFrame(Socket soc, String w, String H) {
		this.soc = soc;
		drawGUI();
		start();
	}

	public void drawGUI() {
		frame.add(desktop, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		interFrame.setLayout(new BorderLayout());
		interFrame.setContentPane(panel);
		interFrame.setSize(frame.getWidth(), frame.getHeight());
		desktop.add(interFrame);
		interFrame.setMaximizable(true);
		interFrame.setFocusable(true);

		interFrame.setVisible(true);
	}

	public void run() {
		try {
			ois = new ObjectInputStream(soc.getInputStream());
			Rectangle rect = null;
			try {
				rect = (Rectangle)ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new ReceiveScreen(soc, panel);
			new SendEvent(soc, panel, rect.getWidth(), rect.getHeight());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("HELLO 1");
		}

	}

}
