package Project;

public class Field implements CoordinateInterface {
	private boolean coordinateWasHit = false;
	@Override
	public char getIcon() {
		return coordinateWasHit ? 'M' : '~';
	}

	@Override
	public Result shootAt() {
		coordinateWasHit = true;
		return Result.NO_HIT;
	}
}
