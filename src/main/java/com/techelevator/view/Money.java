package com.techelevator.view;

import java.util.Scanner;

public class Money {
    public int balance = 0;
    private static int currentMoneyProvided = 0;

    public int feedMoney(int amount){
        Scanner input = new Scanner(System.in);
        System.out.println("Please insert $1, $2, $5, or $10.");
        String bills = input.nextLine();
        int userInteger = Integer.parseInt(bills);
        System.out.println(userInteger);

        balance = balance + amount;
        currentMoneyProvided = amount;

        return balance;
    }

    public static int getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }
}

