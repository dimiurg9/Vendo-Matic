package com.techelevator.view;

import java.util.Scanner;

public class Money {
//    public static double balance = 0;
//    private static int currentMoneyProvided = 0;
    public static double balance;
    private static int currentMoneyProvided;

    public static double getBalance() {
        return balance;
    }

    public static void setBalance(double balance) {
        Money.balance = balance;
    }

    public static void feedMoney(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please insert $1, $2, $5, or $10.");

        String bills = input.nextLine();
        int userInteger = Integer.parseInt(bills);
        System.out.println(userInteger);

        //TODO: to check negative scenarios
//        if (userInteger != 1 || userInteger != 2 || userInteger != 5 || userInteger != 10){
//            System.out.println("Only bills accepted: $1, $2, $5, $10");
//            String bills1 = input.nextLine();
//            int userInteger1 = Integer.parseInt(bills);
//            System.out.println(userInteger);
//        }
//        else {
            balance = balance + userInteger;
            currentMoneyProvided = userInteger;
        System.out.println("Money added: " + Money.getCurrentMoneyProvided());
        System.out.println("Current balance: " + Money.getBalance());
//        }

//        return balance;
    }

    public static int getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }
}

