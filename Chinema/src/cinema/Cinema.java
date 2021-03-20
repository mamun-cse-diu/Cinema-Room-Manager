package cinema;
import java.util.*;
public class Cinema {

    static int n;
    static int total_sell = 0;
    static int income = 0;
    static int total = 0;
    static int total_res = 0;
    static int row1;
    static int set1;

    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int sets = scan.nextInt();
        
        String[][] str = new String[rows][sets];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < sets; j++) {
                str[i][j] = "S";
            }
        }
        
        if (rows * sets > 60) {
            if (rows % 2 == 0) {
            	total = (rows / 2) * sets * 10;
                total += (rows / 2) * sets * 8;
            } else {
            	total = (rows / 2) * sets * 10;
                total += (rows / 2 + 1) * sets * 8;
            }
        } else {
        	total = rows * sets * 10;
        }
        
        manu(rows, sets, str);
    }
    
    public static void manu(int rows, int sets, String[][] str) {
        
        
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        n = scan.nextInt();
        
        if (n == 1) {
            show(rows, sets, str);
        } else if (n == 2) {
            buy(rows, sets, str);
        } else if (n == 3) {
        	statis(rows, sets, str);
        }
    }
    
    public static void show(int rows, int sets, String[][] str) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= sets; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        for (int i = 0; i < rows; i++) {
            System.out.print(i+1 + " ");
            for(int j = 0; j < sets; j++) {
                System.out.print(str[i][j] + " ");
            }
            System.out.println();
        }
        manu(rows, sets, str);
    }
    
    public static void buy(int rows, int sets, String[][] str) {
        int res = 0;
        
        while(true) {
        	System.out.println("Enter a row number:");
            row1 = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            set1 = scan.nextInt();
	        if (row1 > rows || set1 > sets) {
	            System.out.println("Wrong input!");
	        } else if ("B".contentEquals(str[row1 - 1][set1 - 1])) {
	        	System.out.println("That ticket has already been purchased!");
	        } else {
	        	if (rows * sets > 60) {
	                if(row1 <= rows / 2) {
	                    res = 10;
	                    total_res += res;
	                    total_sell++;
	                } else {
	                    res = 8;
	                    total_res += res;
	                    total_sell++;
	                }
	            } else {
	                res = 10;
	                total_res += res;
	                total_sell++;
	            }
	        	break;
	        }
        }
        System.out.print("Ticket price: ");
        System.out.println("$" + res);
        income += res;
        str[row1 - 1][set1 - 1] = "B";
        manu(rows, sets, str);
    }
    
    public static void statis(int rows, int sets, String[][] str) {
    	double c_income;
    	if (total_sell == 0) {
    		c_income = 0.00;
    	} else {
    		c_income = (total_sell * 100.00) / (rows * sets);
    	}
    	String arr = String.format("%.02f", c_income);
    	System.out.println("Number of purchased tickets: " + total_sell);
    	System.out.println("Percentage: " + arr + "%");
    	System.out.print("Current income: ");
        System.out.println("$" + total_res);
        System.out.print("Total income: ");
        System.out.println("$" + total);
        manu(rows, sets, str);
    }
    
    
}