/**
 * 
 */
package com.blablacar.test.mowerapp.app;

import java.util.List;

import com.blablacar.test.mowerapp.engine.core.Location;

/**
 * Application listener.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public interface AppListener {

	/**
	 * Called when application has been executed.
	 * 
	 * @param locations final {@code Mower} locations
	 */
	void onExecuted(List<Location> locations);

}
