

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Log
 */
@WebServlet("/Log")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
PrintWriter pw=response.getWriter();
		
		response.setContentType("text/html");

				pw.write("success");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	PrintWriter pw=response.getWriter();
		
		response.setContentType("text/html");
String uname=request.getParameter("uname");
String pwd=request.getParameter("pwd");
PreparedStatement ps;

				//pw.write("success");
				pw.write(uname);
				pw.write(pwd);
				try {
					Class.forName("oracle.jdbc.OracleDriver");
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					Connection con=DriverManager.getConnection(url,"SYSTEM","madhu123");
					 ps=con.prepareStatement("select * from basics where ROLL=?");
                     ps.setString(1, uname);
                    // ps.setString(2, pwd);
                    ResultSet rs=ps.executeQuery();
                    if(uname.equals("admin") && pwd.equals("vasavi123")) {
                   	 pw.println("<h3>welcome admin" +" " + uname +"</h3>");
                        RequestDispatcher rd1=request.getRequestDispatcher("./Home.html");
                        rd1.include(request,response);
                        pw.println("<form method=\"post\" action=\"login.html\">");
                        pw.println("<input type=\"submit\" name=\"logout\" " + "value=\"Logout\">");
                        pw.println("</form>");
                    }
                    else if(rs.next()&& pwd.equals("vasavi"))
                     {
                           pw.println("<h3>welcome " +" " + uname +"</h3>");
                           RequestDispatcher rd1=request.getRequestDispatcher("./HomeStud.html");
                           rd1.include(request,response);
                           pw.println("<form method=\"post\" action=\"login.html\">");
                           pw.println("<input type=\"submit\" name=\"logout\" " + "value=\"Logout\">");
                           pw.println("</form>");

                     }
                    
                     
                     else
                     {
                           pw.println("<center><h3>invalid username/password Enter Correct username/password</h3></center>");
                           RequestDispatcher rd2=request.getRequestDispatcher("./login.html");
                           rd2.include(request,response);
                           
                     }
	
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
