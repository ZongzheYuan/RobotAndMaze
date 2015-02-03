package robot;

import java.util.List;

import direction.Direction;

public abstract class AbstractRobot {

/*
 * 	the abstract robot class
 * 	all the other robot extends this class
 * 	some method is protected that could only be used in the children class when in the different package
 */
	private int [] position;
	private int [] destination;
	private int [] tryPosition;
	private Direction face;
	private Direction moveDirection;
	
	public AbstractRobot(int[] position,int[] destination, Direction face) {
		this.destination=destination;
		setPosition(position);
		this.tryPosition=position.clone();
		setFace(face);
	}
	
	protected final void setMoveDirection(Direction moveDirection) {
		this.moveDirection = moveDirection;
	}

	public final void setFace(Direction face) {
		this.face = face;
	}

	private void setPosition(int[] position) {
		this.position = position.clone();
	}

/*
 * 	let the robot move
 */
	protected void move() {
		position[0]=tryPosition[0];
		position[1]=tryPosition[1];
	}
	
/*
 * 	set the robot back
 */
	public void back(int[] origin) {
		position[0]=origin[0];
		position[1]=origin[1];
		tryPosition=origin.clone();
	}

/*
 * 	the robot pretend to move
 */
	protected void tryMove() {
		switch (moveDirection) {
		case North:
			tryPosition[0]=position[0]-2;
			tryPosition[1]=position[1];
			break;
		case East:
			tryPosition[0]=position[0];
			tryPosition[1]=position[1]+2;
			break;
		case South:
			tryPosition[0]=position[0]+2;
			tryPosition[1]=position[1];
			break;
		case West:
			tryPosition[0]=position[0];
			tryPosition[1]=position[1]-2;
			break;
		default:
			break;
		}

	}

/*	
 * 	the robot itself could check there is a wall or not
 * 	but no walk path record
 */	
	protected boolean imagine (char [] [] maze) {
		int i = (tryPosition[0] + position [0])/2;
		int j = (tryPosition[1] + position [1])/2;

		if (maze[i][j]!=' ') {
			return false;
		}
		else {
			return true;
		}
	}
	
/*
 * 	three method that is facility for its children class
 */
	public void changePath(int[] originPosition){}
	public abstract String getName();
	public abstract void strategy(char [] [] maze);
	
	public Direction getFace() {
		return face;
	}
	public Direction getMoveDirection() {
		return moveDirection;
	}
	public int[] getPosition() {
		return position;
	}
	public int[] getTryPosition() {
		return tryPosition;
	}
	public int[] getDestination() {
		return destination;
	}
	
/*
 * 	check the maze
 */
	public void checkRoad(int[] position, List<String> finish) throws Exception {
		if (finish.contains(""+position[0]+","+position[1])) {
			throw new Exception("there is an island!!");
		}
	}
	
}
