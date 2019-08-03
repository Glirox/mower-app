/**
 * 
 */
package com.blablacar.test.mowerapp.engine.impl;

import java.util.ArrayList;
import java.util.List;

import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.Movable;
import com.blablacar.test.mowerapp.engine.core.Move;
import com.blablacar.test.mowerapp.engine.core.MoveEngine;
import com.blablacar.test.mowerapp.engine.core.MoveStrategy;
import com.blablacar.test.mowerapp.engine.core.MoveRegistry;
import com.blablacar.test.mowerapp.engine.core.Shape;

/**
 * {@code MoveEngine} implementation.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class MoveEngineImpl implements MoveEngine {

	private List<Movable> movables = new ArrayList<>();
	private Shape shape;
	private MoveRegistry registry;

	@Override
	public int addMovable(Movable movable, Location location) {
		if (!shape.isInside(location)) {
			// Outside the shape
			throw new IllegalArgumentException("You cannot add an element at " + location + ": it's outside the shape");
		} else if (isLocationBusy(location)) {
			// Busy location
			throw new IllegalArgumentException("You cannot add an element at " + location + ": the location is busy");
		} else {
			// Register the element in the engine
			movables.add(movable);
			int index = movables.indexOf(movable);
			movable.moveTo(location);
			return index;
		}
	}

	@Override
	public Location move(int id, Move move) {
		/* Check inputs */
		MoveStrategy strategy = registry.getStrategy(move);
		if (strategy == null) {
			throw new IllegalArgumentException("Failed to move element " + id + ": " + move + " is not supported");
		}
		Movable movable = movables.get(id);
		if (movable == null) {
			throw new IllegalArgumentException("Failed to move element " + id + ": you must add it to the engine before using it");
		}
		/* Compute the new location */
		Location oldLocation = movable.getLocation();
		Location newLocation = strategy.move(oldLocation);
		// Check if the new location is valid: inside the shape and if we move, check there is nobody else
		if (shape.isInside(newLocation) && (oldLocation.hasSamePosition(newLocation) || !isLocationBusy(newLocation))) {
			// Move
			movable.moveTo(newLocation);
			return newLocation;
		} else {
			// Do nothing
			return oldLocation;
		}
	}

	/**
	 * Indicates if a location is busy.
	 * 
	 * @param location the location to check
	 * @return {@code true} if busy, {@code false} otherwise
	 */
	private boolean isLocationBusy(Location location) {
		return movables.stream().anyMatch(e -> location.hasSamePosition(e.getLocation()));
	}

	@Override
	public List<Movable> getMovables() {
		return movables;
	}

	@Override
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	/**
	 * Sets the {@code MoveStrategyRegistry}.
	 * 
	 * @param registry the MoveStrategyRegistry to set
	 */
	public void setMoveStrategyRegistry(MoveRegistry registry) {
		this.registry = registry;
	}

}
