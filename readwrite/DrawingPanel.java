/**
 * @file    -DrawingPanel.java
 * @author  -Thomas M. Booth (864469)
 * 		    - Snippets of code adapted from work by
 *			  P.J. Deitel, H.M. Deitel and R.S. Laramee
 * @date    -11 Dec 2016
 * @see     -Deitel and Deitel, Fig. 11.35, page 432 (for adapted snippets).
 *
 *  \brief A java program to allow the user to draw lines and particle traces
 *	using MouseListeners.
 *
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

	/** The width of the point of the Particle Trace */
	 private final int OVAL_WIDTH = 4;

	 /** The height of the point of the Particle Trace */
     private final int OVAL_HEIGHT = 4;

	 /** The maximum number of drawable points. */
     private final int MAX_POINTS = 10000;

	 /** count number of points of the Particle Trace*/
     private int m_PointCount = 0;

	 /** A array of the all points of a Particle Trace drawing */
     private Point m_Points[] = new Point[MAX_POINTS];

	 /** A string indicating which draw tool is enabled */
     private String m_drawTool = "Line";

	 /** The start point of a drawn line */
     private Point m_lineStart = new Point(0, 0);

	 /** The end point of a drawn line */
     private Point m_lineEnd;

	 /** An arraylist of all drawn lines */
     private ArrayList<Line> m_arrayListLines = new ArrayList<Line>();

	 /** The colour of the line */
     private Color m_colour = Color.BLACK;

	 /**
	  * An array of colours of the trace where maximum number of drawable points
	  * is the limit.
	  */
     private Color[] m_colourArray = new Color[MAX_POINTS];

	 /** A text file containing drawn lines */
     private String m_lineTextFile;

	 /** A text file containing drawn particle traces */
     private String m_particleTextFile;

	 /**
	  * Method to return the ArrayList of lines drawn to screen.
      * @return the ArrayList of lines.
      */
     public ArrayList<Line> getLines(){
         return m_arrayListLines;
     }

     /**
	  * Method to retrieve number of points drawn on screen.
      * @return the number of points
      */
     public int getPointCount() {
         return m_PointCount;
     }


     /**
	  * Increments the point count.
      * @return TRUE on success
      */
     public boolean incrementPointCount() {
         m_PointCount++;
         return true;
     }


     /**
	  * Returns the array of points drawn on screen.
      * @return the current number of points
      */
     public Point[] getPoints() {
         return m_Points;
     }


     /**
	  * Method to retrieve the colour of a drawing
      * @return the current colour of drawing
      */
     public Color getDrawnColour() {
         return m_colour;
     }


     /**
	  * Method to retrieve the array of colours in a trace.
      * @return the array of colour of the point in trace.
      */
     public Color[] getColours(){
         return m_colourArray;
     }


     /**
	  * Method to set the point of trace at current point count in array.
      * @param point
      * @return TRUE on success
      */
     public boolean setTracePoint(Point point) {
         m_Points[getPointCount() ] = point;
         return true;
     }

	 /**
	  * Method to allow user to switch between line and
	  * particle trace.
      * @param mode
      */
     public void setDrawTool(String drawTool){
         m_drawTool = drawTool;
         DrawingHandler handler = new DrawingHandler();
         this.addMouseListener(handler);
         this.addMouseMotionListener(handler);
     }

	 /**
	  * Method to set the colour of a point in a particle trace.
      * @param the colour to be set at a certain point.
      */
     public void setPointColor(Color colour){
         m_colourArray[getPointCount()] = colour;
     }

	 /**
	  * Method to set the colour of a drawing.
      * @param the colour to be set.
      */
     public void setDrawColour(Color colour){
         m_colour = colour;
     }


     /**
	  * Method to set the point at the start of a drawn line.
      * @param the point at the start of the line.
      */
     public void setLineBegin(Point point){
         m_lineStart = point;
     }


     /**
	  * Method to set the point at the end of a drawn line.
      * @param the point at the start of the line.
      */
     public void setLineFinish(Point point){
         m_lineEnd = point;
     }


     /**
	  * Method to add drawn lines to the line ArrayList
      * @param Line to be added to the ArrayList.
      */
     public void addLines(Line line){
         m_arrayListLines.add(line);
     }

     /**
	  * Method to set the ArrayList of lines.
      * @param the ArrayList of lines.
      */
     public void setArrayListLines(ArrayList<Line> line) {
         m_arrayListLines = line;
     }

     /**
	  * Method to set the array of drawn points in a particle trace.
	  * @param the array of points.
	  */
     public void setAllTracePoints(Point[] point) {
         m_Points = point;
     }

	 /**
	  * Method to set the count of points drawn.
	  * @param the count of drawn points.
	  */
     public void setPointCount(int count) {
         m_PointCount = count;
     }

	 /**
	  * Method to set the colours of all points draw in a trace draw.
	  * @param the array of colours.
	  */
     public void setAllPointColor(Color[] arrayOfColours) {
         m_colourArray = arrayOfColours;
     }

	/**
    * TODO : COMPARING THE ACCOUNTS USERNAMES TO MAKE A TEXT FILE WITH THE TWO USERNAMES AS
	* THE TEXT FILE'S NAME.
    *
	* Constructor:
    * set up GUI by loading the file amd repainting on screen.
    */
	public DrawingPanel(Account sender, Account recipient) {

		// If first account username <= second account username
         if (sender.getUsername().compareTo(recipient.getUsername()) <= 0) {
             m_lineTextFile = sender.getUsername() + "_" + recipient.getUsername() + ".txt";
             m_particleTextFile = sender.getUsername() + "_" + recipient.getUsername() + ".txt";
         } else { // Else second account username > first account username
             m_lineTextFile = recipient.getUsername() + "_" + sender.getUsername() + ".txt";
             m_particleTextFile = recipient.getUsername() + "_" + sender.getUsername() + ".txt";
         }

		/**
		 * THE CONSTRUCTOR ALWAYS LOADS A TEXT FILE WHEN ITS CALLED, SO MAYBE WE COULD CREATE AN EMPTY TEXT FILE WHEN A FRIENDSHIP IS CREATED, SO THAT
		 * A TEXT FILE WILL ALWAYS BE FOUND?
		 */


		 try
		 {
			 String abs = System.getProperty("user.dir") + "/" + m_lineTextFile;
			 System.out.println(abs);
			 File m_file = new File(abs);
			 //m_file.getParentFile().mkdirs();
			 //System.out.println(m_file.getAbsolutePath());
			 if(m_file.createNewFile())
			 {
				 System.out.println("file created: " + m_file);
			 }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }

		//loadLineFile(m_lineTextFile); // line doesn't work, removed for now
		//repaint();

		loadParticleFile(m_particleTextFile);
        repaint();
	 }

	 /**
	  * Method to save the drawn line to the line file.
	  * This throws an IOException.
      * @param ArrayList of drawn lines from the window.
      */
     public void saveLine(ArrayList<Line> drawnLine) throws IOException {
         File inputFile = new File(m_lineTextFile);
         FileWriter w = new FileWriter(inputFile);
         for (int i = 0; i < drawnLine.size(); i++) {
             w.write(drawnLine.get(i).getStartX() + "," + drawnLine.get(i).getStartY() + "," + drawnLine.get(i).getEndX()
                     + "," + drawnLine.get(i).getEndY() + "," + drawnLine.get(i).getColour().getRed()
                     + "," + drawnLine.get(i).getColour().getGreen() + "," + drawnLine.get(i).getColour().getBlue()+"\n");
         }
         w.close();
     }


     /**
	  * Method to save all particle trace drawings.
	  * This throws an IOException.
      * @param array of drawn points on screen.
      * @param array of colours of these drawn points.
      */
     public void savePartical(Point[] trace, Color[] traceColour) throws IOException {
         File inputFile = new File(m_particleTextFile);
         FileWriter w = new FileWriter(inputFile);
         for (int i = 0; i < this.getPointCount(); i++) {
		 w.write(trace[i].x + "," + trace[i].y + "," + traceColour[i].getRed()
                     + "," + traceColour[i].getGreen() + "," + traceColour[i].getBlue()+"\n");
         }
         w.close();
     }

	 /**
      * Draw an oval in a OVAL_WIDTH-by-OVAL_HEIGHT bounding box
      * at specified x and y in a specified colour on window.
	  * This method is called from PaintHandler::mouseDragged().
	  *
	  * Draws a line between starting x,y and ending x,y in the
	  * specified colour on window. This is called from
	  * PaintHandler::mouseReleased().
      */
	 public void paintComponent(Graphics g) {

        /* clears drawing area */
         super.paintComponent(g);
 	      /* draw all points in array and all line */
         for (int i = 0; i < this.getPointCount(); i++ ) {
             g.setColor(getColours()[i]);
             g.fillOval(getPoints()[i].x, getPoints()[i].y, OVAL_WIDTH , OVAL_HEIGHT);
         }

		 for (int i = 0; i < m_arrayListLines.size(); i++){
             Line l = m_arrayListLines.get(i);
             g.setColor(m_arrayListLines.get(i).getColour());
             g.drawLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndX());
		 }
     }

	 /**
      * This is a private inner class designed to handle the drawing
	  * of lines and particle traces. It uses MouseListener and
	  * MouseMotionListener interfaces.
      */
     private class DrawingHandler implements MouseListener, MouseMotionListener {
		  /**
		  * Method to store dragged points and their colour, and
		  * repaints to screen.
		  * @param MouseEvent e. This method is defined in MouseMotionListener.
          */
		  public void mouseDragged(MouseEvent e) {

			if (m_drawTool.equals("Particle Trace")) {
                 if (getPointCount() < getPoints().length ) {
                     /* find and store point */
                     setTracePoint(e.getPoint());
 	        		/* find and store the colour of the point */
                     setPointColor(getDrawnColour());
 	        		/* write in txt file */
                     try{
                         savePartical(getPoints(), getColours());
                     } catch (IOException exception){
                         exception.printStackTrace();
                     }
 	        		/* increment number of points in array **/
                     incrementPointCount();
 	        		/* repaint JFrame */
                     repaint();
                 }
             }
         }

		/**
		 * Method to store the start of a drawn line.
		 * @param MouseEvent e. This method is defined in MouseMotionListener.
		 */
		public void mouseMoved(MouseEvent e) {

			/* reset the start point for drawing line */
             if(m_drawTool.equals("Line")){
                 setLineBegin(e.getPoint());
             }
         }

		 /**
		 * Method to store the start of a drawn line.
		 * @param MouseEvent e. This method is defined in MouseMotionListener.
		 */
		 public void mouseClicked(MouseEvent e) {
			/* As the mouse is clicked, the start point of the line is stored */
             if(m_drawTool.equals("Line")){
                 setLineBegin(e.getPoint());
             }
         }

		 /**
		 * Method to store the end of a drawn line, add the line to the ArrayList
		 * to save to file and repaint to screen.
		 * @param MouseEvent e. This method is defined in MouseMotionListener.
		 */
		 public void mouseReleased(MouseEvent e) {

			if(m_drawTool.equals("Line")) {
                  /* As the mouse is released, the end point of the line is stored */
                 setLineFinish(e.getPoint());
 	        	 /* Creates the line between two points */
                 Line newLine = new Line(m_lineStart.x, m_lineStart.y, m_lineEnd.x, m_lineEnd.y, m_colour);
 	        	 /* add it to the ArrayList of all lines */
                 addLines(newLine);
 	        	 /* save to a txt file */
                 try{
                     saveLine(getLines());
                 } catch (IOException exception){
                     exception.printStackTrace();
                 }
 	        	 /* repaint JFrame */
                 repaint();
 	        	 /* Resets point for next line */
                 setLineBegin(e.getPoint());
             }
         }

		/**
		 * Empty methods. These methods are defined in MouseMotionListeners
		 */
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
	 }

	 /**
	  * Method to load a line file, parse through its contents and repaint on screen.
	  * @param the filename of the line file.
	  */
	 public void loadLineFile(String lineFile) {
		 ArrayList<Line> allLine = new ArrayList<Line>();

		 File inputFile = new File(lineFile);
         Scanner in = null;
         try {
             in = new Scanner(inputFile);
         } catch (FileNotFoundException e) {
             System.out.println("Can't open " + lineFile);
             System.exit(0);
         }
		 while (in.hasNextLine()) {
             String line = in.nextLine();
             Scanner in2 = new Scanner(line);
             String[] data = in2.nextLine().split(",");
             int x = Integer.parseInt(data[0]);
             int y = Integer.parseInt(data[1]);
             int x1 = Integer.parseInt(data[2]);
             int y1 = Integer.parseInt(data[3]);
             int red = Integer.parseInt(data[4]);
             int green = Integer.parseInt(data[5]);
             int blue = Integer.parseInt(data[6]);
             Color colour = new Color(red, green, blue);
             Line newLine = new Line(x, y, x1, y1, colour);
             allLine.add(newLine);
             in2.close();
         }
		 in.close();
		 setArrayListLines(allLine);
	}

	/**
	  * Method to load a particle trace file, parse through its contents and repaint on screen.
	  * @param the filename of the particle trace file.
	  */
	public void loadParticleFile(String particleTraceFile) {
		 Point[] allPoint = new Point[MAX_POINTS];
         Color[] allColor = new Color[MAX_POINTS];

		 File inputFile = new File(particleTraceFile);
         Scanner in = null;
         int counter = 0;
         try {
             in = new Scanner(inputFile);
         } catch (FileNotFoundException e) {
             System.out.println("Can't open " + particleTraceFile);
             System.exit(0);
         }
         while (in.hasNextLine()) {
             String line = in.nextLine();
             Scanner s = new Scanner(line);
             String[] data = s.nextLine().split(",");
             int x = Integer.parseInt(data[0]);
             int y = Integer.parseInt(data[1]);
             int red = Integer.parseInt(data[2]);
             int green = Integer.parseInt(data[3]);
             int blue = Integer.parseInt(data[4]);
             Color newColour = new Color(red, green, blue);
             allPoint[counter] = new Point(x, y);
             allColor[counter] = newColour;
             counter++;
             s.close();
         }
         setAllTracePoints(allPoint);
         setAllPointColor(allColor);
         setPointCount(counter);
         in.close();
	}
}
