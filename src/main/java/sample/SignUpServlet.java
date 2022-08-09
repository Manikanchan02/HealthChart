package sample;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		SignUpRecord sr = createSignUpRecord(request, response);
		
		ServletContext context = getServletContext();
//		String fileName = context.getInitParameter("signUpFileName");
//		File signUpFile = new File(fileName);
//		SignUpFile sf = new SignUpFile(signUpFile);
//		List<SignUpRecord> sr = sf.getAllRecords();
//		SignUpRecord sur = new SignUpRecord();
//		
//		sur.setName(name);
//		sur.setUName(uName);
//		sur.setEmail(mail);
//		sur.setPass(password);

//		sf.saveSignUpRecord(sur);
		
		String dbUrl = context.getInitParameter("dbUrl");
		String username = context.getInitParameter("username");
		String pass = context.getInitParameter("password");
		SignUpDB sud = new SignUpDB(dbUrl, username, pass);
		
		try {
			sud.saveSignUpRecord(sr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/logIn.html");  
		rd.forward(request, response);
	}

	private SignUpRecord createSignUpRecord(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String uName = request.getParameter("uName");
		String mail = request.getParameter("email");
		String password = request.getParameter("pass");
		SignUpRecord sr = new SignUpRecord();
		sr.setUName(uName);
		sr.setName(name);
		sr.setEmail(mail);
		sr.setPass(password);
		
		return sr;
	}

}
