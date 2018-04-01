package connection;

import objects.Discipline;
import objects.Schedule;

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

public class ScheduleDAO {

    public static int addSchedule (int discipline_id, int group_id) throws SQLException {
        Connection conn = null;
        int result = -1;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("SELECT * FROM \"schedule\" WHERE \"discipline_id\" = ? AND \"group_id\" = ?");
            stat.setInt(1, discipline_id);
            stat.setInt(2, group_id);
            ResultSet rs = stat.executeQuery();
            if (!rs.next()) {
                stat = conn.prepareStatement("INSERT INTO \"schedule\" (discipline_id, group_id) VALUES (?, ?);");
                stat.setInt(1, discipline_id);
                stat.setInt(2, group_id);
                stat.execute();
                result = 1;
            }
            else {
                result = 0;
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return result;
    }

    public static int delSchedule (int schedule_id) throws SQLException {
        Connection conn = null;
        int result = -1;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("DELETE FROM \"schedule\" WHERE schedule_id = ?;");
            stat.setInt(1, schedule_id);
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

    public static List<Schedule> getSchedules (int group_id) throws SQLException{
        Connection conn = null;
        List<Schedule> schedules = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("SELECT s.schedule_id,  d.name FROM schedule s " +
                    "JOIN \"Discipline\" d ON s.discipline_id = d.discipline_id WHERE s.group_id = ?");

            stat.setInt(1, group_id);
            ResultSet rs = stat.executeQuery();
            schedules = new ArrayList<>();
            while (rs.next()) {
                schedules.add(new Schedule(rs.getInt(1), rs.getString(2)));
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return schedules;
    }
}
