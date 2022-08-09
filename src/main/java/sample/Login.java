package sample;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LogIn Called");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("userName");
		String pass = request.getParameter("userPass");


		boolean passwordMatched = validatePassword(name, pass);



		if(passwordMatched) {

			HttpSession session = request.getSession();
			session.setAttribute("user", name);
			RequestDispatcher rd = request.getRequestDispatcher("welcome");
			rd.forward(request, response);


		} else {
			out.print("Sorry UserName or Password Error!");  
			RequestDispatcher rd=request.getRequestDispatcher("/logIn.html");  
			rd.include(request, response);
		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter write = response.getWriter();
		write.print("You don't have active session, Please Login...");
		RequestDispatcher rd = request.getRequestDispatcher("/logIn.html");  
		rd.include(request, response);
	}

	public boolean validatePassword(String userName, String userPass) {
		ServletContext context = getServletContext();
//		String fileName = context.getInitParameter("signUpFileName");
//		File signUpFile = new File(fileName);
//		SignUpFile sf = new SignUpFile(signUpFile);
		String dbUrl = context.getInitParameter("dbUrl");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		SignUpDB sud = new SignUpDB(dbUrl, username, password);

		String savedpass = sud.getPasswordByUserID(userName);
		
		return userPass.equals(savedpass);
	}

}
