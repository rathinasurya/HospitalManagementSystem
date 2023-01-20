package Practice_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Projects {

	public static void main(String[] args) throws SQLException {
	Connection con=null;
	int result=0;
	try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");
		Statement state = con.createStatement();
		String query = "insert into project values('TY_Project_002','surya','21-12-2022', 'HMS','created',3);";
		result = state.executeUpdate(query);
	}
	catch (Exception e) {
		e.printStackTrace();
		
	}
	finally {
		if(result==1)
		{
			System.out.println("Data added sucessfully");
		}
		else
		{
			System.out.println("Data not inserted");
		}
		con.close();
		System.out.println("connection is closed sucessfully");
	}
}

}
