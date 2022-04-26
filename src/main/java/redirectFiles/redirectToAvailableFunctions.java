package redirectFiles;

import loginFiles.login;
import main.ESAmain;
import adminFiles.adminAccess;
import studentFiles.studentAccess;

import java.util.Scanner;

public class redirectToAvailableFunctions {

    Scanner s = new Scanner(System.in);
    adminAccess adminAccess = new adminAccess();
    studentAccess studentAccess = new studentAccess();
    ESAmain esaMain = new ESAmain();
    login login = new login();

    public void forAdmin() {
        System.out.println();
        System.out.print("    Enter • 1 -» to redirect to available functions (or) " +
                "Enter • 2 -» to Log Out (or) " +
                "Enter • 3 -» to Exit --» ");
        int tempNextFunction = s.nextInt();
        System.out.println();
        if (tempNextFunction == 1) {
            adminAccess.adminQueryTools();
        } else if (tempNextFunction == 2) {
            System.out.println("    **** Logged Out successfully **** " + "\r\n");
            login.loginProcess();
        } else if (tempNextFunction == 3) {
            esaMain.exitOperation();
        }
    }

    public void forStudent() {
        System.out.println();
        System.out.print("    Enter • 1 -» to redirect to available functions (or) " +
                "Enter • 2 -» to Log Out (or) " +
                "Enter • 3 -» to Exit --» ");
        int tempNextFunction = s.nextInt();
        System.out.println();
        if (tempNextFunction == 1) {
            studentAccess.studentQueryTools();
        } else if (tempNextFunction == 2) {
            System.out.println("    **** Logged Out successfully **** " + "\r\n");
            login.loginProcess();
        } else if (tempNextFunction == 3) {
            esaMain.exitOperation();
        }
    }

}
