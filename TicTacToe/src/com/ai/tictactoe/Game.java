package com.ai.tictactoe;

import java.util.Random;

/**
*Author:Shivraj
*Date :Sep 8, 2017
*Time :11:26:07 AM
*Place:Brooklyn,Newyork
*
*/

public class Game {
	
	private Random random;
	private Board board;

	public Game()
	{
		initializeGame();
		displayBoard();
		makeFirstMove();
		playGame();
		checkStatus();
	}
	
	public void initializeGame()
	{
		board = new Board();
		random = new Random();
		board.setupBoard();
	}
	
	public void displayBoard()
	{
		board.displayBoard();
	}
	
	public void makeFirstMove()
	{
		System.out.println("Who goes first? Type 1:for Computer, 2:for User");
		int choice = board.getScanner().nextInt();
		if(choice==1)
		{
			Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE),random.nextInt(Constants.BOARD_SIZE));
			board.move(cell,CellState.COMPUTER);
			displayBoard();
		}
	}
	
	public void playGame()
	{
		while(board.isRunning())
		{
			System.out.println("User's move");
			Cell nextCell = new Cell(board.getScanner().nextInt(),board.getScanner().nextInt());
			board.move(nextCell,CellState.USER);
			if(!board.isRunning()) break;
			board.callMinMax(0,CellState.COMPUTER);
			for(Cell cell:board.getRootValues())
			{
				System.out.println("Cell Values: "+cell+" minmaxValue:"+cell.getMinmaxValue());
			}
			board.move(board.getBestMove(),CellState.COMPUTER);
			displayBoard();
		}
	}
	
	public void checkStatus()
	{
		if(board.isWinning(CellState.COMPUTER))
		{
			System.out.println("Computer has won the game");
		}
		else if(board.isWinning(CellState.USER))
		{
			System.out.println("User has won the game");
		}
		else
		{
			System.out.println("It is a draw");
		}
				
	}
}
