package com.example.foodservice.model;

import com.example.foodservice.R;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String address;
    private String city;
    private int imageId;
    private int franchiseId;
    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    public Restaurant(){
    }

    public Restaurant(String name, String address, String city, int franchiseId, int imageId){
        this.name=name;
        this.address=address;
        this.city=city;
        this.franchiseId=franchiseId;
        this.imageId=imageId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getImageId() {
        return imageId;
    }


    public void initialLoad(){

        restaurants.add(new Restaurant("Açaí","Av dos Búzios 31", "Florianópolis", 1 , R.drawable.berries));
        restaurants.add(new Restaurant("Sucos e cia","Av dos Salmões 41", "Florianópolis", 1 , R.drawable.berries));
        restaurants.add(new Restaurant("Fruit berries","Av dos Lambaris 51", "Palhoça", 1 , R.drawable.berries));

        restaurants.add(new Restaurant("Beach Burger","Av dos Dourados 61", "Florianópolis", 2 , R.drawable.burger));
        restaurants.add(new Restaurant("City Burger","Av da Paixão 71", "Florianópolis", 2 , R.drawable.burger));
        restaurants.add(new Restaurant("Country Burger","Av da Solidão 81", "Florianópolis", 2 , R.drawable.burger));

        restaurants.add(new Restaurant("Pop e Corn","Rua Direita 81", "São Paulo", 3 , R.drawable.popcorn));
        restaurants.add(new Restaurant("Pipocão","Rua dos Pinheiros 91", "São Paulo", 3 , R.drawable.popcorn));
        restaurants.add(new Restaurant("Show do Milhão","Rua Pororoca 101", "Manaus", 3 , R.drawable.popcorn));

        restaurants.add(new Restaurant("Pasta di Roma","Rua Sao Roque 102", "Rio de Janeiro", 4 , R.drawable.spaghetti));
        restaurants.add(new Restaurant("Pizza di Napoli","Rua Vasco da Gama 98", "Rio de Janeiro", 4 , R.drawable.spaghetti));
        restaurants.add(new Restaurant("Fratelli","Rua Copacabana 102", "Rio de Janeiro", 4 , R.drawable.spaghetti));

    }

    public ArrayList<Restaurant> getRestaurantsByFranchise(int id) {
        ArrayList<Restaurant> restaurantsByFranchise = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (id == r.franchiseId) {
                restaurantsByFranchise.add(r);
            }
        }
        return restaurantsByFranchise;
    }

}
