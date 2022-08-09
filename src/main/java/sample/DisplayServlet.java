package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/display")

public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public DisplayServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter write = response.getWriter();
		//		String user = null;
		//		Cookie[] cookies  = request.getCookies();
		//		if(cookies == null) {
		//			write.print("You don't have active session, Please Login...");
		//			RequestDispatcher rd = request.getRequestDispatcher("/logIn.html");  
		//			rd.include(request, response);
		//		}
		//		for (Cookie cookie : cookies) {
		//			if(cookie.getName().equals("user")) {
		//				user = cookie.getValue();
		//			}
		//		}
		//		

		ServletContext context = getServletContext();

//		String fileName = context.getInitParameter("fileName");
//		File myFile = new File(fileName);
//		HealthFile hf = HealthFile.getHealthFile(myFile);
		HttpSession session = request.getSession(false);
		String userID = (String) session.getAttribute("user");	
		
		String dbUrl = context.getInitParameter("dbUrl");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		HealthDb hd = new HealthDb(dbUrl, username, password);
		
		hd.getAllRecords(userID);
		List<HealthRecord> hr = hd.getAllRecords(userID);
		String header = "<table><tr><th>User-Name</th><th>Full-Name</th><th>Age</th><th>Gender</th><th>Blood Pressure</th><th>Pulse Rate</th><th>Weight</th><th>Date</th></tr>";
		write.print(header);

		for(HealthRecord hrecord : hr) {
			String row = "<tr><td>"+hrecord.getUserID()+"</td>"
					+ "<td>"+hrecord.getName()+"</td>"
					+ "<td>"+hrecord.getAge()+"</td>"
					+"<td>"+hrecord.getGender()+"</td>"
					+"<td>"+hrecord.getBpSys()+"/"+hrecord.getBpDias()+"</td>"
					+"<td>"+hrecord.getPulseR()+"</td>"
					+"<td>"+hrecord.getWeight()+"</td>"
					+"<td>"+hrecord.getDate()+"</td></tr>";
			write.print(row);		
		}
		write.print("</table>");

		RequestDispatcher rd = request.getRequestDispatcher("/display.html");  
		rd.include(request, response);


	}

}
