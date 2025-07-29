public class Modifier extends MenuItem {
    private String type;
    private String value;
    private double price;

    public Modifier(String itemName, double basePrice, String category, boolean isAvailable,
                    String type, String value, double price) {
        super(itemName, basePrice, category, isAvailable);
        setType(type);
        setValue(value);
        setPrice(price);
    }

    public String getType() { return type; }
    public void setType(String type) {
        if (type == null || type.isEmpty()) throw new IllegalArgumentException("Type required.");
        this.type = type;
    }

    public String getValue() { return value; }
    public void setValue(String value) {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Value required.");
        this.value = value;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Modifier price cannot be negative.");
        this.price = price;
    }

    @Override
    public String toString() {
        return getItemName() + " (" + type + ": " + value + ") - $" + String.format("%.2f", getPrice());
    }
}
