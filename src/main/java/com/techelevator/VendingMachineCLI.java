package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.Money;
import com.techelevator.view.VendingActions;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				VendingActions.displayMenu();
			}
			if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				VendingActions.purchaces();

			}
			if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				// Exit program
				System.out.println("See you next time!");
				System.exit(0);
			}

		}

	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

	}
}
