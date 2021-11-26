package Project;

import java.util.HashMap;
import java.util.Map;

public class Game {
	private Player[] players;
	static Map<Player, Result> lastTurn;
    public Game() {

        this.players = new Player[]{
                new Player(1),
                new Player(2)
        };
        Game.lastTurn = new HashMap<Player, Result>();
    }

    public void startGame() {
        int i = 0;
        int j = 1;
        int len = players.length;
        Player player = null;

        this.players[i].placeShips();
        this.players[j].placeShips();
        
        while(players[0].getLives() > 0 &&
                players[1].getLives() > 0) {

            players[i++ % len].guessCoordinate(players[j++ % len]);
            player = (players[0].getLives() < players[1].getLives()) ?
                    players[1] :
                    players[0];
        }

        System.out.printf("Congrats Player %d, you won!",player.getId());
//	private Player player1;
//	private Player player2;
//	private Player turn;
//	static Map<Player, Result> lastTurn;
//	public Game() {
//		this.player1 = new Player(1);
//		this.player2 = new Player(2);
//		turn = player1;
//		Game.lastTurn = new HashMap<Player, Result>();
//	}
//	private void changeTurn() {
//		if(this.turn == player1) {
//			this.turn = player2;
//		} else {
//			this.turn = player1;
//		}
//	}
//    public void startGame() {
//    	this.player1.placeShips();
//    	this.player2.placeShips();
//    	
////    	System.out.println(player1.getLives());
//    	while(player1.getLives() > 0 && player2.getLives() > 0) {
//    		if(turn == player1) {
//    			player1.guessCoordinate(player2);
//    		} else {
//    			player2.guessCoordinate(player1);
//    		}
//    		changeTurn();
//    	}
//    	int winner = player1.getLives() > player2.getLives() ? player1.getId() : player2.getId();
//    	System.out.printf("Congrats Player %d, you won!", winner);
    }
}
