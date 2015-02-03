package maze;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class Maze {

/*	
 * 	the Maze class is used to load a maze file and store it in a 2d array	
 */
	private String mazestr="";
	private String temp;
	private char [] [] maze;
	private int [] origin = new int [2];
	private int [] destination = new int [2];
	private int width;
	private int tempWidth;
	private int height;
	
	public Maze(String file) {
		generateMaze(file);
	}
	
/*
 * 	generate the maze by the input file
 * 	firstly record the input file into a string by use read line
 * 	then calculate the length of each row and the height of the maze
 * 	then store the maze into a 2d array
 */
	@SuppressWarnings("deprecation")
	public final void generateMaze(String file) {
		
		try (DataInputStream dataIn = new DataInputStream(new FileInputStream(file))){
			temp=dataIn.readLine();
			width=temp.length();
			while (temp!=null){
/*
 * 				check the maze is rectangle or not				
 */
				tempWidth=temp.length();
				if (width!=tempWidth) {
					throw new Exception("irregular maze!!");
				}
				
				mazestr+=temp;
				temp=dataIn.readLine();
			}
		} catch (Exception e) {
			System.out.println("I/O Error: " + e);
		}
				
		this.height=mazestr.length()/width;
		origin[0]=1;
		origin[1]=1;
		destination[0]=height-2;
		destination[1]=width-2;

/*
 * 		store the information into the array		
 */
		maze = new char [height] [width];
		try {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					maze [i] [j] = mazestr.charAt(i*(width)+j);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index out: " +e);
		}
	}

/*
 * 	two method that could let the user set the origin and the destination
 */
	public void setOrigin(int[] origin) throws Exception {
		if (origin[1]>width-1||origin[0]>height-1||origin[1]<1||origin[0]<1) {
			throw new Exception("position wrong");
		}
		this.origin[0]=origin[0];
		this.origin[1]=origin[1];
	}
		
	public void setDestination(int[] destination) throws Exception {
		if (destination[1]>width-1||destination[0]>height-1||destination[1]<1||destination[0]<1) {
			throw new Exception("position wrong");
		}
		this.destination[0]=destination[0];
		this.destination[1]=destination[1];
	}
	
	
	public int[] getDestination() {
		return destination;
	}
	public int[] getOrigin() {
		return origin;
	}
	public char[][] getMaze() {
		return maze;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
}

