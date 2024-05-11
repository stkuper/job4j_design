package ru.job4j.ood.lsp.warehouseproducts.food;

import java.time.LocalDate;

public abstract class Food {
    protected String name;
    protected LocalDate expiryDate;
    protected LocalDate createDate;
    protected double price;
    protected double productLife;
    protected int discount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        System.out.println(price);
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        this.setPrice(this.getPrice() - (this.getPrice() * discount) / 100);
    }

    public double getProductLife() {
        return productLife;
    }

    public void setProductLife(double productLife) {
        this.productLife = productLife;
    }

    @Override
    public String toString() {
        return "Food{"
               + "name='" + name + '\''
               + ", expiryDate=" + expiryDate
               + ", createDate=" + createDate
               + ", price=" + price
               + ", discount=" + discount
               + '}';
    }
}