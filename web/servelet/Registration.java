package servelet;

import connection.GroupDAO;
import connection.UserDAO;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Registration", value= {"/Registration"})
public class Registration extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            String fio = request.getParameter("fio");
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String number = request.getParameter("number");

            if (!fio.isEmpty() && !mail.isEmpty() && !password.isEmpty() && !name.isEmpty() && !number.isEmpty()) {
                if (GroupDAO.addGroup(number, name) > 0) {
                    int group_id = GroupDAO.getGroupByNumber(number);
                    if (group_id > 0) {
                        if (UserDAO.addUser(fio, mail, password, group_id) > 0) {
                            jsonEnt.put("result", 1); // пользователь добавлен
                            out.print(jsonEnt.toString());
                        } else {
                            GroupDAO.delGroup(group_id);
                            jsonEnt.put("result", -2); // пользователь не добавлен
                            out.print(jsonEnt.toString());
                        }
                    }
                } else {
                    jsonEnt.put("result", -1); // группа не добавлена
                    out.print(jsonEnt.toString());
                }
            } else {
                jsonEnt.put("result", 0); // поля не пустые
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
