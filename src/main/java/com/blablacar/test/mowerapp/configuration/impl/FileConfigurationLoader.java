/**
 * 
 */
package com.blablacar.test.mowerapp.configuration.impl;

import static com.blablacar.test.mowerapp.engine.core.Direction.EAST;
import static com.blablacar.test.mowerapp.engine.core.Direction.NORTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.SOUTH;
import static com.blablacar.test.mowerapp.engine.core.Direction.WEST;
import static com.blablacar.test.mowerapp.engine.core.Move.FORWARD;
import static com.blablacar.test.mowerapp.engine.core.Move.ROTATE_LEFT;
import static com.blablacar.test.mowerapp.engine.core.Move.ROTATE_RIGHT;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.blablacar.test.mowerapp.configuration.core.AppConfiguration;
import com.blablacar.test.mowerapp.configuration.core.ConfigurationLoader;
import com.blablacar.test.mowerapp.configuration.core.LawnConfiguration;
import com.blablacar.test.mowerapp.configuration.core.LoadConfigurationException;
import com.blablacar.test.mowerapp.configuration.core.MowerConfiguration;
import com.blablacar.test.mowerapp.engine.core.Direction;
import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.Move;

/**
 * Load a configuration form a file.
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class FileConfigurationLoader implements ConfigurationLoader {

	private static final Map<Character, Move> MOVE_MAPPING = initMoveMapping();
	private static final Map<String, Direction> DIRECTION_MAPPING = initDirectionMapping();
	private File file;

	/**
	 * Ctor.
	 * 
	 * @param file a file to load configuration from
	 */
	public FileConfigurationLoader(File file) {
		this.file = file;
	}

	@Override
	public AppConfiguration load() throws LoadConfigurationException {
		try (BufferedReader br = Files.newBufferedReader(Paths.get(file.toURI()))) {
			AppConfiguration appConfiguration = new AppConfiguration();

			/* Build lawn configuration */
			// Check first line format
			String lawnConfAsStr = br.readLine();
			if (lawnConfAsStr == null) {
				throwsLoadException("first line cannot be empty - you must specify lawn size");
			}
			String[] lawnConfArgs = lawnConfAsStr.split(" ");
			if (lawnConfArgs.length < 2) {
				throwsLoadException("failed to set lawn size - wrong format");
			}
			// Check dimensions
			Integer lawnXSize = asInteger(lawnConfArgs[0]);
			if (lawnXSize == null) {
				throwsLoadException("failed to set lawn x size - " + lawnConfArgs[0] + " must be a numeric value");
			}
			Integer lawnYSize = asInteger(lawnConfArgs[1]);
			if (lawnYSize == null) {
				throwsLoadException("failed to set lawn y size - " + lawnConfArgs[1] + " must be a numeric value");
			}
			// Everything is valid
			appConfiguration.setLawnConfiguration(new LawnConfiguration(lawnXSize, lawnYSize));

			/* Build mower configurations */
			String mowerConfAsStr = null;
			while ((mowerConfAsStr = br.readLine()) != null) {
				// Check location line format
				String[] mowerConfArgs = mowerConfAsStr.split(" ");
				if (mowerConfArgs.length < 3) {
					throwsLoadException("failed to set mower location - " + mowerConfAsStr + " has a wrong format");
				}
				Integer xLocation = asInteger(mowerConfArgs[0]);
				if (xLocation == null) {
					throwsLoadException("failed to set mower x - " + mowerConfArgs[0] + " must be a numeric value");
				}
				Integer yLocation = asInteger(mowerConfArgs[1]);
				if (yLocation == null) {
					throwsLoadException("failed to set mower y - " + mowerConfArgs[1] + " must be a numeric value");
				}
				Direction direction = asDirection(mowerConfArgs[2]);
				if (direction == null) {
					throwsLoadException("failed to set mower direction - " + mowerConfArgs[2] + " is not a direction");
				}
				// Location is valid
				MowerConfiguration mowerConfiguration = new MowerConfiguration();
				mowerConfiguration.setDefaultLocation(Location.of(xLocation, yLocation, direction));
				// Check move line format
				mowerConfAsStr = br.readLine();
				if (mowerConfAsStr == null) {
					throwsLoadException("mower configuration is incomplete - you must specify mower moves");
				}
				// Adding moves
				for (int i = 0; i < mowerConfAsStr.length(); i++) {
					char moveAsChar = mowerConfAsStr.charAt(i);
					Move move = asMove(moveAsChar);
					if (move == null) {
						throwsLoadException("failed to set mower move - " + moveAsChar + " is not a move");
					}
					mowerConfiguration.addMove(move);
				}
				// Everything is valid
				appConfiguration.addMowerConfiguration(mowerConfiguration);
			}
			return appConfiguration;
		} catch (IOException e) {
			return throwsLoadException(e.getMessage(), e);
		}
	}

	/**
	 * Transform a {@code Character} to an {@code Integer}.
	 * 
	 * @param value
	 * @return an Integer, {@code null} if not matching found
	 */
	private Integer asInteger(String value) {
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Transform a {@code Character} to a {@code Move}.
	 * 
	 * @param value value
	 * @return a move, {@code null} if not matching found
	 */
	private Move asMove(Character value) {
		return MOVE_MAPPING.get(value);
	}

	/**
	 * Transform a {@code String} to a {@code Direction}.
	 * 
	 * @param value value
	 * @return a direction, {@code null} if not matching found
	 */
	private Direction asDirection(String value) {
		return DIRECTION_MAPPING.get(value);
	}

	/**
	 * Throw a {@code LoadConfigurationException}.
	 * 
	 * @param message message
	 * @param cause cause
	 * @throws LoadConfigurationException
	 */
	private <T> T throwsLoadException(String message, Throwable cause) throws LoadConfigurationException {
		throw new LoadConfigurationException("Failed to load configuration from " + file + ": " + message, cause);
	}

	/**
	 * Throw a {@code LoadConfigurationException}.
	 * 
	 * @param message message
	 * @throws LoadConfigurationException
	 */
	private <T> T throwsLoadException(String message) throws LoadConfigurationException {
		return throwsLoadException(message, null);
	}

	/**
	 * Move mapping.
	 * 
	 * @return mapping
	 */
	private static Map<Character, Move> initMoveMapping() {
		Map<Character, Move> mapping = new HashMap<>();
		mapping.put('F', FORWARD);
		mapping.put('L', ROTATE_LEFT);
		mapping.put('R', ROTATE_RIGHT);
		return mapping;
	}

	/**
	 * Direction mapping.
	 * 
	 * @return mapping
	 */
	private static Map<String, Direction> initDirectionMapping() {
		Map<String, Direction> mapping = new HashMap<>();
		mapping.put("N", NORTH);
		mapping.put("S", SOUTH);
		mapping.put("E", EAST);
		mapping.put("W", WEST);
		return mapping;
	}

}
