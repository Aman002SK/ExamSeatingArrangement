package main;

import loginFiles.login;

public class ESAmain {
    public static void main(String[] args) {

        System.out.println("       -- ◘ Exam Seating Arrangement ◘ -- ");
        System.out.println("                 | | | | | | | |         " + "\r\n" );
        System.out.println(" • • • ----------------------------------- • • • ");

        login login = new login();
        login.loginProcess();

    }
    public void exitOperation(){
        System.out.println(" --- ♥ See you again ♥ --- ");

    }
}
