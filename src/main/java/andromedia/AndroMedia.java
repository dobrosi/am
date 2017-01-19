package andromedia;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AndroMedia {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, AWTException {
		InputServer.create().start(1979);
	}
}