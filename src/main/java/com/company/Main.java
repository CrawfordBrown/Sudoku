package com.company;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.*;

import static java.lang.Character.UNASSIGNED;

public class Main {
    public static int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static int[] result;
    public static int[] result2;
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

    public static int[][] savedGrid;

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
                System.out.println("load prev");
                break;
            }
            case 3: {
                System.out.println("load game");
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
                    difficulty = 81 - randomGenerator(5);
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
            printGrid();
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
//                    if (Arrays.equals(grid, savedGrid)) {
//                        System.out.println("congratulations");
//                     mainMenu();
                    printGridSavedGrid();
//                   }
//                   else {
//                       System.out.println("that is not correct");
//                       printGrid();
//                       inGameMenu();
//                   // printGrid();

//                   }
                    break;
                }
                case 3: {
                    System.out.println("coming soon");
                    printGridSavedGrid();
                    inGameMenu();
                    break;
                }
                case 4: {
                    mainMenu();
                    break;
                }
            }

        }


        public static void createGame () throws IOException {
            generateEmptyGrid();
            populateFirstBlock();
            populateMiddleBlock();
            populateLastBlock();
           // populateGrid();
            populateGrid();
            gridResult();
            printGridSavedGrid();
            removeDigits();
            printGrid();
            inGameMenu();
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
                            //if number is safe to assign assign number. If not leave it as unassigned
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

        public static void saveGrid()
        {
            int grid2;
            for (int row = 0; row < 9; row++) {
                if ((row + 1) % 3 == 1 && row > 1) {
                    for (int column = 0; column < 9; column++) {
                        grid2 = grid[row][column];
                    }
                }
            }
        }

        public static void gridResult()
        {

            savedGrid = grid.clone();
//            savedGrid = Arrays.copyOf(grid, grid.length);
        }


        public static void printGrid ()
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
                            grid[row][10] = row;
                            //print y axis numbers
                            System.out.print("[" + grid[row][column] + "]" + "  " + (grid[row][10] + 1));
                        } else if ((column + 1) % 3 == 0 && column < 9) {
                            // prints between blocks spacing vertically
                            System.out.print("[" + grid[row][column] + "] | ");
                        } else {
                            //print cell
                            System.out.print("[" + grid[row][column] + "]");
                        }
                    }
                    System.out.println();
                }
            }
        }




        public static void printGridSavedGrid ()
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
                            savedGrid[row][10] = row;
                            //print y axis numbers
                            System.out.print("[" + savedGrid[row][column] + "]" + "  " + (savedGrid[row][10] + 1));
                        } else if ((column + 1) % 3 == 0 && column < 9) {
                            // prints between blocks spacing vertically
                            System.out.print("[" + savedGrid[row][column] + "] | ");
                        } else {
                            //print cell
                            System.out.print("[" + savedGrid[row][column] + "]");
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

                // System.out.println(cellId);
                // extract coordinates i  and j
                int row = (cellId / 9);
                int column = cellId % 9;
//            if (column != 0)
//                column = column - 1;

                // System.out.println(i+" "+j);
                if (grid[row][column] != 0) {
                    count--;
                    grid[row][column] = 0;
                }
            }
        }


    }





//        printSudoku(sudokuBoard);
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter your cell: A-I & 1-9");
//        String pos = scan.next();
//        System.out.println("Enter number");
//        char number = scan.next().charAt(0);
//
//        switch (pos) {
//            case "A1":
//                sudokuBoard[1][2] =  number;
//                break;
//            case "A2":
//                sudokuBoard[2][2] =  number;
//                break;
//            case "A3":
//                sudokuBoard[3][2] =  number;
//                break;
//                case "A4":
//                sudokuBoard[5][2] =  number;
//                break;
//                case "A5":
//                sudokuBoard[6][2] =  number;
//                break;
//                case "A6":
//                sudokuBoard[7][2] =  number;
//                break;
//                case "A7":
//                sudokuBoard[9][2] =  number;
//                break;
//                case "A8":
//                sudokuBoard[10][2] =  number;
//                break;
//                case "A9":
//                sudokuBoard[11][2] =  number;
//                break;
//        }
//                printSudoku(sudokuBoard);
//
//    }
//    public static void printSudoku(char[][] sudokuBoard) {
//        for(char[] row: sudokuBoard) {
//            for (char c: row) {
//                System.out.print(c);
//            }
//            System.out.println();
//        }









//
//   for(int j =0; j<=0;j++) {
//        int[] value = rand(ints, ints.length);
//        //  System.out.println(Arrays.toString(value));
//
//        //first box of second row
//        int row=1;
//        //   System.out.println(Arrays.toString(ints));
//
//        for (int column = 1; column < 4; column++) {
//            if (grid[row][column] != value[0]) {
//                grid[2][column] = ints[0];
//            }
//            ints = ArrayUtils.remove(ints, 0);
//        }
//    }
//
//    // System.out.println(Arrays.toString(ints));
//
//}


//        if (!ArrayUtils.contains(grid[2], ints[i])) // If not



//        System.out.println(Arrays.toString(ints));
//
//        //populate second row
//        for(int j =1; j<=1;j++) {
//            System.out.println(Arrays.toString(ints));
//

