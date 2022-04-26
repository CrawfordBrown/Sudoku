package com.company;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.*;

import static java.lang.Character.UNASSIGNED;

public class Main {


    public static void main(String[] args)  {

//generateEmptyGrid();
//populateFirstBlock();
//populateMiddleBlock();
//populateLastBlock();
//printGrid(grid);
        mainMenu();


    }

    //array of numbers 1-9
    private static final int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static int difficulty;


    //2d array for game board
    private static int[][] grid;

//    public static int block;

    //2d array to copy solved board to check if game is solved
    public static int[][] resultGrid;

    public static int[][] previousGameGrid;

    // array list of the name of saved games
    private static final ArrayList<String> savedGames = new ArrayList<>();

    //array list to store column of undone moves
    private static final ArrayList<Integer> undoMovesColumn = new ArrayList<>();

    //array list to store row of undone moves
    private static final ArrayList<Integer> undoMovesRow = new ArrayList<>();

    //array list to store value of undone moves
    private static final ArrayList<Integer> undoMovesValue = new ArrayList<>();

    //array list to store column of done or redone moves
    private static final ArrayList<Integer> redoMovesColumn = new ArrayList<>();

    //array list to store row of done or redone moves
    private static final ArrayList<Integer> redoMovesRow = new ArrayList<>();

    //array list to store value of done or redone moves
    private static final ArrayList<Integer> redoMovesValue = new ArrayList<>();



    //main menu method
    private static void mainMenu()  {

        //allow for user input
        Scanner scan = new Scanner(System.in);

        System.out.println("please select an Option \n1) New Game \n2) Previous Game \n3) Load Game");

        //read user input
        int option = scan.nextInt();
        try {
        switch (option) {
            case 1 -> selectDifficulty();
            case 2 -> loadPreviousGame();
            case 3 -> {
                displaySavedGames();
                emptyArrays();
                loadGame();
                printGrid(grid);
                inGameMenu();
            }
        }
        }catch (Exception e) {
            System.out.print("That is not an option\n");
            mainMenu();
    }
    }


    // method to remove board values according to difficulty selected
    private static void selectDifficulty() {

        //allow for user input
        Scanner scan = new Scanner(System.in);

        System.out.println("please select an Option \n1) Easy \n2) Medium \n3) Hard \n4) Impossible \n5) Example for completed game(only one cell set to zero)");


        //read user input
        int option = scan.nextInt();

        try {
            switch (option) {
                case 1 -> {
                    difficulty = 60 - randomGenerator(2);
                    emptyArrays();
                    createGame();
                }
                case 2 -> {
                    difficulty = 42 - randomGenerator(5);
                    emptyArrays();
                    createGame();
                }
                case 3 -> {
                    difficulty = 35 - randomGenerator(5);
                    emptyArrays();
                    createGame();
                }
                case 4 -> {
                    difficulty = 17;
                    emptyArrays();
                    createGame();
                }
                case 5 -> {
                    difficulty = 80;
                    emptyArrays();
                    createGame();
                }


            }
        } catch (Exception e) {
            System.out.print("That is not an option\n");
            selectDifficulty();
        }
    }

    //method for user to update cell
    private static void updateCell() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please select a column from A-I");


        //read user input
        String columnCell = scan.next().toUpperCase(Locale.ROOT);
        int row = 0;
        int column = 0;


        //user selects column
        switch (columnCell) {
            case "A" -> {
            }
            case "B" -> column = 1;
            case "C" -> column = 2;
            case "D" -> column = 3;
            case "E" -> column = 4;
            case "F" -> column = 5;
            case "G" -> column = 6;
            case "H" -> column = 7;
            case "I" -> column = 8;
        }


        System.out.println("Please select a row from 1-9");

        //read user input
        Scanner scan2 = new Scanner(System.in);
        int rowCell = scan2.nextInt();

