package sample;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logOut")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public LogOutServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		Cookie cookies=new Cookie("user",""); 
		//		cookies.setMaxAge(0); 
		//		response.addCookie(cookies);
		HttpSession session = request.getSession(false);
		session.invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("/logIn.html");  
		rd.include(request, response);
	}

}
