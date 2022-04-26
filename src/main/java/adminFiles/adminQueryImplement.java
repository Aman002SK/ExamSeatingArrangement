package adminFiles;

import java.sql.*;

import redirectFiles.redirectToAvailableFunctions;
import reusableDataFunctions.defaultDataFunctions;

public class adminQueryImplement extends adminAccess {

    // temporary variables to store and reuse in all query types ↓
    public static Statement tempStatement; //--> Statement to store temporary
    public static ResultSet tempResultSet; //--> ResultSet to store temporary
    public static int tempSize, rowSize;
    public static String tempQuery;
    public static String requiredTable;
    public static int selectedCRUD, querySelectColumnName;
    static int availabilityCheck;
    static int temp = 1; //--> to store updated value of student ID in end of j loop
    static String tableName;

    redirectToAvailableFunctions redirectToAvailableFunctions = new redirectToAvailableFunctions();
    defaultDataFunctions defaultDataFunctions = new defaultDataFunctions();

    // ****---> SELECT * FROM StudentData WHERE st_id = " "
    String tempQueryValue;

    // ****---> SELECT * FROM StudentData
    public void queryVariation1() {

        try {
            defaultDataFunctions.connections(); //--> Calls default connection process
            tempQuery = adminAccess.executeCurrentQuery;
            ResultSet result = tempStatement.executeQuery(tempQuery);
            tempResultSet = result;

            defaultDataFunctions.findColumnSize(); //--> Calls default findColumn count Process

            defaultDataFunctions.printQuery();
            if (executeCurrentQuery.equals(queryType1)) {
                redirectToAvailableFunctions.forAdmin();
            } else if (executeCurrentQuery.equals(queryType5)) {
                redirectToAvailableFunctions.forAdmin();
            } else
                queryVariation3();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void queryVariation2() {

        if (executeCurrentQuery.equals(queryType2)) {
            System.out.print("    Enter the student ID --» ");
            tempQueryValue = s.nextLine();
        } else if (executeCurrentQuery.equals(queryType3)) {
            System.out.print("    Enter the student NAME --» ");
            tempQueryValue = s.nextLine();
        }

        try {
            defaultDataFunctions.connections();
            tempQuery = adminAccess.executeCurrentQuery;

            if (executeCurrentQuery.equals(queryType2)) {
                ResultSet result = tempStatement.executeQuery(tempQuery + tempQueryValue);
                tempResultSet = result;
            } else if (executeCurrentQuery.equals(queryType3)) {
                ResultSet result = tempStatement.executeQuery(tempQuery + "'" + tempQueryValue + "'");
                tempResultSet = result;
            }


            defaultDataFunctions.findColumnSize();

            defaultDataFunctions.printQuery();

            redirectToAvailableFunctions.forAdmin();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void queryVariation3() {

        try {
            System.out.print("\r\n" + "    Enter 1 to select and view a table (or) Enter 0 to redirect --» ");
            int temp = s.nextInt();
            if (temp == 1) {
                System.out.print("    Enter any one of the above table name to view --» ");
                s.nextLine();
                requiredTable = s.nextLine();
                System.out.println();
                String query = "SELECT * FROM " + requiredTable;

                defaultDataFunctions.connections(); //--> Calls default connection process

                ResultSet result = tempStatement.executeQuery(query);
                tempResultSet = result;

                defaultDataFunctions.findColumnSize(); //--> Calls default findColumn count Process

                defaultDataFunctions.printQuery();

                redirectToAvailableFunctions.forAdmin();
            } else
                redirectToAvailableFunctions.forAdmin();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void defaultCRUDinputMethodCall() {
        addOrUpdateOrRemoveData valuesOfCRUD = new addOrUpdateOrRemoveData();
        valuesOfCRUD.CRUDoperation(addOrUpdateOrRemoveData.id, addOrUpdateOrRemoveData.name,
                addOrUpdateOrRemoveData.branch, addOrUpdateOrRemoveData.batch);
    }

    public void queryVariation4() {
        System.out.print("    Enter • 1 • to add new student data (or) • 2 • to update a student value " +
                "(or) • 3 • to delete a student data --» ");
        int querySelect = s.nextInt();

        switch (querySelect) {
            case 1:
                selectedCRUD = querySelect;
                defaultCRUDinputMethodCall();
                break;
            case 2:
                System.out.print("\r\n" + "    How many values need to be updated in table by student ID ↓ " + "\r\n" +
                        "    Enter either 1 or 2 or 3 --» ");
                int querySelectToUpdate = s.nextInt();
                System.out.println();

                switch (querySelectToUpdate) {
                    case 1:
                        System.out.print("\r\n" + "    Enter 1 to update name (or) 2 to update branch (or) 3 to update batch " + "\r\n" +
                                "    --» ");
                        querySelectColumnName = s.nextInt();
                        selectedCRUD = querySelect + querySelectColumnName - 1;
                        defaultCRUDinputMethodCall();
                        break;
                    case 2:
                        System.out.print("\r\n" + "    Enter 1 to update name & branch (or) 2 to update branch & batch " +
                                "(or) 3 to update batch & name " + "\r\n" +
                                "    --» ");
                        querySelectColumnName = s.nextInt();
                        selectedCRUD = querySelect + querySelectColumnName + 2;
                        defaultCRUDinputMethodCall();
                        break;
                    case 3:
                        selectedCRUD = querySelect + 6;
                        defaultCRUDinputMethodCall();
                        break;
                }
            case 3:
                selectedCRUD = querySelect + 6;
                defaultCRUDinputMethodCall();
                break;
        }
        redirectToAvailableFunctions.forAdmin();
    }

    public void queryVariation5() {

        System.out.print("    Enter 1• to add (or) 2• to update (or) 3• to delete --» ");
        int requiredQuery = s.nextInt();

        switch (requiredQuery) {
            case 1:
                System.out.print("    Name of the student --» ");
                String userName = s.nextLine();
                System.out.print("    Give a temporary password for the Student --» ");
                String userPassword = s.nextLine();

                try {
                    String query = "INSERT INTO LoginDataOfStudent (user_name,user_password) values (" +
                            "'" + userName + "'" + "," + "'" + userPassword + "'" + ")";
                    defaultDataFunctions.loginDatabaseConnection();
                    tempStatement.executeUpdate(query);
                    System.out.println("\r\n" + "    • Successfully added a new student login for " + userName + "•");

                    redirectToAvailableFunctions.forAdmin();

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 2:
                System.out.print("    Enter 1• to update name (or) 2• to update password --» ");
                int nameOrPassword = s.nextInt();
                if (nameOrPassword == 1) {
                    try {
                        System.out.print("    Old user name of the student to update --» ");
                        s.nextLine();
                        String oldName = s.nextLine();
                        System.out.print("    New user name for " + oldName + " --» ");
                        String newName = s.nextLine();
                        String nameQuery = "UPDATE LoginDataOfStudent set user_name = " +
                                "'" + newName + "'" + "WHERE user_name = " + "'" + oldName + "'";
                        defaultDataFunctions.loginDatabaseConnection();
                        tempStatement.executeUpdate(nameQuery);
                        System.out.println("\r\n" + "    • Successfully updated student user name from " +
                                oldName + " to " + newName + " •");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (nameOrPassword == 2) {
                    try {
                        System.out.print("    Enter user name where the password to be updated --» ");
                        s.nextLine();
                        String name = s.nextLine();
                        System.out.print("    Give a temporary password for the Student --» ");
                        String newPassword = s.nextLine();
                        String nameQuery = "UPDATE LoginDataOfStudent set user_password = " +
                                "'" + newPassword + "'" + "WHERE user_name = " + "'" + name + "'";
                        defaultDataFunctions.loginDatabaseConnection();
                        tempStatement.executeUpdate(nameQuery);
                        System.out.println("\r\n" + "    • Successfully updated password for " + name + " •");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                break;
            case 3:
                try {
                    System.out.print("    Enter user name which need to deleted from student login --» ");
                    s.nextLine();
                    String name = s.nextLine();
                    String deleteQuery = "DELETE FROM LoginDataOfStudent WHERE user_name = " + "'" + name + "'";
                    defaultDataFunctions.loginDatabaseConnection();
                    tempStatement.executeUpdate(deleteQuery);
                    System.out.println("\r\n" + "    • Successfully deleted student login for " + name + " •");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
        }
        redirectToAvailableFunctions.forAdmin();
    }

    static int tempDuplicate,duplicateCount;
    public void automaticSeating() {

        reusableDataFunctions.defaultDataFunctions.tempTableNameForRowSize = "StudentData";

        System.out.println(" **** Fetching minimum number of benches required..... pls wait..☺ " + "\r\n");
        defaultDataFunctions.findRowSize();
        System.out.print("    * ↓ Make sure the benches are available for all " + rowSize + " students ↓ *" + "\r\n" + "\r\n" +
                "    Enter number of rooms available --» ");
        int numberOfRooms = s.nextInt();
        System.out.println();
        System.out.println("    * ↓ Please specify the rooms in a specific order ↓ * ");
        System.out.println();

        int tempRoomIncrement, tempBenchIncrement; //--> to increment and loop with roomNumbers and noOfBench

        int[] roomNumbers = new int[numberOfRooms];
        int[] noOfBench = new int[numberOfRooms];

        for (int i = 0; i < numberOfRooms; i++) {
            int tmp = i + 1;
            System.out.print("    Enter room number " + tmp + " --» ");
            roomNumbers[i] = s.nextInt();

            if (!(tempDuplicate == roomNumbers[i])){
                System.out.print("    Enter number beches available in room number " + roomNumbers[i] + " --» ");
                noOfBench[i] = s.nextInt();
                System.out.println();
                availabilityCheck += noOfBench[i];
                tempDuplicate = roomNumbers[i];

            }else {
                System.out.println( "    **** Process terminated because you have entered duplicate room number" );
                redirectToAvailableFunctions.forAdmin();
            }
        }


        for (int i=0; i<roomNumbers.length; i++){
            for (int j=i+1; j<roomNumbers.length; j++){
                if (roomNumbers[i] == roomNumbers[j]){
                    System.out.println( " **** Process is terminated due to duplicate room numbers" );
                    i=100;
                    redirectToAvailableFunctions.forAdmin();
                    break;
                }
            }
        }


        if (availabilityCheck < rowSize) {
            System.out.print("    **--» It seems like you have insufficient bench for the students (" + rowSize + " seats required)" +
                    " but you have provided only " + availabilityCheck + "\r\n" +
                    "    Enter 1 to re-assign or 2 to Exit this operation --» ");
            int temp = s.nextInt();

            if (temp == 1) {
                automaticSeating();
            } else
                redirectToAvailableFunctions.forAdmin();

        } else {
            System.out.print("    Give a name to the table to store the seating arrangement --» ");
            s.nextLine();
            tableName = s.nextLine();
            System.out.println("\r\n" + " **** Processing..... pls wait..☺");
            System.out.println();

            try {
                asaNewTableQuery = "CREATE TABLE " + tableName + " LIKE StudentData";
                asaNewTableAddValuesQuery = "INSERT INTO " + tableName + " SELECT * FROM StudentData";
                asaAddRoomBenchIncQuery = "ALTER TABLE " + tableName + " ADD COLUMN room_no INT,ADD COLUMN bench_no INT,ADD COLUMN auto_inc INT UNIQUE KEY AUTO_INCREMENT";

                defaultDataFunctions.connections();

                tempStatement.executeUpdate(asaNewTableQuery); //--> copy table structure from StudentData table to "+tableName+"

                defaultDataFunctions.connections();

                tempStatement.executeUpdate(asaNewTableAddValuesQuery); //--> copy values from StudentData table

                defaultDataFunctions.connections();

                tempStatement.executeUpdate(asaAddRoomBenchIncQuery); //--> add room and bench column to "+tableName+" table

                for (int i = 0; i < numberOfRooms; i++) {
                    tempRoomIncrement = roomNumbers[i];
                    for (int j = 1; j <= noOfBench[i]; j++) {
                        tempBenchIncrement = j;
                        String asaUpdateRoomQuery = "UPDATE " + tableName + " SET room_no = " + tempRoomIncrement + " WHERE auto_inc =";
                        String asaUpdateBenchQuery = "UPDATE " + tableName + " SET bench_no = " + tempBenchIncrement + " WHERE auto_inc =";

                        defaultDataFunctions.connections();

                        tempStatement.executeUpdate(asaUpdateRoomQuery + temp);

                        defaultDataFunctions.connections();

                        tempStatement.executeUpdate(asaUpdateBenchQuery + temp);
                        temp++;
                    }
                }

                // Drop auto increment column which used temporary for ASA
                defaultDataFunctions.connections();
                tempStatement.executeUpdate("ALTER TABLE " + tableName + " DROP COLUMN auto_inc");

                // below 3 steps is to update the seating for student login
                defaultDataFunctions.connections();
                tempStatement.executeUpdate("DROP TABLE StudentSeating");

                defaultDataFunctions.connections();
                tempStatement.executeUpdate("CREATE TABLE StudentSeating LIKE " + tableName);

                defaultDataFunctions.connections();
                tempStatement.executeUpdate("INSERT INTO StudentSeating SELECT * FROM " + tableName);

                System.out.println("     Seating arrangement has generated successfully and stored in " + tableName + " Table");
                System.out.println();
                System.out.print("     Enter • 1 • to view the generated table (or) • 2 • skip --» ");
                int tempNextFunction = s.nextInt();
                System.out.println();

                //--> To print "+tableName+" table
                if (tempNextFunction == 1) {

                    defaultDataFunctions.connections();

                    ResultSet result = tempStatement.executeQuery("SELECT * FROM " + tableName + "");
                    tempResultSet = result;

                    defaultDataFunctions.findColumnSize();

                    defaultDataFunctions.printQuery();

                    redirectToAvailableFunctions.forAdmin();

                } else
                    redirectToAvailableFunctions.forAdmin();

            } catch (Exception e) {
                System.out.println(" **** Process is terminated due to ( " + e + " )");

                try {
                    defaultDataFunctions.connections();
                    tempStatement.executeUpdate("DROP TABLE " + tableName);

                    redirectToAvailableFunctions.forAdmin();

                }catch (Exception e0){

                    redirectToAvailableFunctions.forAdmin();
                }
            }
        }
    }
}
