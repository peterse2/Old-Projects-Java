package Projects.P1;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.net.*;
import javax.imageio.*;
import java.io.*;

/**
 * Class Project1a - Business Card
 * @author EmilyPeterson
 * @Version Jan 27 2013
 */
public class scheme2 extends JApplet {
    /*****************************************************************************
     * Paint method for applet. 
     * @param  f   the Graphics object for this applet
     ****************************************************************************/
    public void paint(Graphics f)
    {        
     /**locations on card */
        
        //Right Corner
        int RtopX = 500;
        int RtopY = 0;
        int RbottX = 500;
        int RbottY = 300;
        
        //Left Corner
        int LtopX = 0;
        int LtopY = 0;
        int LbottX = 0;
        int LbottY = 300;
        
        //Center 
        int CenterX = 250;
        int CenterY = 150;
        
        
     /** Scales */
        
        // Regular Scale
        int width = 10;
        int height = 10;
        int unit = 10;
        
        // Graph paper drawing scale
        int Gwidth = (500/15);
        int Gheight = (300/9);
        int Gx = (500/15);
        int Gy = (300/9);
        
        
     /** Background */
    
        f.setColor(Color.darkGray);
        f.fillRect(LtopX, LtopY, 500, 300);
        
        
     /** Background design (used Graph paper scale) */
     
        //Common angle
        int angle = 90;
         
        //Foreground bottom right Sliver
        f.setColor(new Color(164, 200, 98));   //sadGreen
        f.fillArc(Gx*7, Gy*2, Gwidth*17, Gheight*15, angle, angle);
        
        //frontground bottom right circle
        f.setColor(new Color(89, 62, 115));    //grayPurple
        f.fillArc(Gx*7, Gy*3, Gwidth*17, Gheight*14, angle, angle);
        
        //top right circle
        f.setColor(new Color(239, 119, 0));    //burntOrange
        f.fillArc(Gx*11, Gy*(-5), Gwidth*9, Gheight*9, angle + 90, angle);
        
     
     /** Company Name */
        
        //Corner Used
        int x = LtopX;
        int y = LtopY;
     
        //Background
        f.setColor(new Color(164, 200, 98));   //sadGreen
        f.fillRect(x + unit*9, y + unit*4, width*15, height*5);
        
        //Setting the font
        Font compFont = new Font("calibri", Font.BOLD, 40);
        f.setFont(compFont);
        f.setColor(new Color(89, 62, 115));    //grayPurple
        
        //Adding words
        String CompName = "uit Ads";
        f.drawString(CompName, x + unit*12, y + unit*8);
        
     
     /** Logo */
        
        //Corner used 
        x = LtopX;
        y = LtopY;
        
        //Outer Ring
        f.setColor(new Color(89, 62, 115));    //grayPurple
        f.fillArc(x + unit*3, y + unit*2, 9*width, 9*height, 300, 300);
        f.setColor(new Color(239, 119, 0));    //burntOrange
        f.drawArc(x + unit*3, y + unit*2, 9*width, 9*height, 300, 300);
        
        //Inter Circle
        f.setColor(Color.darkGray);
        f.fillOval(x + unit*4, y + unit*3, 7*width, 7*height); 
        f.setColor(new Color(164, 200, 98));   //sadGreen
        f.drawOval(x + unit*4, y + unit*3, 7*width, 7*height);
        
        //Stem
        f.setColor(new Color(89, 62, 115));    //grayPurple
        f.fillRect(x + unit*7, y + unit*7, width, height*4);
        
        
     /** Name and contact info */
        
        //Corner Used
        x = LbottX;
        y = LbottY;
        
        //Name
        Font nameFont = new Font("arial", Font.PLAIN, 25);
        f.setFont(nameFont);
        f.setColor(new Color(239, 119, 0));    //burntOrange
        String name = "Emily Peterson";
        f.drawString(name, x + unit*2, y - unit*16);
        
        //Bullet points
        int w = 15;
        int h = w;
        Font info = new Font("arial", Font.PLAIN, 15);
        f.setFont(info);
        f.setColor(new Color(89, 62, 115));    //grayPurple
        f.fillRect(x + unit*2, y - unit*13, w, h);
        f.fillRect(x + unit*2, y - unit*9, w, h);
        f.fillRect(x + unit*2, y - unit*5, w, h);
        
        //Info
        f.setColor(new Color(164, 200, 98));   //sadGreen
        String phoneNumber = " 616-616-6161";
        f.drawString(phoneNumber, x + unit*4, y - unit*12);
        String email = " qwerty@quit.org";
        f.drawString(email, x + unit*4, y - unit*8);
        String website = " www.quitadvertising.org";
        f.drawString(website, x + unit*4, y - unit*4);
        
        //Letters inside bullet point
        Font letters = new Font("arial", Font.BOLD, 12);
        f.setFont(letters);
        f.setColor(new Color(239, 119, 0));    //burntOrange
        String phoneNumber2 = "p";
        f.drawString(phoneNumber2, x + 5*unit/2, y - unit*12);
        String email2 = "e";
        f.drawString(email2, x + 5*unit/2, y - unit*8); 
        String  website2 = "w";
        f.drawString(website2, x + 5*unit/2, y - unit*4);
        
     
     /** Picture */
     
     BufferedImage photo = null;
     try {
        URL u = new URL(getCodeBase(), "MyFace.jpg");
        photo = ImageIO.read(u);
     } catch (IOException e){
        f.drawString("Problem reading the file", 100, 100);
     }
        f.drawImage(photo, RbottX - 165, RbottY - 140, 100, 135, null);

        
     /** Missing squares */
        
        //Variables
        w = 10;
        h = w;
        x = RbottX;
        y = RbottY;
        
        //Color
        f.setColor(Color.darkGray);
        
        //First Row (From the bottom right corner)
        f.fillRect(x - unit, y - unit, w, h);
        f.fillRect(x - 2*unit, y - unit, w, h);
        f.fillRect(x - 3*unit, y - unit, w, h);
        f.fillRect(x - 5*unit, y - unit, w, h);
        f.fillRect(x - 6*unit, y - unit, w, h);
        
        //Second Row
        f.fillRect(x - unit, y - 2*unit, w, h);
        f.fillRect(x - 2*unit, y - 2*unit, w, h);
        f.fillRect(x - 3*unit, y - 2*unit, w, h);
        f.fillRect(x - 4*unit, y - 2*unit, w, h);
        
        //Third Row
        f.fillRect(x - unit, y - 3*unit, w, h);
        f.fillRect(x - 2*unit, y - 3*unit, w, h);
        f.fillRect(x - 4*unit, y - 3*unit, w, h);
        
        //Fourth Row
        f.fillRect(x - unit, y - 4*unit, w, h);
        f.fillRect(x - 2*unit, y - 4*unit, w, h);
        f.fillRect(x - 4*unit, y - 4*unit, w, h);
        f.fillRect(x - 5*unit, y - 4*unit, w, h);
        
        //Fifth Row
        f.fillRect(x - 2*unit, y - 5*unit, w, h);
        f.fillRect(x - 3*unit, y - 5*unit, w, h);
        
        //Sixth Row
        f.fillRect(x - 2*unit, y - 6*unit, w, h);
        
        //Seventh Row
        f.fillRect(x - 3*unit, y - 7*unit, w, h);
     
    
     /** Cover up */
     
        f.setColor(Color.white);
        f.fillRect(LbottX, LbottY, 500, 50);
        f.fillRect(RtopX, RtopY, 50, 350);
    }
}
