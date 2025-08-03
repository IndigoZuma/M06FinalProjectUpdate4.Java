/**
 * Represents a Modifier that can be added to an order item (such as extra syrup or flavor shot).
 * Inherits from MenuItem and adds type, value, and a modifier-specific price.
 */
public class Modifier extends MenuItem {
    private String type;
    private String value;
    private double price;

    /**
     * Constructs a Modifier with the specified details.
     * @param itemName the display name for the modifier
     * @param basePrice the base price (inherited, often 0.0)
     * @param category the modifier category (e.g., "Add-on", "Flavor")
     * @param isAvailable whether the modifier is currently available
     * @param type the type of modifier (e.g., "Syrup", "Espresso Shot")
     * @param value the value or description of the modifier (e.g., "Vanilla")
     * @param price the price to add for this modifier
     */
    public Modifier(String itemName, double basePrice, String category, boolean isAvailable,
                    String type, String value, double price) {
        super(itemName, basePrice, category, isAvailable);
        setType(type);
        setValue(value);
