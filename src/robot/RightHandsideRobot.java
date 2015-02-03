package robot;

import direction.Direction;

public class RightHandsideRobot extends AbstractRobot{

/*
 * 	the right hand robot
 * 	it will choose its right hand as its first move direction
 * 	then straight
 * 	then left
 * 	the last is back
 */
	public RightHandsideRobot(int[] position,int[] destination, Direction face) {
		super(position, destination, face);
	}

	@Override
	public void strategy(char[][] maze) {
		
		setMoveDirection(getFace().rightHandSide());
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
		setMoveDirection(getFace().leftHandSide());
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
		return "RightHandsideRobot";
	}

}
