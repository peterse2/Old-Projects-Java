package Lab;


import java.awt.*;
import javax.swing.*;

/**
 * Class CandyCrush - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class CandyCrush extends JApplet
{
    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g) 
    { 
    {
        // Blue Circle 
        g.setColor(Color.white);
        g.fillRect(0, 0, 50, 50);
        g.setColor(Color.black);
        g.drawRect(0, 0, 50, 50);
        g.setColor(Color.blue);
        g.fillOval(10, 10, 30, 30);
        g.drawArc(10, 10, 30, 40, 180, 180);
    }
}
}
