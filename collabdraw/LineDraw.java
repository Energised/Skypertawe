import java.awt.*;          /* for drawing          */
import java.awt.event.MouseEvent;  /* for mouse input      */
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JFrame;        /* for JFrame PaintPanel constructor */
import java.awt.geom.Line2D;

public class LineDraw extends JPanel {
	private Point m_lineStart;
    private Point m_lineEnd;
	 
	/** the width of the drawing area */
	public final static int FRAME_WIDTH = 1280;

	/** the height of the drawing area */
	public final static int FRAME_HEIGHT = 720;
	
	/**
    * Constructor:
    * set up GUI and register mouse event handler
    */
	public LineDraw() {
	   PaintHandler handler = new PaintHandler();
       this.addMouseListener( handler );
       this.addMouseMotionListener( handler );

	} /* end LineDraw constructor         */
   
   /**
    * Private inner class for event handling.
    * It uses and implements the MouseListener and MouseMotionListener
    * interfaces.
    */
   private class PaintHandler implements MouseListener, MouseMotionListener {
	   
	   /**
       * This method is defined in MouseMotionListener.
       * store drag coordinates and repaint
       */
		public void mouseDragged(MouseEvent event) {
		 
			m_lineEnd = event.getPoint();
			//repaint();
		}    /* end method mouseDragged            */

      /**
       * This method is defined in MouseMotionListener.
       */
		public void mouseMoved(MouseEvent event) {
				m_lineEnd = event.getPoint();
		}
	  
	   /**
       * This method is defined in MouseListener.
       */
		public void mousePressed(MouseEvent event) {
			m_lineStart = event.getPoint();
			
		}

		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mouseClicked(MouseEvent event) {}
		
		public void mouseReleased(MouseEvent event) {
			repaint();
		}
	  
		}    /* end private inner class PaintHandler */
   
	public void paintComponent(Graphics g) {
			super.paintComponents(g);
            if (m_lineStart != null) {
                g.setColor(Color.RED);
                g.drawLine(m_lineStart.x, m_lineStart.y, m_lineEnd.x, m_lineEnd.y);
			}
	}
}
   
   