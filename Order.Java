import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a customer order in the coffee shop POS system.
 * Each order maintains a list of MenuItem objects, tracks totals, and contains an order timestamp.
 */
public class Order {
    private int orderID;
    private List<MenuItem> itemsList;
    private double subtotal;
    private double taxAmount;
    private double totalAmount;
    private LocalDateTime orderTimestamp;

    /**
     * Constructs a new Order with a unique ID.
     * @param orderID the unique identifier for the order
     */
    public Order(int orderID) {
        this.orderID = orderID;
        this.itemsList = new ArrayList<>();
        this.orderTimestamp = LocalDateTime.now();
    }

    /**
     * Adds a MenuItem to the order and updates totals.
     * @param item The MenuItem to add
     */
    public void addItem(MenuItem item) {
        itemsList.add(item);
        calculateTotals();
    }

    /**
     * Removes a MenuItem from the order and updates totals.
     * @param item The MenuItem to remove
     */
    public void removeItem(MenuItem item) {
        itemsList.remove(item);
        calculateTotals();
    }

    private void calculateTotals() {
        calculateSubtotal();
        calculateTax();
        calculateTotal();
    }

    /**
     * Calculates the subtotal for this order.
     */
    public void calculateSubtotal() {
        subtotal = 0;
        for (MenuItem item : itemsList) {
            subtotal += item.getPrice();
        }
    }

    /**
     * Calculates the tax amount based on current subtotal (9%).
     */
    public void calculateTax() {
        taxAmount = subtotal * 0.09;
    }

    /**
     * Calculates the total amount (subtotal + tax).
     */
    public void calculateTotal() {
        totalAmount = subtotal + taxAmount;
    }

    /**
     * Returns a Receipt for this order.
     * @return Receipt object containing details of the order
     */
    public Receipt getReceipt() {
        return new Receipt(this);
    }

    // -- New getters for encapsulation and GUI compatibility --

    /**
     * Returns an unmodifiable list of the items in this order.
     */
    public List<MenuItem> getItemsList() {
        return Collections.unmodifiableList(itemsList);
    }
    /** Returns the subtotal for this order. */
    public double getSubtotal() { return subtotal; }
    /** Returns the tax amount for this order. */
    public double getTaxAmount() { return taxAmount; }
    /** Returns the total amount for this order. */
    public double getTotalAmount() { return totalAmount; }
    /** Returns the unique Order ID. */
    public int getOrderID() { return orderID; }
    /** Returns the order's timestamp. */
    public LocalDateTime getOrderTimestamp() { return orderTimestamp; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderID).append(" [").append(orderTimestamp).append("]\n");
        int index = 1;
        for (MenuItem item : itemsList) {
            sb.append(index++).append(". ").append(item).append("\n");
        }
        sb.append("Subtotal: $").append(String.format("%.2f", subtotal)).append("\n");
        sb.append("Tax: $").append(String.format("%.2f", taxAmount)).append("\n");
        sb.append("Total: $").append(String.format("%.2f", totalAmount)).append("\n");
        return sb.toString();
    }
}
