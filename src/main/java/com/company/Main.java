package com.company;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.*;

import static java.lang.Character.UNASSIGNED;

public class Main {
    public static int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//    public static int[] result;
//    public static int[] result2;
    private static int difficulty;



    public static void main(String[] args) throws IOException {


            mainMenu();





    }

//        difficulty = 40;
//        Main.generateEmptyGrid();
////      Main.setFirstCell();
////        populateFirstRow();
//        populateFirstBlock();
//        populateMiddleBlock();
//        populateLastBlock();
//        populateGrid();
//        removeDigits();
//        printGrid();
////        getBlock(5,7);
//        System.out.println(block);
//
//        System.out.println(randomGenerator(9*9)-1);
//    }

    //declare 2d array
    public static int[][] grid;

    public static int block;

    public static int[][] resultGrid;

    public static ArrayList<String> savedGames = new ArrayList<String>();

    public static ArrayList<Integer> previousMove = new ArrayList<Integer>();


    private static void mainMenu() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("please select an Option \n1) New Game \n2) Previous Game \n3) Load Game");


        int option = scan.nextInt();

        switch (option) {
            case 1: {
                selectDifficulty();
                break;
            }
            case 2: {
                loadPreviousGame();

                break;
            }
            case 3: {
                displaySavedGames();
                loadGame();
                printGrid(grid);
                inGameMenu();
                break;
            }

        }
    }


    public static void selectDifficulty () throws IOException {
            Scanner scan = new Scanner(System.in);

            System.out.println("please select an Option \n1) Easy \n2) Medium \n3) Hard \n4) Impossible");


            int option = scan.nextInt();

            switch (option) {
                case 1: {
                    difficulty = 81 - randomGenerator(2);
                    createGame();
                    break;
                }
                case 2: {
                    difficulty = 38 - randomGenerator(5);
                    createGame();
                    break;
                }
                case 3: {
                    difficulty = 30 - randomGenerator(5);
                    createGame();
                    break;
                }
                case 4: {
                    difficulty = 17;
                    createGame();
                    break;
                }
            }
        }

        public static void updateCell () throws IOException {
            System.out.println("Please select a column from A-I");

            //  Scanner scan = new Scanner(System.in);
            int columnCell = System.in.read();
            int row = 0;
            int column = 0;

            switch (columnCell) {
                case 'A': {
                    column = 0;
                    break;
                }
                case 'B': {
                    column = 1;
                    break;
                }
                case 'C': {
                    column = 2;
                    break;
                }
                case 'D': {
                    column = 3;
                    break;
                }
                case 'E': {
                    column = 4;
                    break;
                }
                case 'F': {
                    column = 5;
                    break;
                }
                case 'G': {
                    column = 6;
                    break;
                }
                case 'H': {
                    column = 7;
                    break;
                }
                case 'I': {
                    column = 8;
                    break;
                }
            }

            System.out.println("Please select a row from 1-9");

            Scanner scan = new Scanner(System.in);
            int rowCell = scan.nextInt();

            switch (rowCell) {
                case 1: {
                    row = 0;
                    break;
                }
                case 2: {
                    row = 1;
                    break;
                }
                case 3: {
                    row = 2;
                    break;
                }
                case 4: {
                    row = 3;
                    break;
                }
                case 5: {
                    row = 4;
                    break;
                }
                case 6: {
                    row = 5;
                    break;
                }
                case 7: {
                    row = 6;
                    break;
                }
                case 8: {
                    row = 7;
                    break;
                }
                case 9: {
                    row = 8;
                    break;
                }
            }

            System.out.println("Please put a number from 1-9 to update the cell");

            int update = scan.nextInt();

            grid[row][column] = update;
            previousMove.add(column);
            previousMove.add(row);
            printGrid(grid);
        }

        public static void inGameMenu () throws IOException {
            Scanner scan = new Scanner(System.in);

            System.out.println("please select an Option \n1) insert a number \n2) check answer \n3) save game \n4) go back");


            int option = scan.nextInt();

            switch (option) {
                case 1: {
                    updateCell();
                    inGameMenu();
                    break;
                }
                case 2: {
                    if (equals(grid, resultGrid)) {
                        System.out.println("congratulations\n");
                        completeOption();
                     mainMenu();

                   }
                   else {
                       System.out.println("that is not correct");
                       printGrid(grid);
                       inGameMenu();
                   // printGrid();

                   }
                    break;
                }
                case 3: {
                    saveGame();
                    System.out.println("Game saved \nPlease select an option \n1) Main menu \n2) Keep playing");
                    int saveOption = scan.nextInt();
                    switch (saveOption) {
                        case 1: {
                            mainMenu();
                            break;
                        }
                        case 2: {
                            printGrid(grid);
                            inGameMenu();
                            break;
                        }
                    }

                    break;
                }
                case 4: {
                    mainMenu();
                    break;
                }
            }

        }

    private static void completeOption() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("please type \n1) to go back to the main menu");

        int option = scan.nextInt();

        switch (option) {
            case 1: {
                mainMenu();
                break;
            }
            case 2: {
                System.out.println("GoodBye");
                break;
            }

        }
    }

    public static void createGame () throws IOException {
            generateEmptyGrid();
            populateFirstBlock();
            populateMiddleBlock();
            populateLastBlock();
            populateGrid();
            copyGrid(grid);
            removeDigits();
            printGrid(grid);
            inGameMenu();
        }

    private static void loadPreviousGame() throws IOException {
            printGrid(grid);
            inGameMenu();
    }

    public static void saveGame() throws IOException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please name your saved game");

        String savedName = scan.next().trim();

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < grid.length; i++)//for each row
        {
            for(int j = 0; j < grid.length; j++)//for each column
            {
                builder.append(grid[i][j]+"");//append to the output string
                if(j < grid.length - 1)//if this is not the last row element
                    builder.append(",");//then add comma (if you don't like commas you can use spaces)
            }
            builder.append("\n");//append new line at the end of the row
        }

        File file = new File("/Users/crawford/Desktop/SudokuSaves/" + savedName +".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(builder.toString());//save the string representation of the board
        writer.close();

        savedGames.add(savedName);

    }

    public static void displaySavedGames() {
        for (int i= 0; i<savedGames.size(); i++ ) {
            System.out.println(savedGames.get(i));
        }

    }

    public static void loadGame() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please type the game you would like to load exactly as it is written above");

        String savedNameFile = scan.next().trim();
        generateEmptyGrid();
        BufferedReader reader = new BufferedReader(new FileReader("/Users/crawford/Desktop/SudokuSaves/" + savedNameFile +".txt"));
        String line = "";
        int row = 0;
        while((line = reader.readLine()) != null)
        {
            String[] cols = line.split(","); //note that if you have used space as separator you have to split on " "
            int col = 0;
            for(String  c : cols)
            {
                grid[row][col] = Integer.parseInt(c);
                col++;
            }
            row++;
        }
        reader.close();
    }



    //check if the saved array of the complete grid and the users filled in grid are equal
    public static boolean equals(final int[][] arr1, final int[][] arr2)
    {
        if (arr1 == null)
        {
            return (arr2 == null);
        }
        if (arr2 == null)
        {
            return false;
        }
        if (arr1.length != arr2.length)
        {
            return false;
        }
        for (int i = 0; i < arr1.length; i++)
        {
            if (!Arrays.equals(arr1[i], arr2[i]))
            {
                return false;
            }
        }
        return true;
    }

        // set all values in 2d array to 0
        public static int[][] generateEmptyGrid () {

            grid = new int[9][11];
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid.length; c++) {
                    grid[r][c] = 0;
                }
            }
            return grid;
        }

        public static int[][] setFirstCell () {
            Random random = new Random();
            int max = 9;
            int random1 = random.nextInt(max) + 1;
            grid[1][3] = random1;
            grid[2][3] = 1;
            return grid;
        }

        public static int getBlock ( int row, int column){
            int rowAct = row - 1;
            int columnAct = column - 1;
            block = (rowAct / 3) * 3 + (columnAct / 3);
            return block;
        }


        static int[] shuffleNumberArray ( int[] array, int a)
        {
            // Creating object for Random class
            Random rd = new Random();

            // Starting from the last element and swapping one by one.
            for (int i = a - 1; i > 0; i--) {

                // Pick a random index from 0 to i
                int j = rd.nextInt(i + 1);

                // Swap array[i] with the element at random index
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            // Printing the random generated array
            return array;
        }


        public static boolean populateGrid () {

            //loop through rows
            for (int row = 0; row < 9; row++) {
                //loop through columns
                for (int column = 0; column < 9; column++) {
                    //if cell is empty try numbers 1-9
                    if (grid[row][column] == UNASSIGNED) {
                        for (int number = 1; number <= 9; number++) {
                            //if number is safe to assign  number. If not leave it as unassigned
                            if (safeToAssign(row, column, number)) {
                                grid[row][column] = number;
                                if (populateGrid()) {
                                    return true;
                                } else {
                                    grid[row][column] = UNASSIGNED;
                                }
                            }
                        }
                        //if number cannot be assigned return false and  loop again
                        return false;
                    }
                }
                // printGrid();
            }
            return true;
        }



    public static int[][] copyGrid(int[][] passedGrid)
    {
        resultGrid = new int[passedGrid.length][passedGrid[0].length];
        // Copy all the values
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                resultGrid[row][column] = passedGrid[row][column];
                resultGrid[row][10] = row;
            }
        }
        return resultGrid;
    }

