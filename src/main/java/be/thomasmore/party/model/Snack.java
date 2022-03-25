package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Snack {
    @Id
    private Integer id;
    private String name;
    private boolean vegan;
    private boolean sideDishPossible;
    private double price;
    private Double priceSideDish;

    public Snack() {
    }

    public Snack(String name, boolean vegan, boolean sideDishPossible, double price, Double priceSideDish) {
        this.name = name;
        this.vegan = vegan;
        this.sideDishPossible = sideDishPossible;
        this.price = price;
        this.priceSideDish = priceSideDish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isSideDishPossible() {
        return sideDishPossible;
    }

    public void setSideDishPossible(boolean sideDishPossible) {
        this.sideDishPossible = sideDishPossible;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getPriceSideDish() {
        return priceSideDish;
    }

    public void setPriceSideDish(Double priceSideDish) {
        this.priceSideDish = priceSideDish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
