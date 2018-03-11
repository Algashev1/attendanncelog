package connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    public static String addUser (String fio, String mail, String password, int group_id) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("INSERT INTO \"User\" (fio, mail, password, group_id) VALUES (?, ?, ?, ?);");
            stat.setString(1, fio);
            stat.setString(2, mail);
            stat.setString(3, password);
            stat.setInt(4, group_id);
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

    public static String delUser (int user_id) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("DELETE FROM \"User\" WHERE user_id = ?;");
            stat.setInt(1, user_id);
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

    public static String updUser (int user_id, String fio, String mail, String password) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("UPDATE \"User\" SET fio = ?, mail = ?, password = ? WHERE user_id = ?;");
            stat.setInt(4, user_id);
            stat.setString(1, fio);
            stat.setString(2, mail);
            stat.setString(3, password);
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
