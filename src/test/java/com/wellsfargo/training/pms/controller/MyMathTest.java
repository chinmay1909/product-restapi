package com.wellsfargo.training.pms.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyMathTest {
	
	MyMath m1;

	@BeforeEach
	void setUp() throws Exception {
		m1= new MyMath();
	}

	@AfterEach
	void tearDown() throws Exception {
		m1=null;
	}

	@Test
	void testSum() {
		System.out.println("Test Sum with 3 Numbers");
		assertEquals(50,m1.sum(new int[] {25,20,5}));
	}
	
	@Test
	void testSum1() {
		System.out.println("Test Sum with 5 Numbers");
		assertEquals(101,m1.sum(new int[] {55,10,6,20,10}));
	}

}
