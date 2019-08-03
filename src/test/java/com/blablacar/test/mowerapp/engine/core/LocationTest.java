/**
 * 
 */
package com.blablacar.test.mowerapp.engine.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import static com.blablacar.test.mowerapp.engine.core.Direction.EAST;
import static com.blablacar.test.mowerapp.engine.core.Direction.NORTH;

/**
 *
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class LocationTest {

	@Test
	public void testFactoryMethod() {
		Location location = Location.of(1, 2, EAST);
		assertThat(location.getX()).isEqualTo(1);
		assertThat(location.getY()).isEqualTo(2);
		assertThat(location.getDirection()).isEqualTo(EAST);
	}
	
	@Test
	public void testFactoryMethod_WithDefaultDirection() {
		Location location = Location.of(2, 3);
		assertThat(location.getX()).isEqualTo(2);
		assertThat(location.getY()).isEqualTo(3);
		assertThat(location.getDirection()).isEqualTo(NORTH);
	}
	
	@Test
	public void testHasSamePosition() {
		assertThat(Location.of(1, 2).hasSamePosition(Location.of(1, 2))).isTrue();
		assertThat(Location.of(1, 2, NORTH).hasSamePosition(Location.of(1, 2, EAST))).isTrue();
		assertThat(Location.of(1, 2).hasSamePosition(Location.of(1, 3))).isFalse();
	}
	
	@Test
	public void testEquals() {
		assertThat(Location.of(1, 2)).isEqualTo(Location.of(1, 2));
		assertThat(Location.of(1, 2, EAST)).isEqualTo(Location.of(1, 2, EAST));
		// x diff
		assertThat(Location.of(1, 2, EAST)).isNotEqualTo(Location.of(2, 2, EAST));
		// y diff
		assertThat(Location.of(1, 2, EAST)).isNotEqualTo(Location.of(1, 3, EAST));
		// direction diff
		assertThat(Location.of(1, 2, EAST)).isNotEqualTo(Location.of(1, 2, NORTH));
	}
	
}
