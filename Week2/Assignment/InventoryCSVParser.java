public class InventoryCSVParser {

    public static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        // Exactly 3 fields required: ProductName, SKU, Quantity
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String product = fields[0].trim();
        String sku     = fields[1].trim();
        String qty     = fields[2].trim();

        System.out.println("Product: " + product + " | SKU: " + sku + " | Qty: " + qty);
    }

    public static void main(String[] args) {
        parseInventoryRecord("Wireless Mouse,WM-2201,150");
        parseInventoryRecord("Wireless Mouse,150");           // 2 fields, invalid
        parseInventoryRecord("Keyboard,KB-3301,75,Extra");   // 4 fields, invalid
    }
}
