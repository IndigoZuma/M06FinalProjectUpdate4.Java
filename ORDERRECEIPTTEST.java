public class OrderTest {
    public static void main(String[] args) {
        Order order = new Order(1);
        Beverage latte = new Beverage("Latte", 3.50, "Coffee", true, "Large", "Oat", 1, "Sugar");
        Modifier extraShot = new Modifier("Extra Shot", 0, "Add-on", true, "Espresso", "Double", 0.75);

        order.addItem(latte);
        order.addItem(extraShot);

        System.out.println(order);

        Receipt receipt = order.getReceipt();
        System.out.println(receipt.printReceipt());
    }
}
