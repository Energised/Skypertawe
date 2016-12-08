/**
 * @file    -CollabDrawGUI.java
 * @author  -Thomas M Booth
 * @date    -
 * @see     -
 *
 *  \brief A simple Java Swing Example that demonstrates
 * mouse input
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CollabDrawGUI2 extends GUI {
	
	public CollabDrawGUI2() {
		
		GUI application = new GUI();
		ImageIcon lineImage = new ImageIcon("line1.png");
	    ImageIcon particleImage = new ImageIcon("particle.png");
	 
		JButton lineBtn = new JButton("Line Draw", lineImage);
		JButton particleBtn = new JButton("Particle Draw", particleImage);
		JButton saveBtn = new JButton("Save Drawing");
		
		JLabel message = new JLabel("Drag the mouse to begin drawing with [Recipient Name].");
		message.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JPanel northPanel = new JPanel (new GridLayout(1, 2, 10, 10));
		JPanel drawBtnPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		//JPanel linePanel = new JPanel(new BorderLayout());
		//JPanel particlePanel = new JPanel(new BorderLayout());
		JPanel drawPanel = new JPanel(new BorderLayout());
		
		lineBtn.setFont(new Font("Arial", Font.PLAIN, 30));
		particleBtn.setFont(new Font("Arial", Font.PLAIN, 30));
		saveBtn.setFont(new Font("Arial", Font.PLAIN, 30));
		
		northPanel.add(message);
		northPanel.add(saveBtn);
		
	    drawBtnPanel.add(lineBtn);
		drawBtnPanel.add(particleBtn);
	  
		application.add(northPanel, BorderLayout.NORTH);
		application.add(drawBtnPanel, BorderLayout.SOUTH);
		
		
		particleBtn.setEnabled(true);
		lineBtn.setEnabled(false);
		LineDraw lineDraw = new LineDraw();
		TraceDraw traceDraw = new TraceDraw();
		drawPanel.add(lineDraw);
		//drawPanel.add(traceDraw);
		application.add( drawPanel, BorderLayout.CENTER );
		application.setSize( LineDraw.FRAME_WIDTH, LineDraw.FRAME_HEIGHT );
		application.setVisible( true );
		//linePanel.setVisible(true);
		//particlePanel.setVisible(true);
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		drawPanel.setVisible(true);
		
		
		lineBtn.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
						
                        particleBtn.setEnabled(true);
						lineBtn.setEnabled(false);
						
						drawPanel.remove(traceDraw);
						drawPanel.add(lineDraw);
						
						application.add( drawPanel, BorderLayout.CENTER );
						//application.setSize( LineDraw.FRAME_WIDTH, LineDraw.FRAME_HEIGHT );
						application.setVisible( true );
						
						//application.add(drawPanel);
						//linePanel.setSize( LineDraw.FRAME_WIDTH, LineDraw.FRAME_HEIGHT );
						
						//application.add(linePanel, BorderLayout.CENTER);
						//application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
						
						
						/** display frame -won't appear without this */
						//application.setVisible( true );
					}
                }
		);
		
		particleBtn.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
						
						particleBtn.setEnabled(false);
						lineBtn.setEnabled(true);
					
						drawPanel.remove(lineDraw);
						drawPanel.add(traceDraw);
						
						application.add( drawPanel, BorderLayout.CENTER );
						//application.setSize( LineDraw.FRAME_WIDTH, LineDraw.FRAME_HEIGHT );
						application.setVisible( true );
						
						//application.add(drawPanel);
						
                        /**particleBtn.setEnabled(false);
						lineBtn.setEnabled(true);
						
						TraceDraw particleTrace = new TraceDraw();
						particlePanel.add(particleTrace);
						particlePanel.setSize( TraceDraw.FRAME_WIDTH, TraceDraw.FRAME_HEIGHT );
						
						application.add(particlePanel, BorderLayout.CENTER);
						application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
						
						
						/** display frame -won't appear without this */
						//application.setVisible( true );
					}
                }
		);	
	}
	/* end constructor          */
	
	public static void main(String[] args) {
		CollabDrawGUI2 me = new CollabDrawGUI2();
	}
   /* end main          */
}  /* end class Painter */