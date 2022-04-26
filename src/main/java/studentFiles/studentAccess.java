package studentFiles;

import main.ESAmain;
import redirectFiles.redirectToAvailableFunctions;

import java.util.Scanner;

interface studentTools {
    void studentQueryTools();
}

public class studentAccess implements studentTools {

    public static String executeCurrentQuery;
    Scanner s = new Scanner(System.in);
    int requiredQuery;
    //--> various Query function variables for student ↓
    String queryType1 = "SELECT st_id,st_name,room_no,bench_no FROM StudentSeating WHERE st_id = ";
    String queryType2 = "SELECT st_id,st_name,room_no,bench_no FROM StudentSeating WHERE st_name = ";
    String queryType3 = "SELECT st_id FROM StudentSeating WHERE st_name = ";

    public void studentQueryTools() {

        studentQueryImplement queryImplement = new studentQueryImplement();
        redirectToAvailableFunctions redirectToAvailableFunctions = new redirectToAvailableFunctions();
        ESAmain esaMain = new ESAmain();

        //--> Available options print statements ↓
        System.out.println("    -- Enter the function number to perform -- ");
        System.out.println("          | | | |  ↓ ↓ ↓ ↓ ↓ ↓  | | | |        " + "\r\n" );

        System.out.println("    | 1 -> Find my room and bench number for the exam (*-» primary function)" + "\r\n" + "\r\n" +

                "    ↓ • Following functions are for the StudentDatabase Table • ↓" + "\r\n" + "\r\n" +
                "    | 2 -> Find ID by name" + "\r\n");

        System.out.println("    *--» or Enter 0 to exit the operation");

        System.out.println(); //--> Empty Space

        System.out.print("    Which action do you need to perform from above --> ");

        requiredQuery = s.nextInt();
        System.out.println(); //--> Empty Space

        switch (requiredQuery) {
            case 1:
                System.out.print("    Enter • 1 • to find bench & room by • ID • (or) • 2 • to find by • Name • --» ");
                int temp = s.nextInt();
                switch (temp) {
                    case 1:
                        executeCurrentQuery = queryType1;
                        queryImplement.queryVariation1();
                        break;
                    case 2:
                        executeCurrentQuery = queryType2;
                        queryImplement.queryVariation2();
                        break;
                    default:
                        System.out.println("    Invalid Entry, Please try again");
                        redirectToAvailableFunctions.forStudent();
                }
                break;
            case 2:
                executeCurrentQuery = queryType3;
                queryImplement.queryVariation2();
                break;
            case 3:
                queryImplement.queryVariation3();
            case 0:
                esaMain.exitOperation();
            default:
                System.out.println("    Invalid Entry, Please try again");
                redirectToAvailableFunctions.forStudent();
        }
    }
}
