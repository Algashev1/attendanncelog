package connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static int addUser (String fio, String mail, String password, int group_id) throws SQLException {
        Connection conn = null;
        int result = -1;
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
            result = 1;

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return result;
    }

    public static int getUser (String mail, String password) throws SQLException {
        Connection conn = null;
        int result = -1; // wrong mail
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("SELECT \"user_id\", \"password\" FROM \"User\" WHERE \"mail\" = ?;");
            stat.setString(1, mail);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).equals(password))
                    result = rs.getInt(1);
                else
                    result = -2; // incorrect password

            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            result = 0; // error
        }
        finally {
            conn.close();
        }
        return result;
    }

    public static int getGroupId (int user_id) throws SQLException {
        Connection conn = null;
        int result = -1;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("SELECT \"group_id\" FROM \"User\" WHERE \"user_id\" = ?;");
            stat.setInt(1, user_id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            result = 0; // error
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
