package BattleShip;

import java.awt.*;
import java.util.Locale;
import java.util.Scanner;


public class Board {
    private static final char WATER = '~';
    private static final int BOARD_SIZE = 7;
    private static final char[] BOARD_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private static final String HORIZONTAL = "H";
    private static final String VERTICAL = "V";

    private Scanner scanner;
    private IGameField[][] board;
    private static final Ship[] ships;

    static {
        ships = new Ship[]{
                new Ship("Carrier", ShipSize.CARRIER),
                new Ship("Battleship", ShipSize.BATTLESHIP),
                new Ship("Cruiser", ShipSize.CRUISER)
        };
    }

    public Board() {
        this.scanner = new Scanner(System.in);
        this.board = new IGameField[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new WaterField();
            }
        }
    }

    public void placeShipsOnBoard() {
        //to show the board before asking for input
        printBoard();

        for (Ship ship : ships) {
            boolean horizontal = askValidShipDirection();
            Point startingPoint = askValidStartingPoint(ship, horizontal);
            placeValidShip(ship, startingPoint, horizontal);

            printBoard();
        }
    }

    public IGameField getField(int x, int y) {
        if (!isInsideBoard(x, y)) {
            throw new IllegalArgumentException("Out of bounds..");
        }
        return board[y][x];
    }

    public void printBoard() {
        System.out.print("\t");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(BOARD_LETTERS[i] + "\t");
        }
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j].getIcon() + "\t");
            }
            System.out.println();
        }
    }

    //ships can be placed vertically for horizontally, so we ask the input for an option
    private boolean askValidShipDirection() {
        System.out.printf("%nDo you want to place the ship horizontally (H) or vertically (V)?");
        String direction;
        do {
            //using touppercase so if user didn't use cap v or h it will also be accepted
            direction = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        } while (!HORIZONTAL.equals(direction) && !VERTICAL.equals(direction));

        return HORIZONTAL.equals(direction);
    }

    private Point askValidStartingPoint(Ship ship, boolean horizontal) {
        Point from;
        do {
            System.out.printf("%nEnter position of %s (length  %d). EX: X ENTER Y:", ship.getName(), ship.getSize());
            from = new Point(validInputsForDrop(scanner.nextLine(), scanner.nextInt()));

        } while (!isValidStartingPoint(from, ship.getSize(), horizontal));

        return from;
    }


    private Point validInputsForDrop(String x, int y){
        //Point finalPoint;
        int z;
        x = x.toUpperCase(Locale.ROOT);

        switch (x){
            case "A": z = 1;
            break;
            case "B": z = 2;
                break;
            case "C": z = 3;
                break;
            case "D": z = 4;
                break;
            case "E": z = 5;
                break;
            case "F": z = 6;
                break;
                case "G": z = 7;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + x);
        }
        return new Point(z,y);
    }



    private boolean isValidStartingPoint(Point from, int length, boolean horizontal) {
        int xDiff = 0;
        int yDiff = 0;
        if (horizontal) {
            xDiff = 1;
        } else {
            yDiff = 1;
        }

        int x = (int) from.getX() - 1;
        int y = (int) from.getY() - 1;
        if (!isInsideBoard(x, y) ||
                (!isInsideBoard(x + length, y) && horizontal) ||
                (!isInsideBoard(x, y + length) && !horizontal)
        ) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (board[(int) from.getY() + i * yDiff - 1]
                    [(int) from.getX() + i * xDiff - 1].getIcon() != WATER) {
                return false;
            }
        }
        return true;
    }

    private void placeValidShip(Ship ship, Point startingPoint, boolean horizontal) {
        int xDiff = 0;
        int yDiff = 0;
        if (horizontal) {
            xDiff = 1;
        } else {
            yDiff = 1;
        }
        for (int i = 0; i < ship.getSize(); i++) {
            board[(int) startingPoint.getY() + i * yDiff - 1]
                    [(int) startingPoint.getX() + i * xDiff - 1] = new ShipField(ship);
        }
    }

    private boolean isInsideBoard(int x, int y) {
        return x <= BOARD_SIZE && x >= 0
                && y <= BOARD_SIZE && y >= 0;
    }
}