//    public static int[][] previousGame(int[][] passedGrid)
//    {
//        passedGrid = new int[grid.length][grid[0].length];
//        // Copy all the values
//        for (int row = 0; row < grid.length; row++) {
//            for (int column = 0; column < grid[0].length; column++) {
//                passedGrid[row][column] = grid[row][column];
//            }
//        }
//        return passedGrid;
//    }




        public static void printGrid (int[][] passedGrid)
        {
            System.out.println(" A  B  C     D  E  F     G  H  I");
            {
                for (int row = 0; row < 9; row++) {
                    if ((row + 1) % 3 == 1 && row > 1) {
                        //print block spacing horizontally
                        System.out.println("-------------------------------------");
                    }
                    for (int column = 0; column < 9; column++) {
                        if (column == 8) {
                            passedGrid[row][10] = row;
                            //print y-axis numbers
                            System.out.print("[" + passedGrid[row][column] + "]" + "  " + (passedGrid[row][10] + 1));
                        } else if ((column + 1) % 3 == 0 && column < 9) {
                            // prints between blocks spacing vertically
                            System.out.print("[" + passedGrid[row][column] + "] | ");
                        } else {
                            //print cell
                            System.out.print("[" + passedGrid[row][column] + "]");
                        }
                    }
                    System.out.println();
                }
            }
        }




