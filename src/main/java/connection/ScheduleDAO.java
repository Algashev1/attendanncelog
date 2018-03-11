package connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScheduleDAO {

    public static String addSchedule (int discipline_id, int group_id) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("INSERT INTO \"schedule\" (discipline_id, group_id) VALUES (?, ?);");
            stat.setInt(1, discipline_id);
            stat.setInt(2, group_id);
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
}
