package cse237;

import java.util.List;
import java.util.Scanner;

public class Menu {
	
	private String name;
	private List<String> message;
	private int options;
	private Scanner keyBoardIn;
	
	public Menu(String menuName, int numOptions, List<String> mainMenuMessage) {
		this.name = menuName;
		this.message = mainMenuMessage;
		this.options = numOptions;
		this.keyBoardIn = new Scanner(System.in);
	}
	
	public void displayMessage() {
		for(int i = 0; i < this.message.size(); ++i) {
			System.out.println(message.get(i));
		}
	}
	
	public String[] storeMessage() {
		String[] storedMessage = new String[this.message.size()];
		for(int i = 0; i < this.message.size(); ++i) {
			storedMessage[i] = message.get(i);
		}
		return storedMessage;
	}
	
	public int getInput() {
		//Checking string is int from: https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
		boolean isNotInt = true;
		String line = keyBoardIn.nextLine();
		int selected = 0;
		try{
		    selected = Integer.parseInt(line);
		    isNotInt = false;
		} catch (NumberFormatException e) {
			isNotInt = true;
		}

		//Need to include section to check for non number input. If input is letter program crashes
		
		while((selected < 1 || selected > this.getOptions()) && isNotInt) {
			System.out.println("Please enter a valid option:");
			line = keyBoardIn.nextLine();
			try{
			  selected = Integer.parseInt(line);
			  isNotInt = false;
			} catch (NumberFormatException e) {
			  isNotInt = true;
			}
		}
		return selected;
	}
	public String getName() {
		return this.name;
	}
	public int getOptions() {
		return this.options;
	}
	
}
