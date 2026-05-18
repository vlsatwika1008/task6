
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Mobiledetails")
public class Mobiledetails extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String model = request.getParameter("ModelId");
        int price = Integer.parseInt(request.getParameter("Price"));
        String company = request.getParameter("Company");
        String color = request.getParameter("Color");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobile","root","");
            
            // Insert the data
            PreparedStatement ps = con.prepareStatement("INSERT INTO Mobiledetails VALUES(?,?,?,?)");
            ps.setString(1, model);
            ps.setInt(2, price);
            ps.setString(3, company);
            ps.setString(4, color);
            ps.executeUpdate();

            // Display all data
            out.println("<h3>Success! Mobile Data Saved.</h3>");
            out.println("<table border='1'><tr><th>ID</th><th>Price</th><th>Brand</th><th>Color</th></tr>");
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Mobiledetails");
            while(rs.next()) {
                out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getInt(2) + 
                            "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td></tr>");
            }
            out.println("</table><br><a href='index.html'>Add New Record</a>");
            con.close();
        } catch (Exception e) { out.println("Error: " + e.getMessage()); }
    }
}

// http://localhost/phpmyadmin/
// new --dbname =mobile
// open mobile--sql-paste code then go


// folder in xampp/tomcat/webapps
// MobileApp
// |-index.html
// |-WEB-INF----|classes------Mobiledetails.class after compiling Mobiledetails.java it in cmd
//                        with ------ javac -cp "C:\xampp\tomcat\lib\servlet-api.jar" -d . Mobiledetails.java                                                                                                            
//                        |lib------go to MySQL Official Downloads download zip file open copy jar file paste in the lib                
//                             folder
// open with: http://localhost:8080/MobileApp/index.html
