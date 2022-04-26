package adminFiles;

import main.ESAmain;
import redirectFiles.redirectToAvailableFunctions;

import java.util.Scanner;

interface adminTools {
    void adminQueryTools();
}

public class adminAccess implements adminTools {

    public static String executeCurrentQuery;

    //--> required query variables to store and implement automatic seating arrangement ↓
    static String asaNewTableQuery;
    static String asaNewTableAddValuesQuery;
    static String asaAddRoomBenchIncQuery;

    //--> various Query function variables for admin ↓
    Scanner s = new Scanner(System.in);
    int requiredQuery;
    String queryType1 = "SELECT * FROM StudentData";
    String queryType2 = "SELECT * FROM StudentData WHERE st_id = ";
    String queryType3 = "SELECT * FROM StudentData WHERE st_name = ";
    String queryType4 = "SHOW TABLES";
    String queryType5 = "SELECT * FROM LoginActivity";

    //--> Query selection process ↓
    public void adminQueryTools() {

        ESAmain esaMain = new ESAmain();
        redirectToAvailableFunctions redirectToAvailableFunctions = new redirectToAvailableFunctions();
        adminQueryImplement queryImplement = new adminQueryImplement(); //--> creating object for adminJavaFiles.adminQueryImplement class to initialize

        //--> Available options print statements ↓
        System.out.println("    -- Enter the function number to perform -- ");
        System.out.println("          | | | |  ↓ ↓ ↓ ↓ ↓ ↓  | | | |        " + "\r\n");

        System.out.println("    | 1 -> Automatic exam seating arrangement (*-» primary function)" + "\r\n" + "\r\n" +
                "    ↓ • Following functions are for the current pursuing StudentData Table • ↓" + "\r\n" + "\r\n" +
                "    | 2 -> View all student data" + "\r\n" +
                "    | 3 -> Find student data by student ID" + "\r\n" +
                "    | 4 -> Find student data by student NAME" + "\r\n" +
                "    | 5 -> Show available tables ( and can view the table if needed ) " + "\r\n" +
                "    | 6 -> C.R.U.D operation for StudentData Table" + "\r\n" +
                "    | 7 -> Show login activities" + "\r\n" +
                "    | 8 -> Add new login id for student or update/delete");

        System.out.println("    *--» or Enter 0 to exit the operation");

        System.out.println(); //--> Empty Space

        System.out.print("    Which action do you need to perform from above --> ");

        requiredQuery = s.nextInt();
        System.out.println(); //--> Empty Space

        //-> redirecting to adminJavaFiles.adminQueryImplement according to the selected function ↓

        switch (requiredQuery) {
            case 1:
                queryImplement.automaticSeating();
                break;
            case 2:
                System.out.println(" **** Processing..... pls wait..☺");
                System.out.println();
                executeCurrentQuery = queryType1;
                queryImplement.queryVariation1();
                break;
            case 3:
                executeCurrentQuery = queryType2;
                queryImplement.queryVariation2();
                break;
            case 4:
                executeCurrentQuery = queryType3;
                queryImplement.queryVariation2();
                break;
            case 5:
                System.out.println("    ↓ ↓ Available tables ↓ ↓" + "\r\n");
                executeCurrentQuery = queryType4;
                queryImplement.queryVariation3();
                break;
            case 6:
                queryImplement.queryVariation4();
                break;
            case 7:
                executeCurrentQuery = queryType5;
                queryImplement.queryVariation1();
            case 8:
                queryImplement.queryVariation5();
                break;
            case 0:
                esaMain.exitOperation();
                break;
            default:
                System.out.println("    Invalid entry, it must be between 0-8");
                redirectToAvailableFunctions.forAdmin();
        }
    }
}
