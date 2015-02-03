package text;

import direction.Direction;
import position.Position;
import robot.AbstractRobot;
import robot.GreedRobot;
import robot.LeftHandsideRobot;
import robot.RandomRecordRobot;
import robot.RandomRobot;
import robot.RightHandsideRobot;
import manager.Manager;
import maze.Maze;

public class Begin {

/*
 * 	this is the text base programming Starting point
 */	
	public static void main(String [] args) throws Exception{

		int step = 0;
/*
 * 	here you can choose the maze file
 */
		Maze aMaze = new Maze("hak-10x10.txt");
		Manager aManager = new Manager();
		
		try {
			aManager.checkStart(aMaze.getOrigin(), aMaze.getMaze());
			aManager.checkStart(aMaze.getDestination(), aMaze.getMaze());
		} catch (Exception e) {
			System.out.println(e);
		}
/*
 * 	here you can change the robot
 */		
		AbstractRobot aRobot = new RandomRecordRobot(aMaze.getOrigin(),aMaze.getDestination(), Direction.South);
		Position aPosition = new Position(aMaze.getOrigin(), aMaze.getDestination(),aRobot.getPosition(),aRobot.getTryPosition());
		
			
/*
 * 	the loop that the robot is running
 */
		do{
			System.out.println("Robot type -> " + aRobot.getName());
			System.out.println("move number -> " + step);
			System.out.println();
			aRobot.strategy(aMaze.getMaze());
			if (aManager.checkValid(aPosition.getOriginPosition(), aPosition.getRobotPostion(), aMaze.getMaze())) {
				
				try {
					aRobot.checkRoad(aPosition.getRobotPostion(), aPosition.getFinishPosition());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				aRobot.changePath(aPosition.getOriginPosition());
				aPosition.move();
				aRobot.setFace(aRobot.getMoveDirection());
				step +=1;
			}
			else {
				aRobot.back(aPosition.getOriginPosition());
				System.out.println("the robot is cheating");
			}
			printMaze(aMaze,aPosition,aRobot);
		}while(!aManager.checkDestination(aPosition.getRobotPostion(), aPosition.getDestination()));
				
	}

/*
 * 	the print method
 */
	private static void printMaze(Maze aMaze, Position aPosition,AbstractRobot aRobot) {
		int [] position = new int [2];
		for (int i = 0; i < aMaze.getHeight(); i++) {
			for (int j = 0; j < aMaze.getWidth(); j++) {
				position[0]=i;
				position[1]=j;
				if (aRobot.getPosition()[0]==i&&aRobot.getPosition()[1]==j) {
					System.out.print('R');
				}
				else if (aPosition.getReachPosition().contains(""+position[0]+","+position[1])) {
					System.out.print('.');
				}
				else {
					System.out.print(aMaze.getMaze()[i][j]);
				}
			}
			System.out.println();
		}
		for (int i = 0; i < 50; i++) {
			System.out.print('-');
		}
		System.out.println("\u001b[2]");
		System.out.flush();
	}
		
}
