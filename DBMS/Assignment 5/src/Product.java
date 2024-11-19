public class Product {
    private String productId;
    private String productName;
    private String categoryId;
    private String dateAdded;
    private int quantity;
    private int price;

    Product(String id, String name, String categoryId, String date, int quantity, int price) {
        this.productId = id;
        this.productName = name;
        this.dateAdded = date;
        this.categoryId = categoryId;
        this.quantity = quantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getPrice() {
        return price;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

}
