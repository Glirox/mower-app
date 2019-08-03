/**
 * 
 */
package com.blablacar.test.mowerapp.app;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.blablacar.test.mowerapp.configuration.core.AppConfiguration;
import com.blablacar.test.mowerapp.configuration.core.LawnConfiguration;
import com.blablacar.test.mowerapp.configuration.core.LoadConfigurationException;
import com.blablacar.test.mowerapp.configuration.core.MowerConfiguration;
import com.blablacar.test.mowerapp.configuration.impl.FileConfigurationLoader;
import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.Movable;
import com.blablacar.test.mowerapp.engine.impl.MoveEngineImpl;
import com.blablacar.test.mowerapp.engine.impl.MoveRegistryImpl;

/**
 * Application that moves mowers on a lawn from a configuration file.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public class Application {

	private File configurationFile;
	private List<AppListener> listeners = new ArrayList<>();

	/**
	 * Ctor.
	 * 
	 * @param configuration configuration file
	 */
	public Application(File configuration) {
		this.configurationFile = configuration;
	}

	/**
	 * Run the application.
	 * 
	 * @throws LoadConfigurationException when an error is encountered
	 */
	public void run() throws LoadConfigurationException {
		/** Load app configuration */
		FileConfigurationLoader configurationLoader = new FileConfigurationLoader(configurationFile);
		AppConfiguration appConfiguration = configurationLoader.load();

		/** Build lawn */
		LawnConfiguration lawnConfiguration = appConfiguration.getLawnConfiguration();
		Lawn lawn = new Lawn(lawnConfiguration.getXSize(), lawnConfiguration.getYSize());

		/** Build move engine */
		MoveEngineImpl engine = new MoveEngineImpl();
		engine.setShape(lawn);
		engine.setMoveStrategyRegistry(new MoveRegistryImpl());

		/** Build and move mowers */
		List<MowerConfiguration> mowerConfigurations = appConfiguration.getMowerConfigurations();
		mowerConfigurations.stream().forEach(mowerConfiguration -> {
			// Register the mower, then move it
			int mowerId = engine.addMovable(new Mower(), mowerConfiguration.getDefaultLocation());
			mowerConfiguration.getMoves().stream().forEach(move -> {
				engine.move(mowerId, move);
			});
		});

		/** Notify listeners */
		List<Location> mowerLocations = engine.getMovables().stream().map(Movable::getLocation).collect(toList());
		listeners.forEach(e -> e.onExecuted(mowerLocations));
	}

	/**
	 * Add a listener.
	 * 
	 * @param listener a listener
	 */
	public void addListener(AppListener listener) {
		listeners.add(listener);
	}

}
