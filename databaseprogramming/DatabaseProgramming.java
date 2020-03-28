/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprogramming;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class DatabaseProgramming {
    public static Connection conn;
    public static String[] slField;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        slField=new String[] {"crn","subject","course","section","credits","time","days",
                "term","campus","room","enrollment"};
        
        int iField;
        String sSign;
        String sKeyword;
        String sSql;
        
        Scanner scanner=new Scanner(System.in);
        
        ///////////////Trying to connect to the database///////////
       
        if (!ConnectDB()){
            System.out.println("Database can not connect!");
            return;
        }; 
        
        System.out.println("Please select the No. of the field what you want to search: ");
        
        for (int i=0; i<slField.length;i++){
             System.out.println((i+1)+" - "+slField[i]);
        }
        
        while(true){
            try {
                System.out.print("( 1 - 11) Your choice: ");
                iField=Integer.parseInt(scanner.nextLine());
                if ((iField<1) || (iField>11)){
                    System.out.print("Input Error! ");
                    continue;
                }
                iField--;
                break;
            } catch (Exception e) {
                System.out.print("Input Error! ");
            }
        }
        
        while(true){
            try {
                if ((iField==4) || (iField==10)) {
                    //number
                    System.out.print("Please enter '<' or '>' or '=' : ");
                    sSign=scanner.nextLine();
                    if ((sSign.equals("<")) || (sSign.equals(">")) || (sSign.equals("="))){
                        break;}
                }else{
                    sSign=" like '%";
                    break;
                }
            } catch (Exception e) {}
                System.out.print("Input Error! ");
            continue;            
        }
        
        System.out.print("Please enter the keyword: ");
        sKeyword=scanner.nextLine();

        if ((iField==4) || (iField==10)) {
            sSql=slField[iField]+sSign+sKeyword+";";
        }else{
            sSql=slField[iField]+sSign+sKeyword+"%';";
        }
        
        try {
            RunSQL(sSql);
            conn.close();
        } catch (Exception e) {
            System.out.print("Sorry! You entered the wrong word!");
        }
        
    }
    
    public static Boolean ConnectDB(){
        try {
             System.out.println("Connecting to the database...\n");
            conn = DriverManager.getConnection("jdbc:mysql://68.178.217.12/CWHDemo", "CWHDemo", "Cwhdemo%123");
            System.out.println("Database connection established.\n");
            return true;
        } catch (Exception e) {
            System.out.println("Can not create connection.\n");
            return false;
        }
    }
    
    public static void RunSQL(String sSQL) throws SQLException, ClassNotFoundException {
        ArrayList<String> aData=new ArrayList<String>();
        
        String queryString;     // a String to hold an SQL query 
        ResultSet rs;           // the result set from an SQL query as a table

        Statement st = conn.createStatement();
        
        queryString = "SELECT * FROM fall2014 where "+sSQL;

        // Send a statement executing the query and saving the result set 
        rs = st.executeQuery(queryString);

        // print headings for the output
        System.out.println("\nYour enter the Query is :");
        System.out.println(queryString+"\n");
        System.out.printf("%-8s%-8s%-8s%-8s%-8s%-20s%-8s%-8s%-8s%-8s%-10s%n",
                "crn","subject","course","section","credits","time","days",
                "term","campus","room","enrollment");
        System.out.println("********************************************************************************************************************");

        String sResult;
        sResult="crn,"+"subject,"+"course,"+"section,"+"credits,"+"time,"+"days,"+
                "term,"+"campus,"+"room,"+"enrollment\n";
        aData.add(sResult);
        
        // Iterate through the result set and print name, owner, and species attributes
        while (rs.next()){
            sResult=rs.getString("crn")+","+ 
                    rs.getString("subject")+","+
                    rs.getString("course")+","+
                    rs.getString("section")+","+
                    rs.getString("credits")+","+
                    rs.getString("time")+","+
                    rs.getString("days")+","+
                    rs.getString("term")+","+
                    rs.getString("campus")+","+
                    rs.getString("room")+","+
                    rs.getString("enrollment")+
                    "\n";
            
            aData.add(sResult);
            
            System.out.printf("%-8s%-8s%-8s%-8s%-8s%-20s%-8s%-8s%-8s%-8s%-10s%n", 
                    rs.getString("crn"), 
                    rs.getString("subject"),
                    rs.getString("course"),
                    rs.getString("section"),
                    rs.getString("credits"),
                    rs.getString("time"),
                    rs.getString("days"),
                    rs.getString("term"),
                    rs.getString("campus"),
                    rs.getString("room"),
                    rs.getString("enrollment")
                    );
        }
        System.out.println("********************************************************************************************************************");
        
        ToCSV(aData);
    } 
    
    public static void ToCSV(ArrayList<String> aData){
        BufferedWriter writer = null;
        try {
            //create file
            String sFilename="Result"+Long.toString(System.currentTimeMillis())+".csv";
            File fFile = new File(sFilename);

            writer = new BufferedWriter(new FileWriter(fFile));
            
            for (int i=0;i<aData.size();i++){
                writer.write(aData.get(i));
            }
            writer.close();
            System.out.println("**The result have been saved to "+sFilename);
            
        } catch (Exception e) {
            System.out.print("CSV File saving error!");
        }
    }
}


