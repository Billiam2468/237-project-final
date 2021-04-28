package cse237;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Run {
	
	private Map<String, Menu> menus;
	private Scanner keyBoardIn;
	private StatesList stateCheck;
	
	public Run() {
		this.menus = new HashMap<String, Menu>();
		this.keyBoardIn = new Scanner(System.in);
		this.stateCheck = new StatesList();
	}
	
	public static void main(String[] args) throws IOException {
		Run run = new Run();
		run.startRun();
	}
	
	private void startRun() throws IOException {
		
		this.makeMenus();
		Menu mainMenu = this.menus.get("main");
		mainMenu.displayMessage();
		int mainSelect = mainMenu.getInput();
		this.mainMenuProcess(mainSelect);
	}
	
	public Map<String, Menu> getMenus() {
		return this.menus;
	}

	public void makeMenus() {
		
		this.makeMainMenu();
		
		this.makeUSMenu();
		
		this.makeStateMenus();
		
	}
	
	private void makeMainMenu() {
		
		List<String> mainMenuMessage = new ArrayList<String>();
		mainMenuMessage.add(" * ---------------------------------- * ");
		mainMenuMessage.add("|                                      |");
		mainMenuMessage.add("|    Welcome to the COVID Dashboard    |");
		mainMenuMessage.add("|                                      |");
		mainMenuMessage.add(" * ---------------------------------- * ");
		mainMenuMessage.add("Please select an option from the following menu:");
		mainMenuMessage.add("1. US Covid Stats");
		mainMenuMessage.add("2. State-specific Covid Stats");
		mainMenuMessage.add("3. Exit");
		Menu mainMenu = new Menu("main",3, mainMenuMessage);
		menus.put(mainMenu.getName(), mainMenu);
		
	}
	
	private void makeUSMenu() {
		
		List<String> USMenuMessage = new ArrayList<String>();
		USMenuMessage.add("*    US Covid Statistics   *");
		USMenuMessage.add("Please select an option from the following menu of US Covid stats:");
		USMenuMessage.add("1. Cases by state");
		USMenuMessage.add("2. Deaths by state");
		USMenuMessage.add("3. Vaccinations by state");
		USMenuMessage.add("4. Conglomerate US Data");
		USMenuMessage.add("5. Back");
		Menu USMenu = new Menu("US", 5, USMenuMessage);
		menus.put(USMenu.getName(), USMenu);
		
	}
	
	private void makeStateMenus() {
		List<String> stateMenuMessage = new ArrayList<String>();
		stateMenuMessage.add("*    State Covid Statistics   *");
		stateMenuMessage.add("Please select an option from the following menu of State Covid stats:");
		stateMenuMessage.add("1. Cases");
		stateMenuMessage.add("2. Deaths");
		stateMenuMessage.add("3. Vaccinations");
		stateMenuMessage.add("4. Back");
		for(String state : stateCheck.getStates()) {
			System.out.println(state + " state is being added");
			stateMenuMessage.set(0, "*    " + state + " Covid Statistics   *");
			Menu StateMenu = new Menu(state, 4, stateMenuMessage);
			menus.put(state, StateMenu);
		}
		
	}
	
	private void mainMenuProcess(int mainSelect) throws IOException {
		
		if(mainSelect == 1) {
			
			Menu USMenu = menus.get("US");
			USMenu.displayMessage();
			int USSelect = USMenu.getInput();
			this.USMenuProcess(USSelect);
			
		}
		else if(mainSelect == 2) {
			
			System.out.println("Please enter the state initials you would like COVID data on (capitalized):");
			String selectedState = keyBoardIn.nextLine();
			while(!stateCheck.isState(selectedState)) {
				System.out.println("Please enter a valid option:");
				selectedState = keyBoardIn.nextLine();
			}
			System.out.println("state received was " + selectedState);
			Menu stateMenu = menus.get(selectedState);
			stateMenu.displayMessage();
			int stateSelect = stateMenu.getInput();
			this.stateMenuProcess(stateMenu, stateSelect);
			
		}
		else {
			
			System.out.println("Thanks and see you next time!");
			
		}
		
	}
	
	private void USMenuProcess(int USSelect) throws IOException {
		System.out.println("selected: " + USSelect);
		//Creating a hashmap for all of the states that each references an array that holds covid vax, cases, deaths (in that order) for each state
		Map<String, int[]> stateData = new HashMap<String, int[]>();
		for(String state : stateCheck.getStates()) {
			FileParser currentState = new FileParser("state", state);
			int[] stateArray = currentState.getData();
			stateData.put(state, stateArray);
		}
		
		if(USSelect == 1) {
			System.out.println("Total US Covid Cases By State:");
			//Display api data here in a neat table that displays state with the cases in a column next to it
			//Would probably look something like this:
			//----------------
			//|State | Cases |
			//----------------
			//|  AL  | 124124|
			//|--------------|
			//|  AK  | 125125|
			//|--------------|
			// ...
			//etc. etc. until you get through all the states. This is just an idea for formatting though so feel free to do whatever is easiest
			//The for loop below will enable you to iterate through all of the states by using the string as a key for the stateData HashMap made above
			//After accessing the hashmap with the key .get([key here no brackets]) you should get an array that you can easily access the states vax,
			//cases, and deaths at [0], [1], [2] respectively
			//The process for all of the ones below should be the same other than the conglomerate data
			for(String state : stateCheck.getStates()) {

			}
			System.out.println("---Not Implemented Yet---");
			
		}
		else if(USSelect == 2) {
			
			System.out.println("Total US Covid Deaths:");
			//Display api data here in a neat table that displays state with the deaths in a column next to it
			System.out.println("---Not Implemented Yet---");
			
		}
		
		else if(USSelect == 3) {
			
			System.out.println("Total US Covid Vaccinations:");
			//Display api data here in a neat table that displays state with the vaccinations in a column next to it
			System.out.println("---Not Implemented Yet---");
			
		}
		else if(USSelect == 4) {
			System.out.println("Conglomerate US Covid Statistics");
			//Display total cases, deaths, and vaccinations total by all the states combined.
			//Top row would include cases, deaths, and vaccinations, bottom row would include the total values
			//Would look something like this:
			// ---------------------------
			// |Cases|Deaths|Vaccinations|
			// ---------------------------
			// |124125|15512|   12412    |
			// ----------------------------
			//If you want, might be easier to put the cases, deaths, and vaccinations all in one column so that the length of the number
			//won't really mess up the formatting like how the ones above are. All up to you.
		}
		
		else {
			
			System.out.println("Returning to Main Menu");
			System.out.println("---Not Implemented Yet---");
			
		}
		
	}
	
	private void stateMenuProcess(Menu stateMenu, int stateSelect) {
		
		if(stateSelect == 1) {
			
			System.out.println("Total " + stateMenu.getName() + " Covid Cases:");
			//Display api data here. Simple text output in a neat table should suffice
			//e.g.
			//----------------------
			//|Cases| 1241241241241|
			//----------------------
			System.out.println("---Not Implemented Yet---");
			
		}
		else if(stateSelect == 2) {
			
			System.out.println("Total " + stateMenu.getName() + " Covid Deaths:");
			//Display api data here. Simple text output in a neat table should suffice. Pretty much identical to one above
			System.out.println("---Not Implemented Yet---");
			
		}
		else if(stateSelect == 3) {
			
			System.out.println("Total " + stateMenu.getName() + " Covid Vaccinations:");
			//Display api data here. Simple text output in a neat table should suffice. Pretty much identical to one two above
			System.out.println("---Not Implemented Yet---");
			
		}
		else {
			
			System.out.println("Returning to Main Menu");
			System.out.println("---Not Implemented Yet---");
			
		}
		
	}
	
}
