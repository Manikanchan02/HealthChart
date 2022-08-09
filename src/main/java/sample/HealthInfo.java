package sample;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HealthInfo extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletContext context = getServletContext();
		String userName = request.getParameter("userName");
		response.setContentType("text/html");
		//		Cookie cookie = new Cookie("user", userName);
		//		response.addCookie(cookie);

		String welcomeMsg = context.getInitParameter("welcomeMsg");

		PrintWriter out = response.getWriter();
		out.print(welcomeMsg);

		//		out.print("<form action=\"/Health/display\" method=\"get\"><input type=\"hidden\" name =\"username\" value =\""+userName+"\"></input> <button type=\"submit\">Show All Details</button> </form>");
		//		out.print("<a href=\"/Health/display?username="+userName+"\">Show All Details</a>");

		String age = (String) request.getAttribute("age");
		RequestDispatcher rd=request.getRequestDispatcher("/health.html");  
		rd.include(request, response);
	}

}
