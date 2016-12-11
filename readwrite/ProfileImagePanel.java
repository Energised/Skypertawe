import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ProfileImagePanel extends JPanel
{

    private BufferedImage image;

    public ProfileImagePanel(String filename)
    {
       try
       {
           //System.out.println("files/" + filename);
           image = ImageIO.read(new File("files/" + filename));
       }
       catch (Exception e)
       {
            System.out.println("no image found");
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

}
