package RDP_Client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;

public class SendEvent implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	public boolean dragg;
	DataOutputStream dos;
	Socket soc;
	JPanel panel = new JPanel();
	PrintWriter writer = null;
	double W, H;

	public SendEvent(Socket soc, JPanel panel, double W, double H) {
		this.soc = soc;
		this.panel = panel;
		this.W = (W);
		this.H = (H);
		this.dragg = false;

		this.panel.addMouseMotionListener(this);
		this.panel.addMouseListener(this);
		this.panel.addMouseWheelListener(this);
		this.panel.addKeyListener(this);
		this.panel.setFocusable(true);
		this.panel.requestFocusInWindow();

		try {
			writer = new PrintWriter(soc.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == 1) {
			writer.println(Commands.MOUSE_LEFTCLICK.value);
		} else if (e.getButton() == 3) {
			writer.println(Commands.MOUSE_RIGHTCLICK.value);
		}
		writer.flush();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		if (dragg) {
			writer.println(Commands.MOUSE_LEFTUP.value);
			writer.flush();
		}
		dragg = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (!dragg) {
			writer.println(Commands.MOUSE_LEFTDOWN.value);
			writer.flush();
			dragg = true;
		} else {
			double Xscale = this.W / panel.getWidth();
			double Yscale = this.H / panel.getHeight();
			writer.println(Commands.MOUSE_MOVE.value);
			writer.println((int) (Xscale * e.getX()));
			writer.println((int) (Yscale * e.getY()));
			writer.flush();
			dragg = true;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		double Xscale = this.W / panel.getWidth();
		double Yscale = this.H / panel.getHeight();
		writer.println(Commands.MOUSE_MOVE.value);
		writer.println((int) (Xscale * e.getX()));
		writer.println((int) (Yscale * e.getY()));
		writer.flush();

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		writer.println(Commands.MOUSE_WHELL.value);
		writer.println(e.getWheelRotation());
		writer.flush();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() != 0) {
			writer.println(Commands.KEY_DOWN.value);
			System.out.println(e.getKeyCode());
			writer.println(e.getKeyCode());
			writer.flush();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() != 0) {
			writer.println(Commands.KEY_UP.value);
			System.out.println(e.getKeyCode());
			writer.println(e.getKeyCode());
			writer.flush();
		}
	}

}
