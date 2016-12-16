package Projects.P1;

import java.awt.*;
import javax.swing.*;

/**
 * Class Project1a - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class scheme3 extends JApplet
{
    /**
     * Paint method for applet.
     * 
     * @param  f   the Graphics object for this applet
     */
    public void paint(Graphics f)
    {        
        setBackground(Color.darkGray);
        f.setColor(Color.darkGray);
        f.fillRect(0, 0, 500, 300);
        
     //Background Design
        int x = (500/15);
        int y = (300/9);
        int width = x;
        int height = y;
        int angle = 90;
        
        //Foreground bottom right Sliver
        f.setColor(new Color(238, 242, 218));  //lightGreen
        f.fillArc(x*7, y*2, width*17, height*15, angle, angle);
        
        //frontground bottom right circle
        f.setColor(new Color(176, 176, 198));  //lightGrayPurple
        f.fillArc(x*7, y*3, width*17, height*14, angle, angle);
        
        //top right circle
        f.setColor(new Color(202, 213, 186));   //lightGrayGreen
        f.fillArc(x*11, y*(-5), width*9, height*9, angle+90, angle);
        
      // Company Name background/ outline
        f.setColor(new Color(238, 242, 218));   //lightGreen
        f.fillRect(x*2, y, width*6, height*2);
        
     //Missing squares
        x = 500;
        y = 300;
        int w = 10;
        int h = w;
        
        //First Row (From the bottom)
        f.setColor(Color.darkGray);
        f.fillRect(x-10, y-10, w, h);
        f.fillRect(x-20, y-10, w, h);
        f.fillRect(x-30, y-10, w, h);
        f.fillRect(x-50, y-10, w, h);
        f.fillRect(x-60, y-10, w, h);
        
        //Second Row
        f.fillRect(x-10, y-20, w, h);
        f.fillRect(x-20, y-20, w, h);
        f.fillRect(x-30, y-20, w, h);
        f.fillRect(x-40, y-20, w, h);
        
        //Third Row
        f.fillRect(x-10, y-30, w, h);
        f.fillRect(x-20, y-30, w, h);
        f.fillRect(x-40, y-30, w, h);
        
        //Fourth Row
        f.fillRect(x-10, y-40, w, h);
        f.fillRect(x-20, y-40, w, h);
        f.fillRect(x-40, y-40, w, h);
        f.fillRect(x-50, y-40, w, h);
        
        //Fifth Row
        f.fillRect(x-20, y-50, w, h);
        f.fillRect(x-30, y-50, w, h);
        
        //Sixth Row
        f.fillRect(x-20, y-60, w, h);
        
        //Seventh Row
        f.fillRect(x-30, y-70, w, h);  
    }
}
