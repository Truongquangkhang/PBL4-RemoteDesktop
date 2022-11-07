package RDP_Client;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ReceiveScreen extends Thread {

	
	private JPanel panel = new JPanel();
	Socket soc;
	DataInputStream dis;
	public ReceiveScreen(Socket soc, JPanel panel) {
		this.soc = soc;
		this.panel = panel;
		start();
	}

	public void run() {
		BufferedImage image;
		ByteArrayInputStream bais;
		try {
			dis= new DataInputStream(soc.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {     
			try {
				byte []bytes = new byte[1920*1080]; 
				int count = 0;
				do {
					try {
						count += dis.read(bytes, count, bytes.length - count);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));
				bais = new ByteArrayInputStream(bytes);
				image = ImageIO.read(bais);
				bais.reset();
 //               Image ig = image.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_FAST);
				Graphics g = panel.getGraphics();
				g.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), panel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
