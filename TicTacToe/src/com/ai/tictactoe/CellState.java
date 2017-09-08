package com.ai.tictactoe;
/**
*Author:Shivraj
*Date :Sep 8, 2017
*Time :11:21:00 AM
*Place:Brooklyn,Newyork
*
*/

public enum CellState {

	COMPUTER("X"),USER("O"),EMPTY("-");
	
	private String text;
	
	private CellState(String text)
	{
		this.text=text;
	}
	
	public String toString()
	{
		return text;
	}
}
