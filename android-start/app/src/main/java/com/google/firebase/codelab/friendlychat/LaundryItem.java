package com.google.firebase.codelab.friendlychat;

/**
 * Created by Ivana on 1/24/2017.
 */
public class LaundryItem {
    String price = null;
    String name =null;
    boolean selected = false;

    public LaundryItem(String price, String name, boolean selected) {
        super();
        this.price = price;
        this.name = name;
        this.selected = selected;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
