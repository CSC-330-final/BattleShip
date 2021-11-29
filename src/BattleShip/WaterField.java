package BattleShip;

public class WaterField implements IGameField {
    private boolean isThisFieldHit = false;

    @Override
    public char getIcon() {
        return isThisFieldHit ? 'M' : '~';
    }

    public Result shootAt() {
        isThisFieldHit = true;
        return Result.NO_HIT;
    }
}
