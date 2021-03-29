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

}
