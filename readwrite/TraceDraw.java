import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import javax.swing.JFrame; 
import javax.swing.JPanel;

public class TraceDraw extends JPanel { 
	
	private int m_xCoord; 
    private int m_yCoord; 
    private int m_x1Coord; 
    private int m_y1Coord;
	
	/** the width of the drawing area */
   public final static int FRAME_WIDTH = 1280;

   /** the height of the drawing area */
   public final static int FRAME_HEIGHT = 720;
   
   public int getXCoord() { 
 		return m_xCoord; 
 	} 
 
 
 	public void setXCoord(int xCoord) { 
 		m_xCoord = xCoord; 
 	} 
 
 
 	public int getYCoord() { 
 		return m_yCoord; 
 	} 
 
 
 	public void setYCoord(int yCoord) { 
 		m_yCoord = yCoord; 
 	} 
 
 
 	public int getX1Coord() { 
 		return m_x1Coord; 
 	} 
 
 
 	public void setX1Coord(int x1Coord) { 
 		m_x1Coord = x1Coord; 
 	} 
 
 
 	public int getY1Coord() { 
 		return m_y1Coord; 
 	} 
 
 
 	public void setY1Coord(int y1Coord) { 
 		m_y1Coord = y1Coord; 
 	}
	
	public TraceDraw() {
		PaintHandler handler = new PaintHandler();
		this.addMouseListener( handler );
		this.addMouseMotionListener(handler);
	}
	
	public void paintComponent(Graphics g) { 
        super.paintComponents(g); 
        Graphics2D g2d = (Graphics2D)g; 
        g2d.setStroke(new BasicStroke(3)); 
        g2d.setColor(Color.BLACK); 
        g2d.drawLine(getX1Coord(), getY1Coord(), getXCoord(), getYCoord()); 
    } 
	
	private class PaintHandler implements MouseListener, MouseMotionListener {

      /**
       * This method is defined in MouseMotionListener.
       * store drag coordinates and repaint
       */
      public void mouseDragged(MouseEvent event) {

        setXCoord(event.getX()); 
     	setYCoord(event.getY()); 
     	setX1Coord(event.getX()); 
     	setY1Coord(event.getY()); 
     	repaint();
      }    /* end method mouseDragged            */
      
	   /**
       * This method is defined in MouseListener.
       */
      public void mouseClicked(MouseEvent event) {

         setX1Coord(event.getX()); 
     	 setY1Coord(event.getY()); 
         
      }

      /**
       * This method is defined in MouseListener.
       */
      public void mousePressed(MouseEvent event) {
		
		setX1Coord(event.getX()); 
     	setY1Coord(event.getY());
      }
	  
	  public void mouseExited(MouseEvent e) {}
      public void mouseEntered(MouseEvent e) {} 
      public void mouseReleased(MouseEvent e) {} 
      public void mouseMoved(MouseEvent e) {}

      
	}    /* end private inner class PaintHandler */
	
	
}