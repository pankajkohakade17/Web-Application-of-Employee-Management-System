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

@WebServlet("/deleteemp")

public class DeleteEmployee extends HttpServlet{
	Connection con=null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=19991001!!Pp");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("empid"));
		
		PreparedStatement pstmt=null;
		PrintWriter pw=resp.getWriter();
		String query="delete from employee_data where emp_id=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, id);
			int count=pstmt.executeUpdate();
			pw.print("<h1>"+count+" RECORD DELETED SUCCESSFULLY</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
