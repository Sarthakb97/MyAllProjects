package operation;

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

@WebServlet("/addlink")
public class AddAccount extends HttpServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// fetch Html Data 
		String accname=req.getParameter("accholder");
		String accno=req.getParameter("accnum");
		String initamt=req.getParameter("initialamt");
		String newPin=req.getParameter("newpin");
		
		//parsing
		int ano=Integer.parseInt(accno);
		double inamt=Double.parseDouble(initamt);
		int npin=Integer.parseInt(newPin);
		
		String query="insert into bank_details values(?,?,?,?)";
		PreparedStatement pstmt=null; 
		
		try {
			pstmt=con.prepareStatement(query);
			
			//value set for placeholder
			pstmt.setInt(1, ano);
			pstmt.setString(2, accname);
			pstmt.setDouble(3, inamt);
			pstmt.setInt(4, npin);
			
			pstmt.execute();
			
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1> Account Added Successfully </h1>");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
