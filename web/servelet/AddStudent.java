package servelet;

import connection.StudentDAO;
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

@WebServlet(name = "AddStudent", value= {"/AddStudent"})
public class AddStudent extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            String fio = request.getParameter("fio");
            HttpSession s = request.getSession(true);
            int group_id = (Integer)s.getAttribute("group_id");
            if (!fio.isEmpty()) {
                int result = StudentDAO.addStudent(fio, group_id);
                jsonEnt.put("result", result);
                out.print(jsonEnt.toString());
            } else {
                jsonEnt.put("result", -1);
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
