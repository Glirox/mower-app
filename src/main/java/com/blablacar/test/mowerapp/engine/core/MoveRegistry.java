/**
 * 
 */
package com.blablacar.test.mowerapp.engine.core;

/**
 * {@code MoveStrategy} registry.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public interface MoveRegistry {

	/**
	 * Get the strategy for a specific move.
	 * 
	 * @param move a move
	 * @return the strategy, {@code null} if the move is not supported
	 */
	MoveStrategy getStrategy(Move move);

}
