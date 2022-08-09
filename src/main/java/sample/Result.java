package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



//@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public Result() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HealthRecord hr = createHealthRecord(request, response);
		
		ServletContext context = getServletContext();
		
//		String fileName = context.getInitParameter("fileName");
//		File myFile = new File(fileName);
//		HealthDB hf = HealthFile.getHealthFile(myFile);
//		hf.saveHealthRecord(hr);
		String dbUrl = context.getInitParameter("dbUrl");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		HealthDb hd = new HealthDb(dbUrl, username, password);
		
		hd.saveHealthRecord(hr);

		String n = (String) context.getAttribute("user");

		String welcomeMsg = context.getInitParameter("welcomeMsg");

		PrintWriter out = response.getWriter();
		out.print(welcomeMsg + "<br/>" + "<br/>");

		out.print("Hello " + n +" Your Record is Saved Sucessfully <br/>" + "<br/>");
		out.print(hr.toString() + "<br/>");
		
//		out.print("<form action=\"/Health/display\" method=\"get\"> <input type=\"hidden\" name =\"username\" value =\""+userName+\"\"></input> <button type=\"submit\">Show All Details</button> </form>");
//		out.print("<a href=\"/Health/display?username="+userName+"\">Show All Details</a>");

		RequestDispatcher rd = request.getRequestDispatcher("/result.html");
		rd.include(request, response);

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter write = response.getWriter();
		write.print("You don't have active session, Please Login...");
		RequestDispatcher rd = request.getRequestDispatcher("/logIn.html");  
		rd.include(request, response);
	}
	private HealthRecord createHealthRecord(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HealthRecord hr = new HealthRecord();
//		String userID = null;
//		Cookie[] cookies  = request.getCookies();
//		
//		if(cookies == null) {
//			PrintWriter out = response.getWriter();
//			out.print("You don't have active session, Please Login...");
//			RequestDispatcher rd = request.getRequestDispatcher("/logIn.html");  
//			rd.include(request, response);
//		}
//		for (Cookie cookie : cookies) {
//			if(cookie.getName().equals("user")) {
//				userID = cookie.getValue();
//			}
//		}
		HttpSession session = request.getSession(false);
		String userID = (String) session.getAttribute("user");
		String name = request.getParameter("name");

		String age = request.getParameter("age");
		int age1 = Integer.parseInt(age);

		String gender = request.getParameter("gender");

		String bloodP = request.getParameter("bp");
		String[] bPressure = bloodP.split("/");
		int bpSys = Integer.parseInt(bPressure[0]);
		int bpDias = Integer.parseInt(bPressure[1]);

		String weight = request.getParameter("weight");
		float weight1 = Float.parseFloat(weight);

		String pulseR = request.getParameter("pr");
		int pRate = Integer.parseInt(pulseR);

		Date currentDate = new Date();

		hr.setName(name);
		hr.setUserID(userID);
		hr.setAge(age1);
		hr.setGender(gender);
		hr.setBpSys(bpSys);
		hr.setBpDias(bpDias);
		hr.setWeight(weight1);
		hr.setPulseR(pRate);
		hr.setDate(currentDate.toString());

		return hr;
	}
}
