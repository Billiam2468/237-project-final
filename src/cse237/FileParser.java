package cse237;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

//Importing ability to use JSON files in Java
//import org.json.JSONException;
//import org.json.JSONObject;

//Google's Json Parser
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

//Test comment

public class FileParser {
	
	private String dataType; //Is either "state" or "us", depending on whether you are grabbing singular state or US data
	private JsonArray jsonFile; //The JsonArray that is returned by the API
	
	public FileParser(String dataType, String state) throws IOException {
		//Set class variable to the type of data we are getting
		this.dataType = dataType;
		
		//Grabs correct API link depending on state or US
		String url = "";
		if(this.dataType.equals("state")) {
			url = "https://api.covidactnow.org/v2/state/" + state + ".json?apiKey=cf7ca43b742345019cb38f605a79d454";
		}
		else if (this.dataType.equals("us")) {
			url = "https://api.covidactnow.org/v2/states.json?apiKey=cf7ca43b742345019cb38f605a79d454";
		}
		else {
			url = "invalid";
		}
		
		//Converts API link to URL object
		URL dataURL = new URL(url);
		
		//JSON data parsing from https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
		
		//Puts reader on return value of getContent on url, returning JsonElement
		URLConnection urlRequest = dataURL.openConnection();
		urlRequest.connect();
		JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream)urlRequest.getContent()));
		
		JsonArray rootobj = new JsonArray();
		if(dataType.equals("us")) {
			//Convert JsonElement in JsonArray form to JsonArray and set equal to rootobj
			rootobj = root.getAsJsonArray();
		}
		else if(dataType.equals("state")) {
			//Add Json element to blank JsonArray rootobj
			rootobj.add(root);
		}
		
		//Set class instance variable JsonArray to our created JsonArray
		this.jsonFile = rootobj;
	}
	
	//Prints contents of JsonArray
	public void printData() {
		System.out.println(this.jsonFile.toString());
	}
	
	//Get specific data in array form
	public int[] getData() {
		int stateData[] = new int[3]; //Data stored as: [vaccinations administered, cases, deaths]
		if(this.dataType.equals("state")) {
			JsonObject stateJson = this.jsonFile.get(0).getAsJsonObject();
			JsonObject actuals = stateJson.get("actuals").getAsJsonObject();
			int vaccinations = actuals.get("vaccinesAdministered").getAsInt();
			int cases = actuals.get("cases").getAsInt();
			int deaths = actuals.get("deaths").getAsInt();
			stateData[0] = vaccinations;
			stateData[1] = cases;
			stateData[2] = deaths;
			System.out.println(Arrays.toString(stateData));
		}
		return stateData;
	}
	
	public int getVaccinations() {
		if(this.dataType.equals("state")) {
			JsonObject stateJson = this.jsonFile.get(0).getAsJsonObject();
			JsonObject actuals = stateJson.get("actuals").getAsJsonObject();
			int a = actuals.get("vaccinesAdministered").getAsInt();
			return a;
		}
		return -1;
	}
	
	public int getDeaths() {
		if(this.dataType.equals("state")) {
			JsonObject stateJson = this.jsonFile.get(0).getAsJsonObject();
			JsonObject actuals = stateJson.get("actuals").getAsJsonObject();
			int a = actuals.get("deaths").getAsInt();
			return a;
		}
		return -1;
	}
	
	public int getPositiveTests() {
		if(this.dataType.equals("state")) {
			JsonObject stateJson = this.jsonFile.get(0).getAsJsonObject();
			JsonObject actuals = stateJson.get("actuals").getAsJsonObject();
			int a = actuals.get("positiveTests").getAsInt();
			return a;
		}
		return -1;
	}
	
	public int getNegativeTests() {
		if(this.dataType.equals("state")) {
			JsonObject stateJson = this.jsonFile.get(0).getAsJsonObject();
			JsonObject actuals = stateJson.get("actuals").getAsJsonObject();
			int a = actuals.get("negativeTests").getAsInt();
			return a;
		}
		return -1;
	}
	
	public int getContactTracers() {
		if(this.dataType.equals("state")) {
			JsonObject stateJson = this.jsonFile.get(0).getAsJsonObject();
			JsonObject actuals = stateJson.get("actuals").getAsJsonObject();
			int a = actuals.get("contactTracers").getAsInt();
			return a;
		}
		return -1;
	}
	
	public int getHospitalBedsCapacity() {
		if(this.dataType.equals("state")) {
			JsonObject stateJson = this.jsonFile.get(0).getAsJsonObject();
			JsonObject actuals = stateJson.get("actuals").getAsJsonObject();
			JsonObject hospitalBeds = actuals.get("hospitalBeds").getAsJsonObject();
			int a = hospitalBeds.get("capacity").getAsInt();
			return a;
		}
		return -1;
	}
	
	public int getCurrentUsageTotal() {
		if(this.dataType.equals("state")) {
			JsonObject stateJson = this.jsonFile.get(0).getAsJsonObject();
			JsonObject actuals = stateJson.get("actuals").getAsJsonObject();
			JsonObject hospitalBeds = actuals.get("hospitalBeds").getAsJsonObject();
			int a = hospitalBeds.get("currentUsageTotal").getAsInt();
			return a;
		}
		return -1;
	}
	
	public static void main(String args[]) throws IOException {
		FileParser dataFile = new FileParser("state", "WA");
		System.out.println("Output when printing all data is:");
		dataFile.printData();
		System.out.println("Output when just printing specific data:");
		dataFile.getData();
	}
	
	
}
