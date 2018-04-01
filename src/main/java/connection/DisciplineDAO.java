package connection;

import objects.Discipline;

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

public class DisciplineDAO {

    public static String addDiscipline(String name) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("INSERT INTO \"Discipline\" (name) VALUES (?);");
            stat.setString(1, name);
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

    public static String delDiscipline (int discipline_id) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("DELETE FROM \"Discipline\" WHERE discipline_id = ?;");
            stat.setInt(1, discipline_id);
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

    public static List<Discipline> getAllDiscipline () throws SQLException {
        Connection conn = null;
        List<Discipline> disciplines = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("SELECT * FROM \"Discipline\"");
            ResultSet rs = stat.executeQuery();
            disciplines = new ArrayList<>();
            while (rs.next()) {
                disciplines.add(new Discipline(rs.getInt(1), rs.getString(2)));
            }
        }
        catch (NamingException |SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return disciplines;
    }

    public static String updDiscipline (int discipline_id, String name) throws SQLException {
        Connection conn = null;
        String result = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/LocalAttendanceLogDB");
            conn = ds.getConnection();

            PreparedStatement stat = conn.prepareStatement("UPDATE \"Discipline\" SET name = ? WHERE discipline_id = ?;");
            stat.setInt(2, discipline_id);
            stat.setString(1, name);
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
