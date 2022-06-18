package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingActions {
    //    static HashMap<String, String[]> menuItems = new HashMap<>();
    static Map<String, Double> itemPrices = new HashMap<>();
    static Map<String, Integer> purchasesHappened = new HashMap<>();
    static Map<String, String> itemType = new HashMap<>();
    static Map<String, String> itemName = new HashMap<>();


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

//            TODO refactor the code with Map<String, String[]>
//            menuItems.put(items[0], items);
//            System.out.println((menuItems.get(items[0]))[3]);

            itemType.put(items[0], items[3]);
            itemName.put(items[0], items[1]);

            if (purchasesHappened.get(items[0]) == null){
                purchasesHappened.put(items[0], 5);
            }
            if (purchasesHappened.get(items[0]) == 0){
                System.out.println(String.format( "%s %-20s price: %s SOLD OUT", items[0],items[1],items[2]));
            }else {
                System.out.println(String.format( "%s %-20s price: %s Available: %s", items[0],items[1],items[2], purchasesHappened.get(items[0] )   ));

            }
            double price = Double.parseDouble(items[2]);
            itemPrices.put(items[0], price);
            itemType.put(items[0], items[3]);
        }

    }
   public static void inventoryCount(String key, Integer count){
        purchasesHappened.put(key, count);

       }


    public static Object purchaces(){
        int purchasesCount = 5;
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println();
        System.out.println("Current Money Provided: " + Money.getCurrentMoneyProvided());

        try{
            Scanner input = new Scanner(System.in);
            System.out.println("What you want to do? ");

            String action = input.nextLine();
            int userInteger = Integer.parseInt(action);

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
                        if (purchasesHappened.get(priceList.getKey()) == 0){
                            System.out.println("ITEM OUT OF STOCK");
                            purchaces();
                        }
                        System.out.println("######################");
                        //TODO: you maid a purchase of <product name> not D4
                        System.out.println("you made a purchase of: "+ itemName.get(priceList.getKey()));
                        System.out.println("It cost you: " + priceList.getValue());
                        System.out.println("balance left: " + Money.getBalance());



                        purchasesCount = purchasesHappened.get(priceList.getKey()) - 1;
                        inventoryCount(priceList.getKey(), purchasesCount);
                        System.out.println("######################");
                        if (itemType.get(priceList.getKey()).equals("Chip")){
                            System.out.println("Crunch Crunch, Yum!");
                        }
                        if (itemType.get(priceList.getKey()).equals("Candy")){
                            System.out.println("Munch Munch, Yum");
                        }
                        if (itemType.get(priceList.getKey()).equals("Drink")){
                            System.out.println("Glug Glug, Yum!");
                        }
                        if (itemType.get(priceList.getKey()).equals("Gum")){
                            System.out.println("Chew Chew, Yum!");
                        }
                        System.out.println("######################");
                        String passToLog = (itemName.get(priceList.getKey()) + " "+ priceList.getKey() +" $"
                                + (Money.getBalance() + priceList.getValue())
                                + " " + Money.getBalance());
                        LogWriter.log(passToLog);
                    }
                    else {
                        System.out.println("not enough money");
                        purchaces();
                    }

                }

            }

        }
        if (userInteger == 3){
           int quarters;
           int dimes;
           int nickles;

            double balanceInCents = Money.getBalance() * 100;
            double coins = balanceInCents;

            quarters = (int)(balanceInCents/25);
            coins %= 25;
            dimes = (int)(coins/10);
            coins %= 10;
            nickles = (int)(coins/5);


            System.out.println("Your change: " + Money.getBalance()  );
            System.out.println("Quarters:" + quarters +" Dimes:" + dimes + " Nickles:" + nickles);
            String passToLog = ("GIVE CHANGE: $"+ Money.getBalance() + " $0.00");
            LogWriter.log(passToLog);
            Money.setBalance(0.0);
            VendingMachineCLI.run();

        }


        } catch(NumberFormatException e){
            System.out.println("Please enter 1, 2, or 3.");

        }


        return null;
    }


}
