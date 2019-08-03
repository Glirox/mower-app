/**
 * 
 */
package com.blablacar.test.mowerapp.engine.impl;

import static com.blablacar.test.mowerapp.engine.core.Direction.EAST;
import static com.blablacar.test.mowerapp.engine.core.Direction.NORTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.SOUTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.WEST;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.blablacar.test.mowerapp.engine.core.Location;

/**
 *
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class ForwardMoveStrategyTest {

	private ForwardMoveStrategy strategy;

	@Before
	public void setUp() {
		strategy = new ForwardMoveStrategy();
	}

	@Test
	public void testMoveToNorth() {
		assertThat(strategy.move(Location.of(5, 5, NORTH))).isEqualTo(Location.of(5, 6, NORTH));
	}

	@Test
	public void testMoveToSouth() {
		assertThat(strategy.move(Location.of(5, 5, SOUTH))).isEqualTo(Location.of(5, 4, SOUTH));
	}

	@Test
	public void testMoveToEast() {
		assertThat(strategy.move(Location.of(5, 5, EAST))).isEqualTo(Location.of(6, 5, EAST));
	}

	@Test
	public void testMoveToWest() {
		assertThat(strategy.move(Location.of(5, 5, WEST))).isEqualTo(Location.of(4, 5, WEST));
	}

}
