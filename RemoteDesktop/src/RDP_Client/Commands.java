package RDP_Client;

public enum Commands {

	MOUSE_MOVE(-1), 
	MOUSE_LEFTCLICK(-2), 
	MOUSE_RIGHTCLICK(-3), 
	MOUSE_LEFTDOWN(-4), 
	MOUSE_LEFTUP(-5),
	MOUSE_WHELL(-6),
	KEY_TYPE(-7),
	KEY_DOWN(-8),
	KEY_UP(-9);

	public int value;

	private Commands(int i) {
		value = i;
	}

}
