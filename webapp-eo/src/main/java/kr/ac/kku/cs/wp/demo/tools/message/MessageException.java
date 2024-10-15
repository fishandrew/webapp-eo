package kr.ac.kku.cs.wp.demo.tools.message;

public class MessageException extends RuntimeException {

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace); // TODO Auto-generated constructor stub
	}

/**
* @param message
* @param cause
*/
	public MessageException(String message, Throwable cause) {
		super (message, cause);
// TODO Auto-generated constructor stub
	/**
	 * @param message
	 */
	}
	public MessageException(String message) {
		super(message);
// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MessageException(Throwable cause) {
		super(cause);
// TODO Auto-generated constructor stub
		}
	}

