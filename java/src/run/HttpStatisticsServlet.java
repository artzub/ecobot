package run;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hp on 23.03.2014.
 */
public class HttpStatisticsServlet extends HttpServlet {
    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String from = request.getParameter("from");
        String to = request.getParameter("to");

        request.getSession().setAttribute("io", from);
        request.getSession().setAttribute("jo", to);*/
        
        response.sendRedirect("data.jsp");
    }
}
