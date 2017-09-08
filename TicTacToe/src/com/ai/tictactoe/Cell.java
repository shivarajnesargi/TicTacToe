package com.ai.tictactoe;
/**
*Author:Shivraj
*Date :Sep 7, 2017
*Time :3:57:20 PM
*Place:Brooklyn,Newyork
*
*/

public class Cell {
	
	int x;
	int y;
	int minmaxValue;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getMinmaxValue() {
		return minmaxValue;
	}
	public void setMinmaxValue(int minmaxValue) {
		this.minmaxValue = minmaxValue;
	}
	
	public Cell(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "Cell :+("+x+","+y+")";
	}

}
