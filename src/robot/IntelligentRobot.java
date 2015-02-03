package robot;

import java.util.ArrayList;
import java.util.List;

import direction.Direction;

public abstract class IntelligentRobot extends AbstractRobot{

/*
 * 	the intelligent robot class
 * 	it has two additional method
 * 	one is to record its walk path
 * 	the other one is to judge the path according to its walk path
 */	
	private List<String> reachPosition = new ArrayList<String>();
	private List<String> finishPosition = new ArrayList<String>();
	private List<Direction> orderList=new ArrayList<Direction>();
	
	public IntelligentRobot(int[] position, int[] destination, Direction face) {
		super(position, destination, face);
		reachPosition.add(""+position[0]+","+position[1]);
	}
	
	public List<String> getReachPosition() {
		return reachPosition;
	}
	public List<String> getFinishPosition() {
		return finishPosition;
	}
	public List<Direction> getOrderList() {
		return orderList;
	}

/*
 * the method to record the walk path
 */
	public void changePath(int[] originPosition){
		
		if (!reachPosition.contains(""+getPosition()[0]+","+getPosition()[1])) {
			reachPosition.add(""+getPosition()[0]+","+getPosition()[1]);
		}
		else {
			reachPosition.remove(""+originPosition[0]+","+originPosition[1]);
			if (!finishPosition.contains(""+originPosition[0]+","+originPosition[1])) {
				finishPosition.add(""+originPosition[0]+","+originPosition[1]);
			}
			
		}

	}

/*
 * 	the method to judge the path
 * 	different from the stupid method imagine
 * 	it will firstly go the path that have never reached
 * 	and record the reached path
 */
	public boolean think (char [] [] maze) {
		int i = (getTryPosition()[0] + getPosition() [0])/2;
		int j = (getTryPosition()[1] + getPosition() [1])/2;
		if (maze[i][j]!=' ') {
			return false;
		}
		else if (finishPosition.contains(""+getTryPosition()[0]+","+getTryPosition()[1])) {
			return false;
		}
		else if (reachPosition.contains(""+getTryPosition()[0]+","+getTryPosition()[1])) {
			orderList.add(getMoveDirection());
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public abstract void strategy(char[][] maze);
	public abstract String getName();

}
