/**
 * 
 */
package com.blablacar.test.mowerapp.engine.core;

/**
 * A location is defined by:
 * <ul>
 * <li>x position</li>
 * <li>y position</li>
 * <li>{@code Direction}</li>
 * </ul>
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public class Location {

	private int x;
	private int y;
	private Direction direction;

	/**
	 * Ctor.
	 * 
	 * @param x x position
	 * @param y y position
	 * @param direction direction
	 */
	private Location(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	/**
	 * Returns the x position.
	 * 
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y position.
	 * 
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the direction.
	 * 
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		return "Location [x=" + x + ", y=" + y + ", direction=" + direction + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (direction != other.direction)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * Indicates if two location have the same position (x, y).
	 * 
	 * @param location other location
	 * @return {@code true} if the position are the same, {@code false} otherwise
	 */
	public boolean hasSamePosition(Location location) {
		return location != null && x == location.getX() && y == location.getY();
	}

	/**
	 * Factory method to build a location.
	 * 
	 * @param x x position
	 * @param y y position
	 * @param direction direction
	 * @return a Location
	 */
	public static Location of(int x, int y, Direction direction) {
		return new Location(x, y, direction);
	}

	/**
	 * Factory method to build a location.
	 * 
	 * @param x x position
	 * @param y y position
	 * @return a Location, default direction is {@code NORTH}
	 */
	public static Location of(int x, int y) {
		return new Location(x, y, Direction.NORTH);
	}

}
