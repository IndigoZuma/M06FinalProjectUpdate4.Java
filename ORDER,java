import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private List<MenuItem> itemsList;
    private double subtotal;
    private double taxAmount;
    private double totalAmount;
    private LocalDateTime orderTimestamp;

    public Order(int orderID) {
        this.orderID = orderID;
        this.itemsList = new ArrayList<>();
        this.orderTimestamp = LocalDateTime.now();
    }

    public void addItem(MenuItem item) {
        itemsList.add(item);
        calculateTotals();
    }

    public void removeItem(MenuItem item) {
        itemsList.remove(item);
        calculateTotals();
    }

    private void calculateTotals() {
        calculateSubtotal();
        calculateTax();
        calculateTotal();
    }

    public void calculateSubtotal() {
        subtotal = 0;
        for (MenuItem item : itemsList) {
            subtotal += item.getPrice();
        }
    }

    public void calculateTax() {
        taxAmount = subtotal * 0.09;
    }

    public void calculateTotal() {
        totalAmount = subtotal + taxAmount;
    }

    public Receipt getReceipt() {
        return new Receipt(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderID).append(" [").append(orderTimestamp).append("]\n");
        for (MenuItem item : itemsList) {
            sb.append(" - ").append(item).append("\n");
        }
        sb.append("Subtotal: $").append(String.format("%.2f", subtotal)).append("\n");
        sb.append("Tax: $").append(String.format("%.2f", taxAmount)).append("\n");
        sb.append("Total: $").append(String.format("%.2f", totalAmount)).append("\n");
        return sb.toString();
    }
}
