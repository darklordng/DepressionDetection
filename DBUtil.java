import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static Connection connection;
    public String url, password, username;

    //make a db class private
    private DBUtil() {}

    public static synchronized getConnection() throws Exception {
        if (connection!=null){
            return connection;
        }
        else {
            try {
                url = "Enter jdbc url";
                username = "Enter jdbc username";
                password = "Enter jdbc password";

                connection = DriverManager.getConnection(url,username,password);

                return connection;
            }catch (SQLException exception) {
                throw new Exception(exception.getMessage());
            }
        }
    }

    public static synchronized  void closeConnection() throws Exception {
        if (connection != null) {
            try{
                connection.close();
            } catch (SQLException e) {
                throw new Exception(e.getMessage());
            } finally {
                connection = null;
            }
        }
    }

}