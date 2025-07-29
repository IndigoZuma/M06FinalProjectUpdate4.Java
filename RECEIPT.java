import java.time.LocalDateTime;

public class Receipt {
    private static int nextID = 1;
    private int receiptID;
    private Order order;
    private LocalDateTime printTimestamp;

    public Receipt(Order order) {
        this.receiptID = nextID++;
        this.order = order;
        this.printTimestamp = LocalDateTime.now();
    }

    public String printReceipt() {
        return "Receipt #" + receiptID + "\n" +
               order.toString() +
               "Printed at: " + printTimestamp + "\n";
    }
}
