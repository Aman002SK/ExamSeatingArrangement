package reusableDataFunctions;

import adminFiles.addOrUpdateOrRemoveData;
import adminFiles.adminQueryImplement;
import studentFiles.studentQueryImplement;
import loginFiles.login;

import java.sql.*;
import java.time.Instant;


public class defaultDataFunctions {
    public static String tempTableNameForRowSize;
    public static String currentUserName;

    String urlForLogin = "jdbc:mysql://localhost:3306/LoginData";
    String url = "jdbc:mysql://localhost:3306/Student";

    public void connections() {
        try {
            Connection con = DriverManager.getConnection(url, "root", "SQLpassword");
            Statement statement = con.createStatement();
            adminQueryImplement.tempStatement = statement; //--> Saving statement value to execute in resultSet
            addOrUpdateOrRemoveData.tempStatement = statement;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void connectionsForStudent() {
        try {
            Connection con = DriverManager.getConnection(url, "root", "SQLpassword");
            Statement statement = con.createStatement();
            studentQueryImplement.tempStatement = statement; //--> Saving statement value to execute in resultSet
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loginDatabaseConnection() {
        try {
            Connection con = DriverManager.getConnection(urlForLogin, "root", "SQLpassword");
            Statement statement = con.createStatement();
            loginFiles.login.tempStatement = statement;
            adminQueryImplement.tempStatement = statement;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void findColumnSize() {
        try {
            ResultSetMetaData rsmd = adminFiles.adminQueryImplement.tempResultSet.getMetaData();
            int size = rsmd.getColumnCount();
            adminFiles.adminQueryImplement.tempSize = size;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void findColumnSizeForStudent() {
        try {
            ResultSetMetaData rsmd = studentQueryImplement.tempResultSet.getMetaData();
            int size = rsmd.getColumnCount();
            studentQueryImplement.tempSize = size;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void findRowSize() {
        try {
            connections();
            adminQueryImplement.tempResultSet = adminQueryImplement.tempStatement.executeQuery(
                    "SELECT COUNT(*) FROM " + tempTableNameForRowSize);
            adminQueryImplement.tempResultSet.next();
            adminQueryImplement.rowSize = adminQueryImplement.tempResultSet.getInt("COUNT(*)");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printQuery() {
        try {
            ResultSet result = adminQueryImplement.tempResultSet;
            int tempSize = adminQueryImplement.tempSize;
            while (result.next()) {
                String data = "";
                for (int i = 1; i <= tempSize; i++) {
                    data += result.getString(i) + " | ";
                }
                System.out.println("    | " + data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printQueryforStudent() {
        try {
            ResultSet result = studentQueryImplement.tempResultSet;
            int tempSize = studentQueryImplement.tempSize;
            while (result.next()) {
                String data = "";
                for (int i = 1; i <= tempSize; i++) {
                    data += result.getString(i) + " | ";
                }
                System.out.println("    | " + data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void triggerForLoginActivity() {
        try {
            Timestamp timestamp = Timestamp.from(Instant.now());
            Connection con = DriverManager.getConnection(url, "root", "SQLpassword");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO LoginActivity " +
                    "(user_type,user_name,login_period) values (?,?," + "\"" + timestamp + "\"" + ")");
            if (login.loginAccess == 1) {
                preparedStatement.setString(1, "Admin");
                preparedStatement.setString(2, currentUserName);
            } else {
                preparedStatement.setString(1, "Student");
                preparedStatement.setString(2, currentUserName);
            }
            preparedStatement.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println();
        }
    }
}
