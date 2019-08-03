/**
 * 
 */
package com.blablacar.test.mowerapp.engine.impl;

import java.util.HashMap;
import java.util.Map;

import com.blablacar.test.mowerapp.engine.core.Move;

import static com.blablacar.test.mowerapp.engine.core.Move.FORWARD;
import static com.blablacar.test.mowerapp.engine.core.Move.ROTATE_LEFT;
import static com.blablacar.test.mowerapp.engine.core.Move.ROTATE_RIGHT;
import com.blablacar.test.mowerapp.engine.core.MoveStrategy;
import com.blablacar.test.mowerapp.engine.core.MoveRegistry;

/**
 * {@code MoveStrategyRegistry} implementation.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class MoveRegistryImpl implements MoveRegistry {

	private static final Map<Move, MoveStrategy> DEFAULT_MAPPING = initMapping();

	@Override
	public MoveStrategy getStrategy(Move move) {
		return DEFAULT_MAPPING.get(move);
	}

	private static Map<Move, MoveStrategy> initMapping() {
		Map<Move, MoveStrategy> strategies = new HashMap<>();
		strategies.put(FORWARD, new ForwardMoveStrategy());
		strategies.put(ROTATE_LEFT, new RotateLeftMoveStrategy());
		strategies.put(ROTATE_RIGHT, new RotateRightMoveStrategy());
		return strategies;
	}

}
