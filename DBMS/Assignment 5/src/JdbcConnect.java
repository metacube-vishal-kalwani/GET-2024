
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnect {

    private static String HOST = "jdbc:mysql://localhost:3306/"; // Host port
    private static String DB_NAME = "StoreFront"; // database name
    private static String MY_SQL_URL = HOST + DB_NAME; // URL of Database for jdbc connectivity
    public static Connection connection; //

    /**
     * Builds the connection with the storeFront Server
     */
    public static void createConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String userId = Credentials.getUSER_NAME();
            String password = Credentials.getPASSWORD();
            connection = DriverManager.getConnection(MY_SQL_URL, userId, password);

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        } catch (SQLException sqlError) {
            System.out.println(sqlError);

        }
    }

    /**
     * Validates the userId of a person
     * 
     * @param user_id String contains user's Id
     * @return return true if valid
     */
    boolean validateUser(String userId) {
        return userId.matches("u\\d+");
    }

    boolean validateProductId(String productId) {
        return productId.matches("P\\d+");
    }

    /**
     * 
     * @param userId takes user's ID
     * @return return the list of orders placed by user and not yet shipped
     */
    public ArrayList<CustomerOrders> fetchUserOrders(String userId) {
        ArrayList<CustomerOrders> ordersList = new ArrayList<>();
        try {
            if (!validateUser(userId)) {
                throw new AssertionError("Invalid userId!!");
            }

            // creating the statement object
            Statement statement = connection.createStatement();
            // Creating query

            ResultSet result = statement.executeQuery(Queries.getQuery1(userId));
            // storing the query result in string
            while (result.next()) {
                String orderId = result.getString("order_id");
                String orderDate = result.getString("order_date");
                int totalAmount = result.getInt("total_amount");
                String orderStatus = result.getString("order_status");
                CustomerOrders newOrders = new CustomerOrders(orderId, orderDate, totalAmount, userId, orderStatus);
                ordersList.add(newOrders);
            }

            // Close resources
            result.close();
            statement.close();
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
        } catch (AssertionError e) {
            System.err.println("Assertion Error: " + e.getMessage());
        }
        return ordersList;
    }

    /**
     * Stores 5 or more Images of the product given by a user
     * 
     * @param productName String carrying name of the product
     */
    public void addImagesOfProduct(String productId) {
        try {

            if (!validateProductId(productId)) {
                throw new AssertionError("Invalid product Id!!");
            }
            // Prepare the batch insert statement
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO Images (img_id, img_url, product_id) VALUES (?, ?, ?)");
            connection.setAutoCommit(false);

            addImageToBatch(pstmt, "img01", "/root/c/images/image1.png", productId);
            addImageToBatch(pstmt, "img02", "/root/c/images/image2.png", productId);
            addImageToBatch(pstmt, "img03", "/root/c/images/image3.png", productId);
            addImageToBatch(pstmt, "img04", "/root/c/images/image4.png", productId);
            addImageToBatch(pstmt, "img07", "/root/c/images/image5.png", productId);

            pstmt.executeBatch();
            connection.commit();

            System.out.println("Images added successfully!!");

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                connection.setAutoCommit(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Takes order parameters of order and adds to batch
     * 
     * @param pstmt     // prepared statement object
     * @param imgId     // image id
     * @param imgUrl    // url of img
     * @param productId // takes productId
     * @throws SQLException
     */
    private void addImageToBatch(PreparedStatement pstmt, String imgId, String imgUrl, String productId)
            throws SQLException {
        pstmt.setString(1, imgId);
        pstmt.setString(2, imgUrl);
        pstmt.setString(3, productId);
        pstmt.addBatch();
    }

    public void deleteProducts() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "DELETE  FROM Product"
                    + "WHERE product_id IN"
                    + "(SELECT I.product_id"
                    + "FROM Item I"
                    + "JOIN CustomerOrders O"
                    + "ON I.order_id = O.order_id"
                    + "WHERE DATEDIFF(CURRENT_TIMESTAMP, O.order_date) > 365";

            int countProducts = statement.executeUpdate(query);
            System.out.println(countProducts);

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Method to run sql query to select and display the category
     * title of all top parent categories sorted alphabetically
     * and the count of their child categories.
     * 
     * @return
     * @throws Exception
     */
    public List<Category> topCategoryInfo() {
        List<Category> categoryList = new ArrayList<>();
        try {
            String inputQuery = "SELECT P.category_name AS top_category_name, COUNT(C.category_id) AS child_count"
                    + " FROM Category c1 "
                    + " JOIN  Category P JOIN Category C "
                    + " ON p.category_id = c.parent_category"
                    + " GROUP BY P.category_id, P.category_name"
                    + " ORDER BY P.category_name";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(inputQuery);

            while (resultSet.next()) {
                String categoryName = resultSet.getString("top_category_name");
                int childCount = resultSet.getInt("child_count");
                Category categoryObj = new Category(categoryName, childCount);
                categoryList.add(categoryObj);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return categoryList;

    }

    public static void main(String[] args) {

        JdbcConnect jdbc = new JdbcConnect();
        jdbc.createConnect();

        // Assignment 5.1
        ArrayList<CustomerOrders> result = jdbc.fetchUserOrders("");
        for (CustomerOrders order : result) {
            System.out.println(order.getOrderId());
            System.out.println(order.getTotalAmount());
            System.out.println(order.getOrderStatus());
        }

        // Assignment 5.2
        jdbc.addImagesOfProduct("P01");

        // Assignment 5.3
        jdbc.deleteProducts();

        // Assignment 5.4
        List<Category> newList = jdbc.topCategoryInfo();
        for (Category category : newList) {
            System.out.print(category.getCategoryName() + "\t");
            System.out.println(category.getChildCategoryCount() + "\t");
        }

    }

}
