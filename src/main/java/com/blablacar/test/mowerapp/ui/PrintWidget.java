/**
 * 
 */
package com.blablacar.test.mowerapp.ui;

import static com.blablacar.test.mowerapp.engine.core.Direction.EAST;
import static com.blablacar.test.mowerapp.engine.core.Direction.NORTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.SOUTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.WEST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blablacar.test.mowerapp.app.AppListener;
import com.blablacar.test.mowerapp.engine.core.Direction;
import com.blablacar.test.mowerapp.engine.core.Location;

/**
 * Widget that prints data for the user.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class PrintWidget implements AppListener {

	private static final Map<Direction, String> DIRECTION_DISPLAY = initDirectionMapping();

	@Override
	public void onExecuted(List<Location> locations) {
		locations.stream().forEach(PrintWidget::displayLocation);
	}

	/**
	 * Display a location.
	 * 
	 * @param location
	 */
	private static void displayLocation(Location location) {
		StringBuilder sb = new StringBuilder();
		sb.append(location.getX()).append(" ")
			.append(location.getY()).append(" ")
			.append(DIRECTION_DISPLAY.get(location.getDirection()));
		System.out.println(sb);
	}

	/**
	 * Default direction mapping for user display.
	 * 
	 * @return mapping
	 */
	private static Map<Direction, String> initDirectionMapping() {
		Map<Direction, String> mapping = new HashMap<>();
		mapping.put(NORTH, "N");
		mapping.put(SOUTH, "S");
		mapping.put(EAST, "E");
		mapping.put(WEST, "W");
		return mapping;
	}

}
