package servelet;

import connection.AttendanceDAO;
import connection.ScheduleDAO;
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

@WebServlet(name = "UpdAttendance", value= {"/UpdAttendance"})
public class UpdAttendance extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            int schedules = Integer.parseInt(request.getParameter("schedules"));
            int number = Integer.parseInt(request.getParameter("number"));
            String[] resultStudent = request.getParameter("resultStudent").split(",");
            int count = resultStudent.length;
            AttendanceDAO.delAttendance(schedules,number);
            for (int i = 0; i < count; i++) {
                AttendanceDAO.addAttendance(Integer.parseInt(resultStudent[i]), schedules, number);
            }
            jsonEnt.put("result", 1);
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
