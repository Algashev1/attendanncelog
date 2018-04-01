package connection;

import objects.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public static int addStudent (String fio, int group_id) throws SQLException {
        Connection conn = null;
        int result = -1;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("INSERT INTO \"Student\" (fio, group_id) VALUES (?, ?);");
            stat.setString(1, fio);
            stat.setInt(2, group_id);
            stat.execute();
            result = 1;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return result;
    }

    public static String delStudent (int student_id) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("DELETE FROM \"Student\" WHERE student_id = ?;");
            stat.setInt(1, student_id);
            stat.execute();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }
        finally {
            conn.close();
        }
        return result;
    }

    public static String updStudent (int student_id, String fio) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("UPDATE \"Student\" SET fio = ? WHERE student_id = ?;");
            stat.setInt(2, student_id);
            stat.setString(1, fio);
            stat.execute();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }
        finally {
            conn.close();
        }
        return result;
    }

    public static List<Student> getStudentsByGroup (int group_id) throws SQLException {
        Connection conn = null;
        List<Student> students = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("SELECT s.student_id, s.fio FROM \"Student\" s JOIN \"Group\" g ON s.group_id = g.group_id " +
                    "WHERE s.group_id = ?;");
            stat.setInt(1, group_id);
            ResultSet rs = stat.executeQuery();
            students = new ArrayList<>();
            while (rs.next()) {
                students.add(new Student(rs.getInt(1), rs.getString(2)));
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return students;
    }
}
