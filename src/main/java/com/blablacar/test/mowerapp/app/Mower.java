/**
 * 
 */
package com.blablacar.test.mowerapp.app;

import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.Movable;

/**
 * A mower.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class Mower implements Movable {

	private Location location;

	@Override
	public void moveTo(Location location) {
		this.location = location;
	}

	@Override
	public Location getLocation() {
		return location;
	}

}
