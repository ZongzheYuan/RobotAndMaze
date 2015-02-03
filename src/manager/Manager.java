package manager;

public class Manager {

/*
 * 	the game manager
 */
	public Manager(){
	}

/*
 * 	the method to check the robot is at destination or not
 */		
	public boolean checkDestination(int[] position, int[] destination){
		return position[0]==destination[0]&&position[1]==destination[1];
	}

/*
 * the method to check each step of the robot is valid or not
 */
	public boolean checkValid(int[] originPosition, int[] position, char[][] maze) {
		try {
			if (Math.abs(originPosition[0]-position[0])>2) {
				return false;
			}
			else if (Math.abs(originPosition[1]-position[1])>2) {
				return false;
			}
			else if (Math.abs(originPosition[0]-position[0])==2 && Math.abs(originPosition[1]-position[1])==2) {
				return false;
			}
			int i=(originPosition[0]+position[0])/2;
			int j=(originPosition[1]+position[1])/2;
			return maze[i][j]==' ';
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array index error: " + e);
			return false;
		}

	}

/*
 * the method to check the origin position and the destination is valid or not
 */
	public boolean checkStart(int[] position, char[][] maze) throws Exception {
		if (position[0]<1 || position[0] > maze.length -2) {
			throw new Exception("invalid!!");
		}
		else if (position[1]<1 || position[1] > maze[0].length -2) {
			throw new Exception("invalid!!");
		}
		else if (maze[position[0]][position[1]]!=' ') {
			throw new Exception("invalid!!");
		}
		else {
			return true;
		}
	}
	
}
