/**
 * 
 */
package com.blablacar.test.mowerapp.app;

import static com.blablacar.test.mowerapp.engine.core.Direction.EAST;
import static com.blablacar.test.mowerapp.engine.core.Direction.NORTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.newCapture;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.io.File;
import java.util.List;

import org.easymock.Capture;
import org.junit.Test;

import com.blablacar.test.mowerapp.configuration.core.LoadConfigurationException;
import com.blablacar.test.mowerapp.engine.core.Location;

/**
 *
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class ApplicationTest {

	private static final File CONF_FOLDER = new File("src/test/resources/app");
	
	@Test
	public void testRun_DefaultUseCase() throws LoadConfigurationException {
		// Expected
		AppListener appListener = createMock(AppListener.class);
		Capture<List<Location>> locationsCapture = newCapture();
		appListener.onExecuted(capture(locationsCapture));
		replay(appListener);
		
		// Test
		Application app = new Application(new File(CONF_FOLDER, "default_usecase"));
		app.addListener(appListener);
		app.run();
		
		// Verify
		verify(appListener);
		List<Location> locations = locationsCapture.getValue();
		assertThat(locations).containsExactly(Location.of(1, 3, NORTH), Location.of(5, 1, EAST));
	}
	
}
