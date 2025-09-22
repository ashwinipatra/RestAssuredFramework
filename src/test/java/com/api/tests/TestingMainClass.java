package com.api.tests;

import org.testng.TestNG;
import org.testng.annotations.Test;

class TestClass {
	@Test
	public void runTestFromProgramme1() {
		System.out.println("@TestMethod: Running @Test Method");
	}
	
	@Test
	public void runTestFromProgramme2() {
		System.out.println("@TestMethod: Running @Test Method");
	}
}

public class TestingMainClass {

	public void runTestUsingTestngClass() {
		TestNG testNG = new TestNG();
		testNG.setTestClasses(new Class[] { TestClass.class });
	}

	public static void main(String[] args) {
		TestingMainClass testingMainClass = new TestingMainClass();
		testingMainClass.runTestUsingTestngClass();
	}

}
