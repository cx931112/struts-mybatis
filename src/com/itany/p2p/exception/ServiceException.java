package com.itany.p2p.exception;

/**
 * 
 * <服务异常>
 * @author 杨东
 * @version [V1.0.0,2013-7-10]
 *
 */
public class ServiceException extends Exception{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
