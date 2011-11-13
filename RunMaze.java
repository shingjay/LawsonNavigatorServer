import java.util.*;

public class RunMaze {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Maze maze1 = new Maze(61, 27);
		//maze1.draw();
		maze1.findBestPath();
		
		List<Square> bestList = maze1.getBestList();
		
		int directions[] = new int[bestList.size() - 1];
		int j = 0;
		
		for(int i = bestList.size() - 1; i > 0; i--){
			//System.out.println("X: " + bestList.get(i).getX() + " Y: " + bestList.get(i).getY());
			Square current = bestList.get(i);
			Square next = bestList.get(i-1);
			
			int dx = next.getX() - current.getX();
			int dy = next.getY() - current.getY();
			
			if(dx < 0){
				directions[j] = 0;
			}else if(dx > 0){
				directions[j] = 2;
			}else if(dy > 0){
				directions[j] = 1;
			}else{
				directions[j] = 3;
			}
			
			j++;
		}
		
		for(int i = 0; i < directions.length; i++){
			System.out.println(directions[i]);
		}
	}
	
	public static int[] Run(int row, int col){
		Maze maze = new Maze(61, 27);
		//maze1.draw();
		maze.findBestPath();
		
		List<Square> bestList = maze.getBestList();
		
		int directions[] = new int[bestList.size() - 1];
		int j = 0;
		
		for(int i = bestList.size() - 1; i > 0; i--){
			//System.out.println("X: " + bestList.get(i).getX() + " Y: " + bestList.get(i).getY());
			Square current = bestList.get(i);
			Square next = bestList.get(i-1);
			
			int dx = next.getX() - current.getX();
			int dy = next.getY() - current.getY();
			
			if(dx < 0){
				directions[j] = 0;
			}else if(dx > 0){
				directions[j] = 2;
			}else if(dy > 0){
				directions[j] = 1;
			}else{
				directions[j] = 3;
			}
			
			j++;
		}
		
		for(int i = 0; i < directions.length; i++){
			System.out.println(directions[i]);
		}
		
		return directions;
	}

}
