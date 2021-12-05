package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {

    //Part One
    public static int countIncreases(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        int count = 0;

        int prev = scan.nextInt();

        while (scan.hasNextInt()) {
            int curr = scan.nextInt();

            if (curr > prev) count++;

            prev = curr;
        }

        return count;

    }

    //Part 2

    public static ArrayList<Integer> arrFromFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        ArrayList<Integer> ints  = new ArrayList<Integer>();

        while (sc.hasNextInt()) {
            ints.add( sc.nextInt() );
        }

        return ints;
    }

    public static int countThrees(String filename) throws FileNotFoundException {
        File file = new File(filename);
        ArrayList<Integer> nums = arrFromFile(file);
        int count = 0;

        for (int i = 0; i < nums.size() - 3; i ++ ) {

            if ( nums.get(i).intValue() < nums.get(i + 3).intValue() ) {
                count++;
            }
        }

        return count;
    }
}
