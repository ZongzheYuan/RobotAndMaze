package robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import direction.Direction;

public class RandomRecordRobot extends IntelligentRobot{

/*
 * 	the random record robot
 * 	it will randomly choose the direction
 * 	it will record the walk path
 */
	private Random aRandom = new Random();
	private int [] record = new int [3];
	private List<Direction> orderlistArrayList = new ArrayList<Direction>(4);

	public RandomRecordRobot(int[] position,int[] destination, Direction face) {
		super(position, destination, face);
	}

/*
 * 	set the move direction order
 */
	private void setOrder(){
		orderlistArrayList.add(getFace().leftHandSide());
		orderlistArrayList.add(getFace().straight());
		orderlistArrayList.add(getFace().rightHandSide());
	}

	@Override
	public void strategy(char[][] maze) {
/*
 * 	randomly judge the walk order of the three direction
 */
		setOrder();
		record[0]=aRandom.nextInt(3);
		do {
			record[1]=aRandom.nextInt(3);
		} while (record[1]==record[0]);
		for (int i = 0; i < 3; i++) {
			if (i!=record[0]&&i!=record[1]) {
				record[2]=i;
			}
		}

		setMoveDirection(orderlistArrayList.get(record[0]));
		tryMove();
		if (think(maze)) {
			move();
			record[0]=record[1]=record[2]=-1;
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}
		
		setMoveDirection(orderlistArrayList.get(record[1]));
		tryMove();
		if (think(maze)) {
			move();
			record[0]=record[1]=record[2]=-1;
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}
		setMoveDirection(orderlistArrayList.get(record[2]));
		tryMove();
		if (think(maze)) {
			move();
			record[0]=record[1]=record[2]=-1;
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}

/*
 * 	if there has a way that the robot can go, then go
 * 	else go back
 */
		if (getOrderList().isEmpty()) {
			setMoveDirection(getFace().back());
			tryMove();
			move();
			record[0]=record[1]=record[2]=-1;
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}
		else {
			setMoveDirection(getOrderList().get(0));
			tryMove();
			move();
			record[0]=record[1]=record[2]=-1;
			orderlistArrayList.clear();
			getOrderList().clear();
			return;
		}
		
	}

	@Override
	public String getName() {
		return "RandomRecordRobot";
	}
	
}
