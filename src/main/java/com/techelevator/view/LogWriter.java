package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class LogWriter {

    public static void log(String message) {
        String os = System.getProperty("os.name").toLowerCase();
        String filepath = "";
        if (os.equals("mac os x")) {
            filepath = "/Users/dzmitry/merit_repos/capstone1/capstone-1/Log.txt";
        }
        if (os.equals("windows 11")) {
            filepath = "C:\\Users\\Admin\\OneDrive\\Desktop\\Merit\\Pair Programming\\Capstone 1\\capstone-1\\capstone-1\\Log.txt";
        }
            try (PrintWriter dataOutputAppending = new PrintWriter(new FileOutputStream(filepath, true))) {
                Date date = new Date();

                dataOutputAppending.println(date.toString() + " " + message);
            } catch (FileNotFoundException e) {
                System.err.println("Cannot open file for writing");
                System.exit(1);
            }

        }

    }


