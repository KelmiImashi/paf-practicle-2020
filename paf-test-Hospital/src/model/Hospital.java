package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Path;


public class Hospital {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalsnew?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String hname,String no,String street,String city,String hos_charges)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			
			String query = " insert into hospital87(`hid`,`hname`,`no`,`street`,`city`,`hos_charges`)" + " values (?, ?, ?, ?, ?,?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, hname);
			preparedStmt.setString(3, no);
			preparedStmt.setString(4, street);
			preparedStmt.setString(5, city);
			preparedStmt.setDouble(6, Double.parseDouble(hos_charges));    
		    
			preparedStmt.execute();    
			con.close(); 
	   
			String newHospital = readHospitals(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
							newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the hospital.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readHospitals()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			output = "<table border=\'1\'><tr><th>Hospital Name</th><th>Phone Number</th><th>Street</th><th>City</th><th>Hospital Charges</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from hospital87";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	    
			while (rs.next())    
			{     
				String hid = Integer.toString(rs.getInt("hid"));     
				String hname = rs.getString("hname");     
				String no = rs.getString("no");     
				String street = rs.getString("street"); 
				String city = rs.getString("city"); 
				String hos_charges = Double.toString(rs.getDouble("hos_charges"));     
			
				output += "<tr><td><input id=\'hidHidUpdate\' name=\'hidHidUpdate\' type=\'hidden\' value=\'" + hid + "'>" 
							+ hname + "</td>";           
				output += "<td>" + no + "</td>";     
				output += "<td>" + street + "</td>"; 
				output += "<td>" + city + "</td>"; 
				output += "<td>" + hos_charges + "</td>"; 
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"hospitals.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidHidDelete\" type=\"hidden\" value=\"" + hid + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hid='" + hid + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the hospitals.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateHospital(String hname,String no,String street,String city,String hos_charges)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	   
			String query = "UPDATE hospital87 SET hname=?,no=?,street=?,city=?,hos_charges=? WHERE hid=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			 
			preparedStmt.setString(1, hname);
			preparedStmt.setString(2, no);      
			preparedStmt.setString(3, street);     
			preparedStmt.setString(4, city);    
			preparedStmt.setDouble(5, Double.parseDouble(hos_charges));
	   
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospitals = readHospitals();    
			output = "{\"status\":\"success\", \"data\": \"" +        
									newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the hospital.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteHospital(String hid)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			String query = "delete from hospital87 where hid=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	   
			preparedStmt.setInt(1, Integer.parseInt(hid)); 
	    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospitals = readHospitals();    
			output = "{\"status\":\"success\", \"data\": \"" +       
								newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the hospital.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}



