public class Beverage extends MenuItem {
    private String size;
    private String milkType;
    private int syrupShots;
    private String sweetener;

    public Beverage(String itemName, double basePrice, String category, boolean isAvailable,
                    String size, String milkType, int syrupShots, String sweetener) {
        super(itemName, basePrice, category, isAvailable);
        setSize(size);
        setMilkType(milkType);
        setSyrupShots(syrupShots);
        setSweetener(sweetener);
    }

    public String getSize() { return size; }
    public void setSize(String size) {
        if (size == null || size.isEmpty()) throw new IllegalArgumentException("Size required.");
        this.size = size;
    }

    public String getMilkType() { return milkType; }
    public void setMilkType(String milkType) {
        if (milkType == null || milkType.isEmpty()) throw new IllegalArgumentException("Milk type required.");
        this.milkType = milkType;
    }

    public int getSyrupShots() { return syrupShots; }
    public void setSyrupShots(int syrupShots) {
        if (syrupShots < 0) throw new IllegalArgumentException("Syrup shots cannot be negative.");
        this.syrupShots = syrupShots;
    }

    public String getSweetener() { return sweetener; }
    public void setSweetener(String sweetener) { this.sweetener = sweetener; }

    @Override
    public double getPrice() {
        return getBasePrice() + (syrupShots * 0.5);
    }

    @Override
    public String toString() {
        return getItemName() + " (" + getSize() + ", " + getMilkType() + ", " +
               syrupShots + " syrup, " + sweetener + ") - $" + String.format("%.2f", getPrice());
    }
}
