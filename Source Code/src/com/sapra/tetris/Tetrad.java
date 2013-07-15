package com.sapra.tetris;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Tetrad
{
	private Block[] blocks;
	public Tetrad(BoundedGrid<Block> grid)
	{
		//INSERT CODE TO INITIALIZE blocks ARRAY
		blocks = new Block[4];
		for (int i = 0; i < blocks.length; i++)
			blocks[i] = new Block();
		getNextShape(grid);
	}

	private void getNextShape(BoundedGrid<Block>grid)
	{
		Color color = null;
		Location[] locs = new Location[4];
		
		int shape = 0;
		boolean next = false;
		//INSERT CODE TO CHOOSE RANDOM INTEGER FROM 0 TO 6
		Random r = new Random();
		shape = r.nextInt(7);
		if (shape == 0)
		{
			color = Color.RED;
			locs[0] = new Location(0, 5);
			locs[1] = new Location(0, 6);
			locs[2] = new Location(0, 3);
			locs[3] = new Location(0, 4);
		}   //INSERT CODE FOR THE REST OF THE COLORS AND SHAPES
		else if (shape == 1)
		{
			color = Color.GRAY;
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 3);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(0, 5);
		}
		else if (shape == 2)
		{
			color = Color.cyan;
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 5);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(1, 5);
		}
		else if (shape == 3)
		{
			color = Color.yellow;
			locs[0] = new Location(2, 4);
			locs[1] = new Location(1, 4);
			locs[2] = new Location(0, 4);
			locs[3] = new Location(2, 5);
		}
		else if (shape == 4)
		{
			color = Color.MAGENTA;
			locs[0] = new Location(2, 5);
			locs[1] = new Location(1, 5);
			locs[2] = new Location(0, 5);
			locs[3] = new Location(2, 4);
		}
		else if (shape == 5)
		{
			color = Color.blue;
			locs[0] = new Location(1, 4);
			locs[1] = new Location(0, 5);
			locs[2] = new Location(1, 3);
			locs[3] = new Location(0, 4);
		}
		else if (shape == 6)
		{
			color = Color.green;
			locs[0] = new Location(1, 4);
			locs[1] = new Location(0, 4);
			locs[2] = new Location(0, 3);
			locs[3] = new Location(1, 5);
		}
		
		//Checks game over
		ArrayList<Location> loc = grid.getOccupiedLocations();
		for (Location l : loc)
		{
			if (l.equals(locs[0])) System.exit(1000);
			else if (l.equals(locs[1])) System.exit(1000);
			else if (l.equals(locs[2])) System.exit(1000);
			else if (l.equals(locs[3])) System.exit(1000);
		}
		
		for (Block b : blocks) b.setColor(color);
		addToLocations(grid, locs);
	}
	//precondition:  blocks are not in any grid;
	//               locs.length = 4.
	//postcondition: The locations of blocks match locs,
	//               and blocks have been put in the grid.
	private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
	{
		for(int i= 0; i < locs.length; i++)
        {
			blocks[i].putSelfInGrid(grid, locs[i]);
        }
	}

	//precondition:  Blocks are in the grid.
	//postcondition: Returns old locations of blocks;
	//               blocks have been removed from grid.
	private Location[] removeBlocks()
	{
		Location[] locs = new Location[blocks.length];
		for (int i = 0; i < blocks.length; i++)
		{
			locs[i] = blocks[i].getLocation();
			blocks[i].removeSelfFromGrid();
			
		}
		return locs;
	}

	//postcondition: Returns true if each of locs is
	//               valid (on the board) AND empty in
	//               grid; false otherwise.
	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
	{
		ArrayList<Location> location = grid.getOccupiedLocations();
		boolean empty = true;
		for (int i = 0; i < locs.length; i++)
		{
			for (Location loc : location)
			{
				if (loc.equals(locs[i])) empty = false;
			}
			if (!grid.isValid(locs[i]) || !empty) return false;
		}
		return true;
	}

	//postcondition: Attempts to move this tetrad deltaRow
	//               rows down and deltaCol columns to the
	//               right, if those positions are valid
	//               and empty; returns true if successful
	//               and false otherwise.
	public boolean translate(int deltaRow, int deltaCol)
	{
		BoundedGrid<Block> grid;
		
		grid = blocks[0].getGrid();
		
		Location[] oldLocs = removeBlocks();
		Location[] newLocs = new Location[blocks.length];
		
		for (int i = 0; i < newLocs.length; i++)
		{
			newLocs[i] = new Location(oldLocs[i].getRow() + deltaRow, oldLocs[i].getCol() + deltaCol);
		}
		
		if (areEmpty(grid, newLocs))
		{
			addToLocations(grid, newLocs);
			return true;
		}
		else
		{
			addToLocations(grid, oldLocs);
			return false;
		}

	}

	//postcondition: Attempts to rotate this tetrad
	//               clockwise by 90 degrees about its
	//               center, if the necessary positions
	//               are empty; returns true if successful
	//               and false otherwise.
	public boolean rotate()
	{
		BoundedGrid<Block> grid;
		grid = blocks[0].getGrid();

		Location[] oldLocs = removeBlocks();
		Location[] newLocs = new Location[blocks.length];
		
		int row = oldLocs[0].getRow();
		int col = oldLocs[0].getCol();
		for (int i = 0; i < newLocs.length; i++)
		{
			newLocs[i] = new Location(row - col + oldLocs[i].getCol(), row + col - oldLocs[i].getRow());
		}
		if (areEmpty(grid, newLocs))
		{
			addToLocations(grid, newLocs);
			return true;
		}
		else
		{
			addToLocations(grid, oldLocs);
			return false;
		}
	}
	public Block[] getBlocks()
	{
		return blocks;
	}
}