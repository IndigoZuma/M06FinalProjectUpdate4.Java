public abstract class MenuItem {
    private String itemName;
    private double basePrice;
    private String category;
    private boolean isAvailable;

    public MenuItem(String itemName, double basePrice, String category, boolean isAvailable) {
        setItemName(itemName);
        setBasePrice(basePrice);
        setCategory(category);
        this.isAvailable = isAvailable;
    }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) {
        if (itemName == null || itemName.isEmpty()) throw new IllegalArgumentException("Item name required.");
        this.itemName = itemName;
    }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) {
        if (basePrice < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.basePrice = basePrice;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) {
        if (category == null || category.isEmpty()) throw new IllegalArgumentException("Category required.");
        this.category = category;
    }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public abstract double getPrice();

    @Override
    public String toString() {
        return itemName + " (" + category + ") - $" + String.format("%.2f", getPrice());
    }
}
