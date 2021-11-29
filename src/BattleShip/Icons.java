package BattleShip;

public class Icons implements IGameField{
	private char icon;
	public Icons(Result result) {
        switch (result) {
            case PARTIAL_HIT: icon = 'X';
                break;
            case DESTROYED: icon = 'X';
                break;
            case NO_HIT: icon = 'O';
                break;
            default: icon = ' ';
                break;
        }
	}
	
	public char getIcon() {
		return icon;
	}

	@Override
	public Result shootAt() {
		// TODO Auto-generated method stub
		return null;
	}

}
