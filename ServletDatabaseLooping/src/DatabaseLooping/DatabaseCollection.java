package DatabaseLooping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseCollection {
	
	String url = "jdbc:oracle:thin:system/password@localhost"; 
	Properties props = new Properties();
	
	//make sure to have the following steps done
	//1 copy ojdbc6.jar to webcontent/web-inf/lib
	//2 add ojdbc6.jar to build path - libraries / add external jar
	//3 Class.forName ("oracle.jdbc.driver.OracleDriver")
	static{
	    try {
	        Class.forName ("oracle.jdbc.driver.OracleDriver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public DatabaseCollection() {
		props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
	}
	
	public ArrayList<DemoCustomer> getCustomerInfo(){
		ArrayList<DemoCustomer> demoCus = new ArrayList<DemoCustomer>(); 
		
		try{
			Connection conn = DriverManager.getConnection(url,props);
			
			String sql ="select * from demo_customers";
	
	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	    
	        ResultSet result = preStatement.executeQuery();
	      
	        while(result.next()){
	        	DemoCustomer cus = new DemoCustomer();
	        	cus.setFirstName(result.getString("cust_first_name"));
	        	cus.setLastName(result.getString("cust_last_name"));
	        	cus.setAddress(result.getString("cust_street_address1"));
	        	cus.setCity(result.getString("cust_city"));
	        	cus.setState(result.getString("cust_state"));
	        	cus.setZipcode(result.getString("cust_postal_code"));
	        	
	            demoCus.add(cus);
	        }
	        
	        conn.close();
		}catch(Exception e){
			demoCus = null;
			e.printStackTrace();
		}
		
		return demoCus;
		
	}

}
