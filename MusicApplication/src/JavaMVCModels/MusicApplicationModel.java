/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaMVCModels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author waqas
 */
public class MusicApplicationModel {
      PreparedStatement pst=null; 
  ResultSet rs=null;
  Connection connection= null;
    public MusicApplicationModel(){
        
       // Connecting with the DATABASE  
    System.out.println("-------- MySQL JDBC Connection Testing ------------");
 
    String Database = "jdbc:mysql://localhost:3306/musicDB";
    String UserName = "root";
    String Password = "";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}

	System.out.println("MySQL JDBC Driver Registered!");
	connection = null;

	try {
		connection = DriverManager
		.getConnection(Database,UserName, Password);

	} catch (SQLException e) {
		return;
	}

	if (connection != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
                JOptionPane.showMessageDialog(null, "DataBase is not configured, Make sure you have configured properly.");
	}
    }

    public ResultSet loadData() {
     if(connection !=null){
        try{
            String sql="select * from tracks";
            pst=connection.prepareStatement(sql);
            rs=pst.executeQuery();
            return rs;
    }
    catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error while getting Data from database.");
            return null;
    }
     }
     return null;
    }

    public ResultSet searchTrackDetails(String track) {
           try{
            String sql="select * from tracks where UPPER(track_title) Like ?";
            pst=connection.prepareStatement(sql);
          
           track =track.toUpperCase(); 
           track =track+"%";
            
           pst.setString(1,track);
               System.err.println(sql);
            rs=pst.executeQuery();
            return rs;
    }
    catch(Exception e){
            JOptionPane.showMessageDialog(null,"Sorry this track doesn't exist! \n OR Invalid track name.");
            return null;
    }
    
    }

    public boolean savaTrackData(String title_str, String singer_str, String duration_str) {
           
        
        // If stings are empty.... 
        if(title_str.length()!=0 && singer_str.length()!=0 && duration_str.length()!=0){
           
            // If title string is less then 4 or have characters other then a to Z
            if(Pattern.matches("[a-zA-Z ]{4,}", title_str)){
               
                    // IF singer string is less then 4 and contain invlaid characters other a-Z or , 
                 if(Pattern.matches("[a-zA-Z ]{4,}", singer_str)){
                     
                     // for duration if duration 00:00 does not have this format
                     // or contain characters
                     if(Pattern.matches("^(\\d){2}:(\\d){2}+$", duration_str)){
                         if(connection !=null){   
                         try{      // the mysql insert statement
                                        String query = " insert into tracks(track_id, track_title,singer,duration)"
                                                     + " values (null, ?, ?,?)";
                                   
                              PreparedStatement preparedStmt = connection.prepareStatement(query);

                              preparedStmt.setString (1,title_str );  
                              preparedStmt.setString (2, singer_str);
                              preparedStmt.setString (3,duration_str );

                              // execute the preparedstatement  
                              preparedStmt.execute(); 
                              connection.close();
                              JOptionPane.showMessageDialog(null,"Track added successfully");
                              return true;
                            }
                            catch (Exception e)
                            {
                              JOptionPane.showMessageDialog(null,"Unable to insert Data..");
                            }
                         }else {
                         JOptionPane.showMessageDialog(null,"Database is not connected.");
                         }
                     }// else for Duration 
                     else JOptionPane.showMessageDialog(null,"Invalid Duration....");
                     
              }// else for singer value condition.....
                 else JOptionPane.showMessageDialog(null,"Invalid Singer Name..");
                 
            }// else of title rregex condition...
            else JOptionPane.showMessageDialog(null,"Invalid Track title..");
                
         } // Empyt condition else statments
        else JOptionPane.showMessageDialog(null,"Some Fields are empty..\n Please insert Data in all fields.");
        return false;
    }
    
}
