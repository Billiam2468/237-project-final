package cse237;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
	private ArrayList<String> message;
	private int options;
	private Scanner keyBoardIn;
	
	public Menu(int numOptions, ArrayList<String> messages) {
		this.message = messages;
		this.options = numOptions;
		this.keyBoardIn = new Scanner(System.in);
	}
	
	private void displayMessage() {
		for(int i = 0; i < this.message.size(); ++i) {
			System.out.println(message.get(i));
		}
	}
	private int getInput() {
		int selected = keyBoardIn.nextInt();
		while(selected < 1 || selected > this.options) {
			System.out.println("Please enter a valid option:");
			selected = keyBoardIn.nextInt();
		}
		return selected;
	}
	
}
