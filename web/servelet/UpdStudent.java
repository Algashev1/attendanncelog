package servelet;

import connection.StudentDAO;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UpdStudent", value= {"/UpdStudent"})
public class UpdStudent extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            int student_id = Integer.parseInt(request.getParameter("student_id"));
            String fio = request.getParameter("fio");
            int result = StudentDAO.updStudent(student_id, fio);
            jsonEnt.put("result", result);
            out.print(jsonEnt.toString());
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
