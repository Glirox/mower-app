/**
 * 
 */
package com.blablacar.test.mowerapp.configuration.impl;

import static com.blablacar.test.mowerapp.engine.core.Direction.EAST;
import static com.blablacar.test.mowerapp.engine.core.Direction.NORTH;
import static com.blablacar.test.mowerapp.engine.core.Move.FORWARD;
import static com.blablacar.test.mowerapp.engine.core.Move.ROTATE_LEFT;
import static com.blablacar.test.mowerapp.engine.core.Move.ROTATE_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.blablacar.test.mowerapp.configuration.core.AppConfiguration;
import com.blablacar.test.mowerapp.configuration.core.LawnConfiguration;
import com.blablacar.test.mowerapp.configuration.core.LoadConfigurationException;
import com.blablacar.test.mowerapp.configuration.core.MowerConfiguration;
import com.blablacar.test.mowerapp.engine.core.Location;

/**
 *
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class FileConfigurationLoaderTest {

	private static final File CONF_FOLDER = new File("src/test/resources/configurations");

	@Test
	public void testLoad() throws LoadConfigurationException {
		FileConfigurationLoader loader = new FileConfigurationLoader(new File(CONF_FOLDER, "conf_ok"));
		AppConfiguration appConf = loader.load();
		LawnConfiguration lwanConf = appConf.getLawnConfiguration();
		List<MowerConfiguration> mowerConfs = appConf.getMowerConfigurations();

		// Lawn
		assertThat(lwanConf).isNotNull();
		assertThat(lwanConf.getXSize()).isEqualTo(5);
		assertThat(lwanConf.getYSize()).isEqualTo(6);

		assertThat(mowerConfs).hasSize(2);

		// Mower 1
		MowerConfiguration mowerConf1 = mowerConfs.get(0);
		assertThat(mowerConf1.getDefaultLocation()).isEqualTo(Location.of(1, 2, NORTH));
		assertThat(mowerConf1.getMoves()).containsExactly(ROTATE_LEFT, FORWARD, ROTATE_RIGHT, FORWARD);

		// Mower 2
		MowerConfiguration mowerConf2 = mowerConfs.get(1);
		assertThat(mowerConf2.getDefaultLocation()).isEqualTo(Location.of(3, 3, EAST));
		assertThat(mowerConf2.getMoves()).containsExactly(FORWARD, ROTATE_RIGHT, ROTATE_LEFT);
	}
	
	@Test(expected = LoadConfigurationException.class)
	public void testLoad_FileNotFound() throws LoadConfigurationException {
		FileConfigurationLoader loader = new FileConfigurationLoader(new File(CONF_FOLDER, "conf_not_found"));
		loader.load();
	}
	
	@Test(expected = LoadConfigurationException.class)
	public void testLoad_IncorrectLawnSize() throws LoadConfigurationException {
		FileConfigurationLoader loader = new FileConfigurationLoader(new File(CONF_FOLDER, "conf_fail_lawn_size"));
		loader.load();
	}
	
	@Test(expected = LoadConfigurationException.class)
	public void testLoad_IncorrectMowerLocation() throws LoadConfigurationException {
		FileConfigurationLoader loader = new FileConfigurationLoader(new File(CONF_FOLDER, "conf_fail_mower_location"));
		loader.load();
	}
	
	@Test(expected = LoadConfigurationException.class)
	public void testLoad_IncorrectMowerMove() throws LoadConfigurationException {
		FileConfigurationLoader loader = new FileConfigurationLoader(new File(CONF_FOLDER, "conf_fail_mower_move"));
		loader.load();
	}

}
