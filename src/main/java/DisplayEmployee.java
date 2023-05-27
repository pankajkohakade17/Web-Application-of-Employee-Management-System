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

@WebServlet("/displayEmployee")
public class DisplayEmployee extends HttpServlet{
    Connection con=null;
    @Override
 public void init() throws ServletException {
 	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=19991001!!Pp");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 Statement stmt=null;
	 ResultSet rs=null;
	 PrintWriter pw=resp.getWriter();
	 String query="select *,DATE_FORMAT(joining_date, '%d-%m-%Y') as formatted_joining_date,DATE_FORMAT(birth_date, '%d-%m-%Y') as formatted_birth_date from employee_data";
	 try {
		stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		pw.print("<table align='center' border='1' rules='all' cellpadding='10' bgcolor='green'style='color:white;width:70%;height:50%;'>");
		pw.print("<tr align='center'>");
		pw.print("<th>EMPLOYEE ID</th>");
		pw.print("<th>EMPLOYEE NAME</th>");
		pw.print("<th>EMPLOYEE SALARY</th>");
		pw.print("<th>BIRTHDATE</th>");
		pw.print("<th>JOINING DATE</th>");
		pw.print("</tr>");
		
		while(rs.next()) {
			pw.print("<tr align='center'>");
			pw.print("<td>"+rs.getInt(1)+"</td>");
			pw.print("<td>"+rs.getString(2)+"</td>");
			pw.print("<td>"+rs.getDouble(3)+"</td>");
			pw.print("<td>"+rs.getString("formatted_birth_date")+"</td>");
			pw.print("<td>"+rs.getString("formatted_joining_date")+"</td>");
			pw.print("</tr>");
		}
		pw.print("</table>");
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
