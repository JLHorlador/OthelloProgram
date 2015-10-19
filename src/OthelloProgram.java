import java.util.Scanner;

public class OthelloProgram {
	static char[][] boardSize;
	int numberOfChips = 4;
	char playerTurn = 'B';
	
	public OthelloProgram()
	{
		//Sets up an 8 x 8 board
		boardSize = new char[8][8];
		setupBoard();
	}
	//Hi there John 
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
		boardSize[7][3] = 'B';
		
		//Left conversion
		boardSize[3][3] = 'W';
		boardSize[2][3] = 'W';
		boardSize[1][3] = 'W';
		boardSize[0][3] = 'B';
		
		//Up conversion
		boardSize[4][2] = 'W';
		boardSize[4][1] = 'W';
		boardSize[4][0] = 'B';
		
		//Down conversion
		boardSize[4][4] = 'W';
		boardSize[4][5] = 'W';
		boardSize[4][6] = 'W';
		boardSize[4][7] = 'B';
		
		//Diagonal Up Right conversion
		boardSize[5][2] = 'W';
		boardSize[6][1] = 'W';
		boardSize[7][0] = 'B';
		
		//Diagonal Down Right conversion
		boardSize[5][4] = 'W';
		boardSize[6][5] = 'W';
		boardSize[7][6] = 'B';
		
		//Diagonal Left Up conversion
		boardSize[3][2] = 'W';
		boardSize[2][1] = 'W';
		boardSize[1][0] = 'B';
		
