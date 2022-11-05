package RDP_Client;

import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StartClient implements ActionListener{
	private JFrame frame;
	private JTextField tfipaddress;
	private JButton btnsubmit;
	

	Socket soc;
	DataOutputStream dos;
	DataInputStream dis;

	public StartClient() {
		frame = new JFrame();
		frame.setBounds(100, 100, 881, 497);
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().setLayout(null);
		
		
		tfipaddress = new JTextField();
		tfipaddress.setBounds(332, 111, 285, 28);
		frame.getContentPane().add(tfipaddress);
		tfipaddress.setColumns(10);
		

		JLabel lblNewLabel = new JLabel("IP ADDRESS");
		lblNewLabel.setBounds(116, 110, 74, 28);
		frame.getContentPane().add(lblNewLabel);

		btnsubmit = new JButton("Submit");
		btnsubmit.setBounds(310, 341, 112, 34);
		frame.getContentPane().add(btnsubmit);
		btnsubmit.addActionListener(this);
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		new StartClient();
		System.out.println("Done");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnsubmit) {
			if (!tfipaddress.getText().isEmpty()) {
				String IP = tfipaddress.getText();

				try {

					soc = new Socket(IP, 2048);
					new CreateFrame(soc, "100","100");

				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "enter fields please", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}