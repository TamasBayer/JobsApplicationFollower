
package jobs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
    
    String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    String URL = "jdbc:derby:sampleDB;create=true";
    String USERNAME = "";
    String PASSWORD = "";
    
    
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
     
    public DB(){
       
        
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Error with Connection");
            System.out.println(" "+ex);
        }
        
        if (conn != null){
            try {
                 createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Error with createStatement");
                System.out.println(" "+ex);
            }
            
        }
            
            try {
                dbmd = conn.getMetaData();
            } catch (SQLException ex) {
                System.out.println("Error with DatabaseMetaData");
                System.out.println(" "+ex);
            }
            
            try {
                ResultSet rs = dbmd.getTables(null, "APP", "JOBS", null);
                if (!rs.next()){
                    createStatement.execute("CREATE TABLE Jobs(id INT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), Name varchar(20), Position varchar(20), ApplicationDate varchar(10), Response varchar(10), Interview varchar(10), JobOffer varchar(10))");
                }
            } catch (SQLException ex) {
                System.out.println("Error with resultTable");
                System.out.println(" "+ex); 
            }
    }
    
    public void addJob(String name, String position, String applicationDate, String response, String interview, String jobOffer){
        try {

              String sql = "INSERT INTO Jobs (name, position, applicationDate, response, interview, jobOffer) VALUES (?, ?, ?, ?, ?, ?)";
              PreparedStatement preparedStatement = conn.prepareStatement(sql);
              preparedStatement.setString(1, name);
              preparedStatement.setString(2, position);
              preparedStatement.setString(3, applicationDate);
              preparedStatement.setString(4, response);
              preparedStatement.setString(5, interview);
              preparedStatement.setString(6, jobOffer);
              preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("Error with addJob");
            System.out.println(" "+ex);
        }
    }
    
    public void showAllJobs(){
        String sql = "select * from Jobs";
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while (rs.next()){
            String name = rs.getString("name");
            String position = rs.getString("position");
            String applicationDate = rs.getString("applicationDate");
            String response = rs.getString("response");
            String interview = rs.getString("interview");
            String jobOffer = rs.getString("jobOffer");
            System.out.println(name + " | " + position + " | " + applicationDate + " | " + response + " | " + interview + " | " + jobOffer);
         
            }
        } catch (SQLException ex) {
            System.out.println("Error with showAllJobs");
            System.out.println(" "+ex);
        }
    }    
    
    public ArrayList<JobsPojo> getAllJobs(){
        String sql = "select * from Jobs";
        ArrayList<JobsPojo> Jobs = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            Jobs = new ArrayList<>();
            
            while (rs.next()){
            JobsPojo actualJobsPojo = new JobsPojo(rs.getInt("id"), rs.getString("name"),rs.getString("position"), rs.getString("applicationDate"), rs.getString("response"), rs.getString("interview"), rs.getString("jobOffer"));
            Jobs.add(actualJobsPojo);
            }
        } catch (SQLException ex) {
            System.out.println("Error with showAllJobs");
            System.out.println(" "+ex);
        }    
        
        return Jobs;
    }
    
    public JobsPojo getLastJob(){
        String sql = "SELECT * FROM Jobs WHERE id = (SELECT MAX(id) FROM Jobs)";
        JobsPojo lastJob = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            
            while (rs.next()){
            lastJob = new JobsPojo(rs.getInt("id"), rs.getString("name"),rs.getString("position"), rs.getString("applicationDate"), rs.getString("response"), rs.getString("interview"), rs.getString("jobOffer"));
            
            }
        } catch (SQLException ex) {
            System.out.println("Error with showAllJobs");
            System.out.println(" "+ex);
        }    
        
        return lastJob;
    }
    
    public void delete(int rowID){
        
        try {
            String sql = "DELETE FROM Jobs WHERE id = " + rowID + "";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Error with delete: " + ex);
        }
    }
    
    public void update(int rowID, int columnNum, String newData){
         try {
            String sql = null;
            
            switch(columnNum){
                 case 1:
                    sql ="UPDATE Jobs SET name = '" + newData +"' where id = " + rowID + "";            
                    break;
                 case 2:
                    sql ="UPDATE Jobs SET position = '" + newData +"' where id = " + rowID + "";            
                    break;
                 case 3:
                    sql ="UPDATE Jobs SET applicationDate = '" + newData +"' where id = " + rowID + "";            
                    break;
                 case 4:
                    sql ="UPDATE Jobs SET response = '" + newData +"' where id = " + rowID + "";            
                    break;
                 case 5:
                    sql ="UPDATE Jobs SET interview = '" + newData +"' where id = " + rowID + "";            
                    break;
                 case 6:
                    sql ="UPDATE Jobs SET jobOffer = '" + newData +"' where id = " + rowID + "";            
                    break;
            }
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Error with Update");
            System.out.println(""+ex);
        }
    }
}
