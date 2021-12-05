package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

    //Part 1
    public static int followDirections(String filename) throws FileNotFoundException {
        /**
         * Two vars: depth and hori (horizontal) both start at 0
         *
         * FORWARD - increase horizontal
         * DOWN - increase depth
         * UP - decreases depth
         *
         * Return depth *  hori
         */

        int depth = 0;
        int hori = 0;
        //Addition for Part 2
        int aim = 0;

        Scanner scan = new Scanner(new File(filename));

        while (scan.hasNextLine()) {

            //Note to self: This would break if change was double digits. It's irrelevant because I already solved it,
            //but if I wanted to fix it I would use .indexOf to find the space and work from there.
            String full = scan.nextLine();
            String command = full.substring(0, full.length() - 2).toLowerCase();
            int change = Integer.parseInt(full.substring(full.length() - 1, full.length()));

            //System.out.println(full+" "+command+" "+change);

            if (command.equals("up")) {
                aim -= change;
            }
            else if (command.equals("down")) {
                aim += change;
            }
            else {
                hori += change;
                depth += aim * change;
            }
        }

        return hori * depth;

    }
}
