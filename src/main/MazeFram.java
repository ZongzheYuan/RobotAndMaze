package main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MazeFram extends JFrame{
	
/*
 * 	the action frame which performed the maze and the action of the robot
 * 	use scroll pane to make the frame scrollable
 */

	private static final long serialVersionUID = 1L;

	public MazeFram(String file,String robot) {
		JPanel mazeJPanel = new MazePanel(file,robot);
		mazeJPanel.setPreferredSize(new Dimension(600, 1000));
		JScrollPane sp=new JScrollPane(mazeJPanel);
		add(sp);
		setVisible(true);
		setSize(700, 600);
		setTitle("Maze!!");
		setLocationRelativeTo(null);
        setResizable(true);
	}
	
}
