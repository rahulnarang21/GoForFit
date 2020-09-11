package goforfit.com.goforfit.models;

import java.util.ArrayList;

public class SportsModel {



    ArrayList<Sports> sportsArrayList;

    public SportsModel(ArrayList<Sports> sportsArrayList) {
        this.sportsArrayList = sportsArrayList;
    }
    public ArrayList<Sports> getSportsArrayList() {
        return sportsArrayList;
    }

   public static class Sports{

       public Sports(String name, int image) {
           this.name = name;
           this.image = image;
       }

       public String getName() {
           return name;
       }

       public int getImage() {
           return image;
       }

       private String name;
       private int image;

   }
}
