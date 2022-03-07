package com.Ashraf;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet

 */

@WebServlet(
value = "/WelcomeServlet",
initParams = 
{
	@WebInitParam(name="welcomeconfig",value ="Welcome Page")
})

//@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ServletConfig config = getServletConfig();
		String s =config.getInitParameter("welcomeconfig");
		out.println("<h1><b>"+s+"</h1></b><hr>");
		
//		Cookie c[]=request.getCookies();
//		out.print("Bye "+c[0].getValue());

		ServletContext context = getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String usernamecon = context.getInitParameter("username");
		String passwordcon = context.getInitParameter("password");
		
		Connection con = null;
		PreparedStatement pst;
		Statement st ;
		ResultSet rs ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,usernamecon,passwordcon);
			st =con.createStatement();
			rs = st.executeQuery("SELECT * FROM LoginDetails");
			out.print("<center><table style='border:2px solid black'><tr style='border:2px solid black'><th>Username</th><th>Password</th></tr>");
			while(rs.next()) {
				out.println("<tr style='border:2px solid black'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
			}
			out.println("</table></center>");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("<a href='ByeServlet'> Logout  </a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
