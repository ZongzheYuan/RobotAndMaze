package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import position.Position;
import direction.Direction;
import robot.AbstractRobot;
import robot.GreedRobot;
import robot.LeftHandsideRobot;
import robot.RandomRecordRobot;
import robot.RandomRobot;
import robot.RightHandsideRobot;
import manager.Manager;
import maze.Maze;



public class MazePanel extends JPanel implements ActionListener{
	
/*
 * 	the panel that perform the action of the robot and the maze
 */

	private static final long serialVersionUID = 1L;
	private String msg;
	private Timer timer;
	private Maze aMaze;
	private AbstractRobot aRobot;
	private Position aPosition;
	private Manager aManager;
	private int step;
	private int tempx1,tempy1,tempx2,tempy2;
	private Stroke stroke1,stroke2;
	private Font small;
	private String mazename;
	private List<Direction> orderDirections=new ArrayList<Direction>(4);
	private Random randomDirection=new Random();;
	private int [] x = new int [3];
	private int [] y = new int [3];
	
	public MazePanel(String mazeC,String robotC) {

/*
 * 	set the property of the Jpanel
 * 	set the step counter to zero
 * 	set the font size and font style
 * 	new a Maze object, a Robot object, a Position object and a Manager object
 * 	start the timer
 */
		setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        

        step = 0;
		mazename=mazeC;
		orderDirections.add(Direction.North);
		orderDirections.add(Direction.West);
		orderDirections.add(Direction.South);
		orderDirections.add(Direction.East);
		
        small = new Font("Helvetica", Font.BOLD, 16);
        this.getFontMetrics(small);
        
        aMaze = new Maze(mazeC);
        aManager = new Manager();
/*
 * origin and destination is valid or not
 */
        try {
			aManager.checkStart(aMaze.getOrigin(), aMaze.getMaze());
			aManager.checkStart(aMaze.getDestination(), aMaze.getMaze());
		} catch (Exception e) {
			System.out.println(e);
		}
        
        chooseRobot(robotC);
        aPosition = new Position(aMaze.getOrigin(), aMaze.getDestination(),aRobot.getTryPosition(),aRobot.getPosition());

        
        timer = new Timer(1, this);
        timer.start();
	}
	
/*
 * 	the action performer
 * 	for each time the robot do its strategy
 * 	the manager check the move of robot is valid or not
 * 	if it is valid then add the step counter and do next step
 * 	else prevent the robot from moving and set the position back and say to the user that the robot is cheating
 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		
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
				repaint();
			}
			else {
				aRobot.back(aPosition.getOriginPosition());
				System.out.println("the robot is cheating");
			}
			
			if (aManager.checkDestination(aRobot.getPosition(), aPosition.getDestination())) {
				timer.stop();
			}
		
	}

/*
 * 	overrides the paint method
 * 	print the step, the robot name and the maze name
 * 	print the maze, print a thin rectangle as a wall and print a white square as the path
 * 	print the robot as a colorful square
 * 	connect the line between each position that the robot reached as the walking path
 */
	public void paint(Graphics g){
		 super.paint(g);
		 Graphics2D g2d = (Graphics2D)g;
		 
		 msg = "The step is " + step;
         
         g2d.setColor(Color.black);
         g2d.setFont(small);
         g2d.drawString(msg, 10,15);
         
         msg =  "The robot is " + aRobot.getName();
         
         g2d.setColor(Color.black);
         g2d.setFont(small);
         g2d.drawString(msg, 210,15);
         
         msg =  "The maze is " + mazename;
         
         g2d.setColor(Color.black);
         g2d.setFont(small);
         g2d.drawString(msg, 10,40);

/*
 * 	print the maze and the robot
 */        
			g2d.setColor(Color.red);
			stroke1 =new BasicStroke(5.0f);
			stroke2=g2d.getStroke();
			g2d.setStroke(stroke1);
			
/*
 * 	print the line as the walk path
 * 	get the position from the reach list
 */
			for (int i = 0; i < aPosition.getReachPosition().size()-1; i++) {	
				tempy1=Integer.parseInt(aPosition.getReachPosition().get(i).substring(0,aPosition.getReachPosition().get(i).indexOf(',')));
				tempx1=Integer.parseInt(aPosition.getReachPosition().get(i).substring(aPosition.getReachPosition().get(i).indexOf(',')+1));
				tempy2=Integer.parseInt(aPosition.getReachPosition().get(i+1).substring(0,aPosition.getReachPosition().get(i+1).indexOf(',')));
				tempx2=Integer.parseInt(aPosition.getReachPosition().get(i+1).substring(aPosition.getReachPosition().get(i+1).indexOf(',')+1));
				g.drawLine(checkPosition(tempx1)+18,checkPosition(tempy1)+58, checkPosition(tempx2)+18, checkPosition(tempy2)+58);
			}
			g2d.setStroke(stroke2);
			
		 int [] position = new int [2];
			for (int i = 0; i < aMaze.getHeight(); i++) {
				for (int j = 0; j < aMaze.getWidth(); j++) {
					position[0]=i;
					position[1]=j;
					if (aRobot.getPosition()[0]==i&&aRobot.getPosition()[1]==j) {
						g2d.setColor(Color.blue);
						g2d.drawOval(checkPosition(j)+10, checkPosition(i)+50, checkThick(j)-10, checkThick(i)-10);
						g2d.fillOval(checkPosition(j)+10, checkPosition(i)+50, checkThick(j)-10, checkThick(i)-10);
						drawNose(aRobot);
						g2d.drawPolygon(x, y, 3);
						g2d.fillPolygon(x, y, 3);
					}
					else if (aMaze.getMaze()[i][j]==' ') {	
/*
 * 	draw nothing if it is a path
 */
					}
					else{
						g2d.setColor(Color.black);
						g2d.drawRect(checkPosition(j)+5, checkPosition(i)+45, checkThick(j), checkThick(i));
						g2d.fillRect(checkPosition(j)+5, checkPosition(i)+45, checkThick(j), checkThick(i));
					}
				}
			}
			
	}

/*
 * 	two method to automatically judge in maze each position thin as a wall or thick as a path
 */	
	private int checkPosition(int i){
		if (i%2==0) {
			return  i*15; 
		}
		else {
			return i*15-10;
		}
	}
	
