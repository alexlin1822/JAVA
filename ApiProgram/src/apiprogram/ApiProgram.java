/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiprogram;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author Alex
 */
class MainFrame extends JFrame implements ActionListener{
    //Var for Image ID
    static String[] sImg={
            "resource/1.jpg",
            "resource/2.jpg",
            "resource/3.jpg",
            "resource/btnLeft.png",
            "resource/Play.png",
            "resource/btnRight.png"};
    
    static String[] sSingerName={
            "Selena Gomez",
            "Katy Perry",
            "Mariah Carey",
            "Luis Fonsi",
            "Justin Bieber"};
    
    static int giSingerID=2;       //Currenly Singer ID    
    static boolean isPlay=false;   //Play static
    
    // Create a panel for the number buttons with a 3 x 3 GridLayout  
    JPanel pnlButton = new JPanel(new GridLayout(2, 3));
    JButton[] buttons;  //Buttons
  
    //Audio Var
    AudioInputStream audioIn;
    Clip myClip;

    //Create a Lable
    JLabel labThanks = new JLabel("Thank you for your using.");
         
    public MainFrame(){
        buttons = new JButton[6];
        for (int i=0;i<6;i++){
            buttons[i]=new JButton(new ImageIcon(sImg[i]));
            buttons[i].addActionListener(this);     //Add buttons Events
            pnlButton.add(buttons[i]);
        }

        // create a Borderlayout and assign it to the JFrame 
        setLayout(new BorderLayout()); 

        //Lable
        labThanks.setForeground(Color.blue);         //Set Font Color
        labThanks.setFont( new Font("Arial", Font.PLAIN, 24) );     //Set Font

        // add the main button panel and Label to the JFrame 
        add(pnlButton, BorderLayout.CENTER);
        add(labThanks, BorderLayout.SOUTH);
     }
    
    //Action
    public void actionPerformed(ActionEvent e) {
        //JButton button = (JButton) e.getSource();
        if (e.getSource() ==buttons[5]){
            giSingerID--;
            if (giSingerID<1) {giSingerID=5;}
            MoveIcon();
            }
        else if (e.getSource() ==buttons[3]){
            giSingerID++;
            if (giSingerID>5) {giSingerID=1;}
            MoveIcon();
            }
        else if (e.getSource() ==buttons[4]){
            isPlay=!isPlay;
            EdisonSoundFrame(isPlay);
        }
      }
    
    // Move the Singer Icon
    private void MoveIcon(){
        int iLeft,iRight;
        iLeft=giSingerID-1;
        if (iLeft<1){iLeft=5;}
        iRight=giSingerID+1;
        if (iRight>5){iRight=1;}

        String sFile="resource/"+Integer.toString(giSingerID)+".jpg";
        String sLeftFile="resource/"+Integer.toString(iLeft)+".jpg";
        String sRightFile="resource/"+Integer.toString(iRight)+".jpg";

        buttons[0].setIcon(new ImageIcon(sLeftFile));
        buttons[1].setIcon(new ImageIcon(sFile));
        buttons[2].setIcon(new ImageIcon(sRightFile));
        isPlay=true;
        EdisonSoundFrame(true);

    }
    
    //Play Song
    public boolean EdisonSoundFrame(boolean isPlay){
        String sFile;
        boolean bplaySuccess=false;
        sFile="resource/"+Integer.toString(giSingerID)+".wav";
        
        try {
            if (myClip!=null) {
                if (myClip.isActive()) {myClip.close();}
            }
            
            if (isPlay) {
                buttons[4].setIcon(new ImageIcon("resource/Pause.png"));
            }
            else{
                buttons[4].setIcon(new ImageIcon("resource/Play.png"));
                return false;
            }
            //if (!isPlay){ return false;}
            labThanks.setText(sSingerName[giSingerID-1]);
            
            // identify the sound file as a File class object
            File soundFile = new File(sFile);

            // Create an AudioInputStream for the File object soundFile
            // This allows Java to read the file and read it as audio data in one step.
            audioIn = AudioSystem.getAudioInputStream(soundFile);

            // create an audio Clip object so we can use the Clip class methods 
            myClip = AudioSystem.getClip();

            // open the myClip object and associate it with the audioIn AudioInputStream
            myClip.open(audioIn);

            // start playing the myClip audio Clip
            myClip.start();
            bplaySuccess=true;

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }  // end catch        
        
        return bplaySuccess;
    }
}

public class ApiProgram {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create a frame to hold our components 
        MainFrame myJFrame = new MainFrame(); 
        
        // set the title, size, location and exit behavior for the JFrame 
        myJFrame.setTitle("Alex's Juke Box"); 
        myJFrame.setSize(780, 580); 
        myJFrame.setLocation(100, 100); 
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // make the frame visible (activate the frame) 
        myJFrame.setVisible(true);
    }   
}
