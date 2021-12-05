package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Day3 {

    //Part 1
    public static int findPower(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));

        String line;
        String gamma = "";
        String epsilon = "";

        /**
         * General Stuff:
         *
         * This is a string-processing approach that I'm not very fond of. The general gist is that
         * there's an array, values, that holds an integer for every position in the binary sequence.
         * For each line with a 1 at that position, the corresponding element in values increments by 1.
         * For each line with a 0 at that position, the corresponding element in values decreases by 1.
         *
         * There's some funny business with the actual declaration of values that makes this probably dummy inefficient, but oh well.
         */

        boolean firstGo = true;
        int[] values = new int[1];


        //fill in Values
        while (scan.hasNextLine()) {
            line = scan.nextLine();

            if (firstGo) {
                values = new int[line.length()];
                firstGo = false;
            }

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '1') values[i] += 1;
                else values[i] -= 1;
            }
        }
        //use values to determine gamma and epsilon

        for (int i = 0; i < values.length; i++) {
            if (values[i] >= 0) {
                gamma += "1";
                epsilon += "0";
            }
            else {
                gamma += "0";
                epsilon += "1";
            }
        }

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
    }

    //Part 2. Couldn't think of a fancy name for this one.

    public static char mostCommon(ArrayList<String> arr, int index, char zeroChar) {
        int count = 0;

        for (String s : arr) {
            if (s.charAt(index) == '1') count++; else count--;
        }

        if (count >= 0) return '1'; else return '0';
    }

    public static String findLastString(ArrayList<String> arr, boolean isO2) {
        int i = 1;
        char zeroChar = '0';

        if (isO2) zeroChar = '1';

        while (arr.size() > 1 && i < arr.get(0).length()) {
            char lookFor = mostCommon(arr, i, zeroChar);

            Iterator<String> itr = arr.iterator();

            while(itr.hasNext()) {
                String seq = itr.next();
                if (isO2 && seq.charAt(i) != lookFor) itr.remove();

                else if (!isO2 && seq.charAt(i) == lookFor) {
                    itr.remove();
                }
            }
            i++;
        }

        return arr.get(0);
    }

    public static int part2(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));

        ArrayList<String> sequences = new ArrayList<>();

        while (scan.hasNextLine()) sequences.add(scan.nextLine());

        //Split sequences
        char o2key = mostCommon(sequences, 0, '1');

        ArrayList<String> oxygenArr = new ArrayList<>();
        ArrayList<String> carbonArr = new ArrayList<>();

        for (String s: sequences) {
            if (s.charAt(0) == o2key) oxygenArr.add(s); else carbonArr.add(s);
        }

        //From here, find the o2 and co2 strings.
        String o2 = findLastString(oxygenArr, true);
        String co2 = findLastString(carbonArr, false);

        System.out.println(o2+" "+co2);

        return Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2);
    }




}
