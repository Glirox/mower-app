/**
 * 
 */
package com.blablacar.test.mowerapp.engine.core;

import java.util.List;

/**
 * Entry point to add {@link Movable} object to a {@link Shape} and move them.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public interface MoveEngine {

	/**
	 * Register a new element in the engine at a specific position.
	 * 
	 * @param movable the element
	 * @param location the location
	 * @return an id to move the element later
	 * @throws IllegalArgumentException if the location is outside the shape or busy
	 */
	int addMovable(Movable movable, Location location);

	/**
	 * Move an element.
	 * 
	 * @param id element id id
	 * @param move a move
	 * @return the new location after the move
	 */
	Location move(int id, Move move);

	/**
	 * Get all registered elements in the engine.
	 * 
	 * @return all elements
	 */
	List<Movable> getMovables();

	/**
	 * Sets the shape where elements can move.
	 * 
	 * @param shape a shape
	 */
	void setShape(Shape shape);

}
