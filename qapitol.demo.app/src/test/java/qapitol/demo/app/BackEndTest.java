package qapitol.demo.app;

import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import qapitol.demo.app.controllers.DemoController;
import qapitol.demo.app.entity.Students;

public class BackEndTest {

	// Connection object
	static Connection con = null;
	// Statement object
	private static Statement stmt;
	// Constant for Database URL
	public static String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
	// Constant for Database Username
	public static String DB_USER = "root";
	// Constant for Database Password
	public static String DB_PASSWORD = "root";
	DemoController demo;

	@BeforeTest
	public void setUp() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// make the database connection
		String dbClass = "com.mysql.jdbc.Driver";
		Class.forName(dbClass).newInstance();
		// Get connection to DB
		Connection con = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		// Statement object to send the SQL statement to the Database
		stmt = (Statement) con.createStatement();

	}
	
	@Test(description="getting all students",priority=1)
	public void getting() throws SQLException
	{
		demo=new DemoController();
		ResponseEntity<List<Students>> s=demo.getAllStudents();
		String query = "select * from students ";
		ResultSet res = stmt.executeQuery(query);
		System.out.println(stmt);
		while (res.next()) {
			int id = Integer.parseInt(res.getString(1));
			int age = Integer.parseInt(res.getString(2));
			String email = res.getString(3);
			String name = res.getString(4);
//			assertEquals(stu.name,name);
//			assertEquals(stu.age,age);
//		assertEquals(stu.email,email);
//		}
	}

//	@Test(description = "creating student", priority = 1)
//	public void create() throws SQLException {
//		demo = new DemoController();
//		Students stu = new Students();
//		stu.name = "rachana";
//		stu.age = 22;
//		stu.email = "rachana@gmail.com";
//		System.out.println(stu);
//		ResponseEntity<Students> ressut=demo.saveStudent(stu);
//		String stnm = "demo";
//		String query = "select * from students where name='" + stnm + "'";
//		ResultSet res = stmt.executeQuery(query);
//		System.out.println(stnm);
//		while (res.next()) {
//			int id = Integer.parseInt(res.getString(1));
//			int age = Integer.parseInt(res.getString(2));
//			String email = res.getString(3);
//			String name = res.getString(4);
//			assertEquals(stu.name,name);
//			assertEquals(stu.age,age);
//		assertEquals(stu.email,email);
//		}
//
//	}
		}

}