        //user selects row
        switch (rowCell) {
            case 1 -> {
            }
            case 2 -> row = 1;
            case 3 -> row = 2;
            case 4 -> row = 3;
            case 5 -> row = 4;
            case 6 -> row = 5;
            case 7 -> row = 6;
            case 8 -> row = 7;
            case 9 -> row = 8;
        }

        System.out.println("Please put a number from 1-9 to update the cell");

        //read user input
        int update = scan.nextInt();

        //add the original value to undo value array before it is updated
        int prevInt = grid[row][column];
        undoMovesValue.add(prevInt);

        grid[row][column] = update;

        //add row and column coordinates to undo row and column array. The commented out printing of the arrays were testing to make sure the correct values were being added in the correct order
        undoMovesColumn.add(column);
        undoMovesRow.add(row);
//            System.out.println(undoMovesValue);
//            System.out.println(undoMovesColumn);
//            System.out.println(undoMovesRow);
        printGrid(grid);
    }


    //Menu for user while playing game
    private static void inGameMenu() {

        //allow for user input
        Scanner scan = new Scanner(System.in);

        System.out.println("please select an Option \n1) insert a number \n2) undo \n3) redo \n4) check answer \n5) save game \n6) Main menu");

        try {
            //read user input
            int option = scan.nextInt();

            switch (option) {
                case 1 -> {
                    updateCell();
                    inGameMenu();
                }
                case 2 -> {
                    undo();
                   // System.out.println(undoMovesRow);
                    printGrid(grid);
                    inGameMenu();
                }
                case 3 -> {
                    redo();

                    printGrid(grid);
                    inGameMenu();
                }

                case 4 -> {
                    if (equals(grid, resultGrid)) {
                        System.out.println("congratulations\n");
                        completeOption();
                        mainMenu();

                    } else {
                        System.out.println("that is not correct");
                        printGrid(grid);
                        inGameMenu();
                        // printGrid();

                    }
                }
                case 5 -> {
                    saveGame();
                    System.out.println("Game saved \nPlease select an option \n1) Main menu \n2) Keep playing");
                    int saveOption = scan.nextInt();
                    switch (saveOption) {
                        case 1 -> mainMenu();
                        case 2 -> {
                            printGrid(grid);
                            inGameMenu();
                        }
                    }

                }
                case 6 -> mainMenu();

            }
        } catch (Exception e) {
            System.out.print("That is not an option\n");
            printGrid(grid);
            inGameMenu();
        }

    }


    //options for user upon completing a game
    private static void completeOption() {

        //allow for user input
        Scanner scan = new Scanner(System.in);

        System.out.println("please type \n1) Play again \n2) Go back to the main menu");

        //read user input
        int option = scan.nextInt();

        try {

            switch (option) {
                case 1 -> loadPreviousGame();
                case 2 -> mainMenu();
            }
        } catch (Exception e) {
            System.out.println("Invalid Option");
            completeOption();
        }
    }

    // method to set up a game
    private static void createGame() {
        generateEmptyGrid();
        populateFirstBlock();
        populateMiddleBlock();
        populateLastBlock();
        populateGrid();
        copyGrid(grid);
        removeDigits();
        getPreviousGameGrid(grid);
        printGrid(grid);
//        printGrid(resultGrid);
//        printGrid(previousGameGrid);
        inGameMenu();
    }


    //method to undo previous move
    private static void undo() {

        //As array indexes starts from zero to get the last element index we minus 1 from the size of the array
        int indexRow = undoMovesRow.size() - 1;
        int indexColumn = undoMovesColumn.size() - 1;
        int indexValue = undoMovesValue.size() - 1;


        //getting the element from that index of arrays for row and column
        int row = undoMovesRow.get(indexRow);
        int column = undoMovesColumn.get(indexColumn);


        //add index value to the redo moves array and delete from the undo moves array
        redoMovesRow.add(row);
        undoMovesRow.remove(indexRow);

        redoMovesColumn.add(column);
        undoMovesColumn.remove(indexColumn);


        //The current value before being undone is added to the redo moves array
        redoMovesValue.add(grid[row][column]);


        //updating the value with the last element from the undo moves value array and deleting it from that array
        grid[row][column] = undoMovesValue.get(indexValue);
        undoMovesValue.remove(indexValue);

    }

    //Method to redo undone moves
    private static void redo() {

        //As with undo, getting the index of the last element in each array
        int indexRow = redoMovesRow.size() - 1;
        int indexColumn = redoMovesColumn.size() - 1;
        int indexValue = redoMovesValue.size() - 1;

        //getting the value of the last index in each array
        int row = redoMovesRow.get(indexRow);
        int column = redoMovesColumn.get(indexColumn);
        int value = redoMovesValue.get(indexValue);

        //getting the current value of the cell before redoing
        int prevInt = grid[row][column];

        //update the cell value with the saved value in the array
        grid[row][column] = value;

        //delete row column and cell values from the arrays
        redoMovesRow.remove(indexRow);
        redoMovesColumn.remove(indexColumn);
        redoMovesValue.remove(indexValue);

        //add the new values to the undo arrays
        undoMovesValue.add(prevInt);
        undoMovesColumn.add(column);
        undoMovesRow.add(row);

    }

    //method to play previous game
    private static void loadPreviousGame() {
        printGrid(previousGameGrid);
        inGameMenu();
    }

    //method to save games
    private static void saveGame() throws IOException {

        //user input
        Scanner scan = new Scanner(System.in);

        System.out.println("Please name your saved game");

        //read user input and trim
        String savedName = scan.next().trim();

        //construct an empty string builder
        StringBuilder builder = new StringBuilder();
        for (int[] value : grid) {
            for (int column = 0; column < grid.length; column++) {
                //append value to string
                builder.append(value[column]);
                //if this is not the last element in a row, add a comma
                if (column < grid.length - 1)
                    builder.append(",");
            }
            //new line at the end of each row
            builder.append("\n");
        }

        //creating file writer with appropriate path
        File file = new File("/Users/crawford/Desktop/SudokuSaves/" + savedName + ".txt");
        //create a buffered writer
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        //write the string builder to file
        writer.write(builder.toString());
        //close the writer
        writer.close();

        //add the saved game name to the array of saved games
        savedGames.add(savedName);


        /////////////////////////////////////////////////////////////////////////////////
        ///////////                     Store undo moves                /////////////////
        /////////////////////////////////////////////////////////////////////////////////

        StringBuilder undoBuilderRow = new StringBuilder();
        for (int i = 0; i<undoMovesRow.size(); i++) {
            undoBuilderRow.append(undoMovesRow.get(i));
            if (i < undoMovesRow.size() - 1) {
                undoBuilderRow.append(",");
            }
        }
        File undoRowFile = new File("/Users/crawford/Desktop/SudokuUndo/" + savedName + "Row.txt" );

        BufferedWriter writerUndoRow = new BufferedWriter(new FileWriter(undoRowFile));
        //write the string builder to file
        writerUndoRow.write(undoBuilderRow.toString());
        //close the writer
        writerUndoRow.close();

        StringBuilder undoBuilderColumn = new StringBuilder();
        for (int j = 0; j<undoMovesColumn.size(); j++) {
            undoBuilderColumn.append(undoMovesColumn.get(j));
            if (j < undoMovesColumn.size() - 1) {
                undoBuilderColumn.append(",");
            }
        }
        File undoColumnFile = new File("/Users/crawford/Desktop/SudokuUndo/" + savedName + "Column.txt" );

        BufferedWriter writerUndoColumn = new BufferedWriter(new FileWriter(undoColumnFile));
        //write the string builder to file
        writerUndoColumn.write(undoBuilderColumn.toString());
        //close the writer
        writerUndoColumn.close();

        StringBuilder undoBuilderValue = new StringBuilder();
        for (int k = 0; k<undoMovesValue.size(); k++) {
            undoBuilderValue.append(undoMovesValue.get(k));
            if (k < undoMovesValue.size() - 1) {
                undoBuilderValue.append(",");
            }
        }
        File undoValueFile = new File("/Users/crawford/Desktop/SudokuUndo/" + savedName + "Value.txt" );

        BufferedWriter writerUndValue = new BufferedWriter(new FileWriter(undoValueFile));
        //write the string builder to file
        writerUndValue.write(undoBuilderValue.toString());
        //close the writer
        writerUndValue.close();

        /////////////////////////////////////////////////////////////////////////////////
        ///////////                     Store redo moves                /////////////////
        /////////////////////////////////////////////////////////////////////////////////

        //redo row
        StringBuilder redoBuilderRow = new StringBuilder();
        for (int i = 0; i<redoMovesRow.size(); i++) {
            redoBuilderRow.append(redoMovesRow.get(i));
            if (i < redoMovesRow.size() - 1) {
                redoBuilderRow.append(",");
            }
        }
        File redoRowFile = new File("/Users/crawford/Desktop/SudokuRedo/" + savedName + "Row.txt" );

        BufferedWriter writerRedoRow = new BufferedWriter(new FileWriter(redoRowFile));
        //write the string builder to file
        writerRedoRow.write(redoBuilderRow.toString());
        //close the writer
        writerRedoRow.close();

        //redo column
        StringBuilder redoBuilderColumn= new StringBuilder();
        for (int j = 0; j<redoMovesColumn.size(); j++) {
            redoBuilderColumn.append(redoMovesColumn.get(j));
            if (j < redoMovesColumn.size() - 1) {
                redoBuilderColumn.append(",");
            }
        }
        File redoColumnFile = new File("/Users/crawford/Desktop/SudokuRedo/" + savedName + "Column.txt" );

        BufferedWriter writerRedoColumn = new BufferedWriter(new FileWriter(redoColumnFile));
        //write the string builder to file
        writerRedoColumn.write(redoBuilderColumn.toString());
        //close the writer
        writerRedoColumn.close();

        //redo Value
        StringBuilder redoBuilderValue= new StringBuilder();
        for (int k = 0; k<redoMovesValue.size(); k++) {
            redoBuilderValue.append(redoMovesValue.get(k));
            if (k < redoMovesValue.size() - 1) {
                redoBuilderValue.append(",");
            }
        }
        File redoValueFile = new File("/Users/crawford/Desktop/SudokuRedo/" + savedName + "Value.txt" );

        BufferedWriter writerRedoValue = new BufferedWriter(new FileWriter(redoValueFile));
        //write the string builder to file
        writerRedoValue.write(redoBuilderValue.toString());
        //close the writer
        writerRedoValue.close();
    }

    //empty all undo and redo arrays
    public static void emptyArrays(){
        undoMovesColumn.clear();
        undoMovesRow.clear();
        undoMovesValue.clear();
        redoMovesColumn.clear();
        redoMovesRow.clear();
        redoMovesValue.clear();
    }

    private static void displaySavedGames() {
        //loop through and display the saved game names
        for (String savedGame : savedGames) {
            System.out.println(savedGame);
        }

    }

    private static void loadGame() throws IOException {

        //user input
        Scanner scan = new Scanner(System.in);

        System.out.println("Please type the game you would like to load exactly as it is written above");

        //read user input
        String savedNameFile = scan.next().trim();
        //create empty grid
        generateEmptyGrid();
        //create file reader
        FileReader file = new FileReader("/Users/crawford/Desktop/SudokuSaves/" + savedNameFile + ".txt");
        //create buffer reader
        BufferedReader reader = new BufferedReader(file);
        String line;
        int row = 0;
        //loop through file
        while ((line = reader.readLine()) != null) {
            String[] cols = line.split(",");
            int column = 0;
            for (String value : cols) {
                //loop through file to update the empty grid with the saved values
                grid[row][column] = Integer.parseInt(value);
                column++;
            }
            row++;
        }
        //close reader
        reader.close();

        //////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////     Undo Moves      //////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////



        FileReader undoRowFile = new FileReader("/Users/crawford/Desktop/SudokuUndo/" + savedNameFile + "Row.txt");
        BufferedReader undoRowReader = new BufferedReader(undoRowFile);
        String line2;
        while ((line2 = undoRowReader.readLine()) != null) {
            String[] undoRow = line2.split(",");
            int count = 0;
            for (String value : undoRow) {
                undoMovesRow.add(Integer.valueOf(value));
            }
        }
        undoRowReader.close();

        FileReader undoColumnFile = new FileReader("/Users/crawford/Desktop/SudokuUndo/" + savedNameFile + "Column.txt");
        BufferedReader undoColumnReader = new BufferedReader(undoColumnFile);
        String line3;
        while ((line3 = undoColumnReader.readLine()) != null) {
            String[] undoRow = line3.split(",");
            int count = 0;
            for (String value : undoRow) {
                undoMovesColumn.add(Integer.valueOf(value));
            }
        }
        undoColumnReader.close();

        FileReader undoValueFile = new FileReader("/Users/crawford/Desktop/SudokuUndo/" + savedNameFile + "Value.txt");
        BufferedReader undoValueReader = new BufferedReader(undoValueFile);
        String line4;
        while ((line4 = undoValueReader.readLine()) != null) {
            String[] undoRow = line4.split(",");
            int count = 0;
            for (String value : undoRow) {
                undoMovesValue.add(Integer.valueOf(value));
            }
        }
        undoValueReader.close();

        //////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////     Redo Moves      //////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////

        FileReader redoRowFile = new FileReader("/Users/crawford/Desktop/SudokuRedo/" + savedNameFile + "Row.txt");
        BufferedReader redoRowReader = new BufferedReader(redoRowFile);
        String line5;
        while ((line5 = redoRowReader.readLine()) != null) {
            String[] undoRow = line5.split(",");
            int count = 0;
            for (String value : undoRow) {
                redoMovesRow.add(Integer.valueOf(value));
            }
        }
        redoRowReader.close();

        FileReader redoColumnFile = new FileReader("/Users/crawford/Desktop/SudokuRedo/" + savedNameFile + "Column.txt");
        BufferedReader redoColumnReader = new BufferedReader(redoColumnFile);
        String line6;
        while ((line6 = redoColumnReader.readLine()) != null) {
            String[] undoRow = line6.split(",");
            int count = 0;
            for (String value : undoRow) {
                redoMovesColumn.add(Integer.valueOf(value));
            }
        }
        redoColumnReader.close();

        FileReader redoValueFile = new FileReader("/Users/crawford/Desktop/SudokuRedo/" + savedNameFile + "Value.txt");
        BufferedReader redoValueReader = new BufferedReader(redoValueFile);
        String line7;
        while ((line7 = redoValueReader.readLine()) != null) {
            String[] undoRow = line7.split(",");
            int count = 0;
            for (String value : undoRow) {
                redoMovesValue.add(Integer.valueOf(value));
            }
        }
        redoValueReader.close();    }



    //check if the saved array of the complete grid and the users filled in grid are equal
    private static boolean equals(final int[][] grid1, final int[][] grid2) {
        if (grid1 == null) {
            return (grid2 == null);
        }
        if (grid2 == null) {
            return false;
        }
        //check grids are the same length
        if (grid1.length != grid2.length) {
            return false;
        }
        //compare the two grids to check if they are equal
        for (int i = 0; i < grid1.length; i++) {
            if (!Arrays.equals(grid1[i], grid2[i])) {
                return false;
            }
        }
        return true;
    }

    // set all values in 2d array to 0
    private static void generateEmptyGrid() {

        //grid size is 11 to add the y axis for user readability
        grid = new int[9][11];
        //loop through each row and column
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid.length; column++) {
                //set the value to zero
                grid[row][column] = 0;
            }
        }
    }

    //shuffle numbers 1-9 so it is always random
    private static int[] shuffleNumberArray(int[] array, int a) {
        // Creating object for Random class
        Random random = new Random();

        // Starting from the last element and swapping one by one.
        for (int i = a - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = random.nextInt(i + 1);

            // Swap array[i] with the element at random index
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        // Printing the random generated array
        return array;
    }

    //populate the grid using traversing
    private static boolean populateGrid() {

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

    //return a complete grid for comparing with when game is complete
    public static void copyGrid(int[][] passedGrid) {
        resultGrid = new int[passedGrid.length][passedGrid[0].length];
        // Copy all the values
        for (int row = 0; row < passedGrid.length; row++) {
            // loop through rows and columns copying to the resultsGrid
            for (int column = 0; column < passedGrid[0].length; column++) {
                resultGrid[row][column] = passedGrid[row][column];
                passedGrid[row][10] = row;
            }
        }

    }

    //makes a copy of the game so previous game can be replayed. It is nearly identical to the copyGrid function. However, I was unsuccessful in getting the code to work with just one
    public static void getPreviousGameGrid(int[][] passedGrid) {
        previousGameGrid = new int[passedGrid.length][passedGrid[0].length];
        // Copy all the values
        for (int row = 0; row < passedGrid.length; row++) {
            // loop through rows and columns copying to the resultsGrid
            for (int column = 0; column < passedGrid[0].length; column++) {
                previousGameGrid[row][column] = passedGrid[row][column];
            }
        }
    }


    //print out grids
    private static void printGrid(int[][] passedGrid) {
        //x-axis
        System.out.println(" A  B  C     D  E  F     G  H  I");
        {
            for (int row = 0; row < 9; row++) {
                if ((row + 1) % 3 == 1 && row > 1) {
                    //print block spacing horizontally every 3 rows
                    System.out.println("-------------------------------------");
                }
                for (int column = 0; column < 9; column++) {
                    if (column == 8) {
                        passedGrid[row][10] = row;
                        //print y-axis numbers
                        System.out.print("[" + passedGrid[row][column] + "]" + "  " + (passedGrid[row][10] + 1));
                    } else if ((column + 1) % 3 == 0) {
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


    //fill in first block
    private static void populateFirstBlock() {
        int[] value = shuffleNumberArray(ints, ints.length);

        shuffleNumberArray(value, value.length);
        //loop through rows 0-2
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                grid[row][column] = value[0];
                value = ArrayUtils.remove(value, 0);
            }
        }
    }

    //fill in middle block
    private static void populateMiddleBlock() {
        int[] value = shuffleNumberArray(ints, ints.length);

        shuffleNumberArray(value, value.length);
        //loop through rows 3-5
        for (int row = 3; row < 6; row++) {
            for (int column = 3; column < 6; column++) {
                grid[row][column] = value[0];
                value = ArrayUtils.remove(value, 0);
            }
        }
    }

    //fill in last block
    private static void populateLastBlock() {
        int[] value = shuffleNumberArray(ints, ints.length);

        shuffleNumberArray(value, value.length);
        //loop throught rows 6-8
        for (int row = 6; row < 9; row++) {
            for (int column = 6; column < 9; column++) {
                grid[row][column] = value[0];
                value = ArrayUtils.remove(value, 0);
            }
        }
    }


    //checks if the number exists in the row or not
    private static boolean checkRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == number) {
                return true;
            }
        }
        return false;
    }


    //checks if the number exists in the column or not
    private static boolean checkColumn(int column, int number) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][column] == number) {
                return true;
            }
        }
        return false;
    }


    //check if number exists in block
    private static boolean checkBlock(int row, int column, int number) {
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
    public static boolean safeToAssign(int row, int col, int number) {
        // if number does not exist in row, column or block return true
        return !(checkRow(row, number) || checkColumn(col, number) || checkBlock(row, col, number));
    }


    // Random generator
    private static int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }


    private static void removeDigits() {

        //depending on difficulty how many numbers are removed
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







