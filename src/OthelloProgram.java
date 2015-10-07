import java.util.Scanner;

public class OthelloProgram {
	private char[][] boardSize;
	private int numberOfChips = 0;
	char playerTurn = 'B';
	
	public OthelloProgram()
	{
		//Sets up an 8 x 8 board
		boardSize = new char[8][8];
		setupBoard();
	}
	
	private void setupBoard()
	{
		//First, it fills up all spaces of the board in asterisks
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++)
			{
				boardSize[x][y] = '*';
			}
		}

		//Then, puts the first 4 pieces in the middle of the board
		boardSize[3][3] = 'W';
		boardSize[3][4] = 'B';
		boardSize[4][3] = 'B';
		boardSize[4][4] = 'W';
	
		/*
		//Used to check all conversion functions
		//boardSize[4][3] = '1'; Place value here to trigger all conversion functions
		//Right conversion
		boardSize[5][3] = 'W';
		boardSize[6][3] = 'W';
		boardSize[7][3] = 'W';
		
		//Left conversion
		boardSize[3][3] = 'W';
		boardSize[2][3] = 'W';
		boardSize[1][3] = 'W';
		boardSize[0][3] = 'W';
		
		//Up conversion
		boardSize[4][2] = 'W';
		boardSize[4][1] = 'W';
		boardSize[4][0] = 'W';
		
		//Down conversion
		boardSize[4][4] = 'W';
		boardSize[4][5] = 'W';
		boardSize[4][6] = 'W';
		boardSize[4][7] = 'W';
		
		//Diagonal Up Right conversion
		boardSize[5][2] = 'W';
		boardSize[6][1] = 'W';
		boardSize[7][0] = 'W';
		
		//Diagonal Down Right conversion
		boardSize[5][4] = 'W';
		boardSize[6][5] = 'W';
		boardSize[7][6] = 'W';
		
		//Diagonal Left Up conversion
		boardSize[3][2] = 'W';
		boardSize[2][1] = 'W';
		boardSize[1][0] = 'W';
		
		//Diagonal Left Down conversion
		boardSize[3][4] = 'W';
		boardSize[2][5] = 'W';
		boardSize[1][6] = 'W';
		boardSize[0][7] = 'W';
		*/
	}
	
	private int rightConversion(int xValue, int yValue)		//Used to convert pieces in the right direction
	{
		boolean cont = false;
		int x = xValue;
		
		//Checks whether another piece of the current player is somewhere on the right
		while (cont == false && x < 8)
		{
			if (boardSize[x][yValue] == playerTurn)		//If there is another piece of the same color as the player, start converting
				cont = true;
			else		//Otherwise, don't convert at all
				cont = false;
			x++;
		}
		
		if (cont == true)
		{
			if (xValue < 8)
			{
			
				if (boardSize[xValue][yValue] != playerTurn && boardSize[xValue][yValue] != '*')	//If the current selected piece is not the same as the current player's piece and it's not an asterisk, then convert
					boardSize[xValue][yValue] = playerTurn;
				else
					//boardSize[xValue][yValue] = boardSize[xValue][yValue];
					return 0;		//Otherwise do nothing
				return rightConversion(xValue + 1, yValue);		//Recalls the method to go to the next piece to the right
			}
		}
		return 0;
	}
	
	private int leftConversion(int xValue, int yValue)
	{
		boolean cont = false;
		int x = xValue;
		
		//Checks whether another piece of the current player is somewhere on the left
		while (cont == false && x >= 0)
		{
			if (boardSize[x][yValue] == playerTurn)		//If there is another piece of the same color as the player, start converting
				cont = true;
			else		//Otherwise, don't convert at all
				cont = false;
			x--;
		}
		
		if (cont == true)
		{
			if (xValue >= 0)
			{
				if (boardSize[xValue][yValue] != playerTurn && boardSize[xValue][yValue] != '*')	//If the current selected piece is not the same as the current player's piece and it is not an asterisk then convert
					boardSize[xValue][yValue] = playerTurn;
				else		//Otherwise do nothing
					return 0;
				return leftConversion(xValue - 1, yValue);		//Recalls the method to go to the next piece to the left
			}
		}
		return 0;
	}
	
	private int downConversion(int xValue, int yValue)
	{
		boolean cont = false;
		int y = yValue;
		
		//Checks whether another piece of the current player is somewhere below
		while (cont == false && y < 8)
		{
			if (boardSize[xValue][y] == playerTurn)		//If there is another piece of the same color as the player, start converting
				cont = true;
			else		//Otherwise, don't convert at all
				cont = false;
			y++;
		}
		
		if (cont == true)
		{
			if (yValue < 8)
			{
				if (boardSize[xValue][yValue] != playerTurn && boardSize[xValue][yValue] != '*')		//If the current selected piece is not the same as the current player's piece and it is not an asterisk then convert 
					boardSize[xValue][yValue] = playerTurn;
				else		//Otherwise do nothing
					return 0;
				return downConversion(xValue, yValue + 1);		//Recalls the method to go to the next piece below
			}
		}
		return 0;
	}
	
	private int upConversion(int xValue, int yValue)
	{
		boolean cont = false;
		int y = yValue;
		
		//Checks whether another piece of the current player is somewhere above
		while (cont == false && y >= 0)
		{
			if (boardSize[xValue][y] == playerTurn)		//If there is another piece of the same color as the player, start converting
				cont = true;
			else		//Otherwise, don't convert at all
				cont = false;
			y--;
		}
		
		if (cont == true)
		{
			if (yValue >= 0)
			{
				if (boardSize[xValue][yValue] != playerTurn && boardSize[xValue][yValue] != '*')		//If the current selected piece is not the same as the current player's piece and it is not an asterisk then convert
					boardSize[xValue][yValue] = playerTurn;
				else		//Otherwise do nothing
					return 0;
				return upConversion(xValue, yValue - 1);		//Recalls the method to go to the next piece above
			}
		}
		return 0;
	}
	
	private int diagonalConversionUpRight(int xValue, int yValue)
	{
		boolean cont = false;
		int x = xValue;
		int y = yValue;
		
		//Checks whether another piece of the current player is somewhere to the upper right
		while (cont == false && (x < 8 && y >= 0))
		{
			if (boardSize[x][y] == playerTurn)		//If there is another piece of the same color as the player, start converting
				cont = true;
			else		//Otherwise, don't convert at all
				cont = false;
			x++;
			y--;
		}
		
		if (cont == true)
		{
			if (xValue < 8 && yValue >= 0)
			{
				if (boardSize[xValue][yValue] != playerTurn && boardSize[xValue][yValue] != '*')		//If the current selected piece is not the same as the current player's piece and it is not an asterisk then convert
					boardSize[xValue][yValue] = playerTurn;
				else		//Otherwise do nothing
					return 0;
				return diagonalConversionUpRight(xValue + 1, yValue - 1);		//Recalls the method to go to the next piece to the upper right
			}
		}
		return 0;
	}
	
	private int diagonalConversionDownRight(int xValue, int yValue)
	{
		boolean cont = false;
		int x = xValue;
		int y = yValue;
		
		//Checks whether another piece of the current player is somewhere to the lower right
		while (cont == false && (x < 8 && y < 8))
		{
			if (boardSize[x][y] == playerTurn)		//If there is another piece of the same color as the player, start converting
				cont = true;
			else	//Otherwise, don't convert at all
				cont = false;
			x++;
			y++;
		}
			
		if (cont == true)
		{
			if (xValue < 8 && yValue < 8)
			{
				if (boardSize[xValue][yValue] != playerTurn && boardSize[xValue][yValue] != '*')		//If the current selected piece is not the same as the current player's piece and it is not an asterisk, then convert
					boardSize[xValue][yValue] = playerTurn;
				else		//Otherwise do nothing
					return 0;
				return diagonalConversionDownRight(xValue + 1, yValue + 1);		//Recalls the method to go to the next piece to the lower right
			}
		}
		return 0;
	}
	
	private int diagonalConversionUpLeft(int xValue, int yValue)
	{
		boolean cont = false;
		int x = xValue;
		int y = yValue;
		
		//Checks whether another piece of the current player is somewhere to the upper left
		while (cont == false && (x >= 0 && y >= 0))
		{
			if (boardSize[x][y] == playerTurn)		//If there is another piece of the same color as the player, start converting
				cont = true;
			else	//Otherwise, don't convert at all
				cont = false;
			x--;
			y--;
		}
		
		if (cont == true)
		{
			if (xValue >= 0 && yValue >= 0)
			{
				if (boardSize[xValue][yValue] != playerTurn && boardSize[xValue][yValue] != '*')		//If the current selected piece is not the same as the current player's piece and it is not an asterisk, then convert
					boardSize[xValue][yValue] = playerTurn;
				else	//Otherwise do nothing
					return 0;
				return diagonalConversionUpLeft(xValue - 1, yValue - 1);		//Recalls the method to go to the next piece to the upper left
			}
		}
		return 0;
	}
	
	private int diagonalConversionDownLeft(int xValue, int yValue)
	{
		boolean cont = false;
		int x = xValue;
		int y = yValue;
		
		//Checks whether another piece of the current player is somewhere to the lower left
		while (cont == false && (x >= 0 && y < 8))
		{
			if (boardSize[x][y] == playerTurn)		//If there is another piece of the same color as the player, start converting
				cont = true;
			else	//Otherwise, don't convert at all
				cont = false;
			x--;
			y++;
		}
		
		if (cont == true)
		{
			if (xValue >= 0 && yValue < 8)
			{
				if (boardSize[xValue][yValue] != playerTurn && boardSize[xValue][yValue] != '*')		//If the current selected piece is not the same as the current player's piece and it is not an asterisk, then convert
					boardSize[xValue][yValue] = playerTurn;
				else	//Otherwise, do nothing
					return 0;
				return diagonalConversionDownLeft(xValue - 1, yValue + 1);		//Recalls the method to go to the next piece to the lower left
			}
		}
		return 0;
	}
	
	private void conversion(int xValue, int yValue)
	{
		//Calls each recursive conversion method
		rightConversion(xValue + 1, yValue);
		leftConversion(xValue - 1, yValue);
		downConversion(xValue, yValue + 1);
		upConversion(xValue, yValue - 1);
		diagonalConversionUpRight(xValue + 1, yValue - 1);
		diagonalConversionDownRight(xValue + 1, yValue + 1);
		diagonalConversionUpLeft(xValue - 1, yValue - 1);
		diagonalConversionDownLeft(xValue - 1, yValue + 1);
	}
	
	private boolean validMove(int mx, int my)
	{
		int x = mx;
		int y = my;
		
		//Checks if the current player has another piece somewhere to the right
		while (x < 8)
		{
			if (boardSize[x][y] == playerTurn)
				return true;
			x++;
		}
		
		
		
	}
	
	public int playerMove(int mx, int my)
	{	
		boolean cont = validMove(mx, my);

		if (cont == true)
		{
		if (boardSize[mx][my] == '*')
		{
			boardSize[mx][my] = playerTurn;
			conversion(mx, my);
			numberOfChips++;
		}
		else
		{
			System.out.println("Space is already taken!\n");
			return 0;
		}
		
		if (playerTurn == 'B')
			playerTurn = 'W';
		else
			playerTurn = 'B';
		
		
		if (numberOfChips != 64)
			return 0;
		else
			return 1;
		}
		else
		{
			System.out.println("Invalid move! Try again.");
			return 0;
		}
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
		Scanner input = new Scanner(System.in);
		int y = 0;
		int x = 0;
		int result;

		do
		{
			game.displayBoard();
			
			if (game.playerTurn == 'B')
				System.out.println("Black's turn");
			else
				System.out.println("White's turn");
			
			System.out.print("Enter column: ");
			x = input.nextInt();
			
			System.out.print("Enter row: ");
			y = input.nextInt();
			
			result = game.playerMove(x, y);
		} while (result == 0);
	}
}
