package com.techelevator.view;

import java.text.DecimalFormat;
import java.util.Scanner;


public class Money {
    public static double balance;
    private static int currentMoneyProvided;

    public static double getBalance() {
        return Double.parseDouble(new DecimalFormat("##.##").format(balance));
    }

    public static void setBalance(double balance) {
        Money.balance = balance;
    }

    public static void feedMoney() throws NumberFormatException {
        int userInteger = 0;
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Please insert $1, $2, $5, or $10.");

            String bills = input.nextLine();
            userInteger = Integer.parseInt(bills);
            if (userInteger == 1 || userInteger == 2 || userInteger == 5 || userInteger == 10) {
                balance = balance + userInteger;
                currentMoneyProvided = userInteger;
                System.out.println("Money added: $" + Money.getCurrentMoneyProvided());
                System.out.println("Current balance: $" + Money.getBalance());

            } else {
                System.out.println("Only bills accepted: $1, $2, $5, $10");

            }
            String passToLog = ("FEED MONEY: $" + Money.getCurrentMoneyProvided()+ " $" + Money.getBalance()  );
            LogWriter.log(passToLog);
        } catch (NumberFormatException e) {
            System.out.println("Enter number 1, 2, 5, or 10");
            VendingActions.purchaces();
        }
    }

    public static int getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }
}

