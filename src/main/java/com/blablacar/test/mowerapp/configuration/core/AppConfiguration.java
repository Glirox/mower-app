/**
 * 
 */
package com.blablacar.test.mowerapp.configuration.core;

import java.util.ArrayList;
import java.util.List;

/**
 * App configuration.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public class AppConfiguration {

	private LawnConfiguration lawnConfiguration;
	private List<MowerConfiguration> mowerConfigurations = new ArrayList<>();

	/**
	 * Returns the lawn configuration.
	 * 
	 * @return lawn configuration
	 */
	public LawnConfiguration getLawnConfiguration() {
		return lawnConfiguration;
	}

	/**
	 * Sets the lawn configuration.
	 * 
	 * @param configuration the lawn configuration to set
	 */
	public void setLawnConfiguration(LawnConfiguration configuration) {
		this.lawnConfiguration = configuration;
	}

	/**
	 * Add a new mower configuration.
	 * 
	 * @param configuration a new mower configuration
	 */
	public void addMowerConfiguration(MowerConfiguration configuration) {
		mowerConfigurations.add(configuration);
	}

	/**
	 * Returns mower configurations.
	 * 
	 * @return mower configurations
	 */
	public List<MowerConfiguration> getMowerConfigurations() {
		return mowerConfigurations;
	}

}
