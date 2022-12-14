package RDP_Client;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	public void GUI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 735, 437);
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("Remote Desktop Control");
		lblNewLabel1.setFont(new Font("Tekton", Font.BOLD, 24));
		lblNewLabel1.setBounds(213, 34, 277, 43);
		frame.getContentPane().add(lblNewLabel1);
		
		tfipaddress = new JTextField();
		tfipaddress.setBounds(286, 133, 345, 29);
		frame.getContentPane().add(tfipaddress);
		tfipaddress.setColumns(10);
		

		JLabel lblNewLabel = new JLabel("IP ADDRESS");
		lblNewLabel.setBounds(111, 124, 108, 43);
		frame.getContentPane().add(lblNewLabel);

		btnsubmit = new JButton("Connect");
		btnsubmit.setBounds(286, 282, 102, 37);
		frame.getContentPane().add(btnsubmit);
		btnsubmit.addActionListener(this);
		
	}
	public StartClient() {
		GUI();
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		new StartClient();
		
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

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "something wrong, try again", "ERROR", JOptionPane.ERROR_MESSAGE);
				} 
			} else {
				JOptionPane.showMessageDialog(null, "enter fields please", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
