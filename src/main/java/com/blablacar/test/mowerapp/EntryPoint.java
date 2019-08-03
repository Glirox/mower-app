/**
 * 
 */
package com.blablacar.test.mowerapp;

import java.io.File;

import com.blablacar.test.mowerapp.app.Application;
import com.blablacar.test.mowerapp.configuration.core.LoadConfigurationException;
import com.blablacar.test.mowerapp.ui.PrintWidget;

/**
 * EntryPoint.
 *
 * @author G. Rouillon
 * @since 2 août 2019
 */
public class EntryPoint {

	// Default input file
	private static final File DEFAULT_INPUTS_FILE = new File("inputs");

	/**
	 * Application entry point.
	 * 
	 * @param args
	 * @throws LoadConfigurationException
	 */
	public static void main(String[] args) throws LoadConfigurationException {
		File inputsFile = args.length >= 1 ? new File(args[0]) : DEFAULT_INPUTS_FILE;
		Application app = new Application(inputsFile);
		app.addListener(new PrintWidget());
		app.run();
	}

}
