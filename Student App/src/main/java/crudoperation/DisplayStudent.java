package crudoperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/displaylink")
public class DisplayStudent extends HttpServlet {
	Connection con;
	@Override
	public void init() throws ServletException {
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","sql@123");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		
		String query="select * from student_info";
		
		try {
			stmt=con.createStatement();
			
			rs=stmt.executeQuery(query);
			
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th> Student Id</th>");
			pw.print("<th> Student Name</th>");
			pw.print("<th> Stream</th>");
			pw.print("<th> Date of Birth</th>");
			pw.print("<th> Date Of Register</th>");
			
			pw.print("</tr>");

			while(rs.next())
			{
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String stream=rs.getString(3);
				String dob=rs.getString(4);
				String dor=rs.getString(5);
				
				
				pw.print("<tr>");
				pw.print("<th>"+id+"</th>");
				pw.print("<th>"+name+"</th>");
				pw.print("<th>"+stream+"</th>");
				pw.print("<th>"+dob+"</th>");
				pw.print("<th>"+dor+"</th>");
				
				pw.print("</tr>");
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
