package po;

public class Product {

    private String id;

    private String barcode;

    private String name;

    private String units;

    private String purchaseprice;

    private String saleprice;

    private String inventory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units == null ? null : units.trim();
    }

    public String getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(String purchaseprice) {
        this.purchaseprice = purchaseprice == null ? null : purchaseprice.trim();
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice == null ? null : saleprice.trim();
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory == null ? null : inventory.trim();
    }
}