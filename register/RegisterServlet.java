package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final String url = "jdbc:mysql://localhost:3306/banking_sysytem";
    private static final String username = "root";
    private static final String password = "tanu";

  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        try {
        	Class.forName("com.mysql.jdbc.Driver");	
        	Connection connection = DriverManager.getConnection(url,username,password); 
            if (userExists(connection, email)) {
                out.println("User Already Exists for this Email Address!!");
                resp.sendRedirect("login.jsp");
            }
           String registerQuery = "INSERT INTO user ( full_name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(registerQuery)){
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, pass);
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                	out.println("Registration Successful!");
                	resp.sendRedirect("login.jsp");
                    
                    out.flush();
            
                } else {
                    out.println("Registration Failed!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("An error occurred while executing the insert statement.");
                System.out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred while connecting to the database.");
           System.out.println(e);
        }

    }
private boolean userExists(Connection connection, String email) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
    }
}
