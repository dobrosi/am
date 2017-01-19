package andromedia;

import java.io.Serializable;

public class MethodPacket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String methodName;
	private Class<?>[] parameterTypes;
	private Object[] parameters;	
	public MethodPacket(String methodName, Class<?>[] parameterTypes, Object[] parameters) {
		super();
		this.methodName = methodName;
		this.parameterTypes = parameterTypes;
		this.parameters = parameters;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}
	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}
	public Object[] getParameters() {
		return parameters;
	}
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public String toString() {
		return "Method " + methodName + " " + super.toString();
	}
}