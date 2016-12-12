/**
 * @file    -CollabDrawGUI.java
 * @author  -Thomas M. Booth (864469)
 * @date    -11 Dec 2016
 *
 *  \brief A simple GUI which allows a user to
 * draw either lines or particle traces onto a JPanel.
 *
 */
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollabDrawGUI extends GUI {

	/* The width (and height) of the colour buttons */
    private final static int COLOUR_WIDTH = 25;
	/* The x coordinate of each colour button */
	private final static int COLOUR_X_COORD = 15;
	/* The respective y coordinates of each colour button */
	private final static int BLACK_Y_COORD = 10;
	private final static int BLUE_Y_COORD = 40;
	private final static int RED_Y_COORD = 70;
	private final static int GREEN_Y_COORD = 100;
	private final static int YELLOW_Y_COORD = 130;
	/* The width and height of the draw buttons.*/
    private final static int DRAW_BTN_WIDTH = 250;
	private final static int DRAW_BTN_HEIGHT = 50;
	/* The x coordinate for both draw buttons */
	private final static int BUTTON_X_COORD = 50;
	/* The respective y coordinates for each draw button */
	private final static int LINE_Y_COORD = 10;
	private final static int PARTICLE_Y_COORD = 70;
	/* The width and height of the message */
    private final static int MESSAGE_WIDTH = 300;
	private final static int MESSAGE_HEIGHT = 25;
	/* The height and width of the draw frame */
    private final static int FRAME_WIDTH = 550;
	private final static int FRAME_HEIGHT = 350;

	/**
    * Constructor:
    * inherits methods and attributes from GUI and loads elements
	* onto window through loadElements().
    */
	public CollabDrawGUI(Account a1, Account a2) throws Exception {
		super();
		loadElements();
	}

	/**
	 * Method which loads the drawing panel (JPanel), colour buttons
	 * and other components onto screen.
	 */
	private void loadElements() {
        DrawingPanel drawingPanel = new DrawingPanel(/**m_firstAccount, m_secondAccount*/);
        drawingPanel.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		drawingPanel.setBackground(Color.WHITE);
        drawingPanel.setLayout(null);

        JLabel label = new JLabel("Draw below!");
        /* Create two button to switch the mode */
        JButton lineBtn = new JButton("Line Element");
        JButton particleBtn = new JButton("Particle Trace");
	    /* Creation of colour buttons */
        JButton blueBtn = new JButton();
        JButton blackBtn = new JButton();
        JButton redBtn = new JButton();
        JButton greenBtn = new JButton();
		JButton yellowBtn = new JButton();
		/* Sets position and size of each button and label */
        label.setBounds(325, 10, MESSAGE_WIDTH, MESSAGE_HEIGHT);
        lineBtn.setBounds(BUTTON_X_COORD, LINE_Y_COORD, DRAW_BTN_WIDTH, DRAW_BTN_HEIGHT);
        particleBtn.setBounds(BUTTON_X_COORD, PARTICLE_Y_COORD, DRAW_BTN_WIDTH, DRAW_BTN_HEIGHT);
        blueBtn.setBounds(COLOUR_X_COORD, BLUE_Y_COORD, COLOUR_WIDTH, COLOUR_WIDTH);
        blackBtn.setBounds(COLOUR_X_COORD, BLACK_Y_COORD, COLOUR_WIDTH, COLOUR_WIDTH);
        redBtn.setBounds(COLOUR_X_COORD, RED_Y_COORD, COLOUR_WIDTH, COLOUR_WIDTH);
        greenBtn.setBounds(COLOUR_X_COORD, GREEN_Y_COORD, COLOUR_WIDTH, COLOUR_WIDTH);
		yellowBtn.setBounds(COLOUR_X_COORD, YELLOW_Y_COORD, COLOUR_WIDTH, COLOUR_WIDTH);
		/* Sets each colour button's background to its respective colour */
        blueBtn.setBackground(Color.BLUE);
        blackBtn.setBackground(Color.BLACK);
        redBtn.setBackground(Color.RED);
        greenBtn.setBackground(Color.GREEN);
		yellowBtn.setBackground(Color.YELLOW);

		/**
		 * ActionListener for the line button.
		 * sets the draw tool on DrawingPanel to a line so that
		 * a line can be drawn after clicking this button.
		 */
        lineBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                drawingPanel.setDrawTool("Line");
            }
        });

		/**
		 * ActionListener for the particle trace button.
		 * sets the draw tool on DrawingPanel to a particle trace so that
		 * a line can be drawn after clicking this button.
		 */
        particleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                drawingPanel.setDrawTool("Particle Trace");
            }
        });

		/**
		 * ActionListener for the blue button.
		 * sets the colour to blue.
		 */
        blueBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                drawingPanel.setDrawColour(Color.BLUE);
            }
        });

		/**
		 * ActionListener for the black button.
		 * sets the colour to black.
		 */
        blackBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                drawingPanel.setDrawColour(Color.BLACK);
            }
        });

		/**
		 * ActionListener for the red button.
		 * sets the colour to red.
		 */
        redBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                drawingPanel.setDrawColour(Color.RED);
            }
        });

		/**
		 * ActionListener for the green button.
		 * sets the colour to blue.
		 */
        greenBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                drawPanel.setDrawColour(Color.GREEN);
            }
        });

		/**
		 * ActionListener for the yellow button.
		 * sets the colour to yellow.
		 */
		yellowBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                drawingPanel.setDrawColour(Color.YELLOW);
            }
        });

		/**
		 * Adds the label and buttons onto the window and sets the
		 * window visible.
		 */
		drawingPanel.add(label);
        drawingPanel.add(lineBtn);
        drawingPanel.add(particleBtn);
        drawingPanel.add(blueBtn);
        drawingPanel.add(blackBtn);
        drawingPanel.add(redBtn);
        drawingPanel.add(greenBtn);
		drawingPanel.add(yellowBtn);
        this.add(drawingPanel);
        this.setVisible(true);

    }

	public static void main(String[] args) throws Exception {
		CollabDrawGUI me = new CollabDrawGUI();
	}
}
