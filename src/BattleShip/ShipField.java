package BattleShip;

public class ShipField implements IGameField {
    private final Ship ship;
    
    public ShipField(Ship ship) {
        this.ship = ship;
    }
    @Override
    public char getIcon() {
    	Result shipState = ship.getState();
        Icons icon = new Icons(shipState);
        return icon.getIcon();
    }

    public Result shootAt() {
        ship.hit();
        return ship.getState();
    }
}
