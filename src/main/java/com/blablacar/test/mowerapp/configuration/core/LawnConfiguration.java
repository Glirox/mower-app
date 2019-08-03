/**
 * 
 */
package com.blablacar.test.mowerapp.configuration.core;

/**
 * A lawn configuration.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public class LawnConfiguration {

	private int xSize;
	private int ySize;

	/**
	 * Ctor.
	 * 
	 * @param xSize x size
	 * @param ySize y size
	 */
	public LawnConfiguration(int xSize, int ySize) {
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

}
