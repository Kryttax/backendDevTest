package com.example.demo.data;

public class Product {
  private String id;
  private String name;
  private Number price;
  private boolean availability;

  public Product() {

  }

  public Product(String id, String name, Number price, boolean availability) {
    super();
    this.id = id;
    this.name = name;
    this.price = price;
    this.availability = availability;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Number getPrice() {
    return price;
  }

  public boolean isAvailable() {
    return availability;
  }
}
