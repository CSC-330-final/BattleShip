package BattleShip;

public interface IPlayer {
    void placeShips();
    void fireAt(IPlayer opponent);
    int getTotalLivesLeft();
}
