package cse237;

import java.util.HashSet;
import java.util.Set;

public class StatesList {

	private Set<String> states;
	
	public StatesList() {
		states = new HashSet<String>();
		states.add("AL");
		states.add("AK");
		states.add("AZ");
		states.add("AR");
		states.add("CA");
		states.add("CO");
		states.add("CT");
		states.add("DE");
		states.add("FL");
		states.add("GA");
		states.add("HI");
		states.add("ID");
		states.add("IL");
		states.add("IN");
		states.add("IA");
		states.add("KS");
		states.add("KY");
		states.add("LA");
		states.add("ME");
		states.add("MD");
		states.add("MA");
		states.add("MI");
		states.add("MN");
		states.add("MS");
		states.add("MO");
		states.add("MT");
		states.add("NE");
		states.add("NV");
		states.add("NH");
		states.add("NJ");
		states.add("NM");
		states.add("NY");
		states.add("NC");
		states.add("ND");
		states.add("OH"); 
		states.add("OK");
		states.add("OR");
		states.add("PA");
		states.add("RI");
		states.add("SC");
		states.add("SD");
		states.add("TN");
		states.add("TX");
		states.add("UT");
		states.add("VT");
		states.add("VA");
		states.add("WA");
		states.add("WV");
		states.add("WI");
		states.add("WY");

	}
	
	public boolean isState(String s) {
		if(this.states.contains(s)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Set<String> getStates(){
		return this.states;
	}
	
}
