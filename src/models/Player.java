package models;

public class Player {

	private int id;
	private String name;
	private int score;
	private int life;
	private int x;
	private int y;
	private int avatar;
	private int fort;
	
	public Player(int id, String name, int avatar, int fort, int x, int y) {
		this.id = id;
		this.name = name;
		this.avatar = avatar;
		this.score = 0;
		this.life = 100;
		this.fort = fort;
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
	
	public int getFort() {
		return fort;
	}
	
	public int getAvatar() {
		return avatar;
	}
}