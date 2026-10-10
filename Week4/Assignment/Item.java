// A2 - Canteen Inventory Batch Restock
// CONCEPT: 'this' keyword for field/parameter naming clash
// Both the constructor and restock() have a parameter named 'stock'
// that shadows the instance field 'stock'.
// 'this.stock' always refers to the field; plain 'stock' refers to the parameter.

public class Item {

    String itemName;
    int stock;   // instance field

    // Parameter 'itemName' shadows field 'itemName' — 'this.' resolves the clash
    public Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock    = stock;
    }

    // Parameter 'stock' shadows field 'stock'
    // 'this.stock += stock' means: field = field + parameter
    public void restock(int stock) {
        this.stock += stock;
    }

    public void printStock() {
        System.out.println(itemName + " | Final Stock: " + stock);
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item("Samosa",       15),
            new Item("Tea Powder",   40),
            new Item("Bread",         8),
            new Item("Biscuit Packs", 25)
        };

        // Restock every item by 20 in a single pass
        for (Item item : items) {
            item.restock(20);
            item.printStock();
        }
    }
}
