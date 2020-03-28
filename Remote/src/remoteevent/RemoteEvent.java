/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteevent;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Alex
 */
class MainFrame extends JFrame implements ActionListener{
    // Create a panel for the number buttons with a 3 x 3 GridLayout  
    JPanel pnlButton = new JPanel(new GridLayout(3, 3));
    
    //Create the buttons
    //Turn on/ off TV
    JButton btnTurnoff = new JButton(new ImageIcon("resource/btnTurnoff.png")); 
    //Up/First
    JButton btnUp = new JButton(new ImageIcon("resource/btnUp.png")); 
    //Down/Last
    JButton btnDown = new JButton(new ImageIcon("resource/btnDown.png")); 
    //Left/ Previous
    JButton btnLeft = new JButton(new ImageIcon("resource/btnLeft.png")); 
    //Right/Next
    JButton btnRight = new JButton(new ImageIcon("resource/btnRight.png")); 
    //Run/Ok/Enter/Play
    JButton btnOk = new JButton(new ImageIcon("resource/btnOk.png")); 
    //Cancel/Return/Stop
    JButton btnCancel = new JButton(new ImageIcon("resource/btnCancel.png")); 
    //Go to Home Screen
    JButton btnHome = new JButton(new ImageIcon("resource/btnHome.png")); 
    //multifunction 
    JButton btnFunction = new JButton(new ImageIcon("resource/btnFunction.png")); 

    //Create a Lable
    JLabel labThanks = new JLabel("Thank you for your using.");
         
    public MainFrame(){
        // create a Borderlayout and assign it to the JFrame 
        setLayout(new BorderLayout()); 
        
        //Add the buttons into grid layout.
        pnlButton.add(btnTurnoff);
        pnlButton.add(btnUp);
        pnlButton.add(btnHome);
        pnlButton.add(btnLeft);
        pnlButton.add(btnOk);
        pnlButton.add(btnRight);
        pnlButton.add(btnFunction);
        pnlButton.add(btnDown);
        pnlButton.add(btnCancel);
        
        //Lable
        labThanks.setForeground(Color.blue);         //Set Font Color
        labThanks.setFont( new Font("Arial", Font.PLAIN, 24) );     //Set Font

        // add the main button panel and Label to the JFrame 
        add(pnlButton, BorderLayout.CENTER);
        add(labThanks, BorderLayout.SOUTH);
        
        //Add buttons text
        btnTurnoff.setText("Turnoff");
        btnUp.setText("Up");
        btnHome.setText("Home");
        btnLeft.setText("Left");
        btnOk.setText("Ok");
        btnRight.setText("Right");
        btnFunction.setText("Function");
        btnDown.setText("Down");
        btnCancel.setText("Cancel");
        
        //Add buttons Events
        btnTurnoff.addActionListener(this);
        btnUp.addActionListener(this);
        btnHome.addActionListener(this);
        btnLeft.addActionListener(this);
        btnOk.addActionListener(this);
        btnRight.addActionListener(this);
        btnFunction.addActionListener(this);
        btnDown.addActionListener(this);
        btnCancel.addActionListener(this);
     }
     
    //Action
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String command ="You pressed button - "+ button.getActionCommand();
        JOptionPane.showMessageDialog(pnlButton,command);
        System.out.print(command);
      }
}

public class RemoteEvent {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create a frame to hold our components 
        MainFrame myJFrame = new MainFrame(); 
        
        // set the title, size, location and exit behavior for the JFrame 
        myJFrame.setTitle("Alex's TV Remote Control"); 
        myJFrame.setSize(780, 840); 
        myJFrame.setLocation(100, 100); 
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // make the frame visible (activate the frame) 
        myJFrame.setVisible(true);
    }
    
}
