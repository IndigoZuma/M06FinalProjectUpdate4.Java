import java.time.LocalDateTime;

/**
 * Represents a printed receipt for an order in the coffee shop POS system.
 * Each receipt has a unique ID, contains an Order, and records a print timestamp.
 */
public class Receipt {
    private static int nextID = 1;
    private int receiptID;
    private Order order;
    private LocalDateTime printTimestamp;

    /**
     * Constructs a Receipt for a given Order. Automatically assigns a unique receipt ID.
     * @param order The Order for which this receipt is generated
     */
    public Receipt(Order order) {
        this.receiptID = nextID++;
        this.order = order;
        this.printTimestamp = LocalDateTime.now();
    }

    /**
     * Prints a formatted receipt string, including receipt number, order details, and print time.
     * @return Formatted receipt string
     */
    public String printReceipt() {
        return "Receipt #" + receiptID + "\n"
             + order.toString()
             + "Printed at: " + printTimestamp + "\n";
    }

    /** Returns this receipt’s ID. */
    public int getReceiptID() { return receiptID; }

    /** Returns the Order associated with this receipt. */
    public Order getOrder() { return order; }

    /** Returns the timestamp this receipt was printed. */
    public LocalDateTime getPrintTimestamp() { return printTimestamp; }
}
