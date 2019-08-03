/**
 * 
 */
package com.blablacar.test.mowerapp.engine.core;

/**
 * A shape where you can move {@link Movable} elements thanks to the
 * {@link MoveEngine}.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public interface Shape {

	/**
	 * Indicates if a location is inside the shape.
	 * 
	 * @param location a location
	 * @return {@code true} if inside the shape, {@code false} otherwise
	 */
	boolean isInside(Location location);

}
