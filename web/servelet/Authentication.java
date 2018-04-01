package servelet;

import connection.UserDAO;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Authentication", value= {"/Authentication"})
public class Authentication extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");

            if (!mail.isEmpty() && !password.isEmpty()) {
                int id = UserDAO.getUser(mail, password);
                if (id > 0) {
                    HttpSession s = request.getSession(true);
                    s.setAttribute("user_id",id);
                    s.setAttribute("group_id", UserDAO.getGroupId(id));
                }
                jsonEnt.put("result", id);
                out.print(jsonEnt.toString());
            } else {
                jsonEnt.put("result", 0);
                out.print(jsonEnt.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {

        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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