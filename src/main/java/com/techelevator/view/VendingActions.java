package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingActions {
    static Map<String, Double> itemPrices = new HashMap<>();
    static Map<String, Integer> purchasesHappened = new HashMap<>();

    public static void displayMenu(){
        String os = System.getProperty("os.name").toLowerCase();
        String filepath = "";
        if (os.equals("mac os x")){
            filepath = "/Users/dzmitry/merit_repos/capstone1/capstone-1/vendingmachine.csv";
        }
        if (os.equals("windows 11")){
            filepath = "C:\\Users\\Admin\\OneDrive\\Desktop\\Merit\\Pair Programming\\Capstone 1\\capstone-1\\capstone-1\\vendingmachine.csv";
        }

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
            purchasesHappened.put(items[0], 5);
        }

    }
   public static void inventoryCount(String key, Integer count){
        purchasesHappened.put(key, count);

       }


    public static void purchaces(){
        int purchasesCount = 5;
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

            for (Map.Entry<String, Double> priceList : itemPrices.entrySet()){
//                TODO: use contaiskey to trobuleshoot if user enter non existing key
                if (priceList.getKey().equals(choise)){
                    if (priceList.getValue()<= Money.getBalance()){
                        Money.setBalance(Money.getBalance() - priceList.getValue());
                        System.out.println("you made a purchase of: "+ priceList.getKey());
                        System.out.println("It cost you: " + priceList.getValue());
                        System.out.println("balance letf: " + Money.getBalance());
                        purchasesCount--;
                        inventoryCount(priceList.getKey(), purchasesCount);
                        System.out.println();
                    }
                    else {
                        System.out.println("not enough money");
                        purchaces();
                    }
                }

            }

        }
        if (userInteger == 3){
            //

        }



    }

}
