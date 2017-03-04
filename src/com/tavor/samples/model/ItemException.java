package com.tavor.samples.model;
/**
 * 
 * Item exception
 *
 */
public class ItemException extends Exception {
	public ItemException() {
		super();
	}
	/**
	 * constructor
	 * @param message
	 * @param throwable
	 */
	public ItemException(String message, Throwable throwable) {
		super(message, throwable);
	}
	/**
	 * constructor
	 * @param message
	 */
	public ItemException(String message) {
		super(message);
	}
}
