package RDP_Server;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Model.IPv4;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartServer {
	Vector<Client> listClient = new Vector<Client>();

	public static void main(String[] args) {
		GUI();
		new StartServer();

	}

	public StartServer() {
		ServerSocket serversoc;
		try {
			serversoc = new ServerSocket(2048);
			while (true) {
				
				Socket socket = serversoc.accept();
				Client client = new Client(socket, this);
				Tool.displayTray("Connected", "");
				listClient.add(client);
				client.start();
			}

		} catch (Exception e) {

		}
	}

	public static void GUI() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 838, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Remote Desktop Control");
		lblNewLabel.setFont(new Font("Tekton", Font.BOLD, 24));
		lblNewLabel.setBounds(216, 10, 279, 42);
		frame.getContentPane().add(lblNewLabel);

		JComboBox<IPv4> comboBox = new JComboBox<IPv4>(ALL_IP());
		comboBox.setBounds(395, 99, 279, 31);
		frame.getContentPane().add(comboBox);
		JTextField textField = new JTextField();
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IPv4 temp = (IPv4) comboBox.getSelectedItem();
				textField.setText(temp.getIPV4());

			}
		});
		textField.setBounds(395, 189, 279, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(ALL_IP().get(0).getIPV4());

		JLabel lblNewLabel_1 = new JLabel("Host Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(99, 94, 89, 36);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("IPv4");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(99, 187, 69, 33);
		frame.getContentPane().add(lblNewLabel_2);
		frame.setVisible(true);
	}

	public static Vector<IPv4> ALL_IP() {
		Vector<IPv4> listHost = new Vector<IPv4>();
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				// filters out 127.0.0.1 and inactive interfaces
				if (iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();
					IPv4 temp = new IPv4();
					// *EDIT*
					if (addr instanceof Inet6Address)
						continue;
					temp.setHostName(iface.getDisplayName());
					temp.setIPV4(addr.getHostAddress());
					listHost.add(temp);
				}
			}
			return listHost;
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

	}
}


class Client extends Thread {
	public Socket socket;
	public StartServer server;

	public Client(Socket soc, StartServer server) {
		this.socket = soc;
		this.server = server;
	}

	public void run() {
		GraphicsEnvironment gpre = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gprd = gpre.getDefaultScreenDevice();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rect = new Rectangle(dim);
		try {
			Robot robot = new Robot(gprd);

			new SendScreen(robot, rect, socket);
			new ReceiveEvent(socket, robot);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
