package Project;

public class Coordinate implements CoordinateInterface {
	private final Ship ship;
	private Result shipState;
	public Coordinate(Ship ship) {
		this.ship = ship;
		this.shipState = ship.getState();
	}
	public Coordinate(Result result) {
		this.ship = null;
		this.shipState = result;
	}
	
	@Override
	public char getIcon() {
        Icons icon = new Icons(shipState);
        return icon.getIcon();
	}

	@Override
	public Result shootAt() {
		ship.hit();
        return ship.getState();
	}
	
}
