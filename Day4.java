package com.company;

import java.io.*;
import java.util.*;

public class Day4 {

    public static double checkBoardWin(List<List<String>> board, String recentCall) {
        //return 0 if no win, otherwise return sum of winning row/col by recentCall

        //I'm using "+.0" as a marking because I anticipated having to score marked squares instead of unmarked ones for
        //Part 2. That's not the case, but I'm not going to change it because, you guessed it, it works.

        //mark the board, and record where the marked square is.
        int r = -1;
        int c = -1;

        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.get(row).size(); col++) {
                //System.out.println(board.get(row).size());
                String square = board.get(row).get(col);
                if (square.equals(recentCall)) {
                    board.get(row).set(col, square+=".0");
                    r = row;
                    c = col;
                }
            }
        }

       // if (recentCall.equals("38")) System.out.println(board);

        //Check the win.
        boolean rWin = false;
        boolean cWin = false;

        if (r > -1) {

            rWin = true;
            cWin = true;
            //Check Row
            for (String s : board.get(r)) {
                if (s.indexOf('.') == -1) rWin = false;
            }

            //check Column
            for (List<String> row : board) {
                if (row.get(c).indexOf('.') == -1) cWin= false;
            }
        }

        //Return sum of all unmarked numbers.
        int score = 0;

        if (rWin || cWin) {
            for (List<String> row : board) {
                for (String s : row) {
                    if (s.indexOf('.') == -1) {
                        score += Integer.parseInt(s);
                    }
                }
            }
        }

        return score;
    }

    public static double part1(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));

        //Generate sequence of numbers to call
        List<String> numberSeq = Arrays.asList(scan.nextLine().split(","));

        //Generate boards

        List<List<List<String>>> boards = new ArrayList<>();

        int count = -1;
        while (scan.hasNextLine()) {
            String line = scan.nextLine().replaceAll("  "," ");

            if (line.length() == 0) {
                boards.add(new ArrayList());
                count++;
            }
            else {
                if (line.charAt(0) == ' ') line = line.substring(1);
                boards.get(count).add( Arrays.asList(line.split(" ")));
            }
        }

        //Iterate through numberSeq and check wins for each board in boards.
        for (String s : numberSeq) {
            for (List<List<String>> board: boards) {
                double score = checkBoardWin(board, s);
                if (score > 0) {
                    System.out.println(s+" "+score);
                    return score * Integer.parseInt(s);
                }
            }
        }
        return 0;
    }

    public static double part2(String filename) throws FileNotFoundException {
        //This is the same as part one, just setting up the arraylists and whatnot.

        Scanner scan = new Scanner(new File(filename));

        //Generate sequence of numbers to call
        List<String> numberSeq = Arrays.asList(scan.nextLine().split(","));

        //Generate boards

        List<List<List<String>>> boards = new ArrayList<>();

        int count = -1;
        while (scan.hasNextLine()) {
            String line = scan.nextLine().replaceAll("  "," ");

            if (line.length() == 0) {
                boards.add(new ArrayList());
                count++;
            }
            else {
                if (line.charAt(0) == ' ') line = line.substring(1);
                boards.get(count).add( Arrays.asList(line.split(" ")));
            }
        }

        //Start original part two stuff.

        Iterator<List<List<String>>> itr = boards.iterator();

        for (String call: numberSeq) {
            while (itr.hasNext()) {
                List<List<String>> board = itr.next();
                double score = checkBoardWin(board, call);

                if (score > 0) {
                    if (boards.size() == 1) {
                        return score * Double.parseDouble(call);
                    } else {
                        itr.remove();
                    }

                }
            }
            //reset the iterator
            itr = boards.iterator();
        }

        return 0.0;
    }

}
