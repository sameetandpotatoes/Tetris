package com.sapra.tetris;
/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

//Modifications made by Dave Feinberg:
//
//Removed package and reference to AbstaactGrid<E>

//PACKAGE REMOVED BY DAVE FEINBERG
//package info.gridworld.grid;

import java.util.ArrayList;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class BoundedGrid<E> //extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }

    public int getNumRows()
    {
        return occupantArray.length;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }

    public boolean isValid(Location loc)
    {
        return(loc.getRow() >= 0 && loc.getRow() < this.getNumRows() && loc.getCol() >= 0 && loc.getCol() < this.getNumCols());
    }

    public ArrayList<Location> getOccupiedLocations()
    {

// CODE ME!!!
    		// HINT: if(get(loc) != null) WILL BE TRUE IF loc IS OCCUPIED.
        ArrayList<Location> theLocations = new ArrayList<Location>();
        
        for (int i = 0; i < getNumRows(); i++)
        {
        	for (int j = 0; j < getNumCols(); j++)
        	{
        		if (occupantArray[i][j] != null)
        		{
        			Location l = new Location(i,j);
        			theLocations.add(l);
        		}
        	}
        }
      
        // Look at all grid locations.
        
// If there's an object at this location, put it in the array.

        

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
         //CODE COMPLETE FOR YOU!

        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
