/**
 * 
 */
package com.blablacar.test.mowerapp.engine.impl;

import java.util.HashMap;
import java.util.Map;

import static com.blablacar.test.mowerapp.engine.core.Direction.NORTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.WEST;
import static com.blablacar.test.mowerapp.engine.core.Direction.SOUTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.EAST;

import com.blablacar.test.mowerapp.engine.core.Direction;
import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.MoveStrategy;

/**
 * Rotate left strategy.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class RotateLeftMoveStrategy implements MoveStrategy {

	private static final Map<Direction, Direction> ROTATION = createRotation();

	@Override
	public Location move(Location fromLocation) {
		Direction newDirection = ROTATION.get(fromLocation.getDirection());
		if (newDirection == null) {
			throw new IllegalArgumentException("Cannot rotate left: unknown direction " + fromLocation.getDirection());
		}
		return Location.of(fromLocation.getX(), fromLocation.getY(), newDirection);
	}

	/**
	 * Rotate left algorithm.
	 * 
	 * @return mapping to rotate
	 */
	private static Map<Direction, Direction> createRotation() {
		Map<Direction, Direction> rotation = new HashMap<>();
		rotation.put(NORTH, WEST);
		rotation.put(WEST, SOUTH);
		rotation.put(SOUTH, EAST);
		rotation.put(EAST, NORTH);
		return rotation;
	}

}
