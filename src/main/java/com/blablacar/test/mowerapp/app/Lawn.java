/**
 * 
 */
package com.blablacar.test.mowerapp.app;

import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.Shape;

/**
 * A lawn.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class Lawn implements Shape {

	private int xSize;
	private int ySize;

	/**
	 * Ctor.
	 * 
	 * @param xSize x size
	 * @param ySize y size
	 */
	public Lawn(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}

	/**
	 * Returns the x size.
	 * 
	 * @return the xSize
	 */
	public int getXSize() {
		return xSize;
	}

	/**
	 * Return the y size.
	 * 
	 * @return the ySize
	 */
	public int getYSize() {
		return ySize;
	}

	@Override
	public boolean isInside(Location location) {
		int x = location.getX();
		int y = location.getY();
		return x >= 0 && x <= xSize && y >= 0 && y <= ySize;
	}

}
