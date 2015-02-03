package position;

import java.util.ArrayList;
import java.util.List;

public class Position {

/*
 * 	the position class to record some information
 * 	it will record the position of the robot, the last position of the robot, the destination and the origin
 * 	it will also record the reachable position and the finished position of the robot
 * 	while, these position is only used in paint method and check the junction in random robot
 */
	private int[] robotPostion;
	private int[] originPosition;
	private int[] origin;
	private int[] destination;
	private List<String> reachPosition = new ArrayList<String>();
	private List<String> finishPosition = new ArrayList<String>();
	
/*
 * 	the constructor 
 */	
	public Position(int[] origin,int[] destination,int[] robotPosition,int[]originPosition){
		this.destination=destination;
		this.origin=origin;
		setRobotPostion(robotPosition);
		setOriginPosition(originPosition);
		reachPosition.add(""+origin[0]+","+origin[1]);
	}
	
	private final void setRobotPostion(int[] robotPostion) {
		this.robotPostion = robotPostion;
	}

	private final void setOriginPosition(int[] originPosition) {
		this.originPosition = originPosition;
	}
	
/*
 * 	for each time the robot valid move, change the position
 */
	public void move(){
		changePath();
		originPosition=robotPostion.clone();
	}

/*
 * 	record the path to draw the picture
 */	
	public void changePath(){
		if (!reachPosition.contains(""+robotPostion[0]+","+robotPostion[1])) {
			reachPosition.add(""+robotPostion[0]+","+robotPostion[1]);
		}
		else {
			reachPosition.remove(""+originPosition[0]+","+originPosition[1]);
			if (!finishPosition.contains(""+originPosition[0]+","+originPosition[1])) {
				finishPosition.add(""+originPosition[0]+","+originPosition[1]);
			}
		}
			
	}
	
	public int[] getOriginPosition() {
		return originPosition;
	}
	public int[] getRobotPostion() {
		return robotPostion;
	}
	public int[] getDestination() {
		return destination;
	}
	public int[] getOrigin() {
		return origin;
	}
	public List<String> getFinishPosition() {
		return finishPosition;
	}
	public List<String> getReachPosition() {
		return reachPosition;
	}
}
