package com.hms.GenericUtilites;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Driver;

public class DatabaseUtility 
{
	Connection con=null;
	public void connectToDB() throws Throwable
	{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection(null, null, null);
	}
	
	public String executeQueryAndgetData(String query, int coloumnIndex, String expData) throws Throwable
	{
		ResultSet result = con.createStatement().executeQuery(expData);
		boolean flag=false;
		while(result.next())
		{
		String data = result.getString(coloumnIndex);	
		System.out.println(data);
		if(data.equalsIgnoreCase(expData))
		{
			flag=true;
			break;
		}
		}
		if(flag)
		{
			System.out.println(expData+"project is created");
			return expData;
		}
		else
		{
			System.out.println("project not created");
			return "";
		}
		}

	public  void closeDB() throws Throwable
	{
		con.close();
	}
}



