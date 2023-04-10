package crudoperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deletelink")
public class DeleteStudent extends HttpServlet {
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//fetch the html data 
		
		String id=req.getParameter("studentid");
		String name=req.getParameter("studentname");
		String stream=req.getParameter("studentstream");
		
		//parsing
		
		int sid=Integer.parseInt(id);
		
		String query="delete from student_info where student_id=?";
		
		PreparedStatement pstmt=null;
		
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setInt(1, sid);
			
			int count=pstmt.executeUpdate();
			
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" record delete successfully" +"<h1>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