//    public static void populateFirstRow() {
//        //populate first row
//        for (int j = 0; j <= 0; j++) {
//            int[] value = shuffleNumberArray(ints, ints.length);
//
//            for (int column = 0; column < 9; column++) {
//                grid[0][column] = value[0];
//                //System.out.println(Arrays.toString(ints));
//                value = ArrayUtils.remove(value, 0);
//            }
//        }
//    }


        public static void populateFirstBlock () {

            int[] value = shuffleNumberArray(ints, ints.length);

            value = shuffleNumberArray(value, value.length);
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    grid[row][column] = value[0];
                    value = ArrayUtils.remove(value, 0);
                }
            }
        }


        public static void populateMiddleBlock () {

            int[] value = shuffleNumberArray(ints, ints.length);

            value = shuffleNumberArray(value, value.length);
            for (int row = 3; row < 6; row++) {
                for (int column = 3; column < 6; column++) {
                    grid[row][column] = value[0];
                    value = ArrayUtils.remove(value, 0);
                }
            }
        }

        public static void populateLastBlock () {

            int[] value = shuffleNumberArray(ints, ints.length);

            value = shuffleNumberArray(value, value.length);
            for (int row = 6; row < 9; row++) {
                for (int column = 6; column < 9; column++) {
                    grid[row][column] = value[0];
                    value = ArrayUtils.remove(value, 0);
                }
            }
        }


//checks if the number exists in the row or not
        public static boolean checkRow ( int row, int number)
        {
            for (int i = 0; i < 9; i++) {
                if (grid[row][i] == number) {
                    return true;
                }
            }
            return false;
        }


//checks if the number exists in the column or not
        public static boolean checkColumn ( int column, int number)
        {
            for (int i = 0; i < 9; i++) {
                if (grid[i][column] == number) {
                    return true;
                }
            }
            return false;
        }

        //check if number exists in block
        public static boolean checkBlock ( int row, int column, int number)
        {
            //calculation gets the first cell in each block
            int r = row - row % 3;
            int c = column - column % 3;
            //adding 3 to the row or column of the first cell returns the last cell of the block allowing for iterating over all cells in the block to check if number exists
            for (int rows = r; rows < r + 3; rows++) {
                for (int columns = c; columns < c + 3; columns++) {
                    if (grid[rows][columns] == number) {
                        return true;
                    }
                }
            }
            return false;
        }

        // checks if row, column and block contain number or not
        public static boolean safeToAssign ( int row, int col, int number)
        {
            // if number does not exist in row, column or block return true
            return !(checkRow(row, number) || checkColumn(col, number) || checkBlock(row, col, number));
        }

        // Random generator
        public static int randomGenerator ( int num)
        {
            return (int) Math.floor((Math.random() * num + 1));
        }
        public static void removeDigits ()
        {
            int count = 81 - difficulty;
            while (count != 0) {
                int cellId = randomGenerator(9 * 9) - 1;


                int row = (cellId / 9);
                int column = cellId % 9;

                if (grid[row][column] != 0) {
                    count--;
                    grid[row][column] = 0;
                }
            }
        }


    }






