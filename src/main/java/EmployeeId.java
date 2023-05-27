
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

	@WebServlet("/employeeid1")
	public class EmployeeId extends HttpServlet{
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
		 PreparedStatement  pstmt=null;
		 ResultSet rs=null;
		 PrintWriter pw=resp.getWriter();
		 int id=Integer.parseInt(req.getParameter("employeeid"));
		 
		 String query="select * from employee_data where emp_id=?";
		 try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
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
				pw.print("<td>"+rs.getString(4)+"</td>");
				pw.print("<td>"+rs.getString(5)+"</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
