/**
 * 
 */
package com.greatconan.commons.db;

/**
 * 自定义异常类，表示访问未设置的JDBC属性时的异常
 * 
 * @author i-jiech
 * 
 */
class JDBCPropertyNotSetException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1917299663982542310L;

	public JDBCPropertyNotSetException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JDBCPropertyNotSetException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public JDBCPropertyNotSetException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public JDBCPropertyNotSetException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
