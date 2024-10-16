import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Transaction {
    private String id;
    private String type;
    private String status;
    private Date date;
    private Integer amount;
    private String recAccNo = null; // Recipient's Account Number

    public Transaction(String type, int amount) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.status = new Random().nextInt(4) > 0 ? "Success" : "Fail";
        this.date = new Date();
        this.amount = amount;
    }

    public Transaction(String type, int amount, String recAccNo) {
        this(type, amount);
        this.recAccNo = recAccNo;
    }

    public HashMap<String, Serializable> getTransactionData() {
        return new HashMap<>() {{
            put("ID", id);
            put("Type", type);
            put("Status", status);
            put("Date", date);
            put("Amount", amount);
            put("Recipient", recAccNo);
        }};
    }
}
