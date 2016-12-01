package com.itany.p2p.exception;

/**
 * 
 * <数据访问异常>
 * @author 杨东 
 * @version [V1.0.0,2013-7-10]
 *
 */
public class DataAccessException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
