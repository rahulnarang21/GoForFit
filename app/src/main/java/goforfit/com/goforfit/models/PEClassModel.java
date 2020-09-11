package goforfit.com.goforfit.models;

import java.util.ArrayList;

public class PEClassModel {

    ArrayList<PEClass> peClassArrayList;

    public PEClassModel(ArrayList<PEClass> peClassArrayList) {
        this.peClassArrayList = peClassArrayList;
    }

    public ArrayList<PEClass> getPeClassArrayList() {
        return peClassArrayList;
    }

    public static class PEClass{
        private String name;
        private int image;

        public PEClass(String name, int image) {
            this.name = name;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public int getImage() {
            return image;
        }
    }

}
