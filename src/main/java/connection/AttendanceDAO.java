package connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public static int addAttendance (int student_id, int schedule_id, int number) throws SQLException {
        Connection conn = null;
        int result = -1;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("INSERT INTO \"Attendance\" (student_id, schedule_id, \"number\") VALUES (?, ?, ?);");
            stat.setInt(1, student_id);
            stat.setInt(2, schedule_id);
            stat.setInt(3, number);
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

    public static String delAttendance (int schedule_id, int number) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("DELETE FROM \"Attendance\"  WHERE schedule_id = ? AND number = ?;");
            stat.setInt(1, schedule_id);
            stat.setInt(2, number);
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

    public static String delAttendanceByStudent_id (int student_id) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("DELETE FROM \"Attendance\"  WHERE student_id = ?;");
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

    public static String delAttendanceBySchedule_id (int schedule_id) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("DELETE FROM \"Attendance\"  WHERE schedule_id = ?;");
            stat.setInt(1, schedule_id);
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

    public static List<Integer> getAttendance (int schedule_id, int number) throws SQLException {
        Connection conn = null;
        List<Integer> result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("SELECT student_id FROM \"Attendance\"  WHERE schedule_id = ? AND number = ?;");
            stat.setInt(1, schedule_id);
            stat.setInt(2, number);
            ResultSet rs = stat.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getInt(1));
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return result;
    }
}
