/**
 * 
 */
package com.blablacar.test.mowerapp.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.blablacar.test.mowerapp.app.Lawn;
import com.blablacar.test.mowerapp.engine.core.Location;

/**
 *
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class LawnTest {

	@Test
	public void testIsInside() {
		Lawn lawn = new Lawn(5, 6);
		assertThat(lawn.isInside(Location.of(5, 6))).isTrue();
		assertThat(lawn.isInside(Location.of(0, 0))).isTrue();
		assertThat(lawn.isInside(Location.of(2, 2))).isTrue();

		assertThat(lawn.isInside(Location.of(-1, 2))).isFalse();
		assertThat(lawn.isInside(Location.of(2, -1))).isFalse();

		assertThat(lawn.isInside(Location.of(6, 2))).isFalse();
		assertThat(lawn.isInside(Location.of(2, 7))).isFalse();
		assertThat(lawn.isInside(Location.of(6, 7))).isFalse();
	}

}
