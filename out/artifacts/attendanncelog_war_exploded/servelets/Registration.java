package servelet;

import org.json.JSONException;
import org.json.JSONObject;
import server.WorkWithObjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Registration", value= {"/Registration"})
public class Registration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            HttpSession s = request.getSession(true);
            String fio = s.getAttribute("fio").toString();
            String mail = s.getAttribute("mail").toString();
            String password = s.getAttribute("password").toString();
            String number = s.getAttribute("number").toString();


            Connection conn;

            Integer exerciseId = Integer.parseInt(s.getAttribute("exerciseId").toString());
            int maxTime = Integer.parseInt(request.getParameter("maxTime"));
            int minTime = Integer.parseInt(request.getParameter("minTime"));
            int fullTime = Integer.parseInt(request.getParameter("fullTime"));
            int error = Integer.parseInt(request.getParameter("error"));
            int speed = Integer.parseInt(request.getParameter("speed"));


            WorkWithObjects.addStatistics(exerciseId, userId ,maxTime, minTime, fullTime, error, speed);
            jsonEnt.put("result",  "");
            out.print(jsonEnt.toString());
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {

        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {

        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}