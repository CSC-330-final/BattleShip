package Project;
public class Icons{
	private char icon;
	private Result result;
	public Icons(Result res) {
		this.result = res;
        switch (res) {
            case PARTIAL_HIT: icon = '*';
                break;
            case DESTROYED: icon = '*';
                break;
            case NO_HIT: icon = 'O';
                break;
            default: icon = ' ';
                break;
        }
	}
	
	public Icons(char ic) {
		this.icon = ic;
		switch (ic) {
        case 'O': result = Result.NO_HIT;
            break;
        case 'X': result = Result.PARTIAL_HIT;
            break;
        default: ;
            break;
    }
	}
	public Result getResult() {
		return result;
	}
	public char getIcon() {
		return icon;
	}
}
