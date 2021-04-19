package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import cse237.FileParser;

class ParserTests {

	@Test
	//This tests US json collection and will throw errors when the program has an issue reading the contents of the website with
	//covid data or if the conversion of the data to json fails
	void testGrabJSONAndPrintContentsOnUS() {
		FileParser currentData = null;
		try {
			currentData = new FileParser("us", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error reading url");
		}
		//currentData.printData();
		currentData.getData();
	}
	
	@Test
	//This tests state json collection and will throw errors when the program has an issue reading the contents of the website with
	//covid data or if the conversion of the data to json fails
	void testGrabJSONAndPrintContentsOnState() {
		FileParser currentData = null;
		try {
			currentData = new FileParser("state", "WA");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error reading url");
		}
		//currentData.printData();
		currentData.getData();
	}
	
	@Test
	//This tests tests ths getData function used on a state json file and checks if each of the values returned are valid (greater than 0)
	void testGrabSpecificStateData() {
		FileParser currentData = null;
		try {
			currentData = new FileParser("state", "WA");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error reading url");
		}
		int[] specificData = currentData.getData();
		int vaccinations = specificData[0];
		int cases = specificData[1];
		int deaths = specificData[2];
		int numValidData = 0;
		if(vaccinations > 0) {
			numValidData++;
		}
		if(cases > 0) {
			numValidData++;
		}
		if(deaths > 0) {
			numValidData++;
		}
		System.out.println(numValidData + " data points were valid (greater than 0)");
		assertEquals(numValidData, 3);
	}
	
	@Test
	//This tests whether the number of vaccinations is accurate
	void testAccurateVaxNumbers() {
		FileParser currentData = null;
		try {
			currentData = new FileParser("state", "WA");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error reading url");
		}
		boolean validNum = false;
		int numVax = currentData.getVaccinations();
		//We know that the current number of vaccinated people is greater than 4.5 mil, so it should never be less
		if(numVax > 4500000) {
			validNum = true;
		}
		assertTrue(validNum);
	}
	
	@Test
	//This tests whether the number of deaths is accurate
	void testAccurateDeathNumbers() {
		FileParser currentData = null;
		try {
			currentData = new FileParser("state", "WA");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error reading url");
		}
		boolean validNum = false;
		int numDeaths = currentData.getDeaths();
		//We know that current the number of deaths is greater than 5000. It should never go below this
		if(numDeaths > 5000) {
			validNum = true;
		}
		assertTrue(validNum);
	}
	
	@Test
	//This test whether the number of tests is within a normal value
	void testValidTestNumbers() {
		FileParser currentData = null;
		try {
			currentData = new FileParser("state", "WA");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error reading url");
		}
		int posTests = currentData.getPositiveTests();
		int negTests = currentData.getNegativeTests();
		int pop = currentData.getPopulation();
		boolean validNum = false;
		//The total combined number of positive and negative tests should never exceed the population of the state
		if(pop > (posTests + negTests)) {
			validNum = true;
		}
		assertTrue(validNum);
	}
	
	@Test
	//This tests whether the number of given vaccinations makes sense
	void testValidVaxNumbers() {
		FileParser currentData = null;
		try {
			currentData = new FileParser("state", "WA");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error reading url");
		}
		int distributed = currentData.getVaxDistributed();
		int administered = currentData.getVaxAdministered();
		boolean validNum = false;
		//The total number of distributed vaccine should never be less than the amount administered
		if(distributed > administered) {
			validNum = true;
		}
		assertTrue(validNum);
	}

}