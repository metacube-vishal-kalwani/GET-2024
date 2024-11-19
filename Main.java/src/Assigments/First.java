/*
 * Assigment 2 - software construction and fundamentals
 * author - vishal kalwani GET Oct 2024
 */

package Assigments;

import java.util.ArrayList;

/* This class Item container the properties of an Item */
class Item {
    private String ItemId;
    public String Name;
    public String Desc;
    public float Price;

    /* constructor to initialize an item */
    Item(String itemid, String name, String desc, float price) {
        this.ItemId = itemid;
        this.Desc = desc;
        this.Name = name;
        this.Price = price;
    }
};

class Quantity {
    Item item;
    int quantity;
};

/* This class maintains the list of items and its associated functions */
class ShopingCart {
    private ArrayList<Quantity> cart = new ArrayList<Quantity>();
    private static int cartSize;

    ShopingCart() {
        cartSize = 0;
    }

    /* Function to add an Item into the Cart */
    void AddToCart(Item item, int qt) {
        Quantity temp = new Quantity();
        temp.item = item;
        temp.quantity = qt;
        this.cart.add(temp);
        cartSize++;
    }

    /* Funtion to delete an Item from the Cart */
    void deleteItem(Item item) {
        if (cartSize == 0) {
            System.out.println("Cart is Empty");
            return;
        }
        for (Quantity i : cart) {
            if (i.item == item) {
                cart.remove(i);
                cartSize--;
                System.out.println("Item deleted Successfully...");
                return;
            }
        }
        System.out.println("Item does not exist");

    }

    /* Function to show the quantity of any perticular item */
    int showQuantity(Item item) {
        if (cartSize == 0) {
            System.out.println("Cart is Empty");
            return -1;
        }
        for (Quantity i : cart) {
            if (i.item == item) {
                return i.quantity;
            }
        }
        System.out.println("Item does not exist");
        return 0;

    }

    /* Function to display the overall bill of shopping */
    double displayBill() {
        double total = 0.0;
        for (Quantity i : cart) {
            System.out.println(i.item.Name + " : " + i.quantity + " X " + i.item.Price);
            total += (double) (i.item.Price * i.quantity);
        }
        return total;
    }

    /* Function to update the Quantity of an Item */
    void updateQuantity(Item item, int newQuantity) {
        if (cartSize == 0) {
            System.out.println("Cart is Empty");
            return;
        }
        for (Quantity i : cart) {
            if (i.item == item) {
                i.quantity = newQuantity;
                System.out.println("Quantity updated for item :" + item.Name);
                return;
            }
        }
        System.out.println("Item does not exist");

    }

    /* Function to show all items of a cart with their quantity */
    void showCart() {
        for (Quantity i : cart) {
            System.out.println(i.item.Name + ":" + i.quantity);
        }
    }
};

public class First {

    public static void main(String[] args) throws Exception {

        /* creating Items */
        Item Milk = new Item("i1", "MiLK", "Pasturized", 50.00F);
        Item Tea = new Item("i2", "Tea", "with Sugar", 20.00F);
        Item Burger = new Item("i3", "Burger", "With extra cheese", 70.00F);

        /* Accessing function of shop cart */
        ShopingCart temp = new ShopingCart();
        temp.AddToCart(Tea, 2);
        temp.AddToCart(Milk, 3);
        temp.AddToCart(Burger, 1);
        temp.AddToCart(Tea, 'a');

        double bill = temp.displayBill();
        System.out.println(bill);
        temp.showCart();
        temp.deleteItem(Tea);
        temp.showCart();
        int teaQnt = temp.showQuantity(Tea);
        System.out.println(teaQnt);

    }
}
