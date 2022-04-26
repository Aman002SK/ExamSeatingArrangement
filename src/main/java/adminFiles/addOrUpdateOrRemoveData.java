package adminFiles;

import redirectFiles.redirectToAvailableFunctions;
import reusableDataFunctions.defaultDataFunctions;

import java.sql.Statement;
import java.util.Scanner;

public class addOrUpdateOrRemoveData {

    public static Statement tempStatement;
    public static int id, batch;
    public static String name, branch;
    defaultDataFunctions defaultDataFunctions = new defaultDataFunctions();
    Scanner s = new Scanner(System.in);

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        addOrUpdateOrRemoveData.id = id;
    }

    public static int getBatch() {
        return batch;
    }

    public static void setBatch(int batch) {
        addOrUpdateOrRemoveData.batch = batch;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        addOrUpdateOrRemoveData.name = name;
    }

    public static String getBranch() {
        return branch;
    }

    public static void setBranch(String branch) {
        addOrUpdateOrRemoveData.branch = branch;
    }


    void CRUDoperation(int id, String name, String branch, int batch) {

        try {
            String query = null;
            switch (adminQueryImplement.selectedCRUD) {
                case 1:
                    System.out.print("    Enter student ID      --» ");
                    setId(s.nextInt());
                    System.out.print("    Enter student Name    --» ");
                    s.nextLine();
                    setName(s.nextLine());
                    System.out.print("    Enter student Branch  --» ");
                    setBranch(s.nextLine());
                    System.out.print("    Enter student Batch   --» ");
                    setBatch(s.nextInt());
                    query = "INSERT INTO StudentData VALUES(" + getId() + ","
                            + "\"" + getName() + "\"" + ","
                            + "\"" + getBranch() + "\"" + ","
                            + getBatch() + ")";
                    break;
                case 2:
                    System.out.print("    Enter student ID where you want update name --» ");
                    setId(s.nextInt());
                    System.out.print("    Enter updated student name for " + getId() + " --» ");
                    s.nextLine();
                    setName(s.nextLine());
                    query = "UPDATE StudentData SET st_name = " + "\"" + getName() + "\"" + " WHERE st_id = " + getId();
                    break;
                case 3:
                    System.out.print("    Enter student ID where you want update branch --» ");
                    setId(s.nextInt());
                    System.out.print("    Enter updated student branch for " + getId() + " --» ");
                    s.nextLine();
                    setBranch(s.nextLine());
                    query = "UPDATE StudentData SET st_branch = " + "\"" + getBranch() + "\"" + " WHERE st_id = " + getId();
                    break;
                case 4:
                    System.out.print("    Enter student ID where you want update batch --» ");
                    setId(s.nextInt());
                    System.out.print("    Enter updated student batch for " + getId() + " --» ");
                    setBatch(s.nextInt());
                    query = "UPDATE StudentData SET batch = " + "\"" + getBatch() + "\"" + " WHERE st_id = " + getId();
                    break;
                case 5:
                    System.out.print("    Enter student ID where you want update name & branch --» ");
                    setId(s.nextInt());
                    System.out.print("    Enter updated student name for " + getId() + "   --» ");
                    s.nextLine();
                    setName(s.nextLine());
                    System.out.print("    Enter updated student branch for " + getId() + " --» ");
                    setBranch(s.nextLine());
                    query = "UPDATE StudentData SET st_name = " + "\"" + getName() + "\"" + "," +
                            "st_branch = " + "\"" + getBranch() + "\"" + " WHERE st_id = " + getId();
                    break;
                case 6:
                    System.out.print("    Enter student ID where you want update branch & batch --» ");
                    setId(s.nextInt());
                    System.out.print("    Enter updated student branch for " + getId() + " --» ");
                    s.nextLine();
                    setBranch(s.nextLine());
                    System.out.print("    Enter updated student batch for " + getId() + "  --» ");
                    setBatch(s.nextInt());
                    query = "UPDATE StudentData SET st_branch = " + "\"" + getBranch() + "\"" + "," +
                            "batch = " + getBatch() + " WHERE st_id = " + getId();
                    break;
                case 7:
                    System.out.print("    Enter student ID where you want update batch & name --» ");
                    setId(s.nextInt());
                    System.out.print("    Enter updated student batch for " + getId() + " --» ");
                    setBatch(s.nextInt());
                    System.out.print("    Enter updated student name for " + getId() + "  --» ");
                    s.nextLine();
                    setName(s.nextLine());
                    query = "UPDATE StudentData SET batch = " + getBatch() + "," +
                            "st_name = " + "\"" + getName() + "\"" + " WHERE st_id = " + getId();
                    break;
                case 8:
                    System.out.print("    Enter student ID where you want update name & branch & batch --» ");
                    setId(s.nextInt());
                    System.out.print("    Enter updated student name for " + getId() + "   --» ");
                    s.nextLine();
                    setName(s.nextLine());
                    System.out.print("    Enter updated student branch for " + getId() + " --» ");
                    setBranch(s.nextLine());
                    System.out.print("    Enter updated student batch for " + getId() + "  --» ");
                    setBatch(s.nextInt());
                    query = "UPDATE StudentData SET st_name = " + "\"" + getName() + "\"" + "," +
                            "st_branch = " + "\"" + getBranch() + "\"" + "," +
                            "batch = " + getBatch() + " WHERE st_id = " + getId();
                    break;
                case 9:
                    System.out.print("    Enter student ID you want delete --» ");
                    setId(s.nextInt());
                    query = "DELETE FROM StudentData WHERE st_id = " + getId();
                    break;
            }

            defaultDataFunctions.connections();
            tempStatement.executeUpdate(query);

            System.out.println("\r\n" + "    Your query executed successfully and updated to StudentData table");

            redirectToAvailableFunctions redirectToAvailableFunctions = new redirectToAvailableFunctions();
            redirectToAvailableFunctions.forAdmin();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
