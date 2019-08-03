/**
 * 
 */
package com.blablacar.test.mowerapp.engine.impl;

import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.MoveStrategy;
import static com.blablacar.test.mowerapp.engine.core.Direction.EAST;
import static com.blablacar.test.mowerapp.engine.core.Direction.NORTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.SOUTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.WEST;

import com.blablacar.test.mowerapp.engine.core.Direction;

/**
 * Move forward strategy.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class ForwardMoveStrategy implements MoveStrategy {

	@Override
	public Location move(Location fromLocation) {
		Direction direction = fromLocation.getDirection();
		if (direction == NORTH) {
			return Location.of(fromLocation.getX(), fromLocation.getY() + 1, direction);
		} else if (direction == SOUTH) {
			return Location.of(fromLocation.getX(), fromLocation.getY() - 1, direction);
		} else if (direction == EAST) {
			return Location.of(fromLocation.getX() + 1, fromLocation.getY(), direction);
		} else if (direction == WEST) {
			return Location.of(fromLocation.getX() - 1, fromLocation.getY(), direction);
		} else {
			throw new IllegalArgumentException("Cannot move forward: unknown direction " + direction);
		}
	}

}
