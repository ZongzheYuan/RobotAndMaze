package robot;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import direction.Direction;

public class RandomRobot extends AbstractRobot{

/*
 * 	the random robot
 * 	it will randomly choose right, left, or straight at each cross
 */
	private Random aRandom = new Random();
	private List<Direction> orderlistArrayList = new ArrayList<Direction>(4);
	private int [] record = new int [4];
	public RandomRobot(int[] position,int[] destination, Direction face) {
		super(position, destination, face);
	}

	@Override	
	public void strategy(char[][] maze) {
/*
 * 	randomly judge the walk order of the three direction
 */
		setOrder();
		if (checkCross(maze)>1) {
			orderlistArrayList.add(getFace().back());
			
			record[0]=aRandom.nextInt(4);
			do {
				record[1]=aRandom.nextInt(4);
			} while (record[1]==record[0]);
			do {
				record[2]=aRandom.nextInt(4);
			} while (record[2]==record[0]||record[2]==record[1]);
			for (int i = 0; i < 4; i++) {
				if (i!=record[0]&&i!=record[1]&&i!=record[2]) {
					record[3]=i;
				}
			}
			
			if (randomGo(maze)) {
				return;
			}

/*
 * 	try the fourth direction
 * 	the possibility of the four direction is same
 */
			setMoveDirection(orderlistArrayList.get(record[3]));
			tryMove();
			if (imagine(maze)) {
				record[0]=record[1]=record[2]=record[3]=-1;
				orderlistArrayList.clear();
				move();
				return;
			}
			
		}
		else {

			record[0]=aRandom.nextInt(3);
			do {
				record[1]=aRandom.nextInt(3);
			} while (record[1]==record[0]);
			for (int i = 0; i < 3; i++) {
				if (i!=record[0]&&i!=record[1]) {
					record[2]=i;
				}
			}

			if (randomGo(maze)) {
				return;
			}
		
/*
 *	if no way, then go back
 */		
			setMoveDirection(getFace().back());
			tryMove();
			record[0]=record[1]=record[2]=-1;
			orderlistArrayList.clear();
			move();
			return;	
		}
	}
	
/*
 * 	random go in the first three direction
 */
	private boolean randomGo(char[][] maze){
		setMoveDirection(orderlistArrayList.get(record[0]));
		tryMove();
		if (imagine(maze)) {
			record[0]=record[1]=record[2]=record[3]=-1;
			orderlistArrayList.clear();
			move();
			return true;
		}
		
		setMoveDirection(orderlistArrayList.get(record[1]));
		tryMove();
		if (imagine(maze)) {
			record[0]=record[1]=record[2]=record[3]=-1;
			orderlistArrayList.clear();
			move();
			return true;
		}
		
		setMoveDirection(orderlistArrayList.get(record[2]));
		tryMove();
		if (imagine(maze)) {
			record[0]=record[1]=record[2]=record[3]=-1;
			orderlistArrayList.clear();
			move();
			return true;
		}
		return false;
	}

/*
 * 	set the move direction order
 */
	private void setOrder(){
		orderlistArrayList.add(getFace().leftHandSide());
		orderlistArrayList.add(getFace().straight());
		orderlistArrayList.add(getFace().rightHandSide());
	}

/*
 * 	check it is a junction or not
 */
	private int checkCross(char[][] maze){
		int i=0;
		setMoveDirection(getFace().leftHandSide());
		tryMove();
		if (imagine(maze)) {
			i++;
		}
		setMoveDirection(getFace().straight());
		tryMove();
		if (imagine(maze)) {
			i++;
		}
		setMoveDirection(getFace().rightHandSide());
		tryMove();
		if (imagine(maze)) {
			i++;
		}
		return i;
	}
	
	@Override
	public String getName() {
		return "RandomRobot";
	}

/*
 * because the random robot will backtrack, we can not check the walkPath to identify if the maze is correct or not 
 */
	public void checkRoad(int[] position, List<String> finish) throws Exception {
		return;
	}

}
