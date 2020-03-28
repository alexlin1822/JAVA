/*
 * Temperature Converter V2
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperatureconverter;

import java.util.Scanner;


/**
 * Temperature Converter V2
 * @author Alex
 */
public class TemperatureConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Var
        String sCityName="";
        double dTempC=0;
        double dTempF=0;
        Scanner sInput = new Scanner(System.in);
        
        //Input City and C
        System.out.println(" -------------------------------------------------------------- ");

        System.out.print("\n Please enter the city name:   ");
        sCityName=sInput.nextLine();

        System.out.print("\n Please enter the temperature in degrees Celsius:   ");
        dTempC=Float.parseFloat(sInput.nextLine());
        sInput.close();
       
        //Loop and Output        
        System.out.print("\n The temperature table which in "+sCityName
                +" is showed from "+Integer.toString((int)dTempC)+" ⁰C \n\n");
        System.out.println("----------------Temperature Table---------------------- ");
        System.out.println("  Celsius  Fahrenheit  ");
        
        //Loop 
        for (int i=0;i<=40;i++){
            double dTp=dTempC+i;
            //Temperature Convert to F
            dTempF= (9.00/5.00 * (dTp)) + 32.00;
            System.out.println("    "+String.format("%.0f",(dTp))+"        "+
                                  String.format("%.1f",dTempF));
        }
        
        System.out.println(" -------------------------------------------------------------- ");
       
    }
    
}
