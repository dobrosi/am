package andromedia;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class InputServer {
	private static InputServer instance;

	private Robot robot;

	private DatagramSocket socket;

	private boolean started;

	private Runnable task;

	public static InputServer create() {
		return instance = new InputServer();
	}

	public InputServer start(int port) throws IOException, AWTException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (instance == null) {
			throw new RuntimeException("Please create before.");
		}

		task = () -> {
			try {
				robot = new Robot();
				socket = new DatagramSocket(port);
				byte[] buf = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				while (true) {
					System.out.println("Input server started");
					started = true;
					socket.receive(packet);
					ObjectInputStream ois;
					try {
						ois = new ObjectInputStream(new ByteArrayInputStream(buf));
						MethodPacket methodPacket = (MethodPacket) ois.readObject();
						robot.getClass().getMethod(methodPacket.getMethodName(), methodPacket.getParameterTypes()).invoke(robot, methodPacket.getParameters());
						System.out.println("!!++++" + methodPacket);
					} catch (IOException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		new Thread(task).start();

//		while (!started) {
//			System.out.print(".");
//		}
		
		return instance;
	}
}