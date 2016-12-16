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
public class ContactInformation extends JApplet {
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
        f.fillArc(Gx*9, Gy*2, Gwidth*15, Gheight*15, angle, angle);
        
        //frontground bottom right circle
        f.setColor(new Color(89, 62, 115));    //grayPurple
        f.fillArc(Gx*9, Gy*3, Gwidth*15, Gheight*14, angle, angle);
        
        //top right circle
        f.setColor(new Color(239, 119, 0));    //burntOrange
        f.fillArc(Gx*11, Gy*(-5), Gwidth*9, Gheight*9, angle + 90, angle);
        
        
     /** Name and contact info */
        
        //Corner Used
        int x = LtopX;
        int y = LtopY;
        
        //Name
        Font nameFont = new Font("arial", Font.PLAIN, 35);
        f.setFont(nameFont);
        f.setColor(new Color(239, 119, 0));    //burntOrange
        String name = "Emily Peterson";
        f.drawString(name, x + unit*5, y + unit*6);
       
        //Major
        Font majorFont = new Font("arial", Font.PLAIN, 14);
        f.setFont(majorFont);
        f.setColor(new Color(164, 200, 98));   //sadGreen
        String major = "Computer Science Major";
        f.drawString(major, x + unit*12, y + unit*8);
        
        //Bullet points
        int w = 17;
        int h = w;
        Font info = new Font("arial", Font.PLAIN, 20);
        f.setFont(info);
        f.setColor(new Color(89, 62, 115));    //grayPurple
        f.fillRect(x + unit*2, y + unit*11, w, h);
        f.fillRect(x + unit*2, y + unit*17, w, h);
        f.fillRect(x + unit*2, y + unit*22, w, h);
        
        //Info
        f.setColor(new Color(164, 200, 98));   //sadGreen
        String phoneNumber = " 616-987-6398";
        f.drawString(phoneNumber, x + unit*4, y + unit*13);
        String email = " peterse2@mail.gvsu.edu";
        f.drawString(email, x + unit*4, y + unit*18);
        String website = " www.linkedin.com/pub/";
        f.drawString(website, x + unit*4, y + unit*23);
        String websiteTwo = "emily-peterson/89/b36/a88/";
        f.drawString(websiteTwo, x + unit*4, y + unit*25);
        
        //Letters inside bullet point
        Font letters = new Font("arial", Font.BOLD, 13);
        f.setFont(letters);
        f.setColor(new Color(239, 119, 0));    //burntOrange
        String phoneNumber2 = "p";
        f.drawString(phoneNumber2, x + 5*unit/2, y + unit*12);
        String email2 = "e";
        f.drawString(email2, x + 5*unit/2, y + unit*18); ;
        String  website2 = "w";
        f.drawString(website2, x + 5*unit/2, y + unit*23);
        
     
     ///** Picture */
     
     //BufferedImage photo = null;
     //try {
       // URL u = new URL(getCodeBase(), "MyFace.jpg");
        //photo = ImageIO.read(u);
     //} catch (IOException e){
        //f.drawString("Problem reading the file", 100, 100);
     //}
        //f.drawImage(photo, RbottX - 165, RbottY - 140, 100, 135, null);

        
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
