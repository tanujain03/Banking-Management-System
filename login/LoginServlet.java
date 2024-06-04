package login;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String url = "jdbc:mysql://localhost:3306/banking_sysytem";
    private static final String username = "root";
    private static final String password = "tanu";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        try{
        	Class.forName("com.mysql.jdbc.Driver");	
        	Connection connection = DriverManager.getConnection(url,username,password);
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, pass);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        response.sendRedirect("openAccount.jsp");
                    } else {
                        response.sendRedirect("Exit.jsp");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Log the error
            System.err.println("Error occurred during database connection or query execution.");
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            // Optionally, redirect to an error page
            response.sendRedirect("error.jsp");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("can't create connection");
		}
    }
}
