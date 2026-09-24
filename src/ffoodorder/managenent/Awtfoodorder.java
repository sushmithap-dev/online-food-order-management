package ffoodorder.managenent;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Awtfoodorder extends Frame implements ActionListener {

    // ================= DATABASE DETAILS =================

    static final String URL =
            "jdbc:mysql://localhost:3306/food_order_db";

    static final String USER = "root";

    static final String PASSWORD = "root";

    // ================= AWT COMPONENTS =================

    TextArea outputArea;

    Button registerCustomerBtn;
    Button viewCustomersBtn;
    Button addFoodBtn;
    Button viewFoodBtn;
    Button searchFoodBtn;
    Button placeOrderBtn;
    Button viewOrdersBtn;
    Button updateStatusBtn;
    Button cancelOrderBtn;
    Button viewOrderDetailsBtn;
    Button exitBtn;

    // ================= CONSTRUCTOR =================

    public Awtfoodorder() {

        setTitle("Online Food Order Management System");

        setSize(1000, 650);

        setLayout(new BorderLayout(10, 10));

        setBackground(new Color(255, 248, 240));

        // ================= TITLE =================

        Label title = new Label(
                "ONLINE FOOD ORDER MANAGEMENT SYSTEM",
                Label.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setBackground(
                new Color(220, 70, 50)
        );

        title.setForeground(Color.WHITE);

        add(title, BorderLayout.NORTH);

        // ================= BUTTON PANEL =================

        Panel buttonPanel = new Panel();

        buttonPanel.setLayout(
                new GridLayout(11, 1, 8, 8)
        );

        buttonPanel.setBackground(
                new Color(255, 239, 213)
        );

        registerCustomerBtn =
                createButton("Register Customer");

        viewCustomersBtn =
                createButton("View Customers");

        addFoodBtn =
                createButton("Add Food");

        viewFoodBtn =
                createButton("View Food");

        searchFoodBtn =
                createButton("Search Food");

        placeOrderBtn =
                createButton("Place Order");

        viewOrdersBtn =
                createButton("View Orders");

        updateStatusBtn =
                createButton("Update Status");

        cancelOrderBtn =
                createButton("Cancel Order");

        viewOrderDetailsBtn =
                createButton("View Order Details");

        exitBtn =
                createButton("Exit");

        buttonPanel.add(registerCustomerBtn);
        buttonPanel.add(viewCustomersBtn);
        buttonPanel.add(addFoodBtn);
        buttonPanel.add(viewFoodBtn);
        buttonPanel.add(searchFoodBtn);
        buttonPanel.add(placeOrderBtn);
        buttonPanel.add(viewOrdersBtn);
        buttonPanel.add(updateStatusBtn);
        buttonPanel.add(cancelOrderBtn);
        buttonPanel.add(viewOrderDetailsBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.WEST);

        // ================= OUTPUT AREA =================

        outputArea = new TextArea();

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font("Monospaced", Font.PLAIN, 15)
        );

        outputArea.setBackground(
                new Color(255, 253, 245)
        );

        outputArea.setForeground(
                new Color(70, 45, 30)
        );

        outputArea.setText(
                "Welcome to Online Food Order Management System!\n\n"
                + "Select an option from the menu."
        );

        add(outputArea, BorderLayout.CENTER);

        // ================= WINDOW CLOSE =================

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();

                System.exit(0);
            }
        });

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // ================= CREATE BUTTON =================

    private Button createButton(String text) {

        Button button = new Button(text);

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setBackground(
                new Color(255, 140, 0)
        );

        button.setForeground(Color.WHITE);

        button.addActionListener(this);

        return button;
    }

    // ================= DATABASE CONNECTION =================

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    // ================= MESSAGE =================

    private void showMessage(String message) {

        Dialog dialog = new Dialog(
                this,
                "Message",
                true
        );

        dialog.setLayout(new BorderLayout());

        Label label = new Label(
                message,
                Label.CENTER
        );

        label.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        label.setBackground(
                new Color(255, 239, 213)
        );

        Button ok = new Button("OK");

        ok.setBackground(
                new Color(220, 70, 50)
        );

        ok.setForeground(Color.WHITE);

        ok.addActionListener(e -> {
            dialog.dispose();
        });

        dialog.add(label, BorderLayout.CENTER);

        dialog.add(ok, BorderLayout.SOUTH);

        dialog.setSize(400, 150);

        dialog.setLocationRelativeTo(this);

        dialog.setVisible(true);
    }

    // ================= INPUT DIALOG =================

    private String getInput(String title, String message) {

        final Dialog dialog = new Dialog(
                this,
                title,
                true
        );

        dialog.setLayout(
                new GridLayout(3, 1, 10, 10)
        );

        Label label = new Label(
                message,
                Label.CENTER
        );

        TextField field = new TextField();

        Button ok = new Button("OK");

        ok.setBackground(
                new Color(255, 140, 0)
        );

        ok.setForeground(Color.WHITE);

        final String[] result = {null};

        ok.addActionListener(e -> {

            result[0] = field.getText().trim();

            dialog.dispose();
        });

        dialog.add(label);

        dialog.add(field);

        dialog.add(ok);

        dialog.setSize(400, 160);

        dialog.setLocationRelativeTo(this);

        dialog.setVisible(true);

        return result[0];
    }

    // ================= ACTION LISTENER =================

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == registerCustomerBtn) {

            registerCustomer();

        } else if (source == viewCustomersBtn) {

            viewCustomers();

        } else if (source == addFoodBtn) {

            addFoodItem();

        } else if (source == viewFoodBtn) {

            viewFoodItems();

        } else if (source == searchFoodBtn) {

            searchFood();

        } else if (source == placeOrderBtn) {

            placeOrder();

        } else if (source == viewOrdersBtn) {

            viewOrders();

        } else if (source == updateStatusBtn) {

            updateOrderStatus();

        } else if (source == cancelOrderBtn) {

            cancelOrder();

        } else if (source == viewOrderDetailsBtn) {

            viewOrderDetails();

        } else if (source == exitBtn) {

            System.exit(0);
        }
    }

    // =====================================================
    // 1. REGISTER CUSTOMER
    // =====================================================

    private void registerCustomer() {

        String name =
                getInput(
                        "Register Customer",
                        "Enter Customer Name:"
                );

        if (name == null || name.isEmpty()) {
            return;
        }

        String phone =
                getInput(
                        "Register Customer",
                        "Enter Phone:"
                );

        if (phone == null || phone.isEmpty()) {
            return;
        }

        String email =
                getInput(
                        "Register Customer",
                        "Enter Email:"
                );

        if (email == null || email.isEmpty()) {
            return;
        }

        String address =
                getInput(
                        "Register Customer",
                        "Enter Address:"
                );

        if (address == null || address.isEmpty()) {
            return;
        }

        String sql =
                "INSERT INTO customers "
                + "(name, phone, email, address) "
                + "VALUES (?, ?, ?, ?)";

        try (
                Connection con = getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, address);

            ps.executeUpdate();

            showMessage(
                    "Customer registered successfully!"
            );

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // 2. VIEW CUSTOMERS
    // =====================================================

    private void viewCustomers() {

        outputArea.setText("");

        String sql =
                "SELECT * FROM customers";

        try (
                Connection con = getConnection();
                Statement st =
                        con.createStatement();
                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            outputArea.append(
                    "========== CUSTOMER DETAILS ==========\n\n"
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                outputArea.append(
                        "Customer ID : "
                        + rs.getInt("customer_id")
                        + "\n"
                );

                outputArea.append(
                        "Name        : "
                        + rs.getString("name")
                        + "\n"
                );

                outputArea.append(
                        "Phone       : "
                        + rs.getString("phone")
                        + "\n"
                );

                outputArea.append(
                        "Email       : "
                        + rs.getString("email")
                        + "\n"
                );

                outputArea.append(
                        "Address     : "
                        + rs.getString("address")
                        + "\n"
                );

                outputArea.append(
                        "----------------------------------------\n"
                );
            }

            if (!found) {

                outputArea.append(
                        "No customers found.\n"
                );
            }

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // 3. ADD FOOD ITEM
    // =====================================================

    private void addFoodItem() {

        String name =
                getInput(
                        "Add Food",
                        "Enter Food Name:"
                );

        if (name == null || name.isEmpty()) {
            return;
        }

        String category =
                getInput(
                        "Add Food",
                        "Enter Category:"
                );

        if (category == null || category.isEmpty()) {
            return;
        }

        String priceText =
                getInput(
                        "Add Food",
                        "Enter Price:"
                );

        if (priceText == null || priceText.isEmpty()) {
            return;
        }

        double price;

        try {

            price =
                    Double.parseDouble(priceText);

        } catch (NumberFormatException ex) {

            showMessage("Enter a valid price.");

            return;
        }

        if (price <= 0) {

            showMessage(
                    "Price must be greater than 0."
            );

            return;
        }

        String sql =
                "INSERT INTO food_items "
                + "(food_name, category, price, availability) "
                + "VALUES (?, ?, ?, TRUE)";

        try (
                Connection con = getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, name);

            ps.setString(2, category);

            ps.setDouble(3, price);

            ps.executeUpdate();

            showMessage(
                    "Food item added successfully!"
            );

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // 4. VIEW FOOD ITEMS
    // =====================================================

    private void viewFoodItems() {

        outputArea.setText("");

        String sql =
                "SELECT * FROM food_items "
                + "WHERE availability = TRUE";

        try (
                Connection con = getConnection();
                Statement st =
                        con.createStatement();
                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            outputArea.append(
                    "========== AVAILABLE FOOD ==========\n\n"
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                outputArea.append(
                        "Food ID   : "
                        + rs.getInt("food_id")
                        + "\n"
                );

                outputArea.append(
                        "Food Name : "
                        + rs.getString("food_name")
                        + "\n"
                );

                outputArea.append(
                        "Category  : "
                        + rs.getString("category")
                        + "\n"
                );

                outputArea.append(
                        "Price     : ₹"
                        + rs.getDouble("price")
                        + "\n"
                );

                outputArea.append(
                        "----------------------------------------\n"
                );
            }

            if (!found) {

                outputArea.append(
                        "No available food items found."
                );
            }

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // 5. SEARCH FOOD
    // =====================================================

    private void searchFood() {

        String category =
                getInput(
                        "Search Food",
                        "Enter Food Category:"
                );

        if (category == null || category.isEmpty()) {
            return;
        }

        String sql =
                "SELECT * FROM food_items "
                + "WHERE category = ? "
                + "AND availability = TRUE";

        outputArea.setText("");

        try (
                Connection con = getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, category);

            try (ResultSet rs =
                         ps.executeQuery()) {

                outputArea.append(
                        "========== SEARCH RESULTS ==========\n\n"
                );

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    outputArea.append(
                            "Food ID   : "
                            + rs.getInt("food_id")
                            + "\n"
                    );

                    outputArea.append(
                            "Food Name : "
                            + rs.getString("food_name")
                            + "\n"
                    );

                    outputArea.append(
                            "Category  : "
                            + rs.getString("category")
                            + "\n"
                    );

                    outputArea.append(
                            "Price     : ₹"
                            + rs.getDouble("price")
                            + "\n"
                    );

                    outputArea.append(
                            "----------------------------------------\n"
                    );
                }

                if (!found) {

                    outputArea.append(
                            "No food found for category: "
                            + category
                    );
                }
            }

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // 6. PLACE ORDER
    // =====================================================

    private void placeOrder() {

        String customerText =
                getInput(
                        "Place Order",
                        "Enter Customer ID:"
                );

        if (customerText == null ||
                customerText.isEmpty()) {
            return;
        }

        String foodText =
                getInput(
                        "Place Order",
                        "Enter Food ID:"
                );

        if (foodText == null ||
                foodText.isEmpty()) {
            return;
        }

        String quantityText =
                getInput(
                        "Place Order",
                        "Enter Quantity:"
                );

        if (quantityText == null ||
                quantityText.isEmpty()) {
            return;
        }

        int customerId;
        int foodId;
        int quantity;

        try {

            customerId =
                    Integer.parseInt(customerText);

            foodId =
                    Integer.parseInt(foodText);

            quantity =
                    Integer.parseInt(quantityText);

        } catch (NumberFormatException ex) {

            showMessage(
                    "Enter valid numeric values."
            );

            return;
        }

        if (customerId <= 0 ||
                foodId <= 0 ||
                quantity <= 0) {

            showMessage(
                    "IDs and quantity must be greater than 0."
            );

            return;
        }

        try (Connection con = getConnection()) {

            // Check customer

            String customerSQL =
                    "SELECT customer_id FROM customers "
                    + "WHERE customer_id = ?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(customerSQL)
            ) {

                ps.setInt(1, customerId);

                try (ResultSet rs =
                             ps.executeQuery()) {

                    if (!rs.next()) {

                        showMessage(
                                "Customer not found."
                        );

                        return;
                    }
                }
            }
            String foodSQL =
                    "SELECT price FROM food_items "
                    + "WHERE food_id = ? "
                    + "AND availability = TRUE";

            double price;

            try (
                    PreparedStatement ps =
                            con.prepareStatement(foodSQL)
            ) {

                ps.setInt(1, foodId);

                try (ResultSet rs =
                             ps.executeQuery()) {

                    if (!rs.next()) {

                        showMessage(
                                "Food item not available."
                        );

                        return;
                    }

                    price =
                            rs.getDouble("price");
                }
            }

            double subtotal =
                    price * quantity;

            // Insert order

            String orderSQL =
                    "INSERT INTO orders "
                    + "(customer_id, total_amount, status) "
                    + "VALUES (?, ?, ?)";

            int orderId;

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    orderSQL,
                                    Statement.RETURN_GENERATED_KEYS
                            )
            ) {

                ps.setInt(1, customerId);

                ps.setDouble(2, subtotal);

                ps.setString(3, "Placed");

                ps.executeUpdate();

                try (ResultSet keys =
                             ps.getGeneratedKeys()) {

                    if (keys.next()) {

                        orderId =
                                keys.getInt(1);

                    } else {

                        showMessage(
                                "Unable to create order."
                        );

                        return;
                    }
                }
            }

            // Insert order item

            String itemSQL =
                    "INSERT INTO order_items "
                    + "(order_id, food_id, quantity, subtotal) "
                    + "VALUES (?, ?, ?, ?)";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(itemSQL)
            ) {

                ps.setInt(1, orderId);

                ps.setInt(2, foodId);

                ps.setInt(3, quantity);

                ps.setDouble(4, subtotal);

                ps.executeUpdate();
            }

            outputArea.setText("");

            outputArea.append(
                    "========== ORDER SUCCESS ==========\n\n"
            );

            outputArea.append(
                    "Order ID     : " + orderId + "\n"
            );

            outputArea.append(
                    "Customer ID  : " + customerId + "\n"
            );

            outputArea.append(
                    "Food ID      : " + foodId + "\n"
            );

            outputArea.append(
                    "Quantity     : " + quantity + "\n"
            );

            outputArea.append(
                    "Total Amount : ₹" + subtotal + "\n"
            );

            outputArea.append(
                    "Status       : Placed\n"
            );

            showMessage(
                    "Order placed successfully!"
            );

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // 7. VIEW ORDERS
    // =====================================================

    private void viewOrders() {

        String customerText =
                getInput(
                        "View Orders",
                        "Enter Customer ID:"
                );

        if (customerText == null ||
                customerText.isEmpty()) {
            return;
        }

        int customerId;

        try {

            customerId =
                    Integer.parseInt(customerText);

        } catch (NumberFormatException ex) {

            showMessage(
                    "Enter a valid Customer ID."
            );

            return;
        }

        String sql =
                "SELECT * FROM orders "
                + "WHERE customer_id = ?";

        outputArea.setText("");

        try (
                Connection con = getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, customerId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                outputArea.append(
                        "========== CUSTOMER ORDERS ==========\n\n"
                );

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    outputArea.append(
                            "Order ID : "
                            + rs.getInt("order_id")
                            + "\n"
                    );

                    outputArea.append(
                            "Date     : "
                            + rs.getTimestamp("order_date")
                            + "\n"
                    );

                    outputArea.append(
                            "Amount   : ₹"
                            + rs.getDouble("total_amount")
                            + "\n"
                    );

                    outputArea.append(
                            "Status   : "
                            + rs.getString("status")
                            + "\n"
                    );

                    outputArea.append(
                            "----------------------------------------\n"
                    );
                }

                if (!found) {

                    outputArea.append(
                            "No orders found."
                    );
                }
            }

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // 8. UPDATE ORDER STATUS
    // =====================================================

    private void updateOrderStatus() {

        String orderText =
                getInput(
                        "Update Status",
                        "Enter Order ID:"
                );

        if (orderText == null ||
                orderText.isEmpty()) {
            return;
        }

        int orderId;

        try {

            orderId =
                    Integer.parseInt(orderText);

        } catch (NumberFormatException ex) {

            showMessage(
                    "Enter a valid Order ID."
            );

            return;
        }

        String[] statuses = {
                "Placed",
                "Preparing",
                "Out for Delivery",
                "Delivered"
        };

        Dialog dialog =
                new Dialog(
                        this,
                        "Update Order Status",
                        true
                );

        dialog.setLayout(
                new GridLayout(5, 1, 10, 10)
        );

        Label label =
                new Label(
                        "Select Order Status:",
                        Label.CENTER
                );

        dialog.add(label);

        Button[] buttons =
                new Button[4];

        for (int i = 0; i < 4; i++) {

            buttons[i] =
                    new Button(statuses[i]);

            buttons[i].setBackground(
                    new Color(255, 140, 0)
            );

            buttons[i].setForeground(
                    Color.WHITE
            );

            final String status =
                    statuses[i];

            buttons[i].addActionListener(e -> {

                updateStatusInDatabase(
                        orderId,
                        status
                );

                dialog.dispose();
            });

            dialog.add(buttons[i]);
        }

        dialog.setSize(350, 250);

        dialog.setLocationRelativeTo(this);

        dialog.setVisible(true);
    }

    private void updateStatusInDatabase(
            int orderId,
            String status) {

        String sql =
                "UPDATE orders SET status = ? "
                + "WHERE order_id = ?";

        try (
                Connection con = getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, status);

            ps.setInt(2, orderId);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                showMessage(
                        "Order status updated successfully!"
                );

            } else {

                showMessage(
                        "Order not found."
                );
            }

        } catch (SQLException ex) {

            showError(ex);
        }
    }
    private void cancelOrder() {

        String orderText =
                getInput(
                        "Cancel Order",
                        "Enter Order ID:"
                );

        if (orderText == null ||
                orderText.isEmpty()) {
            return;
        }

        int orderId;

        try {

            orderId =
                    Integer.parseInt(orderText);

        } catch (NumberFormatException ex) {

            showMessage(
                    "Enter a valid Order ID."
            );

            return;
        }

        String sql =
                "UPDATE orders "
                + "SET status = 'Cancelled' "
                + "WHERE order_id = ?";

        try (
                Connection con = getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, orderId);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                showMessage(
                        "Order cancelled successfully!"
                );

            } else {

                showMessage(
                        "Order not found."
                );
            }

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // 10. VIEW ORDER DETAILS
    // =====================================================

    private void viewOrderDetails() {

        String orderText =
                getInput(
                        "Order Details",
                        "Enter Order ID:"
                );

        if (orderText == null ||
                orderText.isEmpty()) {
            return;
        }

        int orderId;

        try {

            orderId =
                    Integer.parseInt(orderText);

        } catch (NumberFormatException ex) {

            showMessage(
                    "Enter a valid Order ID."
            );

            return;
        }

        String sql =
                "SELECT o.order_id, c.name, "
                + "f.food_name, oi.quantity, "
                + "oi.subtotal, o.total_amount, "
                + "o.status "
                + "FROM orders o "
                + "JOIN customers c "
                + "ON o.customer_id = c.customer_id "
                + "JOIN order_items oi "
                + "ON o.order_id = oi.order_id "
                + "JOIN food_items f "
                + "ON oi.food_id = f.food_id "
                + "WHERE o.order_id = ?";

        outputArea.setText("");

        try (
                Connection con = getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, orderId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    outputArea.append(
                            "========== ORDER DETAILS ==========\n\n"
                    );

                    outputArea.append(
                            "Order ID  : "
                            + rs.getInt("order_id")
                            + "\n"
                    );

                    outputArea.append(
                            "Customer  : "
                            + rs.getString("name")
                            + "\n"
                    );

                    outputArea.append(
                            "Food      : "
                            + rs.getString("food_name")
                            + "\n"
                    );

                    outputArea.append(
                            "Quantity  : "
                            + rs.getInt("quantity")
                            + "\n"
                    );

                    outputArea.append(
                            "Subtotal  : ₹"
                            + rs.getDouble("subtotal")
                            + "\n"
                    );

                    outputArea.append(
                            "Total     : ₹"
                            + rs.getDouble("total_amount")
                            + "\n"
                    );

                    outputArea.append(
                            "Status    : "
                            + rs.getString("status")
                            + "\n"
                    );

                } else {

                    outputArea.append(
                            "Order not found."
                    );
                }
            }

        } catch (SQLException ex) {

            showError(ex);
        }
    }

    // =====================================================
    // ERROR MESSAGE
    // =====================================================

    private void showError(SQLException ex) {

        outputArea.setText("");

        outputArea.append(
                "========== DATABASE ERROR ==========\n\n"
        );

        outputArea.append(
                "Error: " + ex.getMessage()
        );
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        new Awtfoodorder();
    }
}