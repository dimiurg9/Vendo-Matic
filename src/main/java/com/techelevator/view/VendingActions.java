package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingActions {
    static Map<String, Double> itemPrices = new HashMap<>();

    public static void displayMenu(){
//      String filepath = "C:\\Users\\Admin\\OneDrive\\Desktop\\Merit\\Pair Programming\\Capstone 1\\capstone-1\\vendingmachine.csv";
        String filepath = "/Users/dzmitry/merit_repos/capstone1/capstone-1/vendingmachine.csv";
        File dataFile = new File(filepath);
        List<String> itemsCsv = new ArrayList<>();
//        Map<String, Double> itemPrices = new HashMap<>();

        try {
            Scanner dataInput = new Scanner(dataFile);

            while(dataInput.hasNextLine()) {
                String lineOfInput = dataInput.nextLine();
            //    System.out.println(lineOfInput);
                itemsCsv.add(lineOfInput);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(String item: itemsCsv){
            String[] items = item.split("\\|");
            System.out.println(items[0] + " " + items[1] + " price: " + items[2]+" Available:" );
            double price = Double.parseDouble(items[2]);
            itemPrices.put(items[0], price);

        }


    }
    public static void purchaces(){
//        System.out.println("Your ballance: " + Money.balance);
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println("");
//        System.out.println("Current Money Provided: " + Money.getCurrentMoneyProvided());

        Scanner input = new Scanner(System.in);
        System.out.println("What you want to do? ");

        String action = input.nextLine();
        int userInteger = Integer.parseInt(action);
//         TODO: negative scenarios
//        System.out.println(userInteger);
        if (userInteger == 1){
            Money.feedMoney();
            purchaces();
        }
        if (userInteger == 2){
            System.out.println("Current balance: " + Money.getBalance());
            displayMenu();
            System.out.println("Please select your item code: ");
            String choise = input.nextLine();
            //after purchase
//            remove -1 items from inventory
//            display in items availalbe -1
//
            for (Map.Entry<String, Double> priceList : itemPrices.entrySet()){
                if (choise.equals(priceList.getKey())  ){
                    System.out.println(priceList.getKey());
                    if (priceList.getValue()<= Money.getBalance()){
//                        System.out.println(priceList.getKey());
                        Money.setBalance(Money.getBalance() - priceList.getValue());
                        System.out.println("balance in loop: " + Money.getBalance());
                    }
                    else {
                        System.out.println("not enough money");
                        displayMenu();
                    }
                }else {
                    System.out.println("wrong item code"+ priceList.getKey());

                    purchaces();
                }

            }

        }
        if (userInteger == 3){
            //
        }



    }

}
