package andromedia;

import java.awt.AWTException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class InputSender {
	private static InputSender instance;

	private DatagramSocket socket;

	private InetAddress address;

	private int port;

	public static InputSender create() {
		return instance = new InputSender();
	}

	public InputSender start(String host, int port) throws IOException, AWTException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(instance == null) {
			throw new RuntimeException("Please create before.");
		}
		socket = new DatagramSocket();
		address = InetAddress.getByName(host);
		this.port = port;
		
		return instance;
	}
	
	public InputSender send(MethodPacket methodPacket) throws IOException {
		if(socket == null) {
			throw new RuntimeException("Please start before.");
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
		objectOutputStream.writeObject(methodPacket);
		byte[] buf = out.toByteArray();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		socket.send(packet);
		
		return instance;
	}
	
	public InputSender stop() {
		socket.close();
		
		return instance;
	}
}