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
			//System.out.println(state + " state is being added");
			stateMenuMessage.set(0, "*    " + state + " Covid Statistics   *");
			Menu StateMenu = new Menu(state, 4, stateMenuMessage);
			menus.put(state, StateMenu);
		}
		
	}
	
	private void mainMenuProcess(int mainSelect) throws IOException {
		
		while(mainSelect > 0 && mainSelect < 3) {
			
			if(mainSelect == 1) {
				
				Menu USMenu = menus.get("US");
				USMenu.displayMessage();
				int USSelect = USMenu.getInput();
				this.USMenuProcess(USSelect);
				
			}
			else{
				
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
			
			//rerun main menu
			Menu mainMenu = this.menus.get("main");
			mainMenu.displayMessage();
			mainSelect = mainMenu.getInput();
			
		}
		
		//user exits main menu
		System.out.println("Thanks and see you next time!");
		
	}
	
	private void USMenuProcess(int USSelect) throws IOException {
		
		while(USSelect > 0 && USSelect < 5) {
			
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
				System.out.println("-----------------");
				System.out.println("| State|  Cases |");
				System.out.println("-----------------");
				//Display api data here in a neat table that displays state with the cases in a column next to it
				//Would probably look something like this:
				//
				//
				//----------------
				//|  AL  | 124124|
				//|--------------|
				//|  AK  | 125125|
				//
				// ...
				//etc. etc. until you get through all the states. This is just an idea for formatting though so feel free to do whatever is easiest
				//The for loop below will enable you to iterate through all of the states by using the string as a key for the stateData HashMap made above
				//After accessing the hashmap with the key .get([key here no brackets]) you should get an array that you can easily access the states vax,
				//cases, and deaths at [0], [1], [2] respectively
				//The process for all of the ones below should be the same other than the conglomerate data
				for(String state : stateCheck.getStates()) {
					//check
					System.out.println("   " + state + "  |" +  stateData.get(state)[0] + " ");
					System.out.println("|---------------|");
				}
				
			}
			else if(USSelect == 2) {
				
				System.out.println("Total US Covid Deaths:");
				//Display api data here in a neat table that displays state with the deaths in a column next to it
				System.out.println("-----------------");
				System.out.println("| State| Deaths |");
				System.out.println("-----------------");
				for(String state : stateCheck.getStates()) {
					//check
					System.out.println("   " + state + "  |" +  stateData.get(state)[1] + " ");
					System.out.println("|---------------|");
				}
				
			}
			
			else if(USSelect == 3) {
				
				System.out.println("Total US Covid Vaccinations:");
				//Display api data here in a neat table that displays state with the vaccinations in a column next to it
				System.out.println("-----------------");
				System.out.println("| State|  Vaxn's |");
				System.out.println("-----------------");
				for(String state : stateCheck.getStates()) {
					//check
					System.out.println("   " + state + "  |" +  stateData.get(state)[2] + " ");
					System.out.println("|---------------|");
				}
				
			}
			else{
				System.out.println("Conglomerate US Covid Statistics\n");
				final Object[] header = new Object[]{"State", "Cases", "Deaths", "Vaxn's"};
				System.out.format("%15s%15s%15s%15s%n", header);
				System.out.println("          --------------------------------------------------");
				for(String state : stateCheck.getStates()) {
					//check
					final Object[] row = new Object[] {state, stateData.get(state)[0], stateData.get(state)[1], stateData.get(state)[2]};
					System.out.format("%15s%15s%15s%15s%n", row);
				}
			}
			
			//rerun usmenu
			Menu USMenu = menus.get("US");
			USMenu.displayMessage();
			USSelect = USMenu.getInput();
			
		}
		
		//user exits us menu, goes back to main menu
		System.out.println("Returning to Main Menu");
//		Menu mainMenu = this.menus.get("main");
//		mainMenu.displayMessage();
//		int mainSelect = mainMenu.getInput();
//		this.mainMenuProcess(mainSelect);
		
	}
	
	private void stateMenuProcess(Menu stateMenu, int stateSelect) throws IOException {
		
		while(stateSelect > 0 && stateSelect < 4) {
			
			System.out.println("selected: " + stateSelect);
			//grab the data for the specific state
			FileParser chosenState = new FileParser("state", stateMenu.getName());
			int[] stateArray = chosenState.getData();
			
			if(stateSelect == 1) {
				
				//Creating a hashmap for all of the states that each references an array that holds covid vax, cases, deaths (in that order) for each state
				System.out.println(" -------------------------------");
				System.out.print("| Total " + stateMenu.getName() + " Covid Cases: ");
				//Display api data here
				System.out.println(stateArray[0]+" |");
				System.out.println(" -------------------------------");
				
			}
			else if(stateSelect == 2) {
				
				System.out.println(" -------------------------------");
				System.out.print("| Total " + stateMenu.getName() + " Covid Deaths: ");
				//Display api data here
				System.out.println(stateArray[1]+" |");
				System.out.println(" -------------------------------");
				
			}
			else {
				
				System.out.println(" -----------------------------------");
				System.out.print("| Total " + stateMenu.getName() + " Covid Vaccinations: ");
				//Display api data here
				System.out.println(stateArray[2]+" |");
				System.out.println(" -----------------------------------");
				
			}
			
			//reruns state menu
			System.out.println("Please enter the state initials you would like COVID data on (capitalized):");
			String selectedState = keyBoardIn.nextLine();
			while(!stateCheck.isState(selectedState)) {
				System.out.println("Please enter a valid option:");
				selectedState = keyBoardIn.nextLine();
			}
			System.out.println("state received was " + selectedState);
			stateMenu = menus.get(selectedState);
			stateMenu.displayMessage();
			stateSelect = stateMenu.getInput();
			this.stateMenuProcess(stateMenu, stateSelect);
			
		}
		
		//user exits us menu, goes back to main menu
		System.out.println("Returning to Main Menu");
//		Menu mainMenu = this.menus.get("main");
//		mainMenu.displayMessage();
//		int mainSelect = mainMenu.getInput();
//		this.mainMenuProcess(mainSelect);
		
		
	}
}
