package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VendingActions {

    public static void displayMenu(){
        String filepath = "C:\\Users\\Admin\\OneDrive\\Desktop\\Merit\\Pair Programming\\Capstone 1\\capstone-1\\vendingmachine.csv";
        File dataFile = new File(filepath);
        List<String> itemsCsv = new ArrayList<>();
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
            System.out.println(items[1] + " , Available:" );
        }

    }
    public static void purchaces(){
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println("");
        System.out.println("Current Money Provided: " + Money.getCurrentMoneyProvided());

    }

}
