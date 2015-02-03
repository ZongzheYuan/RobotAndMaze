package main;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Launch extends JFrame implements  ActionListener, ItemListener{

/*
 * 	the running class. i use GUI to present the movement of the robot in the maze
 * 	the Launch class extends JFrame class in swing and use two Listener interface
 */	

	private static final long serialVersionUID = 1L;
	
/*
 * 	declare some swing components and some useful variable
 * 	File variable dFile is used to get the current directory.
 * 	Int variable flag is used to get the return value in filechooser
 * 	use container to layout the Frame
 * 	declare some checkbox component and a button group to make sure the user could only choose one robot at each time
*/
	private JButton open;
	private JPanel chooseMaze, chooseRobot, innerPanel;
	private File mazeFile=null;
	private File dFile;
	private JFileChooser mazeFileChooser;
	private Container container;
	private JLabel robot;
	private JCheckBox a1,a2,a3,a4,a5;
	private ButtonGroup bgButtonGroup;
	private String robotnameString;
	private int flag;

	
	
	public Launch() {
/*
 * 	set the layout style of the container
 */
		container=getContentPane();
		container.setLayout(new FlowLayout());

/*
 * 	assign the Jpanel
 * 	add some tutorial words in the label
 */
		chooseRobot = new JPanel();
		robot = new JLabel("Choose a robot");
		chooseRobot.setLayout(new GridLayout(2,1));
		chooseRobot.add(robot);

/*
 * 	set the default robot
 */
		robotnameString="RandomRobot";

/*
 * 	assign checkbox components
 * 	give each checkbox an ItemListener
 * 	group the checkbox
 * 	add them into innerPanel
 */		
		a1=new JCheckBox("LeftHandsideRobot");
		a1.addItemListener(this);
		a2=new JCheckBox("RightHandsideRobot");
		a2.addItemListener(this);
		a3=new JCheckBox("RandomRobot");
		a3.addItemListener(this);
		a4=new JCheckBox("GreedRobot");
		a4.addItemListener(this);
		a5=new JCheckBox("RandomRecordRobot");
		a5.addItemListener(this);
		
		innerPanel =new JPanel();
		innerPanel.setLayout(new GridLayout(3,2));
		innerPanel.add(a1);
		innerPanel.add(a2);
		innerPanel.add(a3);
		innerPanel.add(a4);
		innerPanel.add(a5);
		
		bgButtonGroup = new ButtonGroup();
		bgButtonGroup.add(a1);
		bgButtonGroup.add(a2);
		bgButtonGroup.add(a3);
		bgButtonGroup.add(a4);
		bgButtonGroup.add(a5);
		
		chooseRobot.add(innerPanel);
		container.add(chooseRobot);

/*
 * 	assign the filechooser
 * 	set the open direction as the current direction
 * 	add the open button an Actionlistener
 */
		open = new JButton("Choose Maze File");
		chooseMaze = new JPanel();
		mazeFileChooser = new JFileChooser();
		dFile=new File("");

		try {
			mazeFileChooser.setCurrentDirectory(dFile.getCanonicalFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

		open.addActionListener(this);
		chooseMaze.add(open);
		container.add(chooseMaze);

/*
 * 	set the property of the main frame
 */						
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,200);
		setTitle("Maze and Robot!!");
		setLocationRelativeTo(null);
        setResizable(true);
	}

/*
 * 	an assistant method to get the path of the chosen file 
 */
	private void openfile(){
		mazeFileChooser.setDialogTitle("Open the maze file");
		try {
			flag=mazeFileChooser.showOpenDialog(this);
		} catch (HeadlessException head) {
			System.out.println("Open File Dialog ERROR!");
		}
		if (flag==JFileChooser.APPROVE_OPTION) {
			mazeFile=mazeFileChooser.getSelectedFile();
		}
		
	}
	
/*
 * 	complement two interface method
 * 	in actionPerformed method, open the file chooser and choose file
 * 	in itemStateChanged method, the robot name variable will be assigned when the user choose a robot
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==open){   
            openfile();
            try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
            if (mazeFile!=null) {
            	new MazeFram(mazeFile.getAbsolutePath(),robotnameString);
			}
		}
		
			
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource()==a1&&a1.isSelected()) {
			robotnameString=a1.getLabel();
		}
		if (e.getSource()==a2&&a2.isSelected()) {
			robotnameString=a2.getLabel();
		}
		if (e.getSource()==a3&&a3.isSelected()) {
			robotnameString=a3.getLabel();
		}
		if (e.getSource()==a4&&a4.isSelected()) {
			robotnameString=a4.getLabel();
		}
		if (e.getSource()==a5&&a5.isSelected()) {
			robotnameString=a5.getLabel();
		}
	}

	public static void main(String[] args) {
		new Launch();
	}
}
