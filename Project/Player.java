package Project;

import java.awt.Point;
import java.util.Scanner;

public class Player {
	
	int totalShipSize = Ship.CARRIER + Ship.BATTLESHIP + Ship.CRUISER;

    private int totalLivesLeft = totalShipSize;

    private int id;
    private Board board;
    private Board opponentBoard;
    private Scanner scanner;

    public Player(int id) {
        this.id = id;
        this.board = new Board();
        this.opponentBoard = new Board();
        this.scanner = new Scanner(System.in);
    }

    public int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public void placeShips() {
        System.out.printf("%n======== Player %d - Time to place out your ships ========%n", id);
        board.placeShipsOnBoard();
    }
   
    public void guessCoordinate(Player opponent) {
        System.out.printf("%n Alright Player %d - Enter coordinates for your attack: ", id);

        boolean isPointValid = false;
        while(!isPointValid) {
            try {
            	
            	this.opponentBoard.printBoard();
            	System.out.println("");
            	scanner.reset();
            	System.out.printf("Enter an X value (%s-%s):", Board.BOARD_LETTERS[0],Board.BOARD_LETTERS[Board.BOARD_LETTERS.length - 1]);
            	String input1 = scanner.next();
            	System.out.printf("Enter a Y value (1-%s):", Board.BOARD_SIZE);
            	int input2 = scanner.nextInt();
            	System.out.println("");
            	
            	
                Point point = board.validInputsForDrop(input1, input2);
                int x = (int)point.getX() - 1;
                int y = (int)point.getY() - 1;
                Point point1 = new Point(x,y);
                //inspects opponents board for coordinate
                Result result = ((Player)opponent).getBoard().getField(x, y).shootAt();
                
                Game.lastTurn.put(this, result);
                this.opponentBoard.placeResult(point1, result);
//                this.opponentBoard.printBoard();
                
                if(result == Result.PARTIAL_HIT ||  result == Result.DESTROYED) {
                    totalLivesLeft--;
                    System.out.println(totalLivesLeft);
                } else {
                	isPointValid = true;
                }
                
            } catch(IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
    }

    public int getLives() {
        return totalLivesLeft;
    }
}
