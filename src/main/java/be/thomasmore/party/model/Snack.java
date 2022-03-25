package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Snack {
    @Id
    private Integer id;
    private String name;
    private boolean vegan;
    private boolean sideDishPossible;
    private double price;
//    in theorie wel maar dan moet de klant altijd een sidedish nemen door Double te gebruiken
//    kan sideDish null zijn en moet de klant niet per see een sidedish nemen of kan er ook gewoon geen sidedish zijn
    private Double priceSideDish;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Venue> venues;

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

    public Collection<Venue> getVenues() {
        return venues;
    }

    public void setVenues(Collection<Venue> venues) {
        this.venues = venues;
    }
}
