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
	public int getInput() {
		int selected = keyBoardIn.nextInt();
		//Need to include section to check for non number input. If input is letter program crashes
		while(selected < 1 || selected > this.getOptions()) {
			System.out.println("Please enter a valid option:");
			selected = keyBoardIn.nextInt();
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
