/**
 * 
 */
package com.blablacar.test.mowerapp.configuration.core;

/**
 * Thrown when an error is encountered during a load operation.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class LoadConfigurationException extends Exception {

	private static final long serialVersionUID = -7978653805168291864L;

	/**
	 * Ctor.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public LoadConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Ctor.
	 * 
	 * @param message message
	 */
	public LoadConfigurationException(String message) {
		super(message);
	}

}
