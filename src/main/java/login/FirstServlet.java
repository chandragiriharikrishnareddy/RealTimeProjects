package login;

import java.io.IOException;
import java.util.regex.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletConfig config;

    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    public ServletConfig getServletConfig() {
        return config;
    }

    public String getServletInfo() {
        return "signup";
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/harikrishnareddy";
        String dbUsername = "root";
        String dbPassword = "Harikrishna@999";
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String pattern="^[a-zA-Z0-9_]{3,16}$";
        String pattern1="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String pattern2="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern p=Pattern.compile(pattern);
        Pattern p1=Pattern.compile(pattern1);
        Pattern p2=Pattern.compile(pattern2);
        Matcher m=p.matcher(username);
        Matcher m1=p1.matcher(email);
        Matcher m2=p2.matcher(password);
        if(username!=null&&email!=null&&password!=null&&confirmPassword!=null) {
        	if(m.matches()&&m1.matches()&&m2.matches()&&password.equals(confirmPassword)) {
        		try {
                    // Load the JDBC driver before connecting
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                        String query = "INSERT INTO student2(username, email, password, confirmpassword) VALUES (?, ?, ?, ?)";
                        PreparedStatement st = con.prepareStatement(query);
                        st.setString(1, username);
                        st.setString(2, email);
                        st.setString(3, password);
                        st.setString(4, confirmPassword);
                        st.executeUpdate();

                        response.sendRedirect("login.html");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "JDBC Driver not found.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
                }
        }
        	}
        }
}
