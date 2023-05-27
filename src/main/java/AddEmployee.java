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
@WebServlet("/addemployee")
public class AddEmployee extends HttpServlet{
	Connection con=null;
	@Override
	public void init() throws ServletException {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=19991001!!Pp");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 	String name=req.getParameter("employeename");
    	double salary=Double.parseDouble(req.getParameter("employeesalary"));
    	String birth_date=req.getParameter("birthdate");
    	String joindate=req.getParameter("joiningdate");
    	PreparedStatement pstmt=null;
    	String query="insert into employee_data values(?,?,?,?,?)";
    	try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, 0);
			pstmt.setString(2, name);
			pstmt.setDouble(3, salary);
			pstmt.setString(4, birth_date);
			pstmt.setString(5, joindate);
			int count=pstmt.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" EMPLOYEE ADDED</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
