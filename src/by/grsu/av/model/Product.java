package by.grsu.av.model;

public class Product {
    private String title;

    private int price;

    private boolean isBought;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
        this.isBought = false;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setIsBought(boolean isBought) {
        this.isBought = isBought;
    }

    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
