package com.devops.bbm.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class DBUtilTest {
	
	@Test
	public void testMultiply() {
		assertEquals(12, new DBUtil().multiply(4,3));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMultiply1() {
		assertEquals(123, new DBUtil().multiply(41,3));
	}
	
	@Test
	public void testMultiply2() {
		assertEquals(123, new DBUtil().multiply(41,3));
	}

}