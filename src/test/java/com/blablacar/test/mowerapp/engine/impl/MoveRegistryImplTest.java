/**
 * 
 */
package com.blablacar.test.mowerapp.engine.impl;

import static com.blablacar.test.mowerapp.engine.core.Move.FORWARD;
import static com.blablacar.test.mowerapp.engine.core.Move.ROTATE_LEFT;
import static com.blablacar.test.mowerapp.engine.core.Move.ROTATE_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class MoveRegistryImplTest {

	private MoveRegistryImpl registry;

	@Before
	public void setUp() {
		registry = new MoveRegistryImpl();
	}

	@Test
	public void testGetStrategies() {
		assertThat(registry.getStrategy(FORWARD)).isInstanceOf(ForwardMoveStrategy.class);
		assertThat(registry.getStrategy(ROTATE_LEFT)).isInstanceOf(RotateLeftMoveStrategy.class);
		assertThat(registry.getStrategy(ROTATE_RIGHT)).isInstanceOf(RotateRightMoveStrategy.class);
		assertThat(registry.getStrategy(null)).isNull();
	}

}
