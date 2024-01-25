package test_connexion_db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import test_connexion_db.testconnection;
/**
 * Servlet implementation class testconnect
 */
public class testconnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testconnect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.getWriter().append("Served at: ").append(request.getContextPath());
				testconnection connection = new testconnection();
				List<String> notifications = connection.executerTests(request);
		        response.setContentType("text/html");
		        
		        // Get the output stream of the response
		        PrintWriter out = response.getWriter();
		        
		        // Write HTML content to the response
		        out.println("<html>");
		        out.println("<head><title>My Servlet</title></head>");
		        out.println("<body>");
		        out.println(notifications);
		        out.println("</body>");
		        out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
