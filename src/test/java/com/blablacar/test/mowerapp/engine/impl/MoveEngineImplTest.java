/**
 * 
 */
package com.blablacar.test.mowerapp.engine.impl;

import static com.blablacar.test.mowerapp.engine.core.Move.FORWARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;

import com.blablacar.test.mowerapp.engine.core.Direction;
import com.blablacar.test.mowerapp.engine.core.Location;
import com.blablacar.test.mowerapp.engine.core.Movable;
import com.blablacar.test.mowerapp.engine.core.MoveRegistry;
import com.blablacar.test.mowerapp.engine.core.MoveStrategy;
import com.blablacar.test.mowerapp.engine.core.Shape;

/**
 *
 *
 * @author G. Rouillon
 * @since 3 août 2019
 */
public class MoveEngineImplTest {

	private MoveEngineImpl engine;
	private Shape shape;
	private MoveRegistry registry;
	
	
	@Before
	public void setUp() {
		shape = createMock(Shape.class);
		registry = createMock(MoveRegistry.class);
		engine = new MoveEngineImpl();
		engine.setShape(shape);
		engine.setMoveStrategyRegistry(registry);
	}
	
	@Test
	public void testAddMovable_InsideTheShape() {
		// Init
		Movable movable = createMock(Movable.class);
		Location location = Location.of(1, 5, Direction.NORTH);
		
		// Add element
		expect(shape.isInside(location)).andReturn(true);
		movable.moveTo(location);
		expectLastCall();
		replay(shape, movable);
		
		// Test
		engine.addMovable(movable, location);
		
		// Verify
		verify(shape, movable);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMovable_BusyLocation() {
		// Init 
		Movable movable1 = createMock(Movable.class);
		Location location1 = Location.of(1, 5, Direction.NORTH);
		Movable movable2 = createMock(Movable.class);
		Location location2 = Location.of(1, 5, Direction.SOUTH);
		
		// Add element
		expect(shape.isInside(anyObject(Location.class))).andReturn(true).times(2);
		movable1.moveTo(location1);
		expectLastCall();
		expect(movable1.getLocation()).andReturn(location1);
		replay(shape, movable1);
		
		// Test
		engine.addMovable(movable1, location1);
		engine.addMovable(movable2, location2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMovable_OutsideTheShape() {
		// Init
		Movable movable = createMock(Movable.class);
		Location location = Location.of(1, 5, Direction.NORTH);
		
		// Add element
		expect(shape.isInside(location)).andReturn(false);
		replay(shape);
		
		// Test
		engine.addMovable(movable, location);
	}
	
	@Test
	public void testAddMovable_CheckId() {
		// Init 
		Movable movable1 = createMock(Movable.class);
		Location location1 = Location.of(1, 5, Direction.NORTH);
		Movable movable2 = createMock(Movable.class);
		Location location2 = Location.of(1, 6, Direction.SOUTH);
		
		// Add element
		expect(shape.isInside(anyObject(Location.class))).andReturn(true).times(2);
		movable1.moveTo(location1);
		expectLastCall();
		expect(movable1.getLocation()).andReturn(location1);
		movable2.moveTo(location2);
		expectLastCall();
		replay(shape, movable1, movable2);
		
		// Test
		int id1 = engine.addMovable(movable1, location1);
		int id2 = engine.addMovable(movable2, location2);
		
		// Verify
		verify(shape, movable1, movable2);
		assertThat(id1).isNotEqualTo(id2);
	}
	
	@Test
	public void testGetMovables() {
		// Init 
		Movable movable1 = createMock(Movable.class);
		Location location1 = Location.of(1, 5, Direction.NORTH);
		Movable movable2 = createMock(Movable.class);
		Location location2 = Location.of(1, 6, Direction.SOUTH);
		
		// Add element
		expect(shape.isInside(anyObject(Location.class))).andReturn(true).times(2);
		movable1.moveTo(location1);
		expectLastCall();
		expect(movable1.getLocation()).andReturn(location1);
		movable2.moveTo(location2);
		expectLastCall();
		replay(shape, movable1, movable2);
		
		// Test
		engine.addMovable(movable1, location1);
		engine.addMovable(movable2, location2);
		
		// Verify
		verify(shape, movable1, movable2);
		assertThat(engine.getMovables()).containsExactly(movable1, movable2);
	}
	
	@Test
	public void testMove() {
		// Init
		Movable movable = createMock(Movable.class);
		MoveStrategy strategy = createMock(MoveStrategy.class);
		Location defaultLocation = Location.of(1, 5, Direction.NORTH);
		Location newLocation = Location.of(1, 6, Direction.NORTH);
		
		// Add element
		expect(shape.isInside(defaultLocation)).andReturn(true);
		movable.moveTo(defaultLocation);
		expectLastCall();
		
		// Move element
		expect(registry.getStrategy(FORWARD)).andReturn(strategy);
		expect(movable.getLocation()).andReturn(defaultLocation).anyTimes();
		expect(strategy.move(defaultLocation)).andReturn(newLocation);
		expect(shape.isInside(newLocation)).andReturn(true);
		movable.moveTo(newLocation);
		expectLastCall();
		
		// Replay mocks
		replay(shape, registry, movable, strategy);
		
		// Test
		int id = engine.addMovable(movable, defaultLocation);
		Location newLocationResult = engine.move(id, FORWARD);
		
		// Verify
		verify(shape, registry, movable, strategy);
		assertThat(newLocationResult).isEqualTo(newLocation);
	}
	
	@Test
	public void testMove_OutsideTheShape() {
		// Init
		Movable movable = createMock(Movable.class);
		MoveStrategy strategy = createMock(MoveStrategy.class);
		Location defaultLocation = Location.of(1, 5, Direction.NORTH);
		Location newLocation = Location.of(1, 6, Direction.NORTH);
		
		// Add element
		expect(shape.isInside(defaultLocation)).andReturn(true);
		movable.moveTo(defaultLocation);
		expectLastCall();
		
		// Move element
		expect(registry.getStrategy(FORWARD)).andReturn(strategy);
		expect(movable.getLocation()).andReturn(defaultLocation);
		expect(strategy.move(defaultLocation)).andReturn(newLocation);
		expect(shape.isInside(newLocation)).andReturn(false);
		
		// Replay mocks
		replay(shape, registry, movable, strategy);
		
		// Test
		int id = engine.addMovable(movable, defaultLocation);
		Location newLocationResult = engine.move(id, FORWARD);
		
		// Verify
		verify(shape, registry, movable, strategy);
		assertThat(newLocationResult).isEqualTo(defaultLocation);
	}
	
	@Test
	public void testMove_ToBusyLocation() {
		// Init
		Movable movable1 = createMock(Movable.class);
		Movable movable2 = createMock(Movable.class);
		Location location1 = Location.of(1, 5, Direction.NORTH);
		Location location2 = Location.of(1, 6, Direction.NORTH);
		MoveStrategy strategy = createMock(MoveStrategy.class);
		
		// Add elements
		expect(shape.isInside(location1)).andReturn(true);
		movable1.moveTo(location1);
		expectLastCall();
		expect(shape.isInside(location2)).andReturn(true);
		movable2.moveTo(location2);
		expectLastCall();
		
		// Move element 1
		expect(registry.getStrategy(FORWARD)).andReturn(strategy).anyTimes();
		expect(movable1.getLocation()).andReturn(location1).anyTimes();
		expect(movable2.getLocation()).andReturn(location2).anyTimes();
		expect(strategy.move(location1)).andReturn(location2);
		expect(shape.isInside(location2)).andReturn(true);
		
		// Replay mocks
		replay(shape, registry, movable1, movable2, strategy);
		
		// Test
		int id = engine.addMovable(movable1, location1);
		engine.addMovable(movable2, location2);
		Location newLocationResult = engine.move(id, FORWARD);
		
		// Verify
		verify(shape, registry, movable1, movable2, strategy);
		assertThat(newLocationResult).isEqualTo(location1);
	}
		
}
