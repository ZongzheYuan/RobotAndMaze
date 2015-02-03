package robot;

import direction.Direction;

public class LeftHandsideRobot extends AbstractRobot{
/*
 * 	the left hand robot
 * 	it will choose its left hand as its first move direction
 * 	then straight
 * 	then right
 * 	the last is back
 */
	public LeftHandsideRobot(int[] position,int[] destination, Direction face) {
		super(position, destination, face);
	}

	@Override
	public void strategy(char[][] maze) {
		setMoveDirection(getFace().leftHandSide());
		tryMove();
		if (imagine(maze)) {
			move();
			return;
		}
		setMoveDirection(getFace().straight());
		tryMove();
		
		if (imagine(maze)) {
			move();
			return;
		}
		setMoveDirection(getFace().rightHandSide());
		tryMove();
		
		if (imagine(maze)) {
			move();
			return;
		}
		
/*
 * 	if no way, then go back	
 */			
		setMoveDirection(getFace().back());
		tryMove();
		move();
		return;
		
	}

	@Override
	public String getName() {
		return "LeftHandsideRobot";
	}
	
}
