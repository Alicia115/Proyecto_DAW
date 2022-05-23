package org.fct.servidor.model;

public class ErrorObject {
	
	private Object object;
	private String error;
	
	public ErrorObject() {
	
	}
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	
}
