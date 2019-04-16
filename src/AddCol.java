import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Servlet implementation class AddCol
 */
@WebServlet("/AddCol")
public class AddCol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
PrintWriter pw=response.getWriter();
		
		response.setContentType("text/html");

				
		String Cname=request.getParameter("Cname");
	//	pw.print(Cname);
		
		String Ctype=request.getParameter("Ctype");
	//	pw.print(Ctype);
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			Connection con=DriverManager.getConnection(url,"SYSTEM","madhu123");
			Statement st=con.createStatement();
			if(Ctype.equals("varchar")) {
				st.executeQuery("ALTER TABLE basics ADD "+Cname+" "+Ctype+"(20)");
			}
			else
			st.executeQuery("ALTER TABLE basics ADD "+Cname+" "+Ctype+"");
			
			String to = "poojitha1589@gmail.com";
	        String subject ="New Column added into the student database";
	        String message =  "Hello sir\n"+Cname+"is added of type"+Ctype;
	        String user = "poojitha1589@gmail.com";
	        String pass = "poojitha@123";
	        SendMail.send(to,subject, message, user, pass);
	        pw.println("<form method=\"post\" action=\"login.html\">");
            pw.println("<input type=\"submit\" name=\"logout\" " + "value=\"Logout\">");
            pw.println("</form>");
            HttpSession session=request.getSession();  
            session.setAttribute("Cname",Cname);  
            session.setAttribute("Ctype",Ctype);  
           
            
           pw.println("<form method=\"post\" action=\"AddStud.jsp\">");
//            pw.println("<input type=\"text\" name=\"col\" " + "value=\"Logout\">");
//            
            pw.println("<input type=\"submit\" name=\"add\" " + "value=\"add\">");
            pw.println("</form>");
        RequestDispatcher rd1=request.getRequestDispatcher("./AddStud.jsp");
          rd1.include(request,response);
	
			con.close();

		}
		catch(SQLException e) {
			e.printStackTrace();
			  System.out.println("sqlerror");
		}
		catch(Exception e) {
			e.printStackTrace();
			  System.out.println("error");
		}
	
	}

}
