/**
 * 
 */
package com.blablacar.test.mowerapp.engine.core;

/**
 * A {@code Movable} object can go at a specific {@link Location} inside a
 * {@link Shape} thanks to the {@code MoveEngine}.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public interface Movable {

	/**
	 * Move to a new location.
	 * 
	 * @param location the new location
	 */
	void moveTo(Location location);

	/**
	 * Get the current location.
	 * 
	 * @return current location
	 */
	Location getLocation();

}
