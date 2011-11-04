public class RunMaze {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Maze maze = new Maze(61, 27);
		maze.draw();
		maze.findBestPath();
	}

}
