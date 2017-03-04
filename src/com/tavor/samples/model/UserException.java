package com.tavor.samples.model;
/**
 * 
 * user Exception
 *
 */
public class UserException extends Exception {
	public UserException() {
		super();
	}
	/**
	 * constructor
	 * @param message
	 * @param throwable
	 */
	public UserException(String message, Throwable throwable) {
		super(message, throwable);
	}
	/**
	 * constructor
	 * @param message
	 */
	public UserException(String message) {
		super(message);
	}
}
