package Project;
public class Ship {

	private final String name;
    private final int size;
    private int lives;
    
    public static final int CARRIER = 2;
    public static final int BATTLESHIP = 3;
    public static final int CRUISER = 4;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.lives = size;
    }

    public void hit() {
        if(lives > 0) {
            System.out.printf("%nGood shot! The %s was hit", name);
            lives--;
        } else {
            System.out.printf("%s is destroyed%n",name);
        }
    }

    public Result getState() {
        if(lives == 0) {
            return Result.DESTROYED;
        } else if(lives < size) {
            return Result.PARTIAL_HIT;
        } else {
            return Result.NO_HIT;
        }
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

}
