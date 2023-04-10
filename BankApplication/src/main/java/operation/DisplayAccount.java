package operation;

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
public class DisplayAccount extends HttpServlet {
	Connection con;
	@Override
	public void init() throws ServletException {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","sql@123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		
		String query="select acc_no,acc_name from bank_details";
		
		try {
			stmt=con.createStatement();
			
			rs=stmt.executeQuery(query);
			
			pw.print("<table border='5'");
			pw.print("<tr>");
			pw.print("<th> Account Number </th>");
			pw.print("<th> Account Holder Name </th>");
			pw.print("</tr>");
			
			while(rs.next())
			{
				//data fetch from database
				int accno=rs.getInt(1);
				String name=rs.getString(2);
				
				pw.print("<tr>");
				pw.print("<th>"+accno+"</th>");
				pw.print("<th>"+name+"</th>");
				pw.print("</tr>");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
