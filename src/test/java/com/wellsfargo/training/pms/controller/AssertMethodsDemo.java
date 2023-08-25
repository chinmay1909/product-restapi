package com.wellsfargo.training.pms.controller;

//static imports
import static org.junit.jupiter.api.Assertions.*;
import static java.lang.System.*;
//Remove System

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Assert Methods Demo")
class AssertMethodsDemo {

	private ArrayList<String> list;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		out.println("Using @BeforeAll, exceuted once before All Tests");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		out.println("Using @AfterAll, exceuted once after All Tests");
	}

	@BeforeEach
	void setUp() throws Exception {
		out.println("Using @BeforeEach, exceuted once before each Tests");
		list=new ArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		out.println("Using @AfterEach, exceuted once before each Tests");
		list.clear();
	}

	@Test
	void test() {
		list.add("Junit");
//		list.add("Mockito");
		assertFalse(list.isEmpty());
		assertEquals(1,list.size());
		out.println("In Test Method");
	}
	
	@Test
	void test1() {
		list.add("Junit");
		assertFalse(list.isEmpty());
		assertEquals(1,list.size());
		out.println("In Test Method");
	}
	@Test
	void testAssertEqual() {
		 assertEquals("ABC", "ABC");
		 assertEquals(20, 20, "optional assertion message");
		 assertEquals(2 + 2, 4);
	}

	@Test
	void testAssertFalse() {
		 assertFalse("FirstName".length() == 10);
		 assertFalse(10 > 20, "assertion message");
	}
	
	@Test
	void testAssertNull() {
	     String str1 = null;
		 String str2 = "abc";
		 assertNull(str1);
		 assertNotNull(str2);	
	}

	@Test
	void testAssertAll() {
		 String str1 = "abc";
		 String str2 = "pqr";
		 String str3 = "xyz";
		 
		//Group many assertions and every assertion is executed even 
		 //if one or more of them fails
		 assertAll("numbers",
				 () -> assertEquals(str1, "abc"),
				 () -> assertEquals(str2, "pqr"),
				 () -> assertEquals(str3, "xyz")
				 );

		}
	
	@Test
	void testAssertTrue() {
		String fname="King";
		 assertTrue(fname.startsWith("K"));
		/* assertTrue(10 , {
		      throw new IllegalArgumentException("Illegal Argument Exception occured");
		 });
		 
		 assertEquals("Illegal Argument Exception occured", .getMessage());*/
	}


}
