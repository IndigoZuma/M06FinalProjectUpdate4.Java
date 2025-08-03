# SDEV200FinalProject.Java
Final Project: Update 4

|-----------------------|-------------------------------------|----------------------------|
|        MENU           |           CURRENT ORDER             |      PAYMENT / RECEIPT     |
|-----------------------|-------------------------------------|----------------------------|
| Search: ______        | 1. Latte (Medium, Whole ...) $4.00  | [ Pay (Cash) ]             |
| [Latte      $3.50]    | 2. Extra Shot (Add-on: ...) $0.5    | [ Cancel Order ]           |
| [Mocha      $4.00]    |                                     | Receipt:                   |
| [Green Tea  $2.75]    | Subtotal: $4.50                     | [printed receipt displays] |
| [Extra Shot $0.50]    | Tax: $0.41                          |                            |
| [Vanilla S. $0.60]    | Total: $4.91                        |                            |
|-----------------------|-------------------------------------|----------------------------|




Coffee Shop POS System
A JavaFX application for managing coffee shop orders with a simple point-of-sale interface.

Features
Add beverages and modifiers to an order

Search menu items

Live subtotal, tax, and total calculation

Remove items and process cash payment

Print formatted receipts

Robust error handling

How to Run
Compile all .java files (Java 11+ and JavaFX required)

Run: java CoffeeShopPOS

(Optional) Run MenuItemTest or OrderReceiptTest for console-based class tests




UML

Legend:
+ public   - private   italics = abstract class/method

  <<abstract>> *MenuItem*
-------------------------------
- itemName     : String
- basePrice    : double
- category     : String
- isAvailable  : boolean
-------------------------------
+ MenuItem(...)
+ getItemName()        : String
+ setItemName(String)  : void
+ getBasePrice()       : double
+ setBasePrice(double) : void
+ getCategory()        : String
+ setCategory(String)  : void
+ isAvailable()        : boolean
+ setAvailable(boolean): void
+ getPrice()           : double     (*abstract*)
+ toString()           : String

        /                    \
Beverage                 Modifier
------------------       ------------------
- size       : String    - type  : String
- milkType   : String    - value : String
- syrupShots : int       - price : double
- sweetener  : String    ------------------
------------------       + Modifier(...)
+ Beverage(...)          + getType()       : String
+ getSize()       : String
+ setSize(String) : void
+ getMilkType()   : String
+ setMilkType(String): void
+ getSyrupShots() : int
+ setSyrupShots(int): void
+ getSweetener()  : String
+ setSweetener(String): void
+ getPrice()      : double
+ toString()      : String

Order
------------------------------------------
- orderID        : int
- itemsList      : List<MenuItem>
- subtotal       : double
- taxAmount      : double
- totalAmount    : double
- orderTimestamp : LocalDateTime
------------------------------------------
+ Order(int)
+ addItem(MenuItem)        : void
+ removeItem(MenuItem)     : void
+ calculateSubtotal()      : void
+ calculateTax()           : void
+ calculateTotal()         : void
+ getReceipt()             : Receipt
+ getItemsList()           : List<MenuItem>
+ getSubtotal()            : double
+ getTaxAmount()           : double
+ getTotalAmount()         : double
+ getOrderID()             : int
+ getOrderTimestamp()      : LocalDateTime
+ toString()               : String

Receipt
------------------------------------------
- receiptID       : int
- order           : Order
- printTimestamp  : LocalDateTime
------------------------------------------
+ Receipt(Order)
+ printReceipt()      : String
+ getReceiptID()      : int
+ getOrder()          : Order
+ getPrintTimestamp() : LocalDateTime
