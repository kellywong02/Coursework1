/**
 * 
 */
package com.SDDEVOPS;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Kelly Wong
 *
 */
class ThreadDataTest {
	ArrayList alThread;
	private thread thread1;
	private thread thread2;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		thread1 = new thread(1,"Laptop Servicing","I want to service my laptop","28 July 2021","user1");
		thread2 = new thread(2,"PC Crash","My pc crashed","28 July 2021","user2");
		alThread = new ArrayList();
		alThread.add(thread1);
		alThread.add(thread2);
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.SDDEVOPS.thread#getThread_name()}.
	 */
	@Test
	void testGetThread_name() {
		assertTrue(!alThread.isEmpty());
		assertEquals(alThread.size(),2);
	}

}
