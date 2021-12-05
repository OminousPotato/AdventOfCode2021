package com.company;
import java.util.*;
import java.io.*;

public class Day5 {

    public static int part1 (String filename, int max_x, int max_y) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));

        //set up a 2d array of zeroes that will represent the coordinate plane.
        int[][] plane = new int[max_y][max_x];

        //iterate through lines
        while (scan.hasNextLine()) {
            //process the line
            String[] line = scan.nextLine().split(" -> ");

            int x1 = Integer.parseInt(line[0].substring(0, line[0].indexOf(',')));
            int y1 = Integer.parseInt(line[0].substring(line[0].indexOf(',')+1));
            int x2 = Integer.parseInt(line[1].substring(0, line[1].indexOf(',')));
            int y2 = Integer.parseInt(line[1].substring(line[1].indexOf(',')+1));

            //if horizontal or vertical, "graph" them by incrementing the count at the cell

            if (x1 == x2) {
                for (int i = Math.min(y1, y2); i <= Math.max(y1,y2); i++) {
                    plane[i][x1]++;
                }
            }
            else if (y1 == y2) {
                for (int j = Math.min(x1, x2); j <= Math.max(x2, x1); j++) {
                    plane[y1][j]++;
                }
            }

            //Begin stuff for Part 2

            //Find Diagonals
            else {
                double slope = (y2 - y1) / (x2 - x1);

                //If slope is positive (appears downward right on my plane)
                if (slope == 1.0) {
                    int leftmostY = Math.min(y1, y2);
                    int lesserX = Math.min(x1, x2);

                    for (int i = 0; i <= Math.abs(x2-x1); i++) {
                        plane[leftmostY + i][lesserX + i]++;
                    }
                }
                //If slope is positive (appears downward right on my plane)
                else if (slope == -1.0) {
                    int leftmostY = Math.min(y1, y2);
                    int greaterX = Math.max(x1, x2);

                    for (int i = 0; i <= Math.abs(x2-x1); i++) {
                        plane[leftmostY + i][greaterX - i]++;
                    }
                }
            }
        }
        int count = 0;

        //count all cells > 1
        for (int r = 0; r < plane.length; r++) {
            for (int c = 0; c < plane[r].length; c++) {
                if (plane[r][c] > 1) count++;
            }
        }

        return count;
    }
}
