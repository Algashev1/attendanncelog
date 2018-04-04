package servelet;

import connection.StudentDAO;
import objects.Student;
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
import java.util.List;

@WebServlet(name = "GetStudents", value= {"/GetStudents"})
public class GetStudents extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        //test
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            HttpSession s = request.getSession(true);
            int group_id = (Integer)s.getAttribute("group_id");
            List<Student> students = StudentDAO.getStudentsByGroup(group_id);
            if (students != null) {
                String student_ids = "";
                String names = "";
                int result = 0;
                for (Student student: students) {
                    if (student_ids.isEmpty() && names.isEmpty()) {
                        student_ids += student.getStudent_id();
                        names += student.getFio();
                    }
                    else {
                        student_ids += "," + student.getStudent_id();
                        names += "," + student.getFio();
                    }
                    result ++;
                }
                jsonEnt.put("result", result);
                jsonEnt.put("student_ids", student_ids);
                jsonEnt.put("names", names);
                out.print(jsonEnt.toString());
            }
            else {
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
