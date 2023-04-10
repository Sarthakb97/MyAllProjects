package BankController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mainlink")
public class bankcontroller extends HttpServlet {
	static Connection con;

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
	
	
	// Deposite and Withdraw page
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	// fetch the data from html
		String accno=req.getParameter("accno");
		String amount=req.getParameter("amount");
		String buton=req.getParameter("buton");
		String pin=req.getParameter("pinnum");
	//Parsing 
		int accNum=Integer.parseInt(accno);
		double amt=Double.parseDouble(amount);
		int pinnum=Integer.parseInt(pin);
		
	String query="select * from bank_details";
	Statement stmt=null;
	
	ResultSet rs=null;
	
	double newAmt=0;
	
	boolean status=false;
	PrintWriter pw=resp.getWriter();
	
	if(buton.equals("Withdraw"))
	{
		try {
			stmt=con.createStatement();
			
			rs=stmt.executeQuery(query);
			
			while(rs.next()){
				
				//value fetch from database 
				int acn=rs.getInt(1);
				String acname=rs.getString(2);
				double balance=rs.getDouble(3);
				int dbpin=rs.getInt(4);
				
				if(acn==accNum && pinnum==dbpin )
				{
					if(balance>amt)
					{
						newAmt=balance-amt;
						
						// to call update method we create class object
						bankcontroller b1=new bankcontroller();
						b1.updateAmount(accNum, newAmt);
						pw.print(amt+ " Withdraw Successfully, Your total balance is "+newAmt);
						status=true;
					}
					else {
						
						pw.print("Insufficiant balance, Your Balance is "+balance);
					}
				}
			}
				
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	else if (buton.equals("Deposite"))
	{
		try {
			stmt=con.createStatement();
			
			rs=stmt.executeQuery(query);
			
			while(rs.next()){
				
				// Data fetch from database
				int acn=rs.getInt(1);
				String acname=rs.getString(2);
				double balance=rs.getDouble(3);
				int dbpin=rs.getInt(4);
				
				if(acn==accNum && pinnum==dbpin)
				{
					
						newAmt=balance+amt;
						
						// to call update method we create class object
						bankcontroller b1=new bankcontroller();
						b1.updateAmount(accNum, newAmt);
						status=true;
						pw.print("Deposite Successfully, Your total balance is "+newAmt);
					}
				
				}
			
		}	
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	else {
			pw.print("<h1>Incorrect account number or Pin number</h1>");
	}
	
}
	
	// Database Amount update 
	public void updateAmount(int acc, double amt)
	{
		String query1="update bank_details set acc_balance=? where acc_no=?";
		
				
		PreparedStatement pstmt=null;
		
		try {
			pstmt=con.prepareStatement(query1);
			
			pstmt.setDouble(1, amt);
			pstmt.setInt(2, acc);

			pstmt.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
