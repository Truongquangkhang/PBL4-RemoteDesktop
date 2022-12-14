package RDP_Server;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class SendScreen extends Thread {
	Socket soc;
	Robot robot;
	Rectangle rectangle;
	DataOutputStream dos;
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	boolean continuedloop = true;

	public SendScreen(Robot r, Rectangle rect, Socket s) {
		this.robot = r;
		this.rectangle = rect;
		this.soc = s;
		start();
	}

	@Override
	public void run() {
		ObjectOutputStream oss = null;
		try {
			oss = new ObjectOutputStream(soc.getOutputStream());
			oss.writeObject(rectangle);
			dos = new DataOutputStream(soc.getOutputStream());
			BufferedImage image;
			while (!soc.isClosed()) {
				image = robot.createScreenCapture(rectangle);
				ImageIO.write(image, "jpg", baos);
				dos.write(baos.toByteArray());
				baos.reset();
				Thread.sleep(10);
			}

		} catch (Exception e) {
			Tool.displayTray("Client is closed", "");
			try {
				soc.close();
				dos.close();
				oss.close();
			} catch (IOException e1) {
				
				
			}
		}

	}

}