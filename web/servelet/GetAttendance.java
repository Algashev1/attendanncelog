package servelet;

import connection.AttendanceDAO;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetAttendance", value= {"/GetAttendance"})
public class GetAttendance extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            int schedules = Integer.parseInt(request.getParameter("schedules"));
            int number = Integer.parseInt(request.getParameter("number"));

            List<Integer> listStud = AttendanceDAO.getAttendance(schedules, number);
            String result = "";
            if (listStud != null) {
                for (int i = 0; i < listStud.size(); i++) {
                    if (i == 0) {
                        result += listStud.get(i);
                    }
                    else {
                        result += "," + listStud.get(i);
                    }
                }

            }
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
