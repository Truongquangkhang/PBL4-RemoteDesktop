package RDP_Server;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveEvent extends Thread{
	Socket soc;
	Scanner scanner;
	Robot robot = null;
	public ReceiveEvent(Socket soc, Robot robot) {
		this.soc = soc;
		this.robot = robot;
		start();
	}
	public void run() {
		try {
			scanner = new Scanner(soc.getInputStream());
			
			while(!soc.isClosed()) {
				switch (scanner.nextInt()) {
				//Mouse move
				case -1:	
					robot.mouseMove(scanner.nextInt(), scanner.nextInt());
					break;	
				//Left click
				case -2:
					robot.mousePress(16);
					robot.mouseRelease(16);
					break;
				// Right click
				case -3:
					robot.mousePress(4);
					robot.mouseRelease(4);
					break;
				//Left down
				case -4:
					robot.mousePress(16);
					break;
				//Left up
				case -5:
					robot.mouseRelease(16);
					break;
				case -6:
					robot.mouseWheel(scanner.nextInt());
					break;
				case -7:
					break;
				case -8:
					robot.keyPress(scanner.nextInt());
					break;
				case -9:
					robot.keyRelease(scanner.nextInt());
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			try {
				soc.close();
				scanner.close();
				return;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
}
