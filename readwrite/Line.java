/**
 * @file    -Line.java
 * @author  -Thomas M. Booth (864469)
 * @date    -11 Dec 2016
 *
 * \brief A simple class to represent a 2D Line object.
 *
 */
import java.awt.Color; 

public class Line { 
	 /* Storing the lines start and end (x,y) coordinates. */
	 private int m_startX; 
     private int m_endX; 
     private int m_startY; 
     private int m_endY; 
	 /* Storing the lines colour */
     private Color m_colour; 
 
	 /**
      * Constructor:
      * Creates a new Line object 
	  * @param x - the x coordinate at the start of the line
	  * @param y - the y coordinate at the start of the line
	  * @param x1 - the x coordinate at the end of the line
	  * @param y1 - the y coordinate at the end of the line
	  * @param colour - the colour of the line
      */
     public Line(int x, int y, int x1, int y1, Color colour) { 
         m_startX = x; 
         m_startY = y; 
         m_endX = x1; 
         m_endY = y1; 
         m_colour = colour; 
     } 
 
	 /** 
	  * Method to retrieve the starting X coordinate of the line.
	  * @return an int representing the x coordinate.
	  */
     public int getStartX() { 
         return m_startX; 
     } 
 
	 /** 
	  * Method to retrieve the starting Y coordinate of the line.
	  * @return an int representing the y coordinate.
	  */
     public int getStartY() { 
         return m_startY; 
     } 
 
	 /** 
	  * Method to retrieve the finishing X coordinate of the line.
	  * @return an int representing the x coordinate.
	  */
     public int getEndX() { 
         return m_endX; 
     } 
 
	 /** 
	  * Method to retrieve the finishing Y coordinate of the line.
	  * @return an int representing the y coordinate.
	  */
     public int getEndY() { 
         return m_endY; 
     } 
 
	 /** 
	  * Method to retrieve the colour of the line
	  * @return a colour object representing the colour.
	  */
     public Color getColour() { 
         return m_colour; 
     } 
 } 
