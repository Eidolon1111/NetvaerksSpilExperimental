package NetvaerksSpil;

public class Player {
	String name;
	int xpos;
	int ypos;
	int point;
	String direction;
	boolean ready;

	public Player(String name, int xpos, int ypos, String direction) {
		this.name = name;
		this.xpos = xpos;
		this.ypos = ypos;
		this.direction = direction;
		this.point = 0;
		this.ready = false;
	}

	public int getXpos() {
		return xpos;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	public int getYpos() {
		return ypos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public void addPoints(int p) {
		point+=p;
	}
	public String toString() {
		return name+":   "+point;
	}

	public String getName(){
		return this.name;
	}

	public void setReady(boolean ready){
		this.ready = ready;
	}

	public String getLobbyString(){
		return name + ": " + (ready ?  "ready" : "not ready");
	}

}
