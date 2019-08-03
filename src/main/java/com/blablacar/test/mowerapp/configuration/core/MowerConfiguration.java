/**
 * 
 */
package com.blablacar.test.mowerapp.configuration.core;

import java.util.ArrayList;
import java.util.List;

import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.Move;

/**
 * A mower configuration.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public class MowerConfiguration {

	private Location defaultLocation;
	private List<Move> moves = new ArrayList<>();

	/**
	 * Returns the default location.
	 * 
	 * @return the default location
	 */
	public Location getDefaultLocation() {
		return defaultLocation;
	}

	/**
	 * Sets the default location.
	 * 
	 * @param location the default location to set
	 */
	public void setDefaultLocation(Location location) {
		this.defaultLocation = location;
	}

	/**
	 * Add a new move.
	 * 
	 * @param move a move
	 */
	public void addMove(Move move) {
		moves.add(move);
	}

	/**
	 * Returns all moves.
	 * 
	 * @return all moves
	 */
	public List<Move> getMoves() {
		return moves;
	};

}
