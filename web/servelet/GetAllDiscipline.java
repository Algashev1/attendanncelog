package servelet;

import connection.DisciplineDAO;
import connection.StudentDAO;
import objects.Discipline;
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

@WebServlet(name = "GetAllDiscipline", value= {"/GetAllDiscipline"})
public class GetAllDiscipline extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            List<Discipline> disciplines = DisciplineDAO.getAllDiscipline();
            if (disciplines != null) {
                String discipline_ids = "";
                String names = "";
                int result = 0;
                for (Discipline discipline: disciplines) {
                    if (discipline_ids.isEmpty() && names.isEmpty()) {
                        discipline_ids += discipline.getDiscipline_id();
                        names += discipline.getName();
                    }
                    else {
                        discipline_ids += "," + discipline.getDiscipline_id();
                        names += "," + discipline.getName();
                    }
                    result++;
                }
                jsonEnt.put("result", result);
                jsonEnt.put("discipline_ids", discipline_ids);
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
