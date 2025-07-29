public class MenuItemTest {
    public static void main(String[] args) {
        Beverage latte = new Beverage("Latte", 3.50, "Coffee", true, "Medium", "Almond", 2, "Vanilla");
        System.out.println(latte);

        Modifier extraShot = new Modifier("Extra Syrup", 0, "Add-on", true, "Syrup", "Vanilla", 0.50);
        System.out.println(extraShot);

        try {
            Beverage badDrink = new Beverage("", -1, "Coffee", true, "Small", "Whole", -1, "");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}
