package Model;

public class IPv4 {
	private String HostName;
	public String getHostName() {
		return HostName;
	}
	public void setHostName(String hostName) {
		HostName = hostName;
	}
	public String getIPV4() {
		return IPV4;
	}
	public void setIPV4(String iPV4) {
		IPV4 = iPV4;
	}
	private String IPV4;
	@Override
	public String toString() {
		return HostName;
	}
}
