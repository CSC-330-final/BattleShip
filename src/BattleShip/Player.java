package BattleShip;

import java.awt.*;
import java.util.Scanner;


public class Player implements IPlayer {

    //to reflect the number of ship parts instead of hardcoding it
    int totalShipSize = ShipSize.CARRIER + ShipSize.BATTLESHIP + ShipSize.CRUISER;

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

    @Override
    public void placeShips() {
        System.out.printf("%n======== Player %d - Time to place out your ships ========%n", id);
        board.placeShipsOnBoard();
    }
   
    @Override
    public void fireAt(IPlayer opponent) {
        System.out.printf("%n Alright Player %d - Enter coordinates for your attack: ", id);

        boolean isPointValid = false;
        while(!isPointValid) {
            try {
                Point point = board.validInputsForDrop(scanner.nextLine(), scanner.nextInt());
                int x = (int)point.getX() - 1;
                int y = (int)point.getY() - 1;
                //inspects opponents board for coordinate
                Result result = ((Player)opponent).getBoard().getField(x, y).shootAt();
                //registered result of coordinate
                if(result == Result.PARTIAL_HIT ||  result == Result.DESTROYED) {
                    totalLivesLeft--;
                }
//                this.opponentBoard.placeOpponentBoardMarker(x, y, result);
                this.opponentBoard.printBoard();
                //Moves to next turn if point is valid
                isPointValid = true;
            } catch(IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
    }

    @Override
    public int getTotalLivesLeft() {
        return totalLivesLeft;
    }
}