//            ints = ints2;
//  System.out.println(Arrays.toString(ints));
//            for (int row=1; row<4; row++) {
//                    for (int column = 4; column < 7; column++) {
//                        if (grid[row][column] != ints[0] ) {
//                            for(int count = 1; count<=3; count++ ) {
//                                if(grid[2][count] != ints[0]) {
//                                grid[2][column] = ints[0];
//                                }
//                            }
//                        }                            ints = ArrayUtils.remove(ints, 0);
//
//                    }
//                }
//System.out.println(Arrays.toString(ints));



//    public static void populateGrid() {
//
//        //array of integers between 1 and 9
//        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//
//        //randomise the integers
//        int[] value = rand(ints, ints.length);
//        // System.out.println(Arrays.toString(value));
//
//
//        //populate all the first square with numbers 1 to 9
//        for (int column = 1; column <= 3; column++) {
//            for (int row = 1; row <= 3; row++) {
//                int rand = new Random().nextInt(ints.length);
//                int random = ints[rand];
//
//                grid[row][column] = random;
//                ints = ArrayUtils.remove(ints, rand);
//            }
//        }
//
//        //populate first row
//        for (int column = 4; column <= 9; column++) {
//            for (int i = 0; i < 9; i++) {
//                if (grid[1][1] != value[i] && grid[1][2] != value[i] && grid[1][3] != value[i] && grid[1][4] != value[i] && grid[1][5] != value[i] && grid[1][6] != value[i] && grid[1][7] != value[i] && grid[1][8] != value[i] && grid[1][9] != value[i]) {
//                    grid[1][column] = value[i];
//                }
//            }
//        }
//
//        for (int column = 4; column <= 9; column++) {
//            // if(grid[2][column]==0) {
//            for (int i = 0; i < 9; i++) {
////                for (int j = 1; j < 9; j++) {
//                    if (grid[1][4] != value[i] && grid[3][4] != value[i] && grid[2][1] != value[i] && grid[2][2] != value[i] && grid[2][3] != value[i] && grid[2][4] != value[i] && grid[2][5] != value[i] && grid[2][6] != value[i] && grid[2][7] != value[i] && grid[2][8] != value[i] && grid[2][9] != value[i]) {
////                        while (grid[2][j] != value[i]) {
//                            grid[2][column] = value[i];
//
////                        }
//                    }
//                }
//            }
//        }


//        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};

//            for (int column = 4; column <= 6; column++) {
//                for (int row = 2; row <= 3; row++) {
//
//                    int rand = new Random().nextInt(values.length);
//                    int random = values[rand];
//
//                    grid[row][column] = random;
//                    values = ArrayUtils.remove(values, rand);
//                }
//            }



//    public static void populateGrid() {
//
//        for (int i = 0; i < 3; i++) {
//            if(i == 0) {
//            int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//
//            for (int column = 1; column <= 3; column++) {
//                for (int row = 1; row <= 3; row++) {
//                    int rand = new Random().nextInt(values.length);
//                    int random = values[rand];
//
//                    grid[row][column] = random;
//                    values = ArrayUtils.remove(values, rand);
//                    for (int column1=4; column1<=6;column1++) {
//                        grid[row][column] =random;
//                      //  values = ArrayUtils.remove(values, rand);
//                    }
//                }
//            }
//            }
//            if(i == 1) {
//            int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//
//            for (int column = 4; column <= 6; column++) {
//                for (int row = 1; row <= 3; row++) {
//
//                    int rand = new Random().nextInt(values.length);
//                    int random = values[rand];
//
//                    grid[row][column] = random;
//                    values = ArrayUtils.remove(values, rand);
//                }
//            }
//            }
//            if(i == 2) {
//            int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//
//            for (int column = 7; column <= 9; column++) {
//                for (int row = 1; row <= 3; row++) {
//                    int rand = new Random().nextInt(values.length);
//                    int random = values[rand];
//
//                    grid[row][column] = random;
//                    values = ArrayUtils.remove(values, rand);
//                }
//            }
//            }
//
//        }
//    }
//
//

//
////public static void populateSecondBox() {
//
//        for (int j = 0; j <= 0; j++) {
//            int[] value = shuffleNumberArray(ints, ints.length);
//            for (int index = 0; index<6;index++) {
//                if (!ArrayUtils.contains(grid[2], value[index])) {
//                    System.out.println(value[index]);
//                    result = ArrayUtils.add(result, value[index]);
//                }
//                if (!ArrayUtils.contains(grid[3], value[index])) {
//                    System.out.println(value[index]);
//                    result2 = ArrayUtils.add(result2, value[index]);
//                }
//            }
//            System.out.println(Arrays.toString(result));
//            System.out.println(Arrays.toString(result2));
//        }
//    }




//        int[] value = shuffleNumberArray(ints, ints.length);
//        for (int column = 1; column <= 3; column++)
//        {
//            value = ArrayUtils.removeElement(value, grid[0][column]);
//        }
////        for (int column = 0; column < 3; column++) {
////            // If Array does not contain value[0]
////            if (!ArrayUtils.contains(grid[1], value[0]))
////            {
////                grid[1][column] = value[0];
////                value = ArrayUtils.remove(value, 0);
////            }
////        }
//        value = shuffleNumberArray(value, value.length);
//
//        for (int column = 0; column<3; column++)
//        {
//            grid[2][column] = value[0];
//            value = ArrayUtils.remove(value, 0);
//        }

