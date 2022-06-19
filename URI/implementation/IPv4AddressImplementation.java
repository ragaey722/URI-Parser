package uri.implementation;

import uri.IPv4Address;


public class IPv4AddressImplementation extends HostImplementation implements IPv4Address {

	String ip;
	int[] octs;

	public IPv4AddressImplementation(String host) {
		super(host);
		this.ip = host;
		octs = new int[4];
		String[] parts = this.ip.split("\\.");
		for (int q = 0; q < 4; q++) {
			octs[q] = Integer.parseInt(parts[q]);
		}
	}

	@Override
	public byte[] getOctets() {
		byte[] x = new byte[4];
		for (int q = 0; q < 4; q++)
			x[q] = (byte) octs[q];
		return x;
	}

	@Override
	public String toString() {
		String s = Integer.toString(octs[0]) + "." + Integer.toString(octs[1])+"." + Integer.toString(octs[2])+"." +  Integer.toString(octs[3]);
		return s;
	}

}
