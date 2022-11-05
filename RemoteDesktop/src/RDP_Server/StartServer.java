package RDP_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.awt.*;


public class StartServer {

	Vector<ThreadClient> listClient = new Vector<ThreadClient>();
	private Socket socket;
	public StartServer() {
		try {
			ServerSocket server = new ServerSocket(2048);
			socket = server.accept();
			System.out.println("Success");
			
			GraphicsEnvironment gpre = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gprd = gpre.getDefaultScreenDevice();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			String wight = ""+dim.width;
			String height =""+dim.height;
			Rectangle rect = new Rectangle(dim);
			System.out.println(wight+" "+height);
			try {
				Robot robot = new Robot(gprd);
				
				new SendScreen(robot,rect,socket);
				new ReceiveEvent(socket,robot);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new StartServer();

	}

}