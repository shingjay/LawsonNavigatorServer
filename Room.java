/**
 * 
 */
/**
 * @author ong0
 *
 */
public class Room {
	private int x_position;
	private int y_position;
	private int floor;
	private String name;
	private String type; 
	
	public Room(int x_position, int y_position, int floor, String name, String type) {
		this.x_position = x_position;
		this.y_position = y_position;
		this.floor = floor;
		this.name = name;
		this.type = type;
	}
	
	public int getX() { return x_position; }
	public int getY() { return y_position; }
	public int getFloor() { return floor; }
	public String getName() { return name; }
	public String getType() { return type; }
	
	public void setRoomType(String type) { this.type = type; }
	public void setX(int x) { this.x_position = x; }
	public void setY(int y) { this.y_position = y; }
	public void setName(String name) { this.name = name; }
	public void setFloor(int floor) { this.floor = floor; }
}
