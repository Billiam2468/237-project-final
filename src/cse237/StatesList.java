package cse237;

import java.util.HashSet;
import java.util.Set;

public class StatesList {

	private Set<String> states;
	
	public StatesList() {
		states = new HashSet<String>();
		states.add("alabama");
		states.add("alaska");
		states.add("arizona");
		states.add("arkansas");
		states.add("california");
		states.add("colorado");
		states.add("connecticut");
		states.add("delaware");
		states.add("florida");
		states.add("georgia");
		states.add("hawaii");
		states.add("idaho");
		states.add("illinois");
		states.add("indiana");
		states.add("iowa");
		states.add("kansas");
		states.add("kentucky");
		states.add("louisiana");
		states.add("maine");
		states.add("maryland");
		states.add("massachusetts");
		states.add("michigan");
		states.add("minnesota");
		states.add("mississippi");
		states.add("missouri");
		states.add("montana");
		states.add("nebraska");
		states.add("nevada");
		states.add("new hampshire");
		states.add("new jersey");
		states.add("new mexico");
		states.add("new york");
		states.add("north carolina");
		states.add("north dakota");
		states.add("ohio");
		states.add("oklahoma");
		states.add("oregon");
		states.add("pennsylvania");
		states.add("rhode island");
		states.add("south carolina");
		states.add("south dakota");
		states.add("tennessee");
		states.add("texas");
		states.add("utah");
		states.add("vermont");
		states.add("virginia");
		states.add("washington");
		states.add("west virginia");
		states.add("wisconsin");
		states.add("wyoming");
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
