package com.example.foodservice.model;

import java.util.ArrayList;
import com.example.foodservice.R;

public class Franchise {
    private int id;
    private String name;
    private String description;
    private int imageId;

    private ArrayList<Franchise> franchises = new ArrayList<Franchise>();

    public Franchise(){
    }

    public Franchise(int id, String name, String description, int imageId){
        this.id=id;
        this.name=name;
        this.description=description;
        this.imageId=imageId;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    public void initialLoad(){
        franchises.add(new Franchise(1, "Berries","Healthy food", R.drawable.berries));
        franchises.add(new Franchise(2, "Burger", "Fast food", R.drawable.burger));
        franchises.add(new Franchise(3, "Popcorn", "Snack food",R.drawable.popcorn));
        franchises.add(new Franchise(4, "Pasta", "Italian food",R.drawable.spaghetti));
    }

    public ArrayList<Franchise> getFranchises() {
        return franchises;
    }

}
