package data;

public class MenuItem {

    private String itemName = "", itemParam = "";

    public MenuItem(String itemName, String itemParam) {
        this.itemName = itemName;
        this.itemParam = itemParam;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemParam() {
        return itemParam;
    }

    public void setItemParam(String itemParam) {
        this.itemParam = itemParam;
    }

}
