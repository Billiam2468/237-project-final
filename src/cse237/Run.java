package cse237;

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
	
	public static void main(String[] args) {
		
		Run run = new Run();
		run.startRun();
		
	}
	
	private void startRun() {
		
		this.makeMenus();
		Menu mainMenu = this.menus.get("main");
		mainMenu.displayMessage();
		int mainSelect = mainMenu.getInput();
		this.mainMenuProcess(mainSelect);
		
	}

	private void makeMenus() {
		
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
		USMenuMessage.add("1. Cases");
		USMenuMessage.add("2. Deaths");
		USMenuMessage.add("3. Vaccinations");
		USMenuMessage.add("4. Back");
		Menu USMenu = new Menu("US", 4, USMenuMessage);
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
			stateMenuMessage.set(0, "*    " + state + " Covid Statistics   *");
			Menu StateMenu = new Menu(state, 4, stateMenuMessage);
			menus.put(StateMenu.getName(), StateMenu);
		}
		
	}
	
	private void mainMenuProcess(int mainSelect) {
		
		if(mainSelect == 1) {
			
			Menu USMenu = menus.get("US");
			USMenu.displayMessage();
			int USSelect = USMenu.getInput();
			this.USMenuProcess(USSelect);
			
		}
		else if(mainSelect == 2) {
			
			System.out.println("Please enter the state you would like COVID data on:");
			String selectedState = keyBoardIn.nextLine();
			while(!stateCheck.isState(selectedState)) {
				System.out.println("Please enter a valid option:");
				selectedState = keyBoardIn.nextLine();
			}
			Menu stateMenu = menus.get(selectedState);
			stateMenu.displayMessage();
			int stateSelect = stateMenu.getInput();
			this.stateMenuProcess(stateMenu, stateSelect);
			
		}
		else {
			
			System.out.println("Thanks and see you next time!");
			
		}
		
	}
	
	private void USMenuProcess(int USSelect) {
		
		if(USSelect == 1) {
			
			System.out.println("Total US Covid Cases:");
			System.out.println("---Not Implemented Yet---");
			
		}
		else if(USSelect == 2) {
			
			System.out.println("Total US Covid Deaths:");
			System.out.println("---Not Implemented Yet---");
			
		}
		else if(USSelect == 3) {
			
			System.out.println("Total US Covid Vaccinations:");
			System.out.println("---Not Implemented Yet---");
			
		}
		else {
			
			System.out.println("Returning to Main Menu");
			System.out.println("---Not Implemented Yet---");
			
		}
		
	}
	
	private void stateMenuProcess(Menu stateMenu, int stateSelect) {
		
		if(stateSelect == 1) {
			
			System.out.println("Total " + stateMenu.getName() + " Covid Cases:");
			System.out.println("---Not Implemented Yet---");
			
		}
		else if(stateSelect == 2) {
			
			System.out.println("Total " + stateMenu.getName() + " Covid Deaths:");
			System.out.println("---Not Implemented Yet---");
			
		}
		else if(stateSelect == 3) {
			
			System.out.println("Total " + stateMenu.getName() + " Covid Vaccinations:");
			System.out.println("---Not Implemented Yet---");
			
		}
		else {
			
			System.out.println("Returning to Main Menu");
			System.out.println("---Not Implemented Yet---");
			
		}
		
	}
	
}
