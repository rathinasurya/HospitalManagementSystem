package Practice_package;


import java.security.DrbgParameters.NextBytes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDatabase {
	public static void main(String[] args) throws SQLException 
	{
	Connection con=null;
	try
	{
	Driver driver=new Driver();
	
	//step1: Register the database
	DriverManager.registerDriver(driver);
 
	//step2: get connection for the database
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet45","root","root");
 
	//step3: issue create statement
	Statement state = con.createStatement();
	String query="select * from studentInfo;";
 
	//step4: Execute query
	ResultSet result=state.executeQuery(query);
 
	while(result.next())
	{
		System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
	}
}
	catch (Exception e) {

	}
	finally
	{
		con.close();
		System.out.println("close the database connection successfully");
	}

}
}
