

package loginFiles;

import redirectFiles.redirectToAvailableFunctions;
import reusableDataFunctions.defaultDataFunctions;
import studentFiles.studentQueryImplement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


interface connection {
    void loginProcess();
}

public class login implements connection {

    public static int loginAccess;
    public static Statement tempStatement;
    public static String adminUserName, adminPassword;
    public static String studentUserName, studentPassword;

    public void loginProcess() {

        Scanner s = new Scanner(System.in);
        redirectToAvailableFunctions redirectToAvailableFunctions = new redirectToAvailableFunctions();
        defaultDataFunctions defaultDataFunctions = new defaultDataFunctions();
        studentQueryImplement studentQueryImplement = new studentQueryImplement();


        //--> throws exception if drives is crashed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }


        //--> Login selection statements
        System.out.print(" Enter 1 to login as Admin (requires userName & password)" + "\r\n" +
                " Enter 2 to login as Student" + "\r\n" + "\r\n" +
                " (or) Enter 0 to Exit the process" + "\r\n" +
                " ---» ");


        //--> Admin Login Process
        try {
            loginAccess = s.nextInt();
            if (loginAccess > 2 || loginAccess <= 0) {
                System.out.println();
                System.out.println(" -- Please re-enter a valid entry --");
                System.out.println();
                loginProcess();

            } else if (loginAccess == 1) {

                s.nextLine(); //--> Reads Login
                System.out.print(" Enter your user name : ");
                adminUserName = s.nextLine();
                System.out.print(" Enter your password  : ");
                adminPassword = s.nextLine();
                System.out.println();


                try {
                    String query1 = "SELECT user_name FROM LoginDataOfAdmin WHERE user_name = " + "'" + adminUserName + "'";
                    defaultDataFunctions.loginDatabaseConnection();
                    ResultSet resultSet = tempStatement.executeQuery(query1);

                    if (resultSet.next()) {
                        if (resultSet.getString("user_name").equals(adminUserName)) {

                            String query2 = "SELECT user_password FROM LoginDataOfAdmin WHERE user_name = " + "'" + adminUserName + "'";
                            defaultDataFunctions.loginDatabaseConnection();
                            ResultSet resultSet1 = tempStatement.executeQuery(query2);

                            if (resultSet1.next()) {
                                if (resultSet1.getString("user_password").equals(adminPassword)) {
                                    reusableDataFunctions.defaultDataFunctions.currentUserName = adminUserName;
                                    defaultDataFunctions.triggerForLoginActivity();

                                    System.out.println(" -- ♠ Hey " + adminUserName + " ♠ -- ");

                                    redirectToAvailableFunctions.forAdmin();
                                } else {
                                    System.out.println(" -- Invalid password -- ");
                                    System.out.println();
                                    loginProcess();
                                }
                            } else {
                                System.out.println(" -- Invalid password -- ");
                                System.out.println();
                                loginProcess();
                            }
                        } else {
                            System.out.println(" -- Invalid username -- ");
                            System.out.println();
                            loginProcess();
                        }
                    } else {
                        System.out.println(" -- Invalid username -- ");
                        System.out.println();
                        loginProcess();
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

                //--> Student Login Process
            } else if (loginAccess == 2) {

                System.out.print(" Enter your name please --» ");
                s.nextLine();
                studentUserName = s.nextLine();
                System.out.print(" Enter your password    --» ");
                studentPassword = s.nextLine();
                studentQueryImplement.username = studentUserName;
                try {
                    defaultDataFunctions.loginDatabaseConnection();
                    String query = "SELECT user_name FROM LoginDataOfStudent WHERE user_name = " + "\"" + studentUserName + "\"";
                    ResultSet resultSet = tempStatement.executeQuery(query);

                    if (resultSet.next()) {
                        if (resultSet.getString("user_name").equals(studentUserName)) {

                            defaultDataFunctions.loginDatabaseConnection();
                            String query1 = "SELECT user_password FROM LoginDataOfStudent WHERE user_name = " + "'" + studentUserName + "'";
                            ResultSet resultSet1 = tempStatement.executeQuery(query1);

                            if (resultSet1.next()) {

                                if (resultSet1.getString("user_password").equals(studentPassword)) {
                                    reusableDataFunctions.defaultDataFunctions.currentUserName = studentUserName;
                                    defaultDataFunctions.triggerForLoginActivity();

                                    System.out.println("\r\n" + " -- ☼ Hey " + studentUserName + " ☼ -- ");

                                    redirectToAvailableFunctions.forStudent();
                                } else {
                                    System.out.println(" -- Invalid password -- ");
                                    System.out.println();
                                    loginProcess();
                                }
                            } else {
                                System.out.println(" -- Invalid password -- ");
                                System.out.println();
                                loginProcess();
                            }
                        } else {
                            System.out.println(" -- Invalid username -- ");
                            System.out.println();
                            loginProcess();
                        }
                    } else {
                        System.out.println(" -- Invalid username -- ");
                        System.out.println();
                        loginProcess();
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch (InputMismatchException e) {
            System.out.println(" Invalid Entry, Please try again");
            System.out.println();
            loginProcess();
        }
    }
}
