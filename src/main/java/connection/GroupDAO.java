package connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupDAO {

    public static String addGroup (String number, String name) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("INSERT INTO \"Group\" (number, name) VALUES (?, ?);");
            stat.setString(1, number);
            stat.setString(2, name);
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

    public static String delGroup (int group_id) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("DELETE FROM \"Group\" WHERE group_id = ?;");
            stat.setInt(1, group_id);
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

    public static String updGroup (int group_id, String number, String name) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("UPDATE \"Group\" SET number = ?, name = ? WHERE group_id = ?;");
            stat.setInt(3, group_id);
            stat.setString(1, number);
            stat.setString(2, name);
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
