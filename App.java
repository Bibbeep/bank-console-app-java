import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("resource")
public class App {
    public static void main(String[] args) {
        try {
            showMenu();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void showMenu() throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            clearScreen();
            printBanner();
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction history");
            System.out.println("0. Exit App");

            System.out.print("\nEnter choice: ");
            try {
                int choice = sc.nextInt();
                switch(choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        checkTransaction();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        clearScreen();
                        System.out.println("===== WARNING! =====");
                        System.out.println("Invalid choice!");
                        Thread.sleep(1000);
                }
            } catch (InputMismatchException e) {
                clearScreen();
                System.out.println("===== WARNING! =====");
                System.out.println("Invalid choice!");
                sc.next();
                Thread.sleep(1000);
            }
        }
    }

    public static void checkBalance() throws InterruptedException {
        printProcess();
        printBanner();
        System.out.println("Account balance: " + Account.getBalance());
        pressEnterToContinue();
    }

    public static void deposit() throws InterruptedException {
        clearScreen();
        printBanner();
        Scanner sc = new Scanner(System.in);
        System.out.print("Deposit amount: ");

        try {
            int amount = sc.nextInt();
            Account.depositBalance(amount);
            printProcess();
            String status = Account.getRecentTransaction().get("Status").toString();

            if (status.equals("Success")) {
                printBanner();
                System.out.println("Successfully deposited " + amount + " to your account!");
            } else {
                printBanner();
                System.out.println("Desposit failed!");
            }
        } catch (InputMismatchException e) {
            clearScreen();
            System.out.println("===== WARNING! =====");
            System.out.println("Invalid amount!");
            sc.next();
        } catch (Exception e) {
            clearScreen();
            System.out.println("===== WARNING! =====");
            System.out.println(e.getMessage());
        }

        pressEnterToContinue();
    }

    public static void withdraw() {
        clearScreen();
        printBanner();
        Scanner sc = new Scanner(System.in);
        System.out.print("Withdrawal amount: ");

        try {
            int amount = sc.nextInt();
            Account.withdrawBalance(amount);
            printProcess();
            String status = Account.getRecentTransaction().get("Status").toString();

            if (status.equals("Success")) {
                printBanner();
                System.out.println("Successfully withdrew " + amount + " from your account!");
            } else {
                printBanner();
                System.out.println("Withdrawal failed!");
            }
        } catch (InputMismatchException e) {
            clearScreen();
            System.out.println("===== WARNING! =====");
            System.out.println("Invalid amount!");
            sc.next();
        } catch (Exception e) {
            clearScreen();
            System.out.println("===== WARNING! =====");
            System.out.println(e.getMessage());
        }

        pressEnterToContinue();
    }
    
    public static void transfer() {

    }

    public static void checkTransaction() throws InterruptedException {
        printProcess();
        printBanner();
        
        ArrayList<HashMap<String, Serializable>> transactions = Account.getTransactionRecord();
        
        if (transactions.isEmpty()) {
            System.out.println("No transaction has been made");
        } else {
            transactions.forEach(System.out::println);
        }

        pressEnterToContinue();
    }

    public static void clearScreen() {
        System.out.print("\033\143");
    }

    public static void printBanner() {
        System.out.println("===== BANKING CONSOLE APP =====");
    }

    public static void printProcess() throws InterruptedException {
        clearScreen();
        printBanner();
        System.out.print("Processing. ");
        Thread.sleep(333);
        System.out.print(". ");
        Thread.sleep(333);
        System.out.print(". ");
        Thread.sleep(333);
        clearScreen();
    }

    public static void pressEnterToContinue() { 
        System.out.println("\nPress Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
