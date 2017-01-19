package andromedia;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class FirstTest {

	@Test
	public void testRobot() throws AWTException {
		Robot r = new Robot();
		r.mouseMove(1000, 1000);

		r.mouseWheel(-10);
	}

	@Test
	public void sendPacket() throws IOException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, AWTException {	
		InputServer.create().start(1979);
		System.out.println("...");
		
		InputSender inputSender = InputSender.create().start("localhost", 1979);
		
		inputSender.send(MethodPacketFactory.getMouseMove(500, 500));
		inputSender.send(MethodPacketFactory.getMouseWheel(1));
		inputSender.send(MethodPacketFactory.getMousePress(InputEvent.BUTTON1_MASK));
		inputSender.send(MethodPacketFactory.getMouseRelease(InputEvent.BUTTON1_MASK));
		inputSender.send(MethodPacketFactory.getKeyPress(KeyEvent.VK_SHIFT));
		inputSender.send(MethodPacketFactory.getKeyPress(KeyEvent.VK_A));
		inputSender.send(MethodPacketFactory.getKeyRelease(KeyEvent.VK_A));
		inputSender.send(MethodPacketFactory.getKeyRelease(KeyEvent.VK_SHIFT));
		
		inputSender.send(MethodPacketFactory.get("mouseMove", new String[]{"int", "int"}, new String[]{"300", "300"}));
		
		inputSender.send(MethodPacketFactory.getMousePress(InputEvent.BUTTON2_MASK));
		inputSender.send(MethodPacketFactory.getMouseRelease(InputEvent.BUTTON2_MASK));
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
