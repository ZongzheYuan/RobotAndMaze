package robot;

import java.util.List;
import java.util.ArrayList;

import direction.Direction;

public class GreedRobot extends IntelligentRobot{

/*
 * 	the robot that use greedy method
 * 	it is the most intelligent robot in my class
 */	
	private List<Direction> orderlistArrayList = new ArrayList<Direction>(4);
	
	public GreedRobot(int[] position,int[] destination, Direction face) {
		super(position, destination, face);
	}

/*
 * 	set the move direction order depending on its own position and the destination
 */
	private void setOrder(){
		if (getDestination()[0]-getPosition()[0]>0) {
			if (getDestination()[1]-getPosition()[1]>0) {
				if (Math.abs(getDestination()[0]-getPosition()[0])>Math.abs(getDestination()[1]-getPosition()[1])) {
					addOrderList(Direction.South, Direction.East, Direction.West, Direction.North);
				}
				else {
					addOrderList(Direction.East, Direction.South, Direction.North, Direction.West);
				}
			}
			else {
				if (Math.abs(getDestination()[0]-getPosition()[0])>Math.abs(getDestination()[1]-getPosition()[1])) {
					addOrderList(Direction.South, Direction.West, Direction.East, Direction.North);
				}
				else {
					addOrderList(Direction.West, Direction.South, Direction.North, Direction.East);
				}
			}
		}
		else{
			if (getDestination()[1]-getPosition()[1]>0) {
				if (Math.abs(getDestination()[0]-getPosition()[0])>Math.abs(getDestination()[1]-getPosition()[1])) {
					addOrderList(Direction.North, Direction.East, Direction.West, Direction.South);
				}
				else {
					addOrderList(Direction.East, Direction.North, Direction.South, Direction.West);
				}
			}
			else {
				if (Math.abs(getDestination()[0]-getPosition()[0])>Math.abs(getDestination()[1]-getPosition()[1])) {
					addOrderList(Direction.North, Direction.West, Direction.East, Direction.South);
				}
				else {
					addOrderList(Direction.West, Direction.North, Direction.South, Direction.East);
				}
			}
		}
	}

/*
 * 	the assistant method to add value into direction order list
 */
	private void addOrderList(Direction d1, Direction d2, Direction d3, Direction d4) {
		orderlistArrayList.add(d1);
		orderlistArrayList.add(d2);
		orderlistArrayList.add(d3);
		orderlistArrayList.add(d4);
	}
	
	@Override
	public void strategy(char[][] maze) {

		setOrder();

		setMoveDirection(orderlistArrayList.get(0));
		tryMove();
		if (think(maze)) {
			move();
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}
		setMoveDirection(orderlistArrayList.get(1));
		tryMove();
		if (think(maze)) {
			move();
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}
		setMoveDirection(orderlistArrayList.get(2));
		tryMove();
		if (think(maze)) {
			move();
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}
		setMoveDirection(orderlistArrayList.get(3));
		tryMove();
		if (think(maze)) {
			move();
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}
		
/*
 * 	just move the first recorded path in Order list
 */
		setMoveDirection(getOrderList().get(0));
		tryMove();
		move();
		orderlistArrayList.clear();
		getOrderList().clear();
		return;

	}
	
	public String getName() {
		return "GreedRobot";
	}
	
}
