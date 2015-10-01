import java.util.Scanner;

public class OthelloProgram {
	private char[][] boardSize;
	private int numberOfChips = 0;
	private int turn = 0;
	
	public OthelloProgram()
	{
		boardSize = new char[8][8];
		setupBoard();
	}
	
	private void setupBoard()
	{
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++)
			{
				boardSize[x][y] = '*';
			}
		}
		boardSize[3][3] = 'W';
		boardSize[3][4] = 'B';
		boardSize[4][3] = 'B';
		boardSize[4][4] = 'W';
	}
	
	public void displayBoard()
	{
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++)
			{
				System.out.print(boardSize[x][y] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		OthelloProgram game = new OthelloProgram();
		game.displayBoard();
	}
}
