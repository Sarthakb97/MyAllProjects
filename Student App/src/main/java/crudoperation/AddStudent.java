package crudoperation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addlink")
public class AddStudent extends HttpServlet{
	Connection con;
	@Override
	public void init() throws ServletException {
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // use to load Driver 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","sql@123");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//fetch the html data 
		
		String id=req.getParameter("studentid");
		String name=req.getParameter("studentname");
		String stream=req.getParameter("studentstream");
		String dob=req.getParameter("studentdob");
		
		
		
		Date dw=new Date();
		
		//parsing
		
		int sid=Integer.parseInt(id);
		
		String query="insert into student_info values(?,?,?,?,?)";
		
		PreparedStatement pstmt=null; 
		
		
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setInt(1, sid);
			pstmt.setString(2, name);
			pstmt.setString(3, stream);
			pstmt.setString(4, dob);
			pstmt.setString(5, dw.toString());
			
			
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" record inserted successfully" +"<h1>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