		//Diagonal Left Down conversion
		boardSize[3][4] = 'W';
		boardSize[2][5] = 'W';
		boardSize[1][6] = 'W';
		boardSize[0][7] = 'B';
		*/
	}
	
	private int rightConversion(int xValue, int yValue)		//Used to convert pieces in the right direction
	{
		boolean cont = false;
		int x = xValue;
		
		//Checks whether another piece of the current player is somewhere on the right
		while (x < 8 && boardSize[x][yValue] != '*' && cont == false)
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
		while (x >= 0 && boardSize[x][yValue] != '*' && cont == false)
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
		while (y < 8 && boardSize[xValue][y] != '*' && cont == false)
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
		while (y >= 0 && boardSize[xValue][y] != '*' && cont == false)
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
		while ((x < 8 && y >= 0) && boardSize[x][y] != '*' && cont == false)
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
		while ((x < 8 && y < 8) && boardSize[x][y] != '*' && cont == false)
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
		while ((x >= 0 && y >= 0) && boardSize[x][y] != '*' && cont == false)
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
		while ((x >= 0 && y < 8) && boardSize[x][y] != '*' && cont == false)
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
	
	private boolean validMove(int mx, int my)		//Determines whether the player can place their chip into the targeted location
	{
		int startX = mx;
		int startY = my;
		int x = mx;
		int y = my;
		
		//Checks if the current player has another piece somewhere to the right
		if (x + 1 < 8 && boardSize[x + 1][y] != playerTurn)		//First checks if the chip to the right is one of the current player's chips
		{
			x++;	//Skips to the right of the chosen area, otherwise the validation will fail since the chosen spot is a *
			while (x < 8 && (boardSize[x][y] == 'W' || boardSize[x][y] == 'B'))		//Checks to make sure that the check remains inside the array and is either a white piece or left piece
			{
				if (boardSize[x][y] == playerTurn)
					return true;
				x++;
			} 
		}
		x = startX;		//Resets x to starting position
		
		//Checks if the current player has another piece somewhere to the left
		if (x - 1 >= 0 && boardSize[x - 1][y] != playerTurn)		//First checks if the chip to the left is one of the current player's chips
		{
			x--;	//Skips to the left of the chosen area
			while (x >= 0 && (boardSize[x][y] == 'W' || boardSize[x][y] == 'B'))	//Refer to comment on line 346
			{
				if (boardSize[x][y] == playerTurn)
					return true;
				x--;
			}
		}
		x = startX;		//Resets x to starting position
		
		//Checks if the current player has another piece somewhere below
		if (y + 1 < 8 && boardSize[x][y + 1] != playerTurn)		//First checks if the chip below is one of the current player's chips
		{
			y++;	//Skips to the location under the chosen area
			while (y < 8 && (boardSize[x][y] == 'W' || boardSize[x][y] == 'B'))		//Refer to comment on line 346
			{
				if (boardSize[x][y] == playerTurn)
					return true;
				y++;
			}
		}
		y = startY;		//Resets y to starting position
		
		//Checks if the current player has another piece somewhere above
		if (y - 1 >= 0 && boardSize[x][y - 1] != playerTurn)		//First checks if the chip above is one of the current player's chips
		{
			y--;	//Skips to the location above the chosen area
			while (y >= 0 && (boardSize[x][y] == 'W' || boardSize[x][y] == 'B'))	//Refer to comment on line 346
			{
				if (boardSize[x][y] == playerTurn)
					return true;
				y--;
			}
		}
		y = startY;
		
		//Checks if the current player has another piece somewhere to the upper right
		if ((x + 1 < 8 && y - 1 >= 0) && boardSize[x + 1][y - 1] != playerTurn)		//First checks if the chip to the upper right is one of the current player's chips
		{
			//Skips to the location to the upper right of the chosen area
			x++;	
			y--;	
			while ((x < 8 && y >= 0) && (boardSize[x][y] == 'W' || boardSize[x][y] == 'B'))		//Refer to comment on line 346
			{
				if (boardSize[x][y] != '*' && boardSize[x][y] == playerTurn)
					return true;
				x++;
				y--;
			}
		}
		x = startX;
		y = startY;
		
		//Checks if the current player has another piece somewhere to the lower right
		if ((x + 1 < 8 && y + 1 < 8) && boardSize[x + 1][y + 1] != playerTurn)		//First checks if the chip to the lower right is one of the current player's chips
		{
			//Skips to the location to the lower right of the chosen area
			x++;
			y++;
			while ((x < 8 && y < 8) && (boardSize[x][y] == 'W' || boardSize[x][y] == 'B'))		//Refer to comment on line 346
			{
				if (boardSize[x][y] == playerTurn)
					return true;
				x++;
				y++;
			}
		}
		x = startX;
		y = startY;
		
		//Checks if the current player has another piece somewhere to the upper left
		if ((x - 1 >= 0 && y - 1 >= 0) && boardSize[x - 1][y - 1] != playerTurn)		//First checks if the chip to the upper left is one of the current player's chips
		{
			//Skips to the location to the upper left of the chosen area
			x--;
			y--;
			while ((x >= 0 && y >= 0) && (boardSize[x][y] == 'W' || boardSize[x][y] == 'B'))	//Refer to comment on line 346
			{
				if (boardSize[x][y] == playerTurn)
					return true;
				x--;
				y--;
			}
		}
		x = startX;
		y = startY;
		
		//Checks if the current player has another piece somewhere to the lower left
		if ((x - 1 >= 0 && y + 1 < 8) && boardSize[x - 1][y + 1] != playerTurn)		//First checks if the chip to the lower left is one of the current player's chips
		{
			//Skips to the location to the lower left of the chosen area
			x--;
			y++;
			while ((x >= 0 && y < 8) && (boardSize[x][y] == 'W' || boardSize[x][y] == 'B'))		//Refer to comment on line 346
			{
				if (boardSize[x][y] == playerTurn)
					return true;
				x--;
				y++;
			}
		}
		x = startX;
		y = startY;
		
		return false;
	}
	
	public int playerMove(int mx, int my)
	{	
		boolean cont = validMove(mx, my);		//Makes sure whether the chosen position to place the current player's piece is a valid move

		if (cont == true)		//If it is a valid move, make player moves
		{
			if (boardSize[mx][my] == '*')		//Checks if the space is an empty space
			{
				boardSize[mx][my] = playerTurn;		//Assigns the current space as the current player's piece
				conversion(mx, my);		//Does necessary conversions
			}
			else		//If it is not an empty space, have the player choose another position
			{
				System.out.println("Space is already taken!\n");
				return 0;
			}
			
			numberOfChips++;	//Increases the number of chips on the board
		
			//Change the current player's turn
			if (playerTurn == 'B')
				playerTurn = 'W';
			else
				playerTurn = 'B';
		
			//Checks if all the spaces in the board are filled up 
			if (numberOfChips == 64)
				return 1;
			else		//If it is filled up, end the game
				return 0;
		}
		else		//If it is not a valid move, make the current player choose another location
		{
			System.out.println("Invalid move! Try again.");
			return 0;
		}
	}
	
	public void displayBoard()
	{
		System.out.print("   ");
		for (int i = 0; i < 8; i++)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		for (int y = 0; y < 8; y++)
		{
			System.out.print(y + "| ");
			for (int x = 0; x < 8; x++)
			{
				System.out.print(boardSize[x][y] + " ");	//Displays the items in the current row of the array
				if(x == 7)
				{
					System.out.println("|" + y);	//left column
				}
			}
//			System.out.println();		//Once it reaches the end of a row, move down and print the next row
		}
		System.out.print("   ");
		for (int i = 0; i < 8; i++)
		{
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		OthelloProgram game = new OthelloProgram();
		Scanner input = new Scanner(System.in);
		int y = 0;
		int x = 0;
		int totalWhitePieces = 0;
		int totalBlackPieces = 0;
		int result;

		do
		{
			game.displayBoard();
			
			if (game.playerTurn == 'B')		//Displays who's turn it is
				System.out.println("Black's turn");
			else
				System.out.println("White's turn");
			
			//System.out.println("Number of Chips: " + game.numberOfChips);		//DEBUG: Shows # of chips on board
			
			System.out.print("Enter column: ");
			x = input.nextInt();
			
			System.out.print("Enter row: ");
			y = input.nextInt();
			
			result = game.playerMove(x, y);
		} while (result == 0);
		
		if (result == 1)
		{
			for (int yVal = 0; yVal < 8; yVal++)
			{
				for (int xVal = 0; xVal < 8; xVal++)
				{
					if (boardSize[xVal][yVal] == 'B')
						totalBlackPieces++;
					else if (boardSize[xVal][yVal] == 'W')
						totalWhitePieces++;
				}
			}
			if (totalBlackPieces > totalWhitePieces)
				System.out.println("Black wins!");
			else if (totalWhitePieces > totalBlackPieces)
				System.out.println("White wins!");
			else
				System.out.println("It is a tie!");
		}
	}
}
