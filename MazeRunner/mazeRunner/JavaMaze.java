package mazeRunner;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * CSCI 132 Maze lab
 *
 * @author Hugh Jackovich
 */
public class JavaMaze {

   static char maze[][] = new char[12][12]; //Outlab rules state to always expect a 12x12
   private static Scanner file = null;
   static Scanner input = new Scanner(System.in);
   
    public static void main(String[] args) throws IOException
    {
    	//first performs reading args as a file
		String fileName = args[0];
		file = new Scanner(new File(fileName));
    	for(int row = 0; row < 12; row++)
    	{
    		String charset = file.nextLine();	//sets whole line to string
    		charset = charset.replaceAll("\\s", "");	//replaces white spaces of string with empty
    		System.out.println(charset);
    		for(int col = 0; col < 12; col++)
    		{
    			maze[row][col] = charset.charAt(col);	//sets current character at the position in 2d-array
    		}
    	}
    	
    	//this block of code handles finding the starting position, & from there, calling the recursive method
    	for(int row = 0; row < 12; row++)
    		for(int col = 0; col < 12; col++)
    		{
    			if(row == 0)
    			{
    				if(maze[row][col] == '.')
    				{
    					mazeRunner(row, col, row, col - 1);
    				}
    			}
    			else if(col == 0)
    			{
    				if(maze[row][col] == '.')
    				{
    					mazeRunner(row, col, row + 1, col);
    				}
    			}
    			else if(col == 11)
    			{
    				if(maze[row][col] == '.')
    				{
    					mazeRunner(row, col, row - 1, col);
    				}
    			}
    			else if(row == 11)
    			{
    				if(maze[row][col] == '.')
    				{
    					mazeRunner(row, col, row, col + 1);
    				}
    			}
    		}
    }

    public static void mazeRunner(int curRow,int curCol,int rightRow,int rightCol)
    {
    	//first block handles when x reaches the the finish
    	if(maze[curRow][curCol]=='F')
    	{
    		System.out.println("Maze Finished!");
    		maze[curRow][curCol] = 'x';
    		printMaze();
    		System.exit(0);
    	}
    	else
    		maze[curRow][curCol] = 'x';		//places x in the current row & current column
    	printMaze();
    	//users input decides course of action
    	System.out.println("Take Action!");
    	System.out.println("1. Make the Next Move");
    	System.out.println("2. Exit the Program");
    	int answer = input.nextInt();
    	maze[curRow][curCol] = '.';		//replaces x as . right after it is printed to only show x at current spot
    	if(answer == 1)
    	{
    		//this function firstly handles if a right-turn is possible
    		if((maze[rightRow][rightCol] == '.') || (maze[rightRow][rightCol] == 'F'))
			{
				//checks if facing East
				if(rightRow > curRow)
				{
					mazeRunner(rightRow, rightCol, rightRow, rightCol - 1);
				}
				//checks if facing West
				else if(rightRow < curRow)
				{
					mazeRunner(rightRow, rightCol, rightRow, rightCol + 1);
				}
				//checks if facing North
				else if(rightCol > curCol)
				{
					mazeRunner(rightRow, rightCol, ++rightRow, rightCol);
				}
				//checks if facing South
				else if(rightCol < curCol)
				{
					mazeRunner(rightRow, rightCol, rightRow - 1, rightCol);
				}
			}
    	//Next function block handles moving Straight, or force left turn if not possible
    		//checks if facing East
    	else if(rightRow > curRow)
		{
			if((maze[curRow][curCol + 1] == '.') || (maze[curRow][curCol + 1] == 'F'))
			{
				//engages straight move
				mazeRunner(curRow, ++curCol, rightRow, ++rightCol);
			}
			else
			{
				//performs left turn
				mazeRunner(curRow, curCol, --rightRow, ++rightCol);
			}
		}
    		//checks if facing West
		else if(rightRow < curRow)
		{
			if((maze[curRow][curCol - 1] == '.') || (maze[curRow][curCol - 1] == 'F'))
			{
				//engages straight move
				mazeRunner(curRow, --curCol, rightRow, --rightCol);
			}
			else
			{
				//performs left turn
				mazeRunner(curRow, curCol, ++rightRow, --rightCol);
			}
		}
    		//checks if facing North
		else if(rightCol > curCol)
		{
			if((maze[curRow - 1][curCol] == '.') || (maze[curRow - 1][curCol] == 'F'))
			{
				//engages straight move
				mazeRunner(--curRow, curCol, --rightRow, rightCol);
			}
			else
			{
				//performs left turn
				mazeRunner(curRow, curCol, --rightRow, --rightCol);
			}
		}
    		//checks if facing South
		else if(rightCol < curCol)
		{
			if((maze[curRow + 1][curCol] == '.') || (maze[curRow + 1][curCol] == 'F'))
			{
				//engages straight move
				mazeRunner(++curRow, curCol, ++rightRow, rightCol);
			}
			else
			{
				//performs left turn
				mazeRunner(curRow, curCol, ++rightRow, ++rightCol);
			} 
		}
    	}
    	//if exit is selected
    	else if(answer == 2)
    		System.exit(0);
    	else
    		mazeRunner(curRow,curCol,rightRow,rightCol);
    }
    
    //method that prints array out upon call
   public static void printMaze()
   {
       for(int r = 0; r < 12; r++)
       {
           for(int c = 0; c < 12; c++)
           {
        	   System.out.print(maze[r][c]+ " ");
           }
           System.out.println();
       }
       System.out.println();
	   }
   }

