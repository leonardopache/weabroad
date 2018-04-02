package com.pache.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.pache.utils.LoadPropertiesUtils;

public class LoadPropertiesUtilsTest {

	@Test
	public void testGetInstance() {
		try {
			assertNotNull(LoadPropertiesUtils.getInstance());
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testGetValue() {
		try {
		String key = LoadPropertiesUtils.EMAIL_USER;
		String valueReceived;
			valueReceived = LoadPropertiesUtils.getInstance().getValue(key);
			String valueExpected = "user";
			
			assertEquals(valueReceived, valueExpected);
		} catch (IOException e) {
			fail();
		}
	}

}
