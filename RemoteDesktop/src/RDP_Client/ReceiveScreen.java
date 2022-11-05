package RDP_Client;


import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ReceiveScreen extends Thread {

	
	private JPanel panel = new JPanel();
	ObjectInputStream ois;

	public ReceiveScreen(ObjectInputStream in, JPanel panel) {
		this.ois  = in;
		this.panel = panel;
		start();
	}

	public void run() {
		
		while (true) {     
			try {
				ImageIcon imageIcon = null;
				
				
//				BufferedImage buff = ImageIO.read(dis);
//				int count = 0;
//				do {
//					try {
//						count += dis.read(bytes, count, bytes.length - count);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				} while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));
				
				
				try {
					imageIcon = (ImageIcon) ois.readObject();

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                Image image = imageIcon.getImage();
				
				
                //image = image.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_FAST);
				Graphics g = panel.getGraphics();
				g.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), panel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}