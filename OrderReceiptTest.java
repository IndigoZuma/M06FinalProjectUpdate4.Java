/**
 * Manual test class for Order and Receipt functionality.
 * Adds items to an order, prints order details, prints receipt, and checks edge cases.
 */
public class OrderReceiptTest {
    public static void main(String[] args) {
        // Create a new order
        Order order = new Order(1);

        // Create some menu items
        Beverage latte = new Beverage("Latte", 3.50, "Coffee", true, "Large", "Oat", 1, "Sugar");
        Modifier extraShot = new Modifier("Extra Shot", 0, "Add-on", true, "Espresso", "Double", 0.75);

        // Add items to the order
        order.addItem(latte);
        order.addItem(extraShot);

        // Print the order summary
        System.out.println("Order after adding items:");
        System.out.println(order);

        // Generate and print the receipt
        Receipt receipt = order.getReceipt();
        System.out.println("Receipt output:");
        System.out.println(receipt.printReceipt());

        // Test removing an item
        order.removeItem(latte);
        System.out.println("Order after removing latte:");
        System.out.println(order);

        // Test removing the last item
        order.removeItem(extraShot);
        System.out.println("Order after removing extra shot (should be empty):");
        System.out.println(order);

        // Test receipt with empty order (should still print, but show $0.00 totals)
        Receipt emptyReceipt = order.getReceipt();
        System.out.println("Receipt for empty order:");
        System.out.println(emptyReceipt.printReceipt());
    }
}
