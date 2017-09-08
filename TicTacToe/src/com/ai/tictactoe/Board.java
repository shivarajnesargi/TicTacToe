package com.ai.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
*Author:Shivraj
*Date :Sep 8, 2017
*Time :11:55:12 AM
*Place:Brooklyn,Newyork
*
*/

public class Board {

	List<Cell> rootValues;
	Scanner scanner;
	List<Cell> emptyCells;
	CellState[][] board;
	
	public Board()
	{
		initializeBoard();
	}
	
	public void initializeBoard()
	{
		rootValues = new ArrayList<>();
		scanner = new Scanner(System.in);
		board = new CellState[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
	}
	
	public boolean isWinning(CellState player)
	{
		if(board[0][0]==player && board[1][1]==player && board[2][2]==player)
			return true;
		if(board[0][2]==player && board[1][1]==player && board[2][0]==player)
			return true;
		for(int i=0;i<Constants.BOARD_SIZE;i++)
		{
			for(int j=0;j<Constants.BOARD_SIZE;j++)
			{
				//Checking for columns
				if(board[0][i]==player && board[1][i]==player && board[2][i]==player)
					return true;
				//Checking for rows
				if(board[i][0]==player && board[i][1]==player && board[i][2]==player)
					return true;
			}
		}
		return false;
	}
	
	public boolean isRunning()
	{
		if(isWinning(CellState.COMPUTER)) return false;
		if(isWinning(CellState.USER)) return false;
		if(getEmptyCells().isEmpty()) return false;
		return true;
	}
	
	public List<Cell> getEmptyCells()
	{
		emptyCells = new ArrayList<>();
		for(int i=0;i<Constants.BOARD_SIZE;i++)
		{
			for(int j=0;j<Constants.BOARD_SIZE;j++)
			{
				if(board[i][j]==CellState.EMPTY)
				{
					Cell cell = new Cell(i,j);
					emptyCells.add(cell);
				}
			}
		}
		
		return emptyCells;
	}
	
	public void displayBoard()
	{
		for(int i=0;i<Constants.BOARD_SIZE;i++)
		{
			for(int j=0;j<Constants.BOARD_SIZE;j++)
			{
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void move(Cell cell,CellState player)
	{
		board[cell.getX()][cell.getY()] = player;
	}
	
	public void makeUserInput()
	{
		System.out.println("User's move:");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		Cell cell = new Cell(x,y);
		move(cell,CellState.USER);
	}
	
	public Cell getBestMove()
	{
		int max = Integer.MIN_VALUE;
		int index = Integer.MIN_VALUE;
		for(int i=0;i<rootValues.size();i++)
		{
			if(max<rootValues.get(i).getMinmaxValue())
			{
				max = rootValues.get(i).getMinmaxValue();
				index =i;
			}
		}
		
		return rootValues.get(index);
	}
	
	public void setupBoard()
	{
		for(int i=0;i<Constants.BOARD_SIZE;i++)
		{
			for(int j=0;j<Constants.BOARD_SIZE;j++)
			{
				board[i][j] = CellState.EMPTY;
			}
		}
	}
	
	public int returnMax(List<Integer> list)
	{
		int max = Integer.MIN_VALUE;
		int index = Integer.MIN_VALUE;
		for(int i=0;i<list.size();i++)
		{
			if(max<list.get(i)) 
				{
					max = list.get(i);
					index =i;
				}
		}
		return list.get(index);
	}
	
	public int returnMin(List<Integer> list)
	{
		int min = Integer.MAX_VALUE;
		int index = Integer.MIN_VALUE;
		for(int i=0;i<list.size();i++)
		{
			if(min>list.get(i)) 
				{
					min = list.get(i);
					index =i;
				}
		}
		return list.get(index);
	}

	public List<Cell> getRootValues() {
		return rootValues;
	}

	public void setRootValues(List<Cell> rootValues) {
		this.rootValues = rootValues;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public void setEmptyCells(List<Cell> emptyCells) {
		this.emptyCells = emptyCells;
	}
	
	
	//Minmax Algorithm
	
	public void callMinMax(int depth,CellState player)
	{
		rootValues.clear();
		minMax(depth,player);
	}

	private int minMax(int depth, CellState player) {
		
		if(isWinning(CellState.COMPUTER)) return +1;
		if(isWinning(CellState.USER)) return -1;
		List<Cell> availableCells = getEmptyCells();
		if(availableCells.isEmpty()) return 0;
		
		List<Integer> scores = new ArrayList<>();
		for(int i=0;i<availableCells.size();i++)
		{
			Cell point = availableCells.get(i);
			if(player == CellState.COMPUTER)
			{
				move(point,CellState.COMPUTER);
				int bestScore =minMax(depth+1,CellState.USER);
				scores.add(bestScore);
				if(depth==0)
				{
					point.setMinmaxValue(bestScore);
					rootValues.add(point);
				}
			}
			else if(player == CellState.USER)
			{
				move(point,CellState.USER);
				int bestScore =minMax(depth+1,CellState.COMPUTER);
				scores.add(bestScore);
			}
			board[point.getX()][point.getY()]=CellState.EMPTY;
		}
		if(player==CellState.COMPUTER) return returnMax(scores);
	    return returnMin(scores);
	}
	
}
