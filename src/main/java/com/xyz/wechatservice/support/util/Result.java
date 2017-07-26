package com.xyz.wechatservice.support.util;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	private T data;
	private int errorcode;
	private String error;
	private Exception exception;

	/** 判断返回结果是否成功 */
	public boolean isSuccess() {
		return errorcode == 0;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/** 判断返回结果是否有结果对象 */
	public boolean hasData() {
		return errorcode == 0 && data != null;
	}

	/** 判断返回结果是否有异常 */
	public boolean hasException() {
		return exception != null;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String toString() {
		StringBuilder result = new StringBuilder("Result");
		if (data != null)
			result.append("<" + data.getClass().getSimpleName() + ">");
		result.append(": {code=" + errorcode);
		if (data != null)
			result.append(", object=" + data);
		if (error != null)
			result.append(", error=" + error);
		if (message != null)
			result.append(", message=" + message);
		if (exception != null) {
			StringWriter stringWriter = new StringWriter();
			exception.printStackTrace(new PrintWriter(stringWriter));
			result.append(", exception=" + stringWriter.toString());
		}
		result.append(" }");
		return result.toString();
	}
}
