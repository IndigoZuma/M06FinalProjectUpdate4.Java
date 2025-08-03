/**
 * Simple manual test class for Beverage and Modifier objects.
 * Instantiates objects, prints details, and checks for error handling/validation.
 */
public class MenuItemTest {
    public static void main(String[] args) {
        // Test valid Beverage creation and printing
        Beverage latte = new Beverage("Latte", 3.50, "Coffee", true, "Medium", "Almond", 2, "Vanilla");
        System.out.println(latte);

        // Test valid Modifier creation and printing
        Modifier extraShot = new Modifier("Extra Syrup", 0, "Add-on", true, "Syrup", "Vanilla", 0.50);
        System.out.println(extraShot);

        // Test error handling for invalid beverage creation
        try {
            Beverage badDrink = new Beverage("", -1, "Coffee", true, "Small", "Whole", -1, "");
            System.out.println("ERROR: Bad beverage should have thrown exception: " + badDrink);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error for bad beverage: " + e.getMessage());
        }

        // Test error handling for invalid modifier price
        try {
            Modifier badModifier = new Modifier("Bad Mod", 0, "Add-on", true, "Flavor", "Hazelnut", -0.10);
            System.out.println("ERROR: Bad modifier should have thrown exception: " + badModifier);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error for bad modifier: " + e.getMessage());
        }
    }
}
