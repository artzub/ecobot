package run;

import bot.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by hp on 23.03.2014.
 */
public class HttpBotServlet extends javax.servlet.http.HttpServlet {
    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        Controller bot = Controller.getInstance();
        if (act.toLowerCase().equals("mconn")) {
            if (!bot.isConnected())
                bot.Connect("COM3", null);
        }
        else if (act.toLowerCase().equals("mdisconn")) {
            if (bot.isConnected())
                bot.Disconnect();
        }
        else if (act.toLowerCase().equals("mleft")) {
            bot.MoveLeft();
        }
        else if (act.toLowerCase().equals("mright")) {
            bot.MoveRight();
        }
        else if (act.toLowerCase().equals("mforward")) {
            bot.MoveForward();
        }
        else if (act.toLowerCase().equals("mback")) {
            bot.MoveBack();
        }
        else if (act.toLowerCase().equals("mstop")) {
            bot.Stop();
        }
        response.sendRedirect("act.jsp");
    }
}
