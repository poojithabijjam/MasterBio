
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class MyClass
 */
@WebServlet("/MyClass")
//@MultipartConfig(maxFileSize = 16177215)
public class MyClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.setContentType("text/html");
//		
//	         
//		
//		PrintWriter pw=response.getWriter();
//				
//		String name=request.getParameter("name");
//		String roll=request.getParameter("roll");
//		pw.write(name);
//		pw.write(roll);
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//			String url="jdbc:oracle:thin:@localhost:1521:xe";
//			Connection con=DriverManager.getConnection(url,"SYSTEM","poojitha");
//		PreparedStatement stmt=con.prepareStatement("insert into basics values(?,?)");
//stmt.setString(1, name);
//stmt.setString(2, roll);
//		// String sql = ;
//         
//int row = stmt.executeUpdate();
//if (row > 0) {
//   System.out.println("File uploaded and saved into database");
//}
//else {
//
//   System.out.println("error");
//
//}con.close();
//		
//		}
//		catch(SQLException e) {
//			  System.out.println("sqlerror");
//		}
//		catch(Exception e) {
//			  System.out.println("error");
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
PrintWriter pw=response.getWriter();
		
response.setContentType("text/html");

		
/*String name=request.getParameter("name");
String roll=request.getParameter("roll");
pw.write(name);
pw.write(roll);*/
try {
	Class.forName("oracle.jdbc.OracleDriver");
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	Connection con=DriverManager.getConnection(url,"SYSTEM","madhu123");
	
/*PreparedStatement stmt=con.prepareStatement("insert into basics values(?,?)");
stmt.setString(1, roll);
stmt.setString(2, name);
 
int row = stmt.executeUpdate();
if (row > 0) {
System.out.println("saved into database");
}
else {

System.out.println("error");

}*/
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from basics");
	pw.print("<html><body><table><th>Roll no</th><th>Name</th>");
	while(rs.next()) {
		String a=rs.getString(1);
		String b=rs.getString(2);
		pw.print("<tr><th>"+a+"</th>");
		pw.print("<th>"+b+"</th></tr>");
	}
	pw.print("</table></body></html>");
	con.close();

}
catch(SQLException e) {
	  System.out.println("sqlerror");
}
catch(Exception e) {
	  System.out.println("error");
}
}
	}


