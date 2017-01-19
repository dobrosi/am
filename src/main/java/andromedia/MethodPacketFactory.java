package andromedia;

import java.awt.Rectangle;
import java.util.Arrays;

public class MethodPacketFactory {
	public static MethodPacket get(String methodName, Class<?>[] parameterTypes, Object[] parameters) {
		return new MethodPacket(methodName, parameterTypes, parameters);
	}

	public static MethodPacket get(String methodName, String[] parameterClassNames, String[] parameters) {
		Class<?>[] parameterTypes = convertTypes(parameterClassNames);
		Object[] convertedParameters = convertParameters(parameters, parameterTypes);
		return get(methodName, parameterTypes, convertedParameters);
	}

	public static Object[] convertParameters(String[] parameters, Class<?>[] parameterTypes) {
		if (parameters.length != parameterTypes.length) {
			throw new RuntimeException("parameters.length != parameterTypes.length");
		}
		Object[] res = new Object[parameters.length];
		int i = 0;
		for (Class<?> class1 : parameterTypes) {
			Object v = parameters[i];
			if (v != null) {
				if (class1 == int.class) {
					v = Integer.parseInt(v.toString());
				}
			}
			res[i] = v;
			i++;
		}
		return res;
	}

	public static Class<?>[] convertTypes(String[] parameterClassNames) {
		return Arrays.asList(parameterClassNames).stream().map(className -> {
			try {
				if(PrimitiveTypeUtil.isPrimitive(className)) {
					return PrimitiveTypeUtil.getPrimitiveClass(className);
				}
				return Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}).toArray(size -> new Class<?>[size]);
	}

	public static MethodPacket getKeyPress(int keyCode) {
		return get("keyPress", new Class[] { int.class }, new Object[] { keyCode });
	}

	public static MethodPacket getKeyRelease(int keyCode) {
		return get("keyRelease", new Class[] { int.class }, new Object[] { keyCode });
	}

	public static MethodPacket getMouseMove(int x, int y) {
		return get("mouseMove", new Class[] { int.class, int.class }, new Object[] { x, y });
	}

	public static MethodPacket getMouseWheel(int wheelAmt) {
		return get("mouseWheel", new Class[] { int.class }, new Object[] { wheelAmt });
	}

	public static MethodPacket getMousePress(int buttons) {
		return get("mousePress", new Class[] { int.class }, new Object[] { buttons });
	}

	public static MethodPacket getMouseRelease(int buttons) {
		return get("mouseRelease", new Class[] { int.class }, new Object[] { buttons });
	}

	public static MethodPacket getCreateScreenCapture(Rectangle screenRect) {
		return get("createScreenCapture", new Class[] { Rectangle.class }, new Object[] { screenRect });
	}
}