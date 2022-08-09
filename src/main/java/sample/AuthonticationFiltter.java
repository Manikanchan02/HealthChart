package sample;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

@WebFilter(
        urlPatterns = "/login",
        initParams = @WebInitParam(name = "adminName", value = "admin"))
public class AuthonticationFiltter implements Filter {
	FilterConfig fc;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		fc = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {


		String userName = arg0.getParameter("userName");
		if(userName.equals(fc.getInitParameter("adminName"))) {
			arg1.setContentType("text/html");
			PrintWriter out = arg1.getWriter();
			out.print("Admin facility is not available!");

			out.print("<br /> <a href = logIn.html>Log In</a><br/>");
			out.print("<br /> <a href = signUp.html>Sign Up</a>");
		} else {
			arg2.doFilter(arg0, arg1);
		}

	}

}
