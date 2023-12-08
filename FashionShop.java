import java.util.Arrays;
import java.util.Scanner;

class FashionShop{

    public static int orderCounter=0;
    public static int orderCapacity=100;
    public static String[][] placeOrder = new String[orderCapacity][6];
    public static final int PROCESSING=0;
    public static final int DELIVERING=1;
    public static final int DELIVERED=2;

    static Scanner input = new Scanner(System.in);

    // Clear Console...
    public final static void clearConsole(){
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        // Handle any exceptions.
        }
    }
    
    // Clear Line...
    public final static void clearInputLine(){
        System.out.print("\033[1A");
        System.out.print("\033[2K");
    }

    // Increase The Order Array Capacity...
    public static void increaseOrderCapacity() {
        int newCapacity = orderCapacity + 100;
        String[][] newPlaceOrder = new String[newCapacity][6];
        
        for (int i = 0; i < orderCounter; i++) {
            for (int j = 0; j < 6; j++) {
                newPlaceOrder[i][j] = placeOrder[i][j];
            }
        }
        placeOrder = newPlaceOrder;
        orderCapacity = newCapacity;
    }
    
    ////////////////--------Delete Order Status Process--------////////////////

    // Order Delete
    public static void deleteOrder(int thisRow){

        String[][] tempArray = new String[orderCounter-1][6];
        int tempIndex = 0; 

        for (int i = 0; i < orderCounter; i++) {
            if (i == thisRow) {
                continue; 
            }
            for (int j = 0; j < 6; j++) {
            tempArray[tempIndex][j] = placeOrder[i][j];
            }
            tempIndex++; 
        }
        placeOrder = tempArray; 
        orderCounter--; 

        System.out.print("              Order Deleted..!");

    }

    // Delete This Order Decition
    public static void deleteThisOrder(int i){
        System.out.print("\n       Do you want to delete this order? (y/n) : ");
        char yOrN=input.next().charAt(0);
        if(yOrN=='y' || yOrN=='Y'){
            deleteOrder(i);
            deleteAnotherOrder(); 
        }else if(yOrN=='n' || yOrN=='N'){
            deleteAnotherOrder();
        }else{
            clearInputLine();
            deleteAnotherOrder();
        }
    }

    // Search Another Order
    public static void deleteAnotherOrder(){
        System.out.print("\n\n       Do you want to delete another order? (y/n) : ");
        char yOrN=input.next().charAt(0);
        if(yOrN=='y' || yOrN=='Y'){
            searchOrder();
        }else if(yOrN=='n' || yOrN=='N'){
            homePage();
        }else{
            clearInputLine();
            deleteAnotherOrder();
        }
    } 

    // [6] Delete Order
    public static void deleteOrder(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                     Delete Order                        =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
       
        System.out.print("\n    Enter Order ID : ");
        String inputId=input.next();
        boolean found = false;
        for (int i = 0; i < orderCounter; i++) {
            if (placeOrder[i][0].equals(inputId)) {
            
                System.out.println("\n\n    Phone Number    : " + placeOrder[i][1]);
                System.out.println("    Size            : " + placeOrder[i][2]);
                System.out.println("    Quantity        : " + placeOrder[i][3]);
                System.out.println("    Amount          : " + placeOrder[i][4]);
    
                deleteThisOrder(i);
                found = true;
                break; 
            }
        }
        if (!found) {
            System.out.println("\n        Invalid ID..");
        } 
        deleteAnotherOrder();
    }

    ////////////////--------Change Order Status Process--------////////////////

    // Change Order Status Options
    public static void changeStatusOptions(int row,int option){

        if(option==2){
            System.out.println("\n      [1] Order Delivering");
            System.out.println("\n      [2] Order Delivered");

            System.out.print("\n  Enter Option : ");
            int inputOption=input.nextInt();
            if(inputOption==1){
                placeOrder[row][5]="1";
                System.out.println("\n      Successful Change Status..");
            }else if(inputOption==2){
                placeOrder[row][5]="2";
                System.out.println("\n      Successful Change Status..");
            }else{
                System.out.println("\n      Invalid Input..");
                changeStatusOptions(row,option);
            }  
        }else if(option==1){
            System.out.println("\n      [1] Order Delivered");

            System.out.print("\n  Enter Option : ");
            int inputOption=input.nextInt();
            if(inputOption==1){
                placeOrder[row][5]="2";
                System.out.println("\n      Successful Change Status..");
            }else{
                System.out.println("\n      Invalid Input..");
                changeStatusOptions(row,option);
            } 
        }else{
            clearInputLine();
            changeStatusOptions(row,option);
        }
    }

    // Change Order Status Decition
    public static void changeThisStatus(int i,int option){
        System.out.print("       Do you want to change this order status? (y/n) : ");
        char yOrN=input.next().charAt(0);
        if(yOrN=='y' || yOrN=='Y'){
            changeStatusOptions(i,option);
        }else if(yOrN=='n' || yOrN=='N'){
            homePage();
        }else{
            clearInputLine();
            changeThisStatus(i,option);
        }
    }

    // Change Another Order Status Decision
    public static void changeStatusAnotherOrder(){
        System.out.print("       Do you want to change order status another order? (y/n) : ");
        char yOrN=input.next().charAt(0);
        if(yOrN=='y' || yOrN=='Y'){
            changeOrderStatus();
        }else if(yOrN=='n' || yOrN=='N'){
            homePage();
        }else{
            clearInputLine();
            changeStatusAnotherOrder();
        }
    }

    // [5] Change Order Status
    public static void changeOrderStatus(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                   Change Order Status                   =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");

        System.out.print("\n    Enter Order ID : ");
        String inputId = input.next();
        boolean found = false;

        for (int i = 0; i < orderCounter; i++) {
            if (placeOrder[i][0].equals(inputId)) {
          
                System.out.println("\n\n    Phone Number    : " + placeOrder[i][1]);
                System.out.println("    Size            : " + placeOrder[i][2]);
                System.out.println("    Quantity        : " + placeOrder[i][3]);
                System.out.println("    Amount          : " + placeOrder[i][4]);

                String status = "";
                if (placeOrder[i][5].equals("0")) {
                    status = "Processing";
                } else if (placeOrder[i][5].equals("1")) {
                    status = "Delivering";
                } else {
                    status = "Delivered";
                }
                System.out.println("    Status          : " + status);
                System.out.println("\n");

                if (placeOrder[i][5].equals("0")) {
                    int option = 2;
                    changeThisStatus(i, option);
                } else if (placeOrder[i][5].equals("1")) {
                    int option = 1;
                    changeThisStatus(i, option);
                } else {
                    System.out.println("\n        Can't change this order status, Order already delivered..!");
                }
                System.out.println("\n");
                found = true; 
                break; 
            }
        }
        if (!found) {
            System.out.println("\n        Invalid ID..");
        }
        searchAnotherOrder();
    }

    ////////////////--------View Report--------////////////////

    //////---------------Customer Reports---------------//////

    //---------------All Customer Reports---------------

    public static void calculateTotalAmountAndQty() {
        String[] customerIds = new String[orderCounter];
        double[] totalAmounts = new double[orderCounter];
        int[] xsQty = new int[orderCounter];
        int[] sQty = new int[orderCounter];
        int[] mQty = new int[orderCounter];
        int[] lQty = new int[orderCounter];
        int[] xlQty = new int[orderCounter];
        int[] xxlQty = new int[orderCounter];
    
        for (int i = 0; i < orderCounter; i++) {
            customerIds[i] = "";
            totalAmounts[i] = 0.0;
            xsQty[i] = 0;
            sQty[i] = 0;
            mQty[i] = 0;
            lQty[i] = 0;
            xlQty[i] = 0;
            xxlQty[i] = 0;
        }
    
        for (int i = 0; i < orderCounter; i++) {
            String customerId = placeOrder[i][1];
            double amount = Double.parseDouble(placeOrder[i][4]);
            int quantity = Integer.parseInt(placeOrder[i][3]);
            String size = placeOrder[i][2];
    
            int index = -1;
            for (int j = 0; j < orderCounter; j++) {
                if (customerIds[j].equals(customerId)) {
                    index = j;
                    break;
                }
            }
    
            if (index == -1) {
                index = findEmptyIndex(customerIds);
                customerIds[index] = customerId;
            }
    
            totalAmounts[index] += amount;
    
            if (size.equals("XS")) {
                xsQty[index] += quantity;
            } else if (size.equals("S")) {
                sQty[index] += quantity;
            } else if (size.equals("M")) {
                mQty[index] += quantity;
            } else if (size.equals("L")) {
                lQty[index] += quantity;
            } else if (size.equals("XL")) {
                xlQty[index] += quantity;
            } else {
                xxlQty[index] += quantity;
            }
        }
    
        System.out.println("\n+----------------------+-------+-------+-------+-------+-------+-------+----------------------+");
        System.out.printf("| %-20s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-20s |\n", "Customer ID",
                                                                         "XS", "S", "M", "L", "XL", "XXL", "Total Amount");
        System.out.println("+----------------------+-------+-------+-------+-------+-------+-------+----------------------+");
        for (int i = 0; i < orderCounter; i++) {
            if (!customerIds[i].equals("")) {
                System.out.printf("| %-20s | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d | %20.2f |\n", customerIds[i], 
                                                    xsQty[i], sQty[i], mQty[i], lQty[i], xlQty[i], xxlQty[i], totalAmounts[i]);
            }
        }
        System.out.println("+----------------------+-------+-------+-------+-------+-------+-------+----------------------+");
    }
    
    public static void allCustomerReports() {
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                  All Customer Reports                   =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");
    
        calculateTotalAmountAndQty();
    
        System.out.print("\n    To Access the Main Menu, Please Enter 0 : ");
        int num = input.nextInt();
    
        if (num == 0) {
            homePage();
        } else {
            clearInputLine();
            allCustomerReports();
        }
    }
    
    //---------------View Customers---------------

    public static void calculateTotalAmountAndQtyAndPrint() {
        String[] customerIds = new String[orderCounter];
        double[] totalAmounts = new double[orderCounter];
        int[] totalQuantities = new int[orderCounter];

        for (int i = 0; i < orderCounter; i++) {
            customerIds[i] = "";
            totalAmounts[i] = 0.0;
            totalQuantities[i] = 0;
        }

        for (int i = 0; i < orderCounter; i++) {
            String customerId = placeOrder[i][1];
            double amount = Double.parseDouble(placeOrder[i][4]);
            int quantity = Integer.parseInt(placeOrder[i][3]);

            int index = i; 
            if (customerIds[index].equals("")) {
                customerIds[index] = customerId;
            }

            totalAmounts[index] += amount;
            totalQuantities[index] += quantity;
        }

        System.out.println("\n+----------------------+------------+----------------------+");
        System.out.printf("| %-20s | %-10s | %-20s |\n", "Customer ID", "ALL QTY", "Total Amount");
        System.out.println("+----------------------+------------+----------------------+");
        for (int i = 0; i < orderCounter; i++) {
            if (!customerIds[i].equals("")) {
                System.out.printf("| %-20s | %-10d | %20.2f |\n", customerIds[i], totalQuantities[i], totalAmounts[i]);
            }
        }
        System.out.println("+----------------------+------------+----------------------+");
    }

    // Calculate and print total amount for each customer without sorting
    public static void viewCustomer() {
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                      View Customers                     =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");

        calculateTotalAmountAndQtyAndPrint();

        System.out.print("\n    To Access the Main Menu, Please Enter 0 : ");
        int num = input.nextInt();

        if (num == 0) {
            homePage();
        } else {
            clearInputLine();
            viewCustomer();
        }
    }

    //---------------Best In Customers---------------

    public static void calculateTotalAmountAndQuantityAndPrint() {
        String[] customerIds = new String[orderCounter];
        double[] totalAmounts = new double[orderCounter];
        int[] totalQuantities = new int[orderCounter];

        for (int i = 0; i < orderCounter; i++) {
            customerIds[i] = "";
            totalAmounts[i] = 0.0;
            totalQuantities[i] = 0;
        }

        for (int i = 0; i < orderCounter; i++) {
            String customerId = placeOrder[i][1];
            double amount = Double.parseDouble(placeOrder[i][4]);
            int quantity = Integer.parseInt(placeOrder[i][3]);

            int index = -1;
            for (int j = 0; j < orderCounter; j++) {
                if (customerIds[j].equals(customerId)) {
                    index = j;
                    break;
                }
            }

            if (index == -1) {
                index = findEmptyIndex(customerIds);
                customerIds[index] = customerId;
            }

            totalAmounts[index] += amount;
            totalQuantities[index] += quantity;
        }

        bubbleSortDescending(customerIds, totalAmounts, totalQuantities);

        System.out.println("\n+----------------------+------------+----------------------+");
        System.out.printf("| %-20s | %-10s | %-20s |\n", "Customer ID", "ALL QTY", "Total Amount");
        System.out.println("+----------------------+------------+----------------------+");
        for (int i = 0; i < orderCounter; i++) {
            if (!customerIds[i].equals("")) {
                System.out.printf("| %-20s | %-10d | %20.2f |\n", customerIds[i], totalQuantities[i], totalAmounts[i]);
            }
        }
        System.out.println("+----------------------+------------+----------------------+");
    }

    public static void bubbleSortDescending(String[] customerIds, double[] totalAmounts, int[] totalQuantities) {
        int n = totalAmounts.length;
        boolean swapped;

        do {
            swapped = false;

            for (int i = 0; i < n - 1; i++) {
                if (totalAmounts[i] < totalAmounts[i + 1]) {

                    double tempAmount = totalAmounts[i];
                    totalAmounts[i] = totalAmounts[i + 1];
                    totalAmounts[i + 1] = tempAmount;

                    int tempQuantity = totalQuantities[i];
                    totalQuantities[i] = totalQuantities[i + 1];
                    totalQuantities[i + 1] = tempQuantity;

                    String tempId = customerIds[i];
                    customerIds[i] = customerIds[i + 1];
                    customerIds[i + 1] = tempId;

                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static int findEmptyIndex(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("")) {
                return i;
            }
        }
        return -1; 
    }

    // Calculate and print total amount for each customer in descending order
    public static void bestInCustomer() {
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                      Best In Customers                  =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");

        calculateTotalAmountAndQuantityAndPrint();

        System.out.print("\n    To Access to Main Menu, Please Enter 0 : ");
        int num = input.nextInt();

        if (num == 0) {
            homePage();
        } else {
            clearInputLine();
            bestInCustomer();
        }
    }

    // Select Customer Reports 
    public static void customerReports(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                     Customer Reports                    =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");
        System.out.println("           [1] Best In Customers");
        System.out.println("\n           [2] View Customers");
        System.out.println("\n           [3] All Customer Reports");
        
        //Select Options
        System.out.print("\n  Enter Option : ");
        int num = input.nextInt();

        if(num==1){
            bestInCustomer();
        }
        else if(num==2){
            viewCustomer();
        }
        else if(num==3){
            allCustomerReports();
        }
        else{
            clearInputLine();
            customerReports(); 
        }
    }

    //---------------------------------Item Reports---------------------------

    public static void bestSellingCategoriesSorted(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                       Sorted by QTY                     =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");

        String[] sizes = { "XS", "S", "M", "L", "XL", "XXL" };

        int[] sizeQuantities = new int[sizes.length];
        double[] sizeTotalAmounts = new double[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            sizeQuantities[i] = 0;
            sizeTotalAmounts[i] = 0.0;
        }

        for (int i = 0; i < orderCounter; i++) {
            String size = placeOrder[i][2];
            int quantity = Integer.parseInt(placeOrder[i][3]);
            double amount = Double.parseDouble(placeOrder[i][4]);

            int sizeIndex = Arrays.asList(sizes).indexOf(size);

            if (sizeIndex != -1) {
                sizeQuantities[sizeIndex] += quantity;
                sizeTotalAmounts[sizeIndex] += amount;
            }
        }

        for (int i = 0; i < sizes.length - 1; i++) {
            for (int j = i + 1; j < sizes.length; j++) {
                if (sizeQuantities[i] < sizeQuantities[j]) {

                    int tempQuantity = sizeQuantities[i];
                    sizeQuantities[i] = sizeQuantities[j];
                    sizeQuantities[j] = tempQuantity;

                    double tempAmount = sizeTotalAmounts[i];
                    sizeTotalAmounts[i] = sizeTotalAmounts[j];
                    sizeTotalAmounts[j] = tempAmount;

                    String tempSize = sizes[i];
                    sizes[i] = sizes[j];
                    sizes[j] = tempSize;
                }
            }
        }

        System.out.println("        +--------+---------+--------------+");
        System.out.printf("        | Size   | QTY     | Total Amount |%n");
        System.out.println("        +--------+---------+--------------+");

        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("        | %-6s | %-7d |  %11.2f |%n", sizes[i], sizeQuantities[i], sizeTotalAmounts[i]);
        }

        System.out.println("        +--------+---------+--------------+");

        System.out.print("\n    To Access the Main Menu, Please Enter 0: ");
        int num = input.nextInt();

        if (num == 0) {
            homePage();
        } else {
            clearInputLine();
            bestSellingCategoriesSorted();
        }
    }

    //----------
    public static void bestSellingItemSortedAmount(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                     Sorted by Amount                    =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");
    
        String[] sizes = { "XS", "S", "M", "L", "XL", "XXL" };
    
        int[] sizeQuantities = new int[sizes.length];
        double[] sizeTotalAmounts = new double[sizes.length];
    
        for (int i = 0; i < sizes.length; i++) {
            sizeQuantities[i] = 0;
            sizeTotalAmounts[i] = 0.0;
        }
    
        for (int i = 0; i < orderCounter; i++) {
            String size = placeOrder[i][2];
            int quantity = Integer.parseInt(placeOrder[i][3]);
            double amount = Double.parseDouble(placeOrder[i][4]);
    
            int sizeIndex = Arrays.asList(sizes).indexOf(size);
    
            if (sizeIndex != -1) {
                sizeQuantities[sizeIndex] += quantity;
                sizeTotalAmounts[sizeIndex] += amount;
            }
        }
    
        for (int i = 0; i < sizes.length - 1; i++) {
            for (int j = i + 1; j < sizes.length; j++) {
                if (sizeTotalAmounts[i] < sizeTotalAmounts[j]) {

                    int tempQuantity = sizeQuantities[i];
                    sizeQuantities[i] = sizeQuantities[j];
                    sizeQuantities[j] = tempQuantity;

                    double tempAmount = sizeTotalAmounts[i];
                    sizeTotalAmounts[i] = sizeTotalAmounts[j];
                    sizeTotalAmounts[j] = tempAmount;
    
                    String tempSize = sizes[i];
                    sizes[i] = sizes[j];
                    sizes[j] = tempSize;
                }
            }
        }
    
        System.out.println("        +--------+---------+--------------+");
        System.out.printf("        | Size   | QTY     | Total Amount |%n");
        System.out.println("        +--------+---------+--------------+");
    
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("        | %-6s | %-7d |  %11.2f |%n", sizes[i], sizeQuantities[i], sizeTotalAmounts[i]);
        }
    
        System.out.println("        +--------+---------+--------------+");
    
        System.out.print("\n    To Access the Main Menu, Please Enter 0: ");
        int num = input.nextInt();
    
        if (num == 0) {
            homePage();
        } else {
            clearInputLine();
            bestSellingItemSortedAmount();
        }
    }

    // Select Item Reports
    public static void itemReports(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                      Item Reports                       =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");
        System.out.println("           [1] Best selling categories sorted by QTY");
        System.out.println("\n           [2] Best selling items sorted by amount");
        
        //Select Options
        System.out.print("\n  Enter Option : ");
        int num = input.nextInt();

        if(num==1){
            bestSellingCategoriesSorted();
        }
        else if(num==2){
            bestSellingItemSortedAmount();
        }
        else{
            clearInputLine();
            itemReports(); 
        }
    }

    //---------------------------------Order Reports---------------------------
    
    //--------------------------All Orders------------------------
    public static void allOrders() {
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                        View Orders                      =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");
    
        // Sort orders based on Order ID
        for (int i = 0; i < orderCounter - 1; i++) {
            for (int j = 0; j < orderCounter - i - 1; j++) {
                if (placeOrder[j][0].compareTo(placeOrder[j + 1][0]) < 0) {
                    // Swap order details (customer ID, size, quantity, total amount, status)
                    for (int k = 0; k < 6; k++) {
                        String tempDetail = placeOrder[j][k];
                        placeOrder[j][k] = placeOrder[j + 1][k];
                        placeOrder[j + 1][k] = tempDetail;
                    }
                }
            }
        }
    
        // Print the table
        System.out.println("+------------+--------------+--------+-----------+--------------+------------+");
        System.out.printf("| Order ID   |  Customer ID |  Size  | Quantity  | Total Amount |   Status   |%n");
        System.out.println("+------------+--------------+--------+-----------+--------------+------------+");
    
        for (int i = 0; i < orderCounter; i++) {
            String statusText;
            int status = Integer.parseInt(placeOrder[i][5]);
            if (status == 0) {
                statusText = "Processing";
            } else if (status == 1) {
                statusText = "Delivering";
            } else {
                statusText = "Delivered";
            }
    
            System.out.printf("| %-10s | %-12s | %-6s | %-9s |  %11s | %-8s |%n", placeOrder[i][0], placeOrder[i][1], 
                                                            placeOrder[i][2], placeOrder[i][3], placeOrder[i][4], statusText);
        }
    
        System.out.println("+------------+--------------+--------+-----------+--------------+------------+");
    
        System.out.print("\n    To Access the Main Menu, Please Enter 0: ");
        int num = input.nextInt();
    
        if (num == 0) {
            homePage();
        } else {
            clearInputLine();
            allOrders();
        }
    }
    
    //-----------------------Orders By Amount---------------------
    public static void orderByAmount(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                     Orders By Amount                    =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");

        // Sort orders based on Total Amount in descending order
        for (int i = 0; i < orderCounter - 1; i++) {
            for (int j = 0; j < orderCounter - i - 1; j++) {
                double amount1 = Double.parseDouble(placeOrder[j][4]);
                double amount2 = Double.parseDouble(placeOrder[j + 1][4]);

                if (amount1 < amount2) {
                    // Swap order details (customer ID, size, quantity, total amount, status)
                    for (int k = 0; k < 6; k++) {
                        String tempDetail = placeOrder[j][k];
                        placeOrder[j][k] = placeOrder[j + 1][k];
                        placeOrder[j + 1][k] = tempDetail;
                    }
                }
            }
        }

        // Print the table
        System.out.println("+------------+--------------+--------+-----------+--------------+------------+");
        System.out.printf("| Order ID   |  Customer ID |  Size  | Quantity  | Total Amount |   Status   |%n");
        System.out.println("+------------+--------------+--------+-----------+--------------+------------+");

        for (int i = 0; i < orderCounter; i++) {
            String statusText;
            int status = Integer.parseInt(placeOrder[i][5]);
            if (status == 0) {
                statusText = "Processing";
            } else if (status == 1) {
                statusText = "Delivering";
            } else {
                statusText = "Delivered";
            }

            System.out.printf("| %-10s | %-12s | %-6s | %-9s |  %11s | %-8s |%n", placeOrder[i][0], placeOrder[i][1], 
                                                            placeOrder[i][2], placeOrder[i][3], placeOrder[i][4], statusText);
        }

        System.out.println("+------------+--------------+--------+-----------+--------------+------------+");

        System.out.print("\n    To Access the Main Menu, Please Enter 0: ");
        int num = input.nextInt();

        if (num == 0) {
            homePage();
        } else {
            clearInputLine();
            allOrders();
        }
    }

    // Select Order Reports
    public static void orderReports(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                      Order Reports                      =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");
        System.out.println("           [1] All Orders");
        System.out.println("\n           [2] Order By Amount");
        
        //Select Options
        System.out.print("\n  Enter Option : ");
        int num = input.nextInt();

        if(num==1){
            allOrders();
        }
        else if(num==2){
            orderByAmount();
        }
        else{
            clearInputLine();
            orderByAmount(); 
        }
    }

    // [4] View Reports 
    public static void viewReports(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                     View Report                         =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");
        System.out.println("           [1] Customer Reports");
        System.out.println("\n           [2] Item Reports");
        System.out.println("\n           [3] Order Reports");
        
        //Select Options
        System.out.print("\n  Enter Option : ");
        int num = input.nextInt();

        if(num==1){
            customerReports();
        }
        else if(num==2){
            itemReports();
        }
        else if(num==3){
            orderReports();
        }
        else{
            clearInputLine();
            viewReports(); 
        }
       
    }
    
    ////////////////--------Search Order--------////////////////

    // Search Another Order
    public static void searchAnotherOrder(){
        System.out.print("       Do you want to search another order? (y/n) : ");
        char yOrN=input.next().charAt(0);
        if(yOrN=='y' || yOrN=='Y'){
            searchOrder();
        }else if(yOrN=='n' || yOrN=='N'){
            homePage();
        }else{
            clearInputLine();
            searchAnotherOrder();
        }
    }

    // [3] Search Order 
    public static void searchOrder(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                     Search Order                        =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");

        System.out.print("\n    Enter Order ID : ");
        String inputId=input.next();

        for(int i=0; i<orderCounter; i++){
            if(placeOrder[i][0].equals(inputId)){
                //customerOrderCounter++;
                System.out.println("\n\n    Phone Number    : "+placeOrder[i][1]);
                System.out.println("    Size            : "+placeOrder[i][2]);
                System.out.println("    Quantity        : "+placeOrder[i][3]);
                System.out.println("    Avarege         : "+placeOrder[i][4]);

                String status="";
                if(placeOrder[i][5].equals("0")){
                   status="Processing";      
                }else if(placeOrder[i][5].equals("1")){
                    status="Delivering";
                }else{
                    status="Delivered";
                }
                System.out.println("    Status          : "+status);
                System.out.println("\n");
                searchAnotherOrder();                
            }else{
                System.out.println("\n        Invalid ID..");
                System.out.println("\n");
                searchAnotherOrder();
            }
        }
       
    }
    
    ////////////////--------Search Customer Process--------////////////////

    // Custom sorting method using bubble sort
    public static void bubbleSort(String[][] array, int rows) {
        boolean swapped;
        for (int i = 0; i < rows - 1; i++) {
            swapped = false;
            for (int j = 0; j < rows - i - 1; j++) {
                double amount1 = Double.parseDouble(array[j][2]);
                double amount2 = Double.parseDouble(array[j + 1][2]);
                if (amount1 < amount2) { 
                    String[] temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    // Print Search Customer in ascending order of order amount using bubble sort
    public static void printSearchCustomer(String[][] tempSearchData, int rows) {
        bubbleSort(tempSearchData, rows); 
    
        double totalAmount = 0.0;
        System.out.println("\n+-----------------+-----------------+-----------------+");
        System.out.println("|       Size      |       QTY       |      Amount     |");
        System.out.println("+-----------------+-----------------+-----------------+");
    
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.printf("%17s|", tempSearchData[i][j]);
                if (j == 2) {
                    totalAmount += Double.parseDouble(tempSearchData[i][j]);
                }
            }
            System.out.print("\n");
        }
        System.out.println("+-----------------+-----------------+-----------------+");
        System.out.printf("|   Total Amount                    |%17.2f|\n", totalAmount);
        System.out.println("+-----------------------------------+-----------------+");
    }

    // Search Another Order
    public static void searchAnotherCustomer(){
        System.out.print("\n       Do you want to search another Customer report? (y/n) : ");
        char yOrN=input.next().charAt(0);
        if(yOrN=='y' || yOrN=='Y'){
            searchCustomer();
        }else if(yOrN=='n' || yOrN=='N'){
            homePage();
        }else{
            clearInputLine();
            searchAnotherCustomer();
        }
    }

    // Filter Searched Customer Details
    public static void searchCustomerNumber(String phoneNumber) {
        int customerOrderCounter = 0;
        String[][] tempSearchData = new String[orderCounter][3];
        boolean found = false;
    
        for (int i = 0; i < orderCounter; i++) {
            if (placeOrder[i][1].equals(phoneNumber)) {
                tempSearchData[customerOrderCounter][0] = placeOrder[i][2];
                tempSearchData[customerOrderCounter][1] = placeOrder[i][3];
                tempSearchData[customerOrderCounter][2] = placeOrder[i][4];
                customerOrderCounter++;
                found = true;
            }
        }
        if (found) {
            printSearchCustomer(tempSearchData, customerOrderCounter);
        } else {
            System.out.println("\n        Invalid Input..");
        }
        searchAnotherCustomer();
    }

    // [2] Search Customer 
    public static void searchCustomer(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                     Search Customer                     =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
       
        System.out.print("\n    Enter Custemer Phone Number: ");
        String phoneNum = input.next();
        int length=phoneNum.length();

        if(length==10 && phoneNum.startsWith("0")){
            searchCustomerNumber(phoneNum);
        }else{
            System.out.println("\n        Invalid Input..");
            searchAnotherCustomer();
        }
    }

    ////////////////--------Place Order Process--------////////////////

    // Genarate Order ID
    public static String generateOrderId(int orderCounter) {
        String formattedCounter = String.format("%05d", orderCounter);
        String orderId = "ODR#" + formattedCounter;
        return orderId;
    }

    // Enter Phone Number
    public static String enterNumber(){
    System.out.print("    Enter Custemer Phone Number: ");
    String phoneNum = input.next();
    String trueNumber="";
    int length=phoneNum.length();

    if(length==10 && phoneNum.startsWith("0")){
        trueNumber=phoneNum;
    }else{
        System.out.println("\n        Invalid Phone Number.. Try Again");
        System.out.print("\n       Do you want to enter phone number again? (y/n) : ");
        char yOrN=input.next().charAt(0);
        if(yOrN=='y' || yOrN=='Y'){
            System.out.println("\n");
            // Recursively call enterNumber and capture the result
            trueNumber = enterNumber();
        }else{
            homePage();
        }
    }
    return trueNumber;
    }

    // Input T-Shirt Size
    public static String inputSize(){

        System.out.print("    Enter T-Shirt Size (XS/S/M/L/XL/XXL): ");
        String size = input.next();

        if(size.equals("XS") ||  size.equals("S")||size.equals("M") ||
                                size.equals("L") ||size.equals("XL") ||size.equals("XXL")){
            return size;
        }
        else{
            clearInputLine();
            return inputSize();
        } 
    }

    // Add Quantity
    public static int addQty(){

        System.out.print("    Enter QTY : ");
        int qty = input.nextInt();
        
        if(qty>0){
            return qty;
        }
        else{
            clearInputLine();
            return addQty();   
        }
    }

    // Calculate Place Order Amount
    public static double calculateAmount(int qty, String size){
       
        double price = 0.00;
        switch (size) {
            case "XS":
                price = 600.00;
                break;
            case "S":
                price = 800.00;
                break;
            case "M":
                price = 900.00;
                break;
            case "L":
                price = 1000.00;
                break;
            case "XL":
                price = 1100.00;
                break;
            case "XXL":
                price = 1200.00;
                break;
        }
        return price*qty;
    }

    // Do you want anther order??
    public static void askWantPlaceAnotherOrder(){
       boolean tryAgainAnotherOrder=false;
        do{
            System.out.print("\n\n    Do you want to place another order? (y/n) : ");
            char anotherPlaceOrderDecision=input.next().charAt(0);
                    if(anotherPlaceOrderDecision=='y' || anotherPlaceOrderDecision=='Y'){
                        placeOrder();
                    }else if(anotherPlaceOrderDecision=='n' || anotherPlaceOrderDecision=='N'){
                        homePage();
                    }else{
                        System.out.println("        Invalid Input.. Try Again");
                        tryAgainAnotherOrder=true;
                    }
        }while(tryAgainAnotherOrder);            
    }

    // [1] Place Order 
    public static void placeOrder(){
        clearConsole();
        System.out.println("\n    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                     Place Order                         =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");

        // Increase capacity if needed
        if (orderCounter == orderCapacity) {
            increaseOrderCapacity(); 
        }
        
        // Genarate Order ID
        int nextOrderCounter = orderCounter + 1;
        String formattedCounter = String.format("%05d", nextOrderCounter);
        String orderId = "ODR#" + formattedCounter;
        System.out.print("\n\n    Your Order ID : " + orderId);
        
        System.out.print("\n\n");
        //Input Customer Phone Number
        String validPhoneNumber=enterNumber();
    
        System.out.print("\n");
        //Input T-Shirt Size
        String trueSize=inputSize();

        System.out.print("\n");
        //Input Quantity
        int passQty=addQty();
        String validQty= String.valueOf(passQty);
        
        //Calculate Amount
        Double amount=calculateAmount(passQty, trueSize);
        String amountOfOrder=String.valueOf(amount);
        System.out.println("\n    Amount : "+amount); 

        int orderStatus=PROCESSING;
        
        for(int i=orderCounter; i<orderCapacity;){
            for (int j = 0; j < 6; j++) {        
                //Place Order
                boolean tryAgainPlaceOrder=false;
                do{
                    System.out.print("\n    Do you want to place this order? (y/n) : "); 
                    char placeOrderDecision=input.next().charAt(0);
                    if(placeOrderDecision=='y' || placeOrderDecision=='Y'){

                        orderStatus=DELIVERING;
                        String statusOfOrder=String.valueOf(orderStatus);
                        //Order details save to the system
                        placeOrder[i][0]=orderId;
                        placeOrder[i][1]=validPhoneNumber;
                        placeOrder[i][2]=trueSize;
                        placeOrder[i][3]=validQty;
                        placeOrder[i][4]=amountOfOrder; 
                        placeOrder[i][5]=statusOfOrder;

                        orderCounter+=1;;
                        System.out.print("\n            Order Placed..!\n"); 

                        //Do you want anther order??
                        askWantPlaceAnotherOrder();

                    }else if(placeOrderDecision=='n' || placeOrderDecision=='N'){
                        homePage();
                    }else{
                        System.out.println("        Invalid Input.. Try Again");
                        tryAgainPlaceOrder=true;
                    }
                }while(tryAgainPlaceOrder);               
            }
        } 
    }

    // Home Page
    public static void homePage(){
        clearConsole();
        System.out.println("    ===========================================================");
        System.out.println("    =                                                         =");
        System.out.println("    =                                                         =");
        System.out.println("    =                      Fashion Shop                       =");
        System.out.println("    =                                                         =");
        System.out.println("    =                                                         =");
        System.out.println("    ===========================================================");
        System.out.println("\n");
        System.out.println("           [1] Place Order");
        System.out.println("           [2] Search Customer");
        System.out.println("           [3] Search Order");
        System.out.println("           [4] View Reports");
        System.out.println("           [5] Change order Status");
        System.out.println("           [6] Delete Order\n");

        //Select Options
        System.out.print("           Input Option : ");
        int num = input.nextInt();

        if(num==1){
            placeOrder();
        }
        else if(num==2){
            searchCustomer();
        }
        else if(num==3){
            searchOrder();
        }
        else if(num==4){
            viewReports();
        }
        else if(num==5){
            changeOrderStatus();
        }
        else if(num==6){
            deleteOrder();
        }
        else{
            clearConsole();
            homePage(); 
        }
    }

    public static void main(String args[]){
        clearConsole();
        homePage();
    }   
}