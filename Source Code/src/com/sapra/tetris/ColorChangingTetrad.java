package com.sapra.tetris;
import java.awt.Color;

public class ColorChangingTetrad extends Tetrad
{
	public ColorChangingTetrad(BoundedGrid<Block> grid)
	{
		super(grid);
	}
	public void changeColor()
	{
		int r = (int) (Math.random()* 255);
		int g = (int) (Math.random()* 255);
		int b = (int) (Math.random()* 255);
		Color c = new Color(r, g, b);
		Block[] blocks = super.getBlocks();
		for (int i = 0; i < blocks.length; i++)
		{
			blocks[i].setColor(c);
		}
	}
	
	public boolean translate(int deltaRow, int deltaCol)
	{
		changeColor();
		return super.translate(deltaRow, deltaCol);
	}
	
	public boolean rotate()
	{
		changeColor();
		return super.rotate();
	}
}
