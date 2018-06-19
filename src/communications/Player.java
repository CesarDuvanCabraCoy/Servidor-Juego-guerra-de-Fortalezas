package communications;

public class Player {

	private int id;
	private String name;
	private int score;
	private int life;
	private int x;
	private int y;
	
	public Player(int id, String name, int x, int y) {
		this.id = id;
		this.name = name;
		this.score = 0;
		this.life = 100;
		this.x = x;
		this.y = y;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	public int getLife() {
		return life;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}	
}