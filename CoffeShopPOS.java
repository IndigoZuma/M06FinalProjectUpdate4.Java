import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class CoffeeShopPOS extends Application {

    // Dummy menu data, replace/add items as needed
    private final List<Beverage> beverages = Arrays.asList(
            new Beverage("Latte", 3.50, "Coffee", true, "Medium", "Whole", 1, "Sugar"),
            new Beverage("Mocha", 4.00, "Coffee", true, "Large", "Almond", 2, "Stevia"),
            new Beverage("Green Tea", 2.75, "Tea", true, "Small", "None", 0, "None")
    );
    private final List<Modifier> modifiers = Arrays.asList(
            new Modifier("Extra Shot", 0.50, "Add-on", true, "Espresso", "Extra Shot", 0.50),
            new Modifier("Vanilla Syrup", 0.60, "Flavor", true, "Syrup", "Vanilla", 0.60)
    );

    private ObservableList<MenuItem> currentOrderList = FXCollections.observableArrayList();
    private Order currentOrder = new Order(1);
    private TextArea orderArea;
    private Label subtotalLabel, taxLabel, totalLabel;
    private TextArea receiptArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Coffee Shop POS System");

        // --- Left: Menu and Search ---
        VBox leftMenuBox = new VBox(8);
        leftMenuBox.setPadding(new Insets(10));
        Label menuLabel = new Label("Menu");
        TextField searchField = new TextField();
        searchField.setPromptText("Search menu...");
        ListView<MenuItem> menuList = new ListView<>();
        ObservableList<MenuItem> allMenuItems = FXCollections.observableArrayList();
        allMenuItems.addAll(beverages);
        allMenuItems.addAll(modifiers);
        menuList.setItems(allMenuItems);

        // Filter menu by search
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                menuList.setItems(allMenuItems);
            } else {
                String query = newVal.toLowerCase();
                ObservableList<MenuItem> filtered = FXCollections.observableArrayList();
                for (MenuItem mi : allMenuItems) {
                    if (mi.getItemName().toLowerCase().contains(query))
                        filtered.add(mi);
                }
                menuList.setItems(filtered);
            }
        });
        Button addToOrderBtn = new Button("Add to Order");
        addToOrderBtn.setOnAction(e -> handleAddToOrder(menuList.getSelectionModel().getSelectedItem()));
        leftMenuBox.getChildren().addAll(menuLabel, searchField, menuList, addToOrderBtn);

        // --- Center: Current Order ---
        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(10));
        Label orderLabel = new Label("Current Order");
        orderArea = new TextArea();
        orderArea.setPrefRowCount(15);
        orderArea.setEditable(false);

        Button removeSelectedBtn = new Button("Remove Selected");
        removeSelectedBtn.setOnAction(e -> handleRemoveFromOrder());

        subtotalLabel = new Label("Subtotal: $0.00");
        taxLabel = new Label("Tax: $0.00");
        totalLabel = new Label("Total: $0.00");

        centerBox.getChildren().addAll(orderLabel, orderArea, removeSelectedBtn, subtotalLabel, taxLabel, totalLabel);

        // --- Right: Payment/Receipt Area ---
        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(10));
        Label paymentLabel = new Label("Payment");
        Button payBtn = new Button("Pay (Cash)");
        payBtn.setOnAction(e -> handlePayment());
        Button cancelBtn = new Button("Cancel Order");
        cancelBtn.setOnAction(e -> handleCancelOrder());

        receiptArea = new TextArea();
        receiptArea.setPrefRowCount(10);
        receiptArea.setEditable(false);

        rightBox.getChildren().addAll(paymentLabel, payBtn, cancelBtn, new Label("Receipt:"), receiptArea);

        // --- Main Layout ---
        BorderPane root = new BorderPane();
        root.setLeft(leftMenuBox);
        root.setCenter(centerBox);
        root.setRight(rightBox);

        Scene scene = new Scene(root, 950, 430);
        primaryStage.setScene(scene);
        primaryStage.show();

        updateOrderDisplay();
    }

    // ---- Event Handlers ----

    // Add item to order
    private void handleAddToOrder(MenuItem item) {
        if (item == null) {
            showError("Select an item from the menu.");
            return;
        }
        // Create a copy of the item for current order, if needed (to allow quantity modification etc.)
        // Here, add as is
        currentOrder.addItem(item);
        updateOrderDisplay();
    }

    // Remove selected item by line number from order area
    private void handleRemoveFromOrder() {
        try {
            List<String> lines = Arrays.asList(orderArea.getText().split("\n"));
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Remove Item");
            dialog.setHeaderText("Enter item number to remove (first item is 1):");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                int idx = Integer.parseInt(result.get()) - 1;
                if (idx >= 0 && idx < currentOrderList.size()) {
                    currentOrder.removeItem(currentOrderList.get(idx));
                    updateOrderDisplay();
                } else {
                    showError("Invalid item number.");
                }
            }
        } catch (Exception e) {
            showError("Invalid input.");
        }
    }

    // Complete payment (prints receipt)
    private void handlePayment() {
        if (currentOrderList.isEmpty()) {
            showError("Order is empty.");
            return;
        }
        Receipt receipt = currentOrder.getReceipt();
        receiptArea.setText(receipt.printReceipt());
        resetOrder();
    }

    // Cancel order
    private void handleCancelOrder() {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel this order?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> res = confirm.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.YES) {
            resetOrder();
            receiptArea.clear();
        }
    }

    private void resetOrder() {
        currentOrder = new Order((int) (Math.random() * 1000) + 1);
        currentOrderList.clear();
        updateOrderDisplay();
    }

    // Update current order display and totals
    private void updateOrderDisplay() {
        currentOrderList.setAll(currentOrder.itemsList); // Use reflection or make itemsList public/getter if necessary
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (MenuItem item : currentOrderList) {
            sb.append(i++).append(". ").append(item).append("\n");
        }
        orderArea.setText(sb.toString());
        subtotalLabel.setText(String.format("Subtotal: $%.2f", currentOrder.subtotal));
        taxLabel.setText(String.format("Tax: $%.2f", currentOrder.taxAmount));
        totalLabel.setText(String.format("Total: $%.2f", currentOrder.totalAmount));
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
