package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

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
                itemsCsv.add(lineOfInput);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(String item: itemsCsv){
            String[] items = item.split("\\|");
            if (purchasesHappened.get(items[0]) == null){
                purchasesHappened.put(items[0], 5);
            }
            if (purchasesHappened.get(items[0]) == 0){
                System.out.println(items[0] + " " + items[1] + " price: " + items[2]+ " SOLD OUT" );
            }else {
                System.out.println(items[0] + " " + items[1] + " price: " + items[2]+" Available: "+ purchasesHappened.get(items[0] ));
            }
            double price = Double.parseDouble(items[2]);
            itemPrices.put(items[0], price);
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
                if (priceList.getKey().equals(choise)){
                    if (priceList.getValue()<= Money.getBalance()){
                        Money.setBalance(Money.getBalance() - priceList.getValue());
                        if (purchasesHappened.get(priceList.getKey()) == 0){
                            System.out.println("ITEM OUT OF STOCK");
                            purchaces();
                        }
                        //TODO: you maid a purchase of <product name> not D4
                        System.out.println("you made a purchase of: "+ priceList.getKey());
                        System.out.println("It cost you: " + priceList.getValue());
                        System.out.println("balance letf: " + Money.getBalance());

                        purchasesCount = purchasesHappened.get(priceList.getKey()) - 1;
                        inventoryCount(priceList.getKey(), (Integer) purchasesCount);
/* TODO Dispensing also returns a message:
All chip items print "Crunch Crunch, Yum!"
All candy items print "Munch Munch, Yum!"
All drink items print "Glug Glug, Yum!"
All gum items print "Chew Chew, Yum!"
 */
                    }
                    else {
                        System.out.println("not enough money");
                        purchaces();
                    }

                }
                else {
                    System.out.println("Item does not exists");
                    purchaces();
                }

            }

        }
        if (userInteger == 3){
//TODO: printl change money is returned using nickels, dimes, and quarters (using the smallest amount of coins possible)
            System.out.println("Your change: " + Money.getBalance());
            Money.setBalance(0.0);
            VendingMachineCLI.run();

        }



    }

}
