package com.mka.lesson2.dz;

public class Product {

    private int id;
    private int prodId;
    private String title;
    private int cost;

    public Product(int id, int prodId, String title, int cost) {
        this.id = id;
        this.prodId = prodId;
        this.title = title;
        this.cost = cost;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getProdId() {
        return prodId;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodId=" + prodId +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
