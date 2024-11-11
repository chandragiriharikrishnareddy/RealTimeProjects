package login;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SecondServlet")

public class SecondServlet implements Servlet {
	ServletConfig config;
	public void init(ServletConfig config) {
		this.config=config;
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return config;
	}
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return "login";
	}
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstname1=request.getParameter("username1");
		String secondname1=request.getParameter("password1");
		String url="jdbc:mysql://localhost:3306/harikrishnareddy";
		String username="root";
		String password="Harikrishna@999";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			String qry="select * from student2 where username=? and password=?";
			PreparedStatement ps=con.prepareCall(qry);
			ps.setString(1, firstname1);
			ps.setString(2,secondname1);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				((HttpServletResponse) response).sendRedirect("LoginSuccess.html");
			}else {
				((HttpServletResponse) response).sendRedirect("login.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

