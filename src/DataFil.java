

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

/**
 * Servlet implementation class DataFil
 */
@WebServlet("/DataFil")
public class DataFil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataFil() {
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

				
		String name=request.getParameter("Fname");
		pw.print(name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter pw=response.getWriter();
		
		response.setContentType("text/html");

				
		
		
		String par=request.getParameter("Fname");
		//pw.print(par);
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			Connection con=DriverManager.getConnection(url,"SYSTEM","madhu123");
			Statement st=con.createStatement();
			//ResultSet rs=st.executeQuery("select * from basics");
			if(par.equals("roll")) {
				ResultSet rs=st.executeQuery("select * from basics order by ROLL");
				pw.print("<html><body><table><th>Roll no</th><th>Name</th>");
				while(rs.next()) {
					String a=rs.getString(1);
					String b=rs.getString(2);
					pw.print("<tr><th>"+a+"</th>");
					pw.print("<th>"+b+"</th></tr>");
				}
				pw.print("</table></body></html>");
			}
			else if(par.equals("alp")) {
				
				ResultSet rs=st.executeQuery("select * from basics order by NAME");
				pw.print("<html><body><table><th>Roll no</th><th>Name</th>");
				while(rs.next()) {
					String a=rs.getString(1);
					String b=rs.getString(2);
					pw.print("<tr><th>"+a+"</th>");
					pw.print("<th>"+b+"</th></tr>");
				}
				pw.print("</table></body></html>");
				
			}
else if(par.equals("all")) {
				
				ResultSet rs=st.executeQuery("select * from basics");
				pw.print("<html><body><table><th>Roll no</th><th>Name</th>");
				while(rs.next()) {
					String a=rs.getString(1);
					String b=rs.getString(2);
					pw.print("<tr><th>"+a+"</th>");
					pw.print("<th>"+b+"</th></tr>");
				}
				pw.print("</table></body></html>");
				
			}
			
			/* else if(par.equals("gpa")) {
				
				ResultSet rs=st.executeQuery("select * from basics where name LIKE 'A%'");
				pw.print("<html><body><table><th>Roll no</th><th>Name</th>");
				while(rs.next()) {
					String a=rs.getString(1);
					String b=rs.getString(2);
					pw.print("<tr><th>"+a+"</th>");
					pw.print("<th>"+b+"</th></tr>");
				}
				pw.print("</table></body></html>");
				
			}*/
			 
			
			
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
