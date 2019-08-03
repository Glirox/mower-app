/**
 * 
 */
package com.blablacar.test.mowerapp.configuration.core;

/**
 * Configuration loader.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public interface ConfigurationLoader {

	/**
	 * Load a configuration.
	 * 
	 * @return a configuration
	 * @throws LoadConfigurationException when an error is encountered
	 */
	AppConfiguration load() throws LoadConfigurationException;

}