	private int checkThick(int i){
		if (i%2==0) {
			return  5; 
		}
		else {
			return 25;
		}
	}

/*
 * 	draw the nose to represent the direction
 */
	private void drawNose(AbstractRobot robot){
		
		switch (robot.getFace()) {
		case North:
			x [0]=checkPosition(robot.getPosition()[1])+18;
			x [1]=checkPosition(robot.getPosition()[1])+13;
			x [2]=checkPosition(robot.getPosition()[1])+23;
			y [0]=checkPosition(robot.getPosition()[0])+46;
			y [1]=checkPosition(robot.getPosition()[0])+54;
			y [2]=checkPosition(robot.getPosition()[0])+54;
			break;
		case South:
			x [0]=checkPosition(robot.getPosition()[1])+18;
			x [1]=checkPosition(robot.getPosition()[1])+13;
			x [2]=checkPosition(robot.getPosition()[1])+23;
			y [0]=checkPosition(robot.getPosition()[0])+70;
			y [1]=checkPosition(robot.getPosition()[0])+62;
			y [2]=checkPosition(robot.getPosition()[0])+62;
			break;
		case East:
			x [0]=checkPosition(robot.getPosition()[1])+30;
			x [1]=checkPosition(robot.getPosition()[1])+22;
			x [2]=checkPosition(robot.getPosition()[1])+22;
			y [0]=checkPosition(robot.getPosition()[0])+58;
			y [1]=checkPosition(robot.getPosition()[0])+53;
			y [2]=checkPosition(robot.getPosition()[0])+63;
			break;
		case West:
			x [0]=checkPosition(robot.getPosition()[1])+5;
			x [1]=checkPosition(robot.getPosition()[1])+13;
			x [2]=checkPosition(robot.getPosition()[1])+13;
			y [0]=checkPosition(robot.getPosition()[0])+58;
			y [1]=checkPosition(robot.getPosition()[0])+53;
			y [2]=checkPosition(robot.getPosition()[0])+63;
			break;

		default:
			break;
		}
	}
	
/*
 * 	use switch case to choose the robot
 * 	set the initial direction by random
 */
	private void chooseRobot(String robotC) {
		switch (robotC) {
		case "LeftHandsideRobot":
			aRobot = new LeftHandsideRobot(aMaze.getOrigin(),aMaze.getDestination(), orderDirections.get(randomDirection.nextInt(4)));
			break;
		case "RightHandsideRobot":
			aRobot = new RightHandsideRobot(aMaze.getOrigin(),aMaze.getDestination(), orderDirections.get(randomDirection.nextInt(4)));
			break;
		case "RandomRobot":
			aRobot = new RandomRobot(aMaze.getOrigin(),aMaze.getDestination(), orderDirections.get(randomDirection.nextInt(4)));
			break;
		case "GreedRobot":
			aRobot = new GreedRobot(aMaze.getOrigin(),aMaze.getDestination(), orderDirections.get(randomDirection.nextInt(4)));
			break;
		case "RandomRecordRobot":
			aRobot = new RandomRecordRobot(aMaze.getOrigin(),aMaze.getDestination(), orderDirections.get(randomDirection.nextInt(4)));
			break;
		default:
			break;
		}
	}
}
