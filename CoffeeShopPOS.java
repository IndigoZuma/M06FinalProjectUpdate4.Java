import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

/**
 * Main JavaFX GUI application for the Coffee Shop Point-of-Sale System.
 * Relies on backend classes for all business logic.
 */
public class CoffeeShopPOS extends Application {

    // Sample menu; replace or populate dynamically as needed
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

        // --- Left: Menu with search ---
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

        // Filter the menu by search input
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
