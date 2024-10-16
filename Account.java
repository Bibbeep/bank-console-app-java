import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Account {
    private static int balance = 0;
    private static ArrayList<HashMap<String, Serializable>> transactionRecord = new ArrayList<>();

    public static int getBalance() {
        return balance;
    }

    public static ArrayList<HashMap<String, Serializable>> getTransactionRecord() {
        return transactionRecord;
    }

    public static HashMap<String, Serializable> getRecentTransaction() {
        int index = transactionRecord.size() - 1;
        return index >= 0 ? transactionRecord.get(index) : null;
    }

    public static void depositBalance(int amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Invalid amount!");
        }

        final Transaction thisTransaction = new Transaction("Deposit", amount);
        final HashMap<String, Serializable> thisTransactionData = thisTransaction.getTransactionData();
        transactionRecord.add(thisTransactionData);

        if (thisTransactionData.get("Status").equals("Fail")) {
            return;
        }

        balance += amount;
    }

    public static void withdrawBalance(int amount) throws Exception {
        if (amount <= 0 || amount > balance) {
            throw new Exception("Invalid amount!");
        }

        final Transaction thisTransaction = new Transaction("Withdraw", amount);
        final HashMap<String, Serializable> thisTransactionData = thisTransaction.getTransactionData();
        transactionRecord.add(thisTransactionData);

        if (thisTransactionData.get("Status").equals("Fail")) {
            return;
        }

        balance -= amount;
    }

    public static void transfer(String recAccNo, int amount) throws Exception {
        if (!recAccNo.matches("^[0-9]+$")) {
            throw new Exception("Invalid account number!");
        }

        if (amount <= 0 || amount > balance) {
            throw new Exception("Invalid amount!");
        }

        final Transaction thisTransaction = new Transaction("Transfer", amount, recAccNo);
        final HashMap<String, Serializable> thisTransactionData = thisTransaction.getTransactionData();
        transactionRecord.add(thisTransactionData);

        if (thisTransactionData.get("Status").equals("Fail")) {
            return;
        }

        balance -= amount;
    }
}