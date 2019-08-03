/**
 * 
 */
package com.blablacar.test.mowerapp.engine.core;

/**
 * A move strategy.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public interface MoveStrategy {

	/**
	 * Compute a new location.
	 * 
	 * @param fromLocation old location
	 * @return a new location
	 */
	Location move(Location fromLocation);

}